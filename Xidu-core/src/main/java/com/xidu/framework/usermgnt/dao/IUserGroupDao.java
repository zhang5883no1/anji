/************************************************************************************
 * @File name   :      IUserGroupDao.java
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
 * 2011-3-22 上午09:24:50            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.UserGroup;

/**
 * Interface of UserGroupDao
 */
public interface IUserGroupDao extends IBaseDao<UserGroup, Long> {

    /**
     * Get all the user groups
     * 
     * @Date : 2011-3-21
     * @return list of all the user groups
     */
    public List<UserGroup> getAllUserGroups();

    /**
     * Get user group by code
     * 
     * @Date : 2011-3-22
     * @param userGroupCode
     *            code of the user group
     * @return  UserGroup
     */
    public UserGroup getUserGroupByCode(String userGroupCode);
    
}
