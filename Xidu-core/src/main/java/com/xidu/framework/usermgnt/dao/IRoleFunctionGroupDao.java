/******************************************************************************
 * @File name   :      IRoleFunctionGroupDao.java
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
 * 2011-3-31 下午03:25:21        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.RoleFunctionGroup;

/**
 * IRoleFunctionGroupDao
 */
public interface IRoleFunctionGroupDao extends IBaseDao<RoleFunctionGroup, Long> {

    /**
     * Delete RoleFunctionGroup relations by role code
     * @Date        :      2011-4-2
     * @param roleCode      role code of the role
     */
    public void deleteRoleFunctionGroupsByRoleCode(String roleCode);

    /**
     * Delete RoleFunctionGRoup relation by function group code
     * @Date        :      2011-4-2
     * @param funcGrpCode   function group code
     */
    public void deleteRoleFunctionGroupsByFuncGrpCode(String funcGrpCode);
}
