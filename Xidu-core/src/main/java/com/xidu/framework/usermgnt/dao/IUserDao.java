/************************************************************************************
 * @File name   :      IUserManagementDao.java
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
 * 2011-3-21 下午05:27:53            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.dto.QueryUserInfoDto;
import com.xidu.framework.usermgnt.dto.UserDto;

/**
 * Interface of UserDao
 */
public interface IUserDao extends IBaseDao<BaseUser, Long> {

    /**
     * Get the user by name
     * 
     * @Date : 2011-3-21
     * @param name
     *            name of the user
     * @return A list of users
     */
    public List<BaseUser> getUserByName(String name);

    /**
     * Get the user by userCode
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userCode of the user
     * @return the user with the userCode
     */
    public BaseUser getUserByUserCode(String userCode);

    /**
     * Get the users by user group
     * 
     * @Date : 2011-3-21
     * @param userGroup
     *            name of the userGroup
     * @return A list of users
     */
    public List<BaseUser> getUsersByUserGroup(String userGroup);

    /**
     * get user list by organization code
     * 
     * @Date : 2011-4-6
     * @param orgCode
     *            - organization code
     * @return user list
     */
    List<BaseUser> getUsersByOrgCode(String orgCode);

    /**
     * get user list including all child organizations' users by organization
     * code
     * 
     * @Date : 2011-4-6
     * @param orgCode
     *            - organization code
     * @return - user list
     */
    List<BaseUser> getAllUsersByOrgCode(String orgCode);

    /**
     * get user list by organization code and role code
     * 
     * @Date : 2011-4-6
     * @param orgCode - organization code 
     * @param roleCode - role code
     * @return user list
     */
    List<BaseUser> getUsersByOrgAndRoleCode(String orgCode, String roleCode);
    
    /**
     * get manager of organization with this organization code
     * @Date        :      2011-4-7
     * @param orgCode - organization code
     * @return list of manager user
     */
    List<BaseUser> getManagerUserByOrgCode(String orgCode);
    
    /**
     * 
     * @Date        :      2011-6-30
     * @param userDto
     * @return
     */
    List<BaseUser> getUsersByDto(UserDto userDto);

	BasePagerDto queryUser(QueryUserInfoDto<BaseUser> queryDto);

	public BaseUser findByUserCode(String usercode);

	public List<BaseUser> queryUserByWarehouse(
			QueryUserInfoDto<BaseUser> queryuserDto);

	public List<BaseUser> findByRoomId(Long id);



}
