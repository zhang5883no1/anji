/************************************************************************************
 * @File name   :      IFunctionGroupDao.java
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
 * Date                             Who                 Version         Comments
 * 2011-3-22 上午09:26:49            Eric Zhang          1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.FunctionGroup;

/**
 * Interface of FunctionGroupDao
 */
public interface IFunctionGroupDao extends IBaseDao<FunctionGroup, Long> {

    /**
     * Get all the function groups
     * 
     * @Date : 2011-3-21
     * @return list of all the function groups
     */
    public List<FunctionGroup> getAllFunctionGroups();

    /**
     * Get the function group by code
     * 
     * @Date : 2011-3-21
     * @param funcGrpCode
     *            code of the function group
     * @return the function group
     */
    public FunctionGroup getFunctionGroupByCode(String funcGrpCode);
    
    /**
     * GEt function group by role code
     * @Date        :      2011-4-1
     * @param roleCode  code of role
     * @return  list of function group
     */
    public List<FunctionGroup> getFunctionGroupsByRoleCode(String roleCode);
}
