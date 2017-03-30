/******************************************************************************
 * @File name   :      IRoleFunctionDao.java
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
 * 2011-3-31 下午03:24:40        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.RoleFunction;

/**
 * Interface of IRoleFunctionDao
 */
public interface IRoleFunctionDao extends IBaseDao<RoleFunction, Long> {

    /**
     * Get RoleFunctions by role code
     * @Date        :      2011-4-2
     * @param roleCode  role code of the role
     * @return      list of RoleFunction
     */
    public List<RoleFunction> getRoleFunctionsByRoleCode(String roleCode);

    /**
     * Delete RoleFunction by role code
     * @Date        :      2011-4-2
     * @param roleCode  role code of the role
     */
    public void deleteRoleFunctionsByRoleCode(String roleCode);

    /**
     * Delete RoleFunction by function code
     * @Date        :      2011-4-2
     * @param functionCode  function code of the function
     */
    public void deleteRoleFunctionsByFunctionCode(String functionCode);
    
    //get role function list by role id
    public List<RoleFunction> queryRoleFunctionByRoleId(Long id);

	public void deleteRoleFunctionsByRole(Role id);

}
