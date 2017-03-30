/************************************************************************************
 * @File name   :      IRoleDao.java
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
 * Date                             Who                 Version          Comments
 * 2011-3-21 下午05:51:41            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.dto.QueryRoleDto;

/**
 * Interface of RoleDao
 */
public interface IRoleDao extends IBaseDao<Role, Long> {

    /**
     * Get all the roles
     * 
     * @Date : 2011-3-21
     * @return list of all the roles
     */
    public List<Role> getAllRoles();

    /**
     * Get Role by code
     * 
     * @Date : 2011-3-22
     * @param roleCode
     *            code of the role
     * @return the role
     */
    public Role getRoleByCode(String roleCode);
    
    /**
     * get Role list by user code
     * @Date        :      2011-3-31
     * @param userCode  user code
     * @return  list of the roles
     */
    public List<Role> getRolesByUserCode(String userCode);
    
    // query role by query
    public BasePagerDto queryRole(QueryRoleDto<Role> queryDto);

}
