/******************************************************************************
 * @File name   :      IUserRoleDao.java
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
 * 2011-3-31 下午03:23:01        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.UserRole;

/**
 * IUserRoleDao
 */
public interface IUserRoleDao extends IBaseDao<UserRole, Long> {

    /**
     * Delete userRole by user code
     * @Date        :      2011-4-1
     * @param userCode  code of user
     */
    public void deleteUserRolesByUserCode(String userCode);
    
    /**
     * Delete userRole by role code
     * @Date        :      2011-4-1
     * @param roleCode  code of role
     */
    public void deleteUserRolesByRoleCode(String roleCode);

	public void removeByUserId(Long id);

	public List<UserRole> findByUserId(Long valueOf);

}
