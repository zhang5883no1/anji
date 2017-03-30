/************************************************************************************
 * @File name   :      BaseDaoImpl.java
 *
 * @Author      :      Jianxi Wu
 *
 * @Date        :      2011-1-18
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                             Who             Version         Comments
 * 2011-1-18 上午10:25:45            Jianxi Wu         1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dao.callback.DataOperationQueryStringJpaCallback;
import com.xidu.framework.common.dao.callback.NamedQueryWithPagingJpaCallback;
import com.xidu.framework.common.dao.callback.NativeQueryByPagingAndRsmJpaCallback;
import com.xidu.framework.common.dao.callback.NativeQueryJpaCallback;
import com.xidu.framework.common.dao.callback.NativeQueryWithPagingJpaCallback;
import com.xidu.framework.common.dao.callback.NativeQueryWithResultSetMappingJpaCallback;
import com.xidu.framework.common.dao.callback.QueryWithPagingJpaCallback;
import com.xidu.framework.common.domain.BaseDomain;
import com.xidu.framework.common.util.Pager;

/**
 * IBaseDao's implementation.
 * {@inheritDoc} overridden:
 * 
 * @param <T>-indicates the Type of DAO, ex: Domain.
 * @param <PK>-indicates the Type of PK, ex: Long
 */
public class BaseDaoImpl<T extends BaseDomain, PK extends Serializable> extends JpaDaoSupport
    implements IBaseDao<T, PK> {
    // sql key word: from
    private static final String SQL_KEY_WORD_FROM = " from ";
    // count clause constant
    private static final String SQL_COUNT_CLAUSE = "select count(*) ";

    // domain Class instance
    private Class<T> clazz;

    // logger instance
    protected Logger logger;

    // instance of EntityManager
    @PersistenceContext
    private EntityManager entityManager;

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

    /**
     * get logger instance to log info.
     * 
     * @Date : 2011-3-7
     * 
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * set logger instance
     * 
     * @Date : 2011-3-7
     * 
     * @param logger
     *            the logger to set
     */
    @Resource(name = "logger")
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * construct BaseDaoImpl by Domain Class instance.
     * 
     * @Date : 2011-3-23
     * @param clazz
     *            - domain Class instance
     */
    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#findById(java.lang.Object)
     * 
     */
    public T findById(PK id) {
        T obj = (T) getJpaTemplate().find(clazz, id);
        return obj;
    }
    
    
    /* (non-Javadoc)
	 * @see com.crc.appFrameWork.core.dao.BaseDao#findAll()
	 */
	@Override
	public List<T> findAll() {
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getJpaTemplate().find("from " + clazz.getName() + " t where t.deleteFlag=0");
		return list;
	}

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#save(java.lang.Object)
     * 
     */
    public T save(T obj) {
        if (obj == null) {
            return null;
        }
        // obj.setDeleted(CommonConstants.NOT_DELETED_FLAG);
        obj.setCreateDate(new Date());
        obj.setLastUpdateDate(new Date());
        getJpaTemplate().persist(obj);
        return obj;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#remove(java.lang.Object)
     * 
     */
    @Override
    public void remove(T obj) {

        if (obj == null) {
            return;
        }
        getJpaTemplate().remove(getJpaTemplate().merge(obj));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#removeByQuery(java.lang.String)
     * 
     */
    @Override
    public void removeByQuery(String queryStr) {

        getJpaTemplate().execute(new DataOperationQueryStringJpaCallback(queryStr));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#update(java.lang.Object)
     * 
     */
    @Override
    public void update(T obj) {

        if (obj == null) {
            return;
        }
        obj.setLastUpdateDate(new Date());
        getJpaTemplate().merge(obj);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#updateByQuery(java.lang.String)
     * 
     */
    @Override
    public void updateByQuery(String queryStr) {

        getJpaTemplate().execute(new DataOperationQueryStringJpaCallback(queryStr));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#remove(long)
     * 
     */
    @Override
    public void remove(long id) {

        String queryStr = "delete from " + clazz.getSimpleName() + " o where o.id = " + id;
        this.removeByQuery(queryStr);
    }

    /**
     * set parameters to query object.
     * 
     * @Date : 2011-3-23
     * @param query
     *            - instance of JPA Query.
     * @param params
     *            - variable parameters.
     */

    private void prepareQueryParam(Query query, Object... params) {
        if (null != params) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
        }
    }

    /**
     * set query parameters' map to query object.
     * 
     * @Date : 2011-3-23
     * @param query
     *            - instance of JPA Query.
     * @param paraMap
     *            - variable parameters.
     */
    private void prepareQueryParam(Query query, Map<String, ?> paraMap) {
        if (null != paraMap) {
            for (Map.Entry<String, ?> entry : paraMap.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByNamedQuery(java.lang.String)
     * 
     */
    @Override
    public int countByNamedQuery(final String queryName) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                Object countObj = em.createNamedQuery(queryName).getSingleResult();
                return Integer.parseInt(countObj.toString());
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByNamedQueryWithMap(java.lang.String,
     *      java.util.Map)
     * 
     */
    @Override
    public int countByNamedQueryWithMap(final String queryName, final Map<String, ?> paraMap) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {

                Query namedQuery = em.createNamedQuery(queryName);
                prepareQueryParam(namedQuery, paraMap);

                Object countObj = namedQuery.getSingleResult();
                return Integer.parseInt(countObj.toString());
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByNamedQueryWithVaParam(java.lang.String,
     *      java.lang.Object[])
     * 
     */
    @Override
    public int countByNamedQueryWithVaParam(final String queryName, final Object... params) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {

                Query namedQuery = em.createNamedQuery(queryName);
                prepareQueryParam(namedQuery, params);

                Object countObj = namedQuery.getSingleResult();
                return Integer.parseInt(countObj.toString());
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByNativeQuery(java.lang.String)
     * 
     */
    @Override
    public int countByNativeQuery(final String nativeQuerySql) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {
            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                Object countObj = em.createNativeQuery(nativeQuerySql).getSingleResult();
                return Integer.parseInt(countObj.toString());
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByNativeQueryWithMap(java.lang.String,
     *      java.util.Map)
     * 
     */
    @Override
    public int countByNativeQueryWithMap(final String nativeQuerySql
        , final Map<String, ?> paraMap) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {

                Query nativeQuery = em.createNativeQuery(nativeQuerySql);
                prepareQueryParam(nativeQuery, paraMap);

                Object countObj = nativeQuery.getSingleResult();
                return Integer.parseInt(countObj.toString());
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByNativeQueryWithVaParam(java.lang.String,
     *      java.lang.Object[])
     * 
     */
    @Override
    public int countByNativeQueryWithVaParam(final String nativeQuerySql, final Object... params) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {

                Query nativeQuery = em.createNativeQuery(nativeQuerySql);
                prepareQueryParam(nativeQuery, params);

                Object countObj = nativeQuery.getSingleResult();
                return Integer.parseInt(countObj.toString());
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByQuery(java.lang.String)
     * 
     */
    @Override
    public int countByQuery(final String queryStr) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {

                return ((Long) em.createQuery(queryStr).getSingleResult()).intValue();
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByQueryWithMap(java.lang.String,
     *      java.util.Map)
     * 
     */
    @Override
    public int countByQueryWithMap(final String queryStr, final Map<String, ?> paraMap) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {

                Query query = em.createQuery(queryStr);
                prepareQueryParam(query, paraMap);
                return ((Long) query.getSingleResult()).intValue();
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#countByQueryWithVaParam(java.lang.String,
     *      java.lang.Object[])
     * 
     */
    @Override
    public int countByQueryWithVaParam(final String queryStr, final Object... params) {

        return getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {

                Query query = em.createQuery(queryStr);
                prepareQueryParam(query, params);
                return ((Long) query.getSingleResult()).intValue();
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getEntityManager()
     * 
     */
    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNamedQuery(java.lang.String)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNamedQuery(String queryName) {

        return getJpaTemplate().findByNamedQuery(queryName);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNamedQueryWithMap(java.lang.String,
     *      java.util.Map)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNamedQueryWithMap(String queryName, Map<String, ?> paraMap) {

        return getJpaTemplate().findByNamedQueryAndNamedParams(queryName, paraMap);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNamedQueryWithPaging(java.lang.String,
     *      java.lang.String, com.xidu.framework.common.util.Pager)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNamedQueryWithPaging(final String queryName,
        final String countQueryName, Pager pager) {

        if (null == pager) {
            pager = new Pager();
        }
        return getJpaTemplate().executeFind(
            new NamedQueryWithPagingJpaCallback<T>(queryName, countQueryName, pager));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNamedQueryWithVaParam(java.lang.String,
     *      java.lang.Object[])
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNamedQueryWithVaParam(final String queryName, final Object... params) {

        return getJpaTemplate().findByNamedQuery(queryName, params);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQuery(java.lang.String)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNativeQuery(final String nativeQuerySql) {

        return getJpaTemplate().executeFind(new NativeQueryJpaCallback<T>(nativeQuerySql, clazz));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQueryWithMap(java.lang.String,
     *      java.util.Map)
     * 
     */
    @Override
    public List<T> getListByNativeQueryWithMap(final String nativeQuerySql,
        final Map<String, ?> paraMap) {

        return getJpaTemplate().execute(new JpaCallback<List<T>>() {

            @SuppressWarnings("unchecked")
            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {

                Query nativeQuery = em.createNativeQuery(nativeQuerySql, clazz);
                prepareQueryParam(nativeQuery, paraMap);
                return nativeQuery.getResultList();
            }
        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQueryWithPaging(java.lang.String,
     *      java.lang.String, com.xidu.framework.common.util.Pager)
     * 
     */
    @Override
    public List<T> getListByNativeQueryWithPaging(final String nativeQuerySql,
        final String countNativeQuerySql, Pager pager) {
        if (null == pager) {
            pager = new Pager();
        }
        return getJpaTemplate().execute(
            new NativeQueryWithPagingJpaCallback<T>(nativeQuerySql, countNativeQuerySql, pager));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQueryWithVaParam(java.lang.String,
     *      java.lang.Object[])
     * 
     */
    @Override
    public List<T> getListByNativeQueryWithVaParam(final String nativeQuerySql,
        final Object... params) {

        return getJpaTemplate().execute(new JpaCallback<List<T>>() {

            @SuppressWarnings("unchecked")
            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {

                Query nativeQuery = em.createNativeQuery(nativeQuerySql, clazz);
                prepareQueryParam(nativeQuery, params);
                return nativeQuery.getResultList();
            }
        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByQuery(java.lang.String)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByQuery(String queryStr) {

        return getJpaTemplate().find(queryStr);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByQueryWithMap(java.lang.String,
     *      java.util.Map)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByQueryWithMap(final String queryStr, final Map<String, ?> paraMap) {

        return getJpaTemplate().findByNamedParams(queryStr, paraMap);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByQueryWithPaging(java.lang.String,
     *      java.lang.String, com.xidu.framework.common.util.Pager)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByQueryWithPaging(final String queryStr, final String countQueryStr,
        Pager pager) {
        if (null == pager) {
            pager = new Pager();
        }

        return getJpaTemplate().executeFind(
            new QueryWithPagingJpaCallback<T>(queryStr, countQueryStr, pager));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByQueryWithVaParam(java.lang.String,
     *      java.lang.Object[])
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByQueryWithVaParam(final String queryStr, final Object... params) {

        return getJpaTemplate().find(queryStr, params);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#flush()
     * 
     */
    @Override
    public void flush() {

        getJpaTemplate().flush();
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#clear()
     * 
     */
    @Override
    public void clear() {

        getEntityManager().flush();
        getEntityManager().clear();
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQueryAndRSMapping(java.lang.String,
     *      java.lang.String)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNativeQueryAndRSMapping(String nativeQuerySql,
        String sqlResultSetMappingName) {
        return getJpaTemplate().executeFind(
            new NativeQueryWithResultSetMappingJpaCallback<T>(nativeQuerySql,
                sqlResultSetMappingName));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQueryWithMapAndRSMapping(java.lang.String,
     *      java.lang.String, java.util.Map)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNativeQueryWithMapAndRSMapping(final String nativeQuerySql,
        final String sqlResultSetMappingName, final Map<String, ?> paraMap) {
        return getJpaTemplate().executeFind(new JpaCallback<List<T>>() {

            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {
                Query nativeQuery = em.createNativeQuery(nativeQuerySql, sqlResultSetMappingName);
                prepareQueryParam(nativeQuery, paraMap);

                return nativeQuery.getResultList();
            }
        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQueryWithPagingAndRSMapping(java.lang.String,
     *      java.lang.String, java.lang.String,
     *      com.xidu.framework.common.util.Pager)
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNativeQueryWithPagingAndRSMapping(String nativeQuerySql,
        String sqlResultSetMappingName, String countNativeQuerySql, Pager pager) {
        if (null == pager) {
            pager = new Pager();
        }
        return getJpaTemplate().executeFind(
            new NativeQueryByPagingAndRsmJpaCallback<T>(nativeQuerySql, countNativeQuerySql, pager,
                sqlResultSetMappingName));
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByNativeQueryWithVaParamAndRSMapping(java.lang.String,
     *      java.lang.String, java.lang.Object[])
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getListByNativeQueryWithVaParamAndRSMapping(final String nativeQuerySql,
        final String sqlResultSetMappingName, final Object... params) {
        return getJpaTemplate().executeFind(new JpaCallback<List<T>>() {

            @Override
            public List<T> doInJpa(EntityManager em) throws PersistenceException {
                Query nativeQuery = em.createNativeQuery(nativeQuerySql, sqlResultSetMappingName);
                prepareQueryParam(nativeQuery, params);

                return nativeQuery.getResultList();
            }

        });
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.common.dao.IBaseDao#getListByQueryWithDefaultPaging(java.lang.String,
     *      com.xidu.framework.common.util.Pager)
     * 
     */
    @Override
    public List<T> getListByQueryWithDefaultPaging(String queryStr, Pager pager) {
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
    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2013-7-9
     * @see com.xidu.framework.common.dao.IBaseDao#getListByQueryWithDefaultPagingDistinct(java.lang.String, java.lang.String, com.xidu.framework.common.util.Pager)
    *
     */
    @Override
	public List<T> getListByQueryWithDefaultPagingDistinct(String queryStr,
			String distinctColumn, Pager pager) {
    	if (StringUtils.isBlank(queryStr)) {
            throw new IllegalArgumentException("Query String is NULL.");
        }
    	int fromIndex = queryStr.toLowerCase().indexOf(SQL_KEY_WORD_FROM);
        String countQueryStr;
        if (fromIndex > -1) {
            countQueryStr = "select count(distinct "+ distinctColumn+" )" + queryStr.substring(fromIndex);
        } else {
            countQueryStr = "select count(distinct "+ distinctColumn+" )" + queryStr;
        }
    	return getListByQueryWithPaging(queryStr, countQueryStr, pager);
	}

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.common.dao.IBaseDao#removeByQueryAndVaParam(java.lang.String,
     *      java.lang.Object[])
     **/
    @Override
    public void removeByQueryAndVaParam(final String queryStr, final Object... params) {
        getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                // TODO Auto-generated method stub
                Query removeQuery = em.createQuery(queryStr);
                prepareQueryParam(removeQuery, params);
                return removeQuery.executeUpdate();
            }

        });

    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.common.dao.IBaseDao#removeByQueryAndMap(java.lang.String,
     *      java.util.Map)
     **/
    @Override
    public void removeByQueryAndMap(final String queryStr, final Map<String, ?> paraMap) {
        getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                // TODO Auto-generated method stub
                Query removeQuery = em.createQuery(queryStr);
                prepareQueryParam(removeQuery, paraMap);
                return removeQuery.executeUpdate();
            }

        });

    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.common.dao.IBaseDao#updateByQUeryAndVaParam(java.lang.String,
     *      java.lang.Object[])
     **/
    @Override
    public void updateByQUeryAndVaParam(final String queryStr, final Object... params) {
        // TODO Auto-generated method stub
        getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                // TODO Auto-generated method stub
                Query updateQuery = em.createQuery(queryStr);
                prepareQueryParam(updateQuery, params);
                return updateQuery.executeUpdate();
            }

        });
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.common.dao.IBaseDao#updateByQueryAndMap(java.lang.String,
     *      java.util.Map)
     **/
    @Override
    public void updateByQueryAndMap(final String queryStr, final Map<String, ?> paraMap) {
        // TODO Auto-generated method stub
        getJpaTemplate().execute(new JpaCallback<Integer>() {

            @Override
            public Integer doInJpa(EntityManager em) throws PersistenceException {
                // TODO Auto-generated method stub
                Query updateQuery = em.createQuery(queryStr);
                prepareQueryParam(updateQuery, paraMap);
                return updateQuery.executeUpdate();
            }

        });
    }
}
