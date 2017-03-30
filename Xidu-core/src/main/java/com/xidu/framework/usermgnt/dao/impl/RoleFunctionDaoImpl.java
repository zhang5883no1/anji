/******************************************************************************
 * @File name   :      RoleFunctionDaoImpl.java
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
 * 2011-3-31 下午03:36:09        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.xidu.framework.usermgnt.dao.IRoleFunctionDao;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.RoleFunction;

/**
 *
 */
@Repository("roleFunctionDao")
public class RoleFunctionDaoImpl extends BaseDaoImpl<RoleFunction, Long> implements
    IRoleFunctionDao {

    /**
     * @Date : 2011-3-31
     * @param clazz com.trinasolar.framework.usermgnt.domain.RoleFunction
     */
    @Autowired
    public RoleFunctionDaoImpl(
        @Value("com.xidu.framework.usermgnt.domain.RoleFunction") Class<RoleFunction> clazz) {
        super(clazz);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IRoleFunctionDao#getRoleFunctionsByRoleCode(java.lang.String)
     **/
    @Override
    public List<RoleFunction> getRoleFunctionsByRoleCode(String roleCode) {
        String hql = "select rf from RoleFunction rf, Role r "
            + "where r.id = rf.role.id and r.roleCode = ?1";
        return getListByQueryWithVaParam(hql, roleCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IRoleFunctionDao#deleteRoleFunctionsByRoleCode(java.lang.String)
     **/
    @Override
    public void deleteRoleFunctionsByRoleCode(String roleCode) {
        String hql = "delete from RoleFunction rf where exists "
            + "(select r from Role r where rf.role.id = r.id and r.roleCode = ?1)";
        removeByQueryAndVaParam(hql, roleCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.xidu.framework.usermgnt.dao.IRoleFunctionDao#deleteRoleFunctionsByFunctionCode(java.lang.String)
     **/
    @Override
    public void deleteRoleFunctionsByFunctionCode(String functionCode) {
        String hql = "delete from RoleFunction rf where exists "
            + "(select f from Function f where rf.function.id = f.id and f.functionCode = ?1)";
        removeByQueryAndVaParam(hql, functionCode);
    }
    
    @Override
	public List<RoleFunction> queryRoleFunctionByRoleId(Long id) {
		// TODO Auto-generated method stub
		String sql = "from RoleFunction t where 1=1";
		
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if(Utils.notEmpty(String.valueOf(id))){
			wcg.and("t.role", "=", id);
		}
		logger.debug("sql query : " + wcg);
		
		//List<RoleFunction> list= getListByQueryWithDefaultPaging(wcg.toQuery(), new Pager());
		List<RoleFunction> list=getListByQuery(wcg.toQuery());
		return list;
	}

	public void deleteRoleFunctionsByRole(Role role) {
		// TODO Auto-generated method stub
		 String hql = "delete from RoleFunction rf where rf.role="+role.getId();
		 logger.debug("sql query : " + hql);
		 removeByQuery(hql);
	}

}
