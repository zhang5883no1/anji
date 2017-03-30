/************************************************************************************
 * @File name   :      FunctionGroupDaoImpl.java
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
 * 2011-3-22 下午01:18:23            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IFunctionGroupDao;
import com.xidu.framework.usermgnt.domain.FunctionGroup;

/**
 * Implement IFunctionGroupDao
 */
@Repository("functionGroupDao")
public class FunctionGroupDaoImpl extends BaseDaoImpl<FunctionGroup, Long> implements
    IFunctionGroupDao {
    
    /**
     * Constructor
     * @Date : 2011-3-22
     * @param clazz     FunctionGroup
     */
    @Autowired
    public FunctionGroupDaoImpl(
        @Value("com.xidu.framework.usermgnt.domain.FunctionGroup") 
            Class<FunctionGroup> clazz) {
        super(clazz);
    }

    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IFunctionGroupDao#getAllFunctionGroups()
     */
    @Override
    public List<FunctionGroup> getAllFunctionGroups() {
        List<FunctionGroup> functionGroups = 
            getListByNativeQuery("select * from ts_fmwk_func_group");
        return functionGroups;
    }

    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IFunctionGroupDao#getFunctionGroupByCode(java.lang.String)
     */
    @Override
    public FunctionGroup getFunctionGroupByCode(String funcGrpCode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("funcGrpCode", funcGrpCode);
        List<FunctionGroup> functionGroups = getListByQueryWithMap(
            "from FunctionGroup d where funcGrpCode = :funcGrpCode", paraMap);
        if (functionGroups != null && functionGroups.size() > 0) {
            return functionGroups.get(0);
        } else {
            return null;
        }
    }

    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IFunctionGroupDao#getFunctionGroupsByRoleCode(java.lang.String)
     */
    @Override
    public List<FunctionGroup> getFunctionGroupsByRoleCode(String roleCode) {
        String hql = "select rfg.functionGroup from Role r, RoleFunctionGroup rfg where " 
            + "r.id = rfg.role.id and r.roleCode = ?1";
        List<FunctionGroup> functions = this.getListByQueryWithVaParam(hql, roleCode);
        return functions;
    }

}
