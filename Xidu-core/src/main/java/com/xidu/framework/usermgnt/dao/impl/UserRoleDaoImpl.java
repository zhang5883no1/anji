/******************************************************************************
 * @File name   :      UserRoleDaoImpl.java
 *
 * @Author      :      <Jianxi Wu>
 *
 * @Date        :      2011-3-31
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2011-3-31 下午03:28:49        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IUserRoleDao;
import com.xidu.framework.usermgnt.domain.UserRole;

/**
 * Implement IUserRoleDao
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, Long> implements IUserRoleDao {

    /**
     * @Date : 2011-3-31
     * @param clazz     UserRole
     */
	@Autowired
    public UserRoleDaoImpl(
        @Value("com.xidu.framework.usermgnt.domain.UserRole") Class<UserRole> clazz) {
        super(clazz);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IUserRoleDao#deleteUserRolesByUserCode(java.lang.String)
     **/
    @Override
    public void deleteUserRolesByUserCode(String userCode) {
        String hql = "delete from UserRole ur where exists "
            + "(select 1 from BaseUser u where ur.user.id = u.id and u.userCode = ?1)";
        removeByQueryAndVaParam(hql, userCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IUserRoleDao#deleteUserRolesByRoleCode(java.lang.String)
     **/
    @Override
    public void deleteUserRolesByRoleCode(String roleCode) {
        String hql = "delete from UserRole ur where exists "
            + "(select 1 from Role r where ur.role.id = r.id and r.roleCode = ?1)";
        removeByQueryAndVaParam(hql, roleCode);
    }

	@Override
	public void removeByUserId(Long id) {
		// TODO Auto-generated method stub
		String hql="delete from UserRole ur where ur.user="+id;
		removeByQuery(hql);
	}

	@Override
	public List<UserRole> findByUserId(Long userid) {
		// TODO Auto-generated method stub
		String hql="from UserRole ur where ur.user="+userid;
		List<UserRole> list=getListByQuery(hql);
		return list;
	}

}
