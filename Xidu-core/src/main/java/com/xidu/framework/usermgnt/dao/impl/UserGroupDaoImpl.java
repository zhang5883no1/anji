/************************************************************************************
 * @File name   :      UserGroupDaoImpl.java
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
 * 2011-3-22 下午01:33:05            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IUserGroupDao;
import com.xidu.framework.usermgnt.domain.UserGroup;

/**
 * Implement IUserGroupDao
 */
@Repository("userGroupDao")
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup, Long> implements IUserGroupDao {

    /**
     * Constructor
     * @Date : 2011-3-22
     * @param clazz     UserGroup
     */
	@Autowired
    public UserGroupDaoImpl(
        @Value("com.xidu.framework.usermgnt.domain.UserGroup") 
        Class<UserGroup> clazz) {
        super(clazz);
    }

    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IUserGroupDao#getAllUserGroups()
     */
    @Override
    public List<UserGroup> getAllUserGroups() {
        List<UserGroup> userGroups = 
            getListByNativeQuery("select * from ts_fmwk_user_group ");
        return userGroups;
    }

    /**
     * 
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-28
     * @see com.xidu.framework.usermgnt.dao.IUserGroupDao#getUserGroupByCode(java.lang.String)
     */
    @Override
    public UserGroup getUserGroupByCode(String userGroupCode) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userGroupCode", userGroupCode);
        List<UserGroup> userGroups = getListByQueryWithMap(
            "from UserGroup d where d.userGroupCode = :userGroupCode", paraMap);
        if (userGroups != null && userGroups.size() > 0) {
            return userGroups.get(0);
        } else {
            return null;
        }  
    }

}
