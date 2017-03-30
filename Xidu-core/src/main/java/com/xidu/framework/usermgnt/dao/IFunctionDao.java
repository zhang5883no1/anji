/************************************************************************************
 * @File name   :      IFunctionDao.java
 *
 * @Author      :      Eric Zhang
 *
 * @Date        :      2011-3-21
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
 * 2011-3-21 上午09:19:35            Eric Zhang          1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.Function;

/**
 * Interface of FunctionDao
 */
public interface IFunctionDao extends IBaseDao<Function, Long> {

    /**
     * Get all functions
     * 
     * @Date : 2011-3-21
     * @return list of all the functions
     */
    public List<Function> getAllFunctions();

    /**
     * Get function by function code
     * 
     * @Date : 2011-3-21
     * @param functionCode
     *            code of the function
     * @return the function
     */
    public Function getFunctionByCode(String functionCode);

    /**
     * Get functions by function group
     * 
     * @Date : 2011-3-21
     * @param functionGroupCode
     *            code of the function group
     * @return the list of function
     */
    public List<Function> getFunctionsByFuncGrpCode(String functionGroupCode);

    /**
     * Get functions by role code
     * 
     * @Date : 2011-4-1
     * @param roleCode
     *            code of role
     * @return the list of function
     */
    public List<Function> getFunctionsByRoleCode(String roleCode);
}
