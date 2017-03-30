/************************************************************************************
 * @File name   :      RoleDaoImpl.java
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
 * 2011-3-22 上午10:49:29            Eric Zhang          1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.xidu.framework.usermgnt.dao.IRoleDao;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.dto.QueryRoleDto;

/**
 * Implement IRoleDao
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements IRoleDao {

    /**
     * Constructor
     * 
     * @Date : 2011-3-22
     * @param clazz
     *            Role
     */
	@Autowired
    public RoleDaoImpl(@Value("com.xidu.framework.usermgnt.domain.Role") Class<Role> clazz) {
        super(clazz);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IRoleDao#getAllRoles()
     */
    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = getListByNativeQuery("select * from ts_fmwk_role where delete_flag=0");
        return roles;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IRoleDao#getRoleByCode(java.lang.String)
     */
    @Override
    public Role getRoleByCode(String roleCode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("roleCode", roleCode);
        List<Role> roles = getListByQueryWithMap("from Role d where d.deleteFlag=0 and d.roleCode = :roleCode",
            paraMap);
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-31
     * @see com.xidu.framework.usermgnt.dao.IRoleDao#getRolesByUserCode(java.lang.String)
     **/
    @Override
    public List<Role> getRolesByUserCode(String userCode) {
        String hql = "select r from BaseUser u,UserRole ur, Role r "
            + "where r.deleteFlag=0 and u.id = ur.user.id and ur.role.id = r.id and u.userCode = ?1 "
            + "union "
            + "( select r from BaseUser u, Role r, UserGroup ug, UserGroupRole ugr "
            + "where r.deleteFlag=0 and u.userGroup.id = ug.id and ug.id = ugr.userGroup.id and ugr.role.id = r.id " 
            + "and u.userCode = ?1 )";
        return getListByQueryWithVaParam(hql, userCode);
    }
    
    @Override
	public BasePagerDto queryRole(QueryRoleDto<Role> queryDto) {
		// TODO Auto-generated method stub
		String sql = "select distinct(t) from Role t where t.deleteFlag=0 ";
		
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if(Utils.notEmpty(queryDto.getRoleCode())){
			wcg.and("t.roleCode", "like", "%" + queryDto.getRoleCode() + "%");
		}
		if(Utils.notEmpty(queryDto.getRoleDesc())){
			wcg.and("t.roleDesc", "like", "%" + queryDto.getRoleDesc() + "%");
		}
		if(Utils.notEmpty(queryDto.getRoleName())){
			wcg.and("t.roleName", "like", "%" + queryDto.getRoleName() + "%");
		}
		if(queryDto.getGroupid()!=null){
			wcg.and("t.rolegroup", "=", queryDto.getGroupid());
		}
		if(Utils.notEmpty(queryDto.getSortColumn())){
			wcg.sort(queryDto.getSortColumn(),queryDto.getAscOrDesc());
		}
		logger.debug("sql query : " + wcg);
		
		List<Role> list= getListByQueryWithDefaultPaging(wcg.toQuery(), queryDto.getPager());
		queryDto.setResultList(list);
		return queryDto;
	}

}
