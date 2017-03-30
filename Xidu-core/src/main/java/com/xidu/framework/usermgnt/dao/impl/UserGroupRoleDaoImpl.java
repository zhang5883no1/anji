/******************************************************************************
 * @File name   :      UserGroupRoleDaoImpl.java
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
 * 2011-3-31 下午03:33:39        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IUserGroupRoleDao;
import com.xidu.framework.usermgnt.domain.UserGroupRole;

/**
 * UserGroupRoleDaoImpl implements IUserGroupRoleDao
 */
@Repository("userGroupRoleDao")
public class UserGroupRoleDaoImpl extends BaseDaoImpl<UserGroupRole, Long> implements
    IUserGroupRoleDao {

    /**
     * @Date : 2011-3-31
     * @param clazz     UserGroupRole
     */
    @Autowired
    public UserGroupRoleDaoImpl(
        @Value("com.xidu.framework.usermgnt.domain.UserGroupRole") Class<UserGroupRole> clazz) {
        super(clazz);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IUserGroupRoleDao#deleteUserGroupRoleByUsrGrpCode(java.lang.String)
     **/
    @Override
    public void deleteUserGroupRoleByUsrGrpCode(String userGrpCode) {
        String hql = "delete from UserGroupRole ugr where exists "
            + "(select ug from UserGroup ug where ugr.userGroup.id = ug.id " 
            + "and ug.userGroupCode = ?1)";
        removeByQueryAndVaParam(hql, userGrpCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IUserGroupRoleDao#deleteUserGroupRoleByRoleCode(java.lang.String)
     **/
    @Override
    public void deleteUserGroupRoleByRoleCode(String roleCode) {
        String hql = "delete from UserGroupRole ugr where exists "
            + "(select r from Role r where ugr.role.id = r.id and r.roleCode = ?1)";
        removeByQueryAndVaParam(hql, roleCode);
    }

}
