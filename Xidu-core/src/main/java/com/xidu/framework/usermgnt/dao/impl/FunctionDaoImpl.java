/************************************************************************************
 * @File name   :      FunctionDaoImpl.java
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
 * 2011-3-22 上午11:26:51            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IFunctionDao;
import com.xidu.framework.usermgnt.domain.Function;

/**
 * Implement IFunctionDao
 */
@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function, Long> implements IFunctionDao {

    /**
     * Constructor
     * @Date : 2011-3-22
     * @param clazz     Function
     */
    @Autowired
    public FunctionDaoImpl(
        @Value("com.xidu.framework.usermgnt.domain.Function") 
            Class<Function> clazz) {
        super(clazz);
    }

    /**
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IFunctionDao#getAllFunctions()
    *
     */
    @Override
    public List<Function> getAllFunctions() {
        List<Function> functions = 
            getListByNativeQuery("select * from ts_fmwk_function");
        return functions;
    }

    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IFunctionDao#getFunctionByCode(java.lang.String)
     */
    @Override
    public Function getFunctionByCode(String functionCode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("functionCode", functionCode);
        List<Function> functions = getListByQueryWithMap(
            "from Function d where d.functionCode = :functionCode", paraMap);
        if (functions != null && functions.size() > 0) {
            return functions.get(0);  
        }       
        else {
            return null;
        } 
    }

    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IFunctionDao#getFunctionsByFuncGrpCode(java.lang.String)
     */
    @Override
    public List<Function> getFunctionsByFuncGrpCode(String functionGroupCode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("funcGrpCode", functionGroupCode);
        List<Function> functions = getListByQueryWithMap(
            "select func from Function func, FunctionGroup fg where " 
            + "fg.funcGrpCode = :funcGrpCode and func.functionGroup.id = fg.id", paraMap);
        return functions;
    }

    /**
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IFunctionDao#getFunctionsByRoleCode(java.lang.String)
    **/
    @Override
    public List<Function> getFunctionsByRoleCode(String roleCode) {
        String hql = "select rf.function from Role r, RoleFunction rf where " 
            + "r.id = rf.role.id and r.roleCode = ?1";
        List<Function> functions = this.getListByQueryWithVaParam(hql, roleCode);
        return functions;
    }

}
