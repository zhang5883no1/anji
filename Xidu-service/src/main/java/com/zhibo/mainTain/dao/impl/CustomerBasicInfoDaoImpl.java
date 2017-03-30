package com.zhibo.mainTain.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.UserGroupDto;
import com.zhibo.mainTain.dao.CustomerBasicInfoDao;
import com.zhibo.mainTain.domain.CustomerBasicInfo;
import com.zhibo.mainTain.dto.QueryCustomerBasicInfoDto;

@Repository
public class CustomerBasicInfoDaoImpl extends
		BaseDaoImpl<CustomerBasicInfo, Long> implements CustomerBasicInfoDao {

	@Autowired
	public CustomerBasicInfoDaoImpl(
			@Value("com.zhibo.mainTain.domain.CustomerBasicInfo") Class<CustomerBasicInfo> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasePagerDto queryCustomerBasicInfo(
			QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto) {
		// TODO Auto-generated method stub
		String sql = "from CustomerBasicInfo t where DELETE_FLAG=0 ";
		String queryUserId="";
		//所属业务员不为空
		if(Utils.notEmpty(queryDto.getUserId())){
			queryUserId="("+queryDto.getUserId()+")";
		//所属业务员为空
		}else{
			//组不为空
			if(Utils.notEmpty(queryDto.getGroupCode())){
				for(UserGroupDto group:queryDto.getGrouplist()){
					//如果查询组相等
					if(group.getUserGroupCode().equals(queryDto.getGroupCode())){
						List<BaseUser> userlist=group.getUserlist();
						for(int i=0;i<userlist.size();i++){
							if(i==0){
								queryUserId+="("+userlist.get(i).getId();
							}else{
								queryUserId+=","+userlist.get(i).getId();
							}
						}
						queryUserId+=")";
					}
				}
			//组空
			}else{
				queryUserId+="("+queryDto.getSessionUser().getUserId();
				for(UserGroupDto group:queryDto.getGrouplist()){
					List<BaseUser> userlist=group.getUserlist();
					for(int i=0;i<userlist.size();i++){
						queryUserId+=","+userlist.get(i).getId();
					}
				}
				queryUserId+=")";
			}
		}
		if(null==queryDto.getLevel()||"".equals(queryDto.getLevel())){
			sql+=" and t.userId in "+queryUserId+ " and t.level<90";
		}else{
			sql+=" and t.userId in "+queryUserId+" and t.level in ("+queryDto.getLevel()+") and t.level<90";
		}

		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if (Utils.notEmpty(queryDto.getId())) {
			wcg.and("t.id", "=",  queryDto.getId());
		}
		if (Utils.notEmpty(queryDto.getStatus())) {
			wcg.and("t.status", "=",  queryDto.getStatus());
		}
		/*if (Utils.notEmpty(queryDto.getLevel())) {
			System.out.println(queryDto.getLevel()+"------------------------------------------------------------");
			wcg.and("t.level", "in",  "("+queryDto.getLevel()+")");
		}*/
		if(Utils.notEmpty(queryDto.getNickName())){
			wcg.and("t.nickName", "=",  queryDto.getNickName());
		}
		if(Utils.notEmpty(queryDto.getRoomId())){
			wcg.and("t.roomNo", "like",  "%"+queryDto.getRoomId()+"%");
		}
		
		
		if (Utils.notEmpty(queryDto.getSortColumn())) {
			wcg.sort(queryDto.getSortColumn(), queryDto.getAscOrDesc());
		}else{
			wcg.append(" order by t.createDate desc");
		}
		logger.debug("sql query : " + wcg);

		List<CustomerBasicInfo> list = getListByQueryWithDefaultPaging(
				wcg.toQuery(), queryDto.getPager());
		List<CustomerBasicInfo> list1 = new ArrayList<CustomerBasicInfo>(); 
		for(CustomerBasicInfo cu : list){
			String mobile = cu.getMobile();
			/*try {
				mobile = mobile.substring(0,3)+"****"+mobile.substring(7);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			CustomerBasicInfo c=new CustomerBasicInfo();
			BeanUtils.copyProperties(cu, c);
			c.setMobile(mobile);
			list1.add(c);
		}
		queryDto.setResultList(list1);
		return queryDto;
	}

	@Override
	public List<CustomerBasicInfo> getAllAdminCustomer() {
		// TODO Auto-generated method stub
		String sql = "from CustomerBasicInfo t where DELETE_FLAG=0 and level >=90";
		List<CustomerBasicInfo> list = getListByQuery(sql);
		return list;
	}

	@Override
	public BasePagerDto queryAdminBasicInfo(
			QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto) {
		// TODO Auto-generated method stub
		String sql = "from CustomerBasicInfo t where DELETE_FLAG=0 and t.level >=90 ";
		String queryUserId="";

		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		String appendSql=queryDto.getSessionUser().getAssignUser()+"";
		for(UserGroupDto groupDto:queryDto.getGrouplist()){
			for(BaseUser users:groupDto.getUserlist()){
				appendSql+=" , "+users.getUserType();
			}
		}
		wcg.append(" and t.id in ("+appendSql+") ");
		
		if (Utils.notEmpty(queryDto.getId())) {
			wcg.and("t.id", "=",  queryDto.getId());
		}
		if (Utils.notEmpty(queryDto.getStatus())) {
			wcg.and("t.status", "=",  queryDto.getStatus());
		}
		if (Utils.notEmpty(queryDto.getLevel())) {
			wcg.and("t.level", "=",  queryDto.getLevel());
		}
		if(Utils.notEmpty(queryDto.getNickName())){
			wcg.and("t.nickName", "=",  queryDto.getNickName());
		}
		if(Utils.notEmpty(queryDto.getRoomId())){
			wcg.and("t.roomNo", "=",  queryDto.getRoomId());
		}else{
			String rs="0";
			for(RoomDto r:queryDto.getRooms()){
				rs+=","+r.getId();
			}
			wcg.append(" and t.roomNo in ("+rs+") ");
		}
		
		
		if (Utils.notEmpty(queryDto.getSortColumn())) {
			wcg.sort(queryDto.getSortColumn(), queryDto.getAscOrDesc());
		}
		logger.debug("sql query : " + wcg);

		List<CustomerBasicInfo> list = getListByQueryWithDefaultPaging(
				wcg.toQuery(), queryDto.getPager());
		queryDto.setResultList(list);
		return queryDto;
	}
	
}
