/******************************************************************************
 * @File name   :      IUserGroupRoleDao.java
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
 * 2011-3-31 下午03:24:01        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.UserGroupRole;

/**
 * IUserGroupRoleDao
 */
public interface IUserGroupRoleDao extends IBaseDao<UserGroupRole, Long> {

    /**
     * delete UserGroupRole By UsrGrpCode
     * @Date        :      2011-4-1
     * @param userGrpCode   UsrGrpCode
     */
    public void deleteUserGroupRoleByUsrGrpCode(String userGrpCode);
    
    /**
     * Delete UserGroupRole by role code
     * @Date        :      2011-4-1
     * @param roleCode  code of role
     */
    public void deleteUserGroupRoleByRoleCode(String roleCode);
}
