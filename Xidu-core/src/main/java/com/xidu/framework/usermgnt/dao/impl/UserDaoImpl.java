/************************************************************************************
 * @File name   :      UserDaoImpl.java
 *
 * @Author      :      Eric Zhang
 *
 * @Date        :      2011-3-22
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                             Who                 Version          Comments
 * 2011-3-22 上午09:30:19            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.xidu.framework.usermgnt.dao.IUserDao;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.dto.QueryUserInfoDto;
import com.xidu.framework.usermgnt.dto.UserDto;

/**
 * Implement IUserDao
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<BaseUser, Long> implements IUserDao {

    /**
     * Constructor
     * 
     * @Date : 2011-3-22
     * @param clazz
     *            User
     */
	@Autowired
	public UserDaoImpl(@Value("com.xidu.framework.usermgnt.domain.BaseUser") Class<BaseUser> clazz) {
        super(clazz);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IUserDao#getUserByName(java.lang.String)
     */
    @Override
    public List<BaseUser> getUserByName(String name) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("name", name);
        List<BaseUser> users = getListByQueryWithMap("from BaseUser d where d.deleteFlag=0 and d.name = :name", paraMap);
        return users;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IUserDao#getUserByUserCode(java.lang.String)
     */
    @Override
    public BaseUser getUserByUserCode(String userCode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userCode", userCode);
//        List<User> users = getListByQuery("from BaseUser d where d.deleteFlag=0 and d.userCode ='"+userCode+"';");
        List<BaseUser> users = getListByQueryWithMap("from BaseUser d where d.deleteFlag=0 and d.userCode = :userCode",
            paraMap);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IUserDao#getUsersByUserGroup(java.lang.String)
     */
    @Override
    public List<BaseUser> getUsersByUserGroup(String userGroup) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userGroupCode", userGroup);
        List<BaseUser> users = getListByQueryWithMap("select u from BaseUser u, UserGroup ug where u.deleteFlag=0 and "
            + " ug.userGroupCode = :userGroupCode and u.userGroup.id = ug.id", paraMap);
        return users;
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.usermgnt.dao.IUserDao#getUsersByOrgCode(java.lang.String)
     **/
    @Override
    public List<BaseUser> getUsersByOrgCode(String orgCode) {
        // TODO Auto-generated method stub
        String queryStr = "select u from Organization o, OrganizationUser ou, BaseUser u "
            + "where u.deleteFlag=0 and o.id = ou.organizationId and ou.userId = u.id and o.orgCode = ?1";
        return getListByQueryWithVaParam(queryStr, orgCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.usermgnt.dao.IUserDao#getAllUsersByOrgCode(java.lang.String)
     **/
    @Override
    public List<BaseUser> getAllUsersByOrgCode(String orgCode) {
        // TODO Auto-generated method stub
        String nativeSql = "select u.* from (select * from ts_fmwk_organization "
            + "connect by prior org_code = parent_org_code start with org_code= ?1) org,"
            + "tr_fmwk_org_user ou, ts_fmwk_user u where u.deleteFlag=0 and org.id = ou.organization_id "
            + "and ou.user_id = u.id ";
        return getListByNativeQueryWithVaParam(nativeSql, orgCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.usermgnt.dao.IUserDao#getUsersByOrgAndRoleCode(java.lang.String,
     *      java.lang.String)
     **/
    @Override
    public List<BaseUser> getUsersByOrgAndRoleCode(String orgCode, String roleCode) {
        String queryStr = "select u from Organization o, OrganizationUser ou"
            + ",BaseUser u, UserRole ur, Role r "
            + "where u.deleteFlag=0 and o.id = ou.organizationId and ou.userId = u.id "
            + "and u.id = ur.user.id and ur.role.id = r.id "
            + "and o.orgCode = ?1 and r.roleCode = ?2 ";
        return getListByQueryWithVaParam(queryStr, orgCode, roleCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-7
     * @see com.xidu.framework.usermgnt.dao.IUserDao#getManagerUserByOrgCode(java.lang.String)
     **/
    @Override
    public List<BaseUser> getManagerUserByOrgCode(String orgCode) {
        // TODO Auto-generated method stub
        String queryStr = "select u from Organization o, OrganizationUser ou "
            + "where o.id = ou.organizationId and o.orgCode = ?1 and ou.ouTitle = 1";

        return getListByQueryWithVaParam(queryStr, orgCode);
    }

    /**
     * 
     * @Date : 2011-6-30
     * @param userDto
     * @return
     */
    public List<BaseUser> getUsersByDto(UserDto userDto) {
        String sql = " from BaseUser u  where u.deleteFlag=0 and 1=1";

        WhereConditionGenerator wcg = new WhereConditionGenerator(sql);

        if (userDto.getRealName() != null && !"".equals(userDto.getRealName())) {
            wcg.and("lower(u.name)", "like", "%" + userDto.getRealName().toLowerCase() + "%");
        }

        if (userDto.getRegionId() != null && !"".equals(userDto.getRegionId())) {
           wcg.append(" and u.id in (select r.user.id from UserRegion r  where r.region.id="+userDto.getRegionId()+")");
        }
        
        if(Utils.notEmpty(userDto.getParentId()+"")){
        	wcg.append("and parent_id ="+userDto.getParentId());
        }
        return getListByQueryWithDefaultPaging(wcg.toQuery(), userDto.getPager());
    }

	@Override
	public BasePagerDto queryUser(QueryUserInfoDto<BaseUser> queryDto) {
		// TODO Auto-generated method stub
		String sql="from BaseUser u where u.deleteFlag=0";

        WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
        
        if(Utils.notEmpty(queryDto.getUserCode())){
        	wcg.and("u.userCode","like","%"+queryDto.getUserCode()+"%");
        }
        if(Utils.notEmpty(queryDto.getName())){
        	wcg.and("u.name","like","%"+queryDto.getName()+"%");
        }
//        if(Utils.notEmpty(queryDto.getEmployerOwnerId())){
//        	wcg.and("u.employerOwnerId","like","%"+queryDto.getEmployerOwnerId()+"%");
//        }
//        if(String.valueOf(queryDto.getUserType())!=""){
//        	wcg.and("u.userType","=",queryDto.getUserType());
//        }
//        if(String.valueOf(queryDto.getStatusCode())!=""){
//        	wcg.and("u.statusCode","=",queryDto.getStatusCode());
//        }
        if(queryDto.getUserGroup()!=null){
        	wcg.and("u.userGroup","=",queryDto.getUserGroup().getId());
        }
        
        if(Utils.notEmpty(queryDto.getParentId())){
        	wcg.and("u.parentId", "=", queryDto.getParentId());
        }
        if(Utils.notEmpty(queryDto.getSortColumn())){
			wcg.sort(queryDto.getSortColumn(),queryDto.getAscOrDesc());
		}
        
        logger.debug("sql query : " + wcg);
		
		List<BaseUser> list= getListByQueryWithDefaultPaging(wcg.toQuery(), queryDto.getPager());
		queryDto.setResultList(list);
		return queryDto;
	}

	@Override
	public BaseUser findByUserCode(String usercode) {
		// TODO Auto-generated method stub
		String sql="from BaseUser u where 1=1 ";
        WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
        wcg.and("u.userCode", "=", usercode);
        List<BaseUser> list=getListByQuery(wcg.toQuery());
        if(list!=null&&list.size()>0){
        	return list.get(0);
        }else{
    		return null;
        }
	}

	@Override
	public List<BaseUser> queryUserByWarehouse(
			QueryUserInfoDto<BaseUser> queryuserDto) {
		// TODO Auto-generated method stub
		String sql="from BaseUser u where 1=1 ";
		WhereConditionGenerator wcg=new WhereConditionGenerator(sql);
		wcg.and("u.userGroup", "=", queryuserDto.getUserGroup().getId());
		wcg.and("u.employerOwnerId", "!=", "havewarehouse");
		return getListByQuery(wcg.toString());
	}

	@Override
	public List<BaseUser> findByRoomId(Long id) {
		List<BaseUser> result=new ArrayList<BaseUser>();
		// TODO Auto-generated method stub
		String sql="select u.userCode,u.name,u.id,u.employerOwnerId from BaseUser u ,UserGroup g ,GroupRoom gr,Room r where u.userGroup.id=g.id and g.id=gr.group and gr.room=r.id and r.id="+id;
		List list=getListByQuery(sql);
		for(int i=0;i<list.size();i++){
			Object[] object = (Object[])list.get(i);
			BaseUser u=new BaseUser();
			u.setUserCode(object[0].toString());
			u.setName(object[1].toString());
			u.setId(Long.valueOf(object[2].toString()));
			u.setEmployerOwnerId(object[3].toString());
			result.add(u);
		}
		return result;
	}

}
