/******************************************************************************
 * @File name   :      RoleFunctionGroupDao.java
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
 * 2011-3-31 下午03:37:34        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IRoleFunctionGroupDao;
import com.xidu.framework.usermgnt.domain.RoleFunctionGroup;

/**
 * RoleFunctionGroupDaoImpl implements IRoleFunctionGroupDao
 */
@Repository("roleFunctionGroupDao")
public class RoleFunctionGroupDaoImpl extends BaseDaoImpl<RoleFunctionGroup, Long> implements
    IRoleFunctionGroupDao {

    /**
     * @Date : 2011-3-31
     * @param clazz RoleFunctionGroup
     */
    @Autowired
    public RoleFunctionGroupDaoImpl(
        @Value("com.xidu.framework.usermgnt.domain.RoleFunctionGroup") Class<RoleFunctionGroup> clazz) {
        super(clazz);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IRoleFunctionGroupDao#deleteRoleFunctionGroupsByRoleCode(java.lang.String)
     **/
    @Override
    public void deleteRoleFunctionGroupsByRoleCode(String roleCode) {
        String hql = "delete from RoleFunctionGroup rfg where exists "
            + "(select r from Role r where rfg.role.id = r.id and r.roleCode = ?1)";
        removeByQueryAndVaParam(hql, roleCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IRoleFunctionGroupDao#deleteRoleFunctionGroupsByFuncGrpCode(java.lang.String)
     **/
    @Override
    public void deleteRoleFunctionGroupsByFuncGrpCode(String funcGrpCode) {
        String hql = "delete from RoleFunctionGroup rfg where exists "
            + "(select fg from FunctionGroup fg where rfg.role.id = fg.id and fg.funcGrpCode = ?1)";
        removeByQueryAndVaParam(hql, funcGrpCode);
    }

}
