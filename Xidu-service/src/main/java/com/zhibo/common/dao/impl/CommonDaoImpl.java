/**
 * 
 */
package com.zhibo.common.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.log.Log;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.callback.QueryWithPagingJpaCallback;
import com.xidu.framework.common.util.Pager;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.zhibo.common.dao.CommonDao;
import com.zhibo.common.dto.QueryCommonDto;

/**
 * @author WEICWANG
 * 
 */
@Repository
public class CommonDaoImpl extends JpaDaoSupport implements CommonDao {
	private static final String SQL_KEY_WORD_FROM = " from ";
	// count clause constant
	private static final String SQL_COUNT_CLAUSE = "select count(*) ";

	/**
	 * call JpaDaoSupport.setJpaTemplate to set jpaTemplate instance.
	 * 
	 * @Date : 2011-3-23
	 * @param jpaTemplate
	 *            - instance of JpaTemplate.
	 */
	@Resource(name = "jpaTemplate")
	public void setJpaTemplateWrap(JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public QueryCommonDto getResultListByCondition(QueryCommonDto queryDto) {
		String sql = "from " + queryDto.getDomainName() + " t where t.deleteFlag=0 ";

		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		Map param = queryDto.getQueryProperties();
		if(param!=null){
			Set keys = param.keySet();
			Iterator iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = (String) param.get(key);
				if (Utils.notEmpty(value)) {
					wcg.and("t." + key, "like", "%" + value + "%");
				}
			}
		}
		if (Utils.notEmpty(queryDto.getSortColumn())) {
			wcg.sort(queryDto.getSortColumn(), queryDto.getAscOrDesc());
		}
		logger.debug("zhangcheng sql query : " + wcg);

		List list = getListByQueryWithDefaultPaging(
				wcg.toQuery(), queryDto.getPager());
		queryDto.setResultList(list);
		return queryDto;
	}

	private List getListByQueryWithDefaultPaging(String queryStr, Pager pager) {
		if (StringUtils.isBlank(queryStr)) {
			throw new IllegalArgumentException("Query String is NULL.");
		}
		int fromIndex = queryStr.toLowerCase().indexOf(SQL_KEY_WORD_FROM);
		String countQueryStr;
		if (fromIndex > -1) {
			countQueryStr = SQL_COUNT_CLAUSE + queryStr.substring(fromIndex);
		} else {
			countQueryStr = SQL_COUNT_CLAUSE + queryStr;
		}

		return getListByQueryWithPaging(queryStr, countQueryStr, pager);
	}

	private List getListByQueryWithPaging(final String queryStr,
			final String countQueryStr, Pager pager) {
		if (null == pager) {
			pager = new Pager();
		}

		return getJpaTemplate().executeFind(
				new QueryWithPagingJpaCallback(queryStr, countQueryStr, pager));
	}

	@Override
	public QueryCommonDto getResultListBySQL(QueryCommonDto queryDto) throws Exception {

		WhereConditionGenerator wcg = new WhereConditionGenerator(queryDto.getSql());
		Map param = queryDto.getQueryProperties();
		if(param!=null){
			Set keys = param.keySet();
			Iterator iter = keys.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = (String) param.get(key);
				if (Utils.notEmpty(value)) {
					wcg.and("t." + key, "like", "%" + value + "%");
				}
			}
		}
		if (Utils.notEmpty(queryDto.getSortColumn())) {
			wcg.sort(queryDto.getSortColumn(), queryDto.getAscOrDesc());
		}
		logger.debug("sql query : " + wcg);

		List list = createNativeQuery(wcg.toString(),queryDto);
		queryDto.setResultList(list);
		return queryDto;
	}
	
	
	
	
	protected List createNativeQuery(String sql,QueryCommonDto queryDto) throws Exception {
		EntityManager entityManager=this.getJpaTemplate().getEntityManagerFactory().createEntityManager();
		Query countQuery=entityManager.createNativeQuery(SQL_COUNT_CLAUSE+" "+sql.substring(sql.indexOf("from")));
		logger.debug("count query:"+countQuery);
		BigDecimal totalSize=(BigDecimal) countQuery.getSingleResult();
		queryDto.getPager().rebuild(totalSize.intValue());
		
		Query query=entityManager.createNativeQuery(sql.toString());
		query.setFirstResult((queryDto.getPager().getCurrentPage()-1)*queryDto.getPager().getPageSize());
	    query.setMaxResults(queryDto.getPager().getPageSize()); 
		List list=query.getResultList();
		String returnClazz=queryDto.getReturnDto();
		String[] props=queryDto.getProperties().split(",");
		return packageDto(returnClazz,props,list);
		
	}

	/**
	 * package dto by java refect,all properties is String
	 * @param returnClazz
	 * @param props
	 * @param objs
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private List packageDto(String returnClazz, String[] props, List list) throws Exception {
		// TODO Auto-generated method stub
		List returnList=new ArrayList();
		Class clazz=Class.forName(returnClazz);
		for(int i=0;i<list.size();i++){	
			Object o=clazz.newInstance();
			Object[] objs=(Object[]) list.get(i);
			for(int j=0;j<props.length;j++){
				Utils.setProperty(o, props[j], String.valueOf(objs[j]));
			}
			returnList.add(o);	
		}
		return returnList;
		
	}

	@Override
	public String getSizeByQuery(String sql) {
		// TODO Auto-generated method stub
		EntityManager entityManager=this.getJpaTemplate().getEntityManagerFactory().createEntityManager();
		Query countQuery=entityManager.createNativeQuery(sql);
		logger.debug("count query:"+countQuery);
		BigDecimal totalSize=(BigDecimal) countQuery.getSingleResult();
		logger.debug("size: "+totalSize);
		if(totalSize.intValue()>0){
			return "isExist";
		}
		return "isnotExist";
	}

	@Override
	public String getEntityBySql(String sql) {
		// TODO Auto-generated method stub
		EntityManager entityManager=this.getJpaTemplate().getEntityManagerFactory().createEntityManager();
		Query query=entityManager.createNativeQuery(sql);
		logger.debug("query:"+query);
		List list=query.getResultList();
		if(list!=null&&list.size()>0){
			System.out.println(list.get(0));
			return String.valueOf(list.get(0));
		}
		return null;
	}

}
