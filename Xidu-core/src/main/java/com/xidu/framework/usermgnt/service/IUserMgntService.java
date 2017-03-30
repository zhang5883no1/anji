/************************************************************************************
 * @File name   :      IUserManagementService.java
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
 * 2011-3-21 上午09:29:24            Eric Zhang          1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.service;

import java.util.List;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.IBaseService;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.domain.Function;
import com.xidu.framework.usermgnt.domain.FunctionGroup;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.UserGroup;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.UserDto;

/**
 * Interface for all the user,role and function management
 */
public interface IUserMgntService extends IBaseService {
    /**
     * Create an user
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userId of the user
     * @param password
     *            password of the user
     * @param userName
     *            userName of the user
     * @param userGroupCode
     *            user group of the user
     * @param userType
     *            user type
     * @param employerOwnerId
     *              owner Id           
     * @param roleCodes
     *            roles of the user
     * @param operatorId
     *            operator id
     * @return create successfully or not
     * @throws AppException
     *             the AppException
     */
    public boolean createUser(String userCode, String password, String userName,
        String userGroupCode, int userType, String employerOwnerId, 
        String[] roleCodes, long operatorId) throws AppException;

    /**
     * Update an user by userId
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userCode of the user
     * @param userName
     *            userName of the user
     * @param statusCode
     *            statusCode of the user
     * @param userGroupCode
     *            user group of the user
     * @param userType
     *            user type of the user
     * @param employerOwnerId
     *            employerOwnerId      
     * @param roleCodes
     *            roles of the user
     * @param operatorId
     *            operator id
     * @return update successfully or not
     * @throws AppException
     *             the AppException
     */
    public boolean updateUser(String userCode, String userName, int statusCode,
        String userGroupCode, int userType, String employerOwnerId, 
        String[] roleCodes, long operatorId) throws AppException;

    /**
     * Delete the user by userCode
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userCode of the user
     * @return delete successfully or not
     * @throws AppException
     *             the AppException
     */
    public boolean deleteUser(String userCode) throws AppException;

    /**
     * Get the user by name
     * 
     * @Date : 2011-3-21
     * @param name
     *            name of the user
     * @return A list of users
     * @throws AppException
     *             the AppException
     */
    public List<BaseUser> getUserByName(String name) throws AppException;

    /**
     * Get the user by userId
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userCode of the user
     * @return the user with the userCode
     * @throws AppException
     *             the AppException
     */
    public BaseUser getUserByUserCode(String userCode) throws AppException;

    /**
     * Update the password
     * 
     * @Date : 2011-3-22
     * @param userCode
     *            userId of the user
     * @param password
     *            password of the user
     * @param operatorId
     *            operator id
     * @return true if password is updated
     * @throws AppException
     *             the AppException
     */
    public boolean updatePassword(String userCode, String password, long operatorId)
        throws AppException;

    /**
     * Get roles by user Id
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userId of the user
     * @return A list of roles
     * @throws AppException
     *             the AppException
     */
    public List<Role> getRolesByUserCode(String userCode) throws AppException;

    /**
     * Get functions by user Id
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userId of the user
     * @return A list of functions
     * @throws AppException
     *             the AppException
     */
    public List<Function> getFunctionsByUserCode(String userCode) throws AppException;

    /**
     * Check the function access of the user
     * 
     * @Date : 2011-3-21
     * @param userCode
     *            userId of the user
     * @param functionCode
     *            function code of the function
     * @return boolean if user can access the function
     * @throws AppException
     *             the AppException
     */
    public boolean checkFuncAccess(String userCode, String functionCode) throws AppException;

    /**
     * Create a role
     * 
     * @Date : 2011-3-21
     * @param roleCode
     *            role code of the role
     * @param roleName
     *            role name of the role
     * @param roleDesc
     *            description of the role
     * @param functionCodes
     *            functions of the role
     * @param functionGroupCodes
     *            function groups of the role
     * @param operatorId
     *            operator id
     * @return boolean if role is created
     * @throws AppException
     *             the AppException
     */
    public boolean createRole(String roleCode, String roleName, String roleDesc,
        String[] functionCodes, String[] functionGroupCodes, long operatorId) throws AppException;

    /**
     * Update the role by role code
     * 
     * @Date : 2011-3-21
     * @param roleCode
     *            role code of the role
     * @param roleName
     *            role name of the role
     * @param roleDesc
     *            description of the role
     * @param functionCodes
     *            functions of the role
     * @param functionGroupCodes
     *            function groups of the role
     * @param operatorId
     *            operator id
     * @return boolean if role is updated
     * @throws AppException
     *             the AppException
     */
    public boolean updateRole(String roleCode, String roleName, String roleDesc,
        String[] functionCodes, String[] functionGroupCodes, long operatorId) throws AppException;

    /**
     * Delete role by the role code
     * 
     * @Date : 2011-3-21
     * @param roleCode
     *            role code of the role
     * @return boolean if role is deleted
     * @throws AppException
     *             the AppException
     */
    public boolean deleteRole(String roleCode) throws AppException;

    /**
     * Get all the roles
     * 
     * @Date : 2011-3-21
     * @return list of all the roles
     * @throws AppException
     *             the AppException
     */
    public List<Role> getAllRoles() throws AppException;

    /**
     * Create a function
     * 
     * @Date : 2011-3-21
     * @param functionCode
     *            function code of the function
     * @param functionName
     *            function name of the function
     * @param functionDesc
     *            Description of the function
     * @param functionGroupCode
     *            function group of the function
     * @param operatorId
     *            operator id
     * @return boolean if the function is created
     * @throws AppException
     *             the AppException
     */
    public boolean createFunction(String functionCode, String functionName, String functionDesc,
        String functionGroupCode, long operatorId) throws AppException;

    /**
     * Update the function
     * 
     * @Date : 2011-3-21
     * @param functionCode
     *            function code of the function
     * @param functionName
     *            function name of the function
     * @param functionDesc
     *            Description of the function
     * @param functionGroupCode
     *            function group of the function
     * @param operatorId
     *            operator id
     * @return boolean if the function is updated
     * @throws AppException
     *             the AppException
     */
    public boolean updateFunction(String functionCode, String functionName, String functionDesc,
        String functionGroupCode, long operatorId) throws AppException;

    /**
     * Delete the function by function code
     * 
     * @Date : 2011-3-21
     * @param functionCode
     *            function code of the function
     * @return boolean if the function is deleted
     * @throws AppException
     *             the AppException
     */
    public boolean deleteFunction(String functionCode) throws AppException;

    /**
     * Get all functions
     * 
     * @Date : 2011-3-21
     * @return list of all the functions
     * @throws AppException
     *             the AppException
     */
    public List<Function> getAllFunctions() throws AppException;

    /**
     * Create an user group
     * 
     * @Date : 2011-3-21
     * @param userGroupCode
     *            code of the user group
     * @param userGroupName
     *            name of the user group
     * @param roleCodes
     *            roles of the user group
     * @param operatorId
     *            operator id
     * @return boolean if the user group is created
     * @throws AppException
     *             the AppException
     */
    public boolean createUserGroup(String userGroupCode, String userGroupName, String[] roleCodes,
        long operatorId) throws AppException;

    /**
     * Update the user group
     * 
     * @Date : 2011-3-21
     * @param userGroupCode
     *            code of the user group
     * @param userGroupName
     *            name of the user group
     * @param roleCodes
     *            roles of the user group
     * @param operatorId
     *            operator id
     * @return true if the user group is updated
     * @throws AppException
     *             the AppException
     */
    public boolean updateUserGroup(String userGroupCode, String userGroupName, String[] roleCodes,
        long operatorId) throws AppException;

    /**
     * Delete the user group by code
     * 
     * @Date : 2011-3-21
     * @param userGroupCode
     *            code of the user group
     * @return true if the user group is deleted
     * @throws AppException
     *             the AppException
     */
    public boolean deleteUserGroup(String userGroupCode) throws AppException;

    /**
     * Get all user groups
     * 
     * @Date : 2011-4-2
     * @return list of user group
     * @throws AppException
     *             the AppException
     */
    public List<UserGroup> getAllUserGroups() throws AppException;

    /**
     * Create a function group
     * 
     * @Date : 2011-3-21
     * @param funcGrpCode
     *            code of the function group
     * @param funcGrpName
     *            name of the function group
     * @param funcGrpDesc
     *            description of the function group
     * @param operatorId
     *            operator id
     * @return true if the function group is created
     * @throws AppException
     *             the AppException
     */
    public boolean createFunctionGroup(String funcGrpCode, String funcGrpName, String funcGrpDesc,
        long operatorId) throws AppException;

    /**
     * Update the function group
     * 
     * @Date : 2011-3-21
     * @param funcGrpCode
     *            code of the function group
     * @param funcGrpName
     *            name of the function group
     * @param funcGrpDesc
     *            description of the function group
     * @param operatorId
     *            operator id
     * @return true if the function group is updated
     * @throws AppException
     *             the AppException
     */
    public boolean updateFunctionGroup(String funcGrpCode, String funcGrpName, String funcGrpDesc,
        long operatorId) throws AppException;

    /**
     * Delete the function group by group code
     * 
     * @Date : 2011-3-21
     * @param funcGrpCode
     *            code of the function group
     * @return true if the function group is deleted
     * @throws AppException
     *             the AppException
     */
    public boolean deleteFunctionGroup(String funcGrpCode) throws AppException;

    /**
     * Get all the function groups
     * 
     * @Date : 2011-3-21
     * @return list of all the function groups
     * @throws AppException
     *             the AppException
     */
    public List<FunctionGroup> getAllFunctionGroups() throws AppException;

    /**
     * Get functions by function group code
     * 
     * @Date : 2011-3-21
     * @param funcGrpCode
     *            code of the function group
     * @return list of the functions under the function group
     * @throws AppException
     *             the AppException
     */
    public List<Function> getFunctionsByFuncGroup(String funcGrpCode) throws AppException;

    /**
     * Get functions by role code
     * 
     * @Date : 2011-3-22
     * @param roleCode
     *            code of the role
     * @return list of the functions under the role
     * @throws AppException
     *             the AppException
     */
    public List<Function> getFunctionsByRoleCode(String roleCode) throws AppException;


    /**
     * get user list by organization code
     * 
     * @Date : 2011-4-6
     * @param orgCode
     *            - organization code
     * @return - user list
     * @throws AppException
     *             - application exception
     */
    List<BaseUser> getUsersByOrgCode(String orgCode) throws AppException;


    /**
     * get all user including child organization's users by organization code
     * 
     * @Date : 2011-4-6
     * @param orgCode
     *            - organization code
     * @return - user list
     * @throws AppException
     *             - application exception
     */
    List<BaseUser> getAllUsersByOrgCode(String orgCode) throws AppException;

    /**
     * get manager of organization by organization code
     * 
     * @Date : 2011-4-6
     * @param orgCode
     *            - organization code
     * @return list of manager user
     * @throws AppException
     *             - application exception
     */
    List<BaseUser> getManagerByOrgCode(String orgCode) throws AppException;

    /**
     * check if user is a manager in organization
     * 
     * @Date : 2011-4-6
     * @param userCode
     *            - user code
     * @param orgCode
     *            - ogranization code
     * @return true if user is a manager; otherwise, false
     * @throws AppException
     *             - application exception
     */
    boolean isManagerUser(String userCode, String orgCode) throws AppException;

    
    /**
     * 
     * @Date        :      2011-6-30
     * @param roleCode 角色CODE
     * @return 
     */
    public Role getRoleByRoleCode(String roleCode);
    
    /**
     * 
     * @Date        :      2011-6-30
     * @param userDto
     * @return
     */
    List<BaseUser> getUsersByDto(UserDto userDto);
    
    /**
     * 
     * @Date        :      2011-6-30
     * @param user
     * @param roleCodes
     * @param operatorId
     * @return
     */
    

	void updateUser(BaseUser actualUser);
	
	List<BaseUser> getUsersByParentId(Long id);
	
	 List<BaseUser> getUserListByGroupCode(String groupCode);
	 
	 List<RoomDto> getRoomsByGroupId(String groupId);

}
