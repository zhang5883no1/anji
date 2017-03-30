/************************************************************************************
 * @File name   :      UserManagementServiceImpl.java
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
 * 2011-3-22 下午01:49:09            Eric Zhang          1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidu.framework.common.constant.CommonErrorConstants;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.impl.BaseServiceImpl;
import com.xidu.framework.usermgnt.constant.UserMgntConstants;
import com.xidu.framework.usermgnt.dao.IFunctionDao;
import com.xidu.framework.usermgnt.dao.IFunctionGroupDao;
import com.xidu.framework.usermgnt.dao.IGroupRoomDao;
import com.xidu.framework.usermgnt.dao.IRoleDao;
import com.xidu.framework.usermgnt.dao.IRoleFunctionDao;
import com.xidu.framework.usermgnt.dao.IRoleFunctionGroupDao;
import com.xidu.framework.usermgnt.dao.IRoomDao;
import com.xidu.framework.usermgnt.dao.IUserDao;
import com.xidu.framework.usermgnt.dao.IUserGroupDao;
import com.xidu.framework.usermgnt.dao.IUserGroupRoleDao;
import com.xidu.framework.usermgnt.dao.IUserRoleDao;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.domain.Function;
import com.xidu.framework.usermgnt.domain.FunctionGroup;
import com.xidu.framework.usermgnt.domain.GroupRoom;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.RoleFunction;
import com.xidu.framework.usermgnt.domain.RoleFunctionGroup;
import com.xidu.framework.usermgnt.domain.Room;
import com.xidu.framework.usermgnt.domain.UserGroup;
import com.xidu.framework.usermgnt.domain.UserGroupRole;
import com.xidu.framework.usermgnt.domain.UserRole;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.UserDto;
import com.xidu.framework.usermgnt.service.IUserMgntService;



/**
 * Implement IUserManagementService
 */
@Transactional(rollbackFor = AppException.class)
@Service
public class UserMgntServiceImpl extends BaseServiceImpl implements IUserMgntService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Resource(name = "userGroupDao")
    private IUserGroupDao userGroupDao;

    @Resource(name = "roleDao")
    private IRoleDao roleDao;

    @Resource(name = "functionDao")
    private IFunctionDao functionDao;

    @Resource(name = "functionGroupDao")
    private IFunctionGroupDao functionGroupDao;

    @Resource(name = "roleFunctionDao")
    private IRoleFunctionDao roleFunctionDao;

    @Resource(name = "roleFunctionGroupDao")
    private IRoleFunctionGroupDao roleFunctionGroupDao;

    @Resource(name = "userRoleDao")
    private IUserRoleDao userRoleDao;

    @Resource(name = "userGroupRoleDao")
    private IUserGroupRoleDao userGroupRoleDao;
    
    @Resource(name = "groupRoomDao")
    private IGroupRoomDao groupRoomDao;
    
    @Resource(name = "roomDao")
    private IRoomDao roomDao;

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#checkFuncAccess(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean checkFuncAccess(String userCode, String functionCode) throws AppException {
        try {
            if (functionCode == null || functionCode.length() < 1) {
                return false;
            }

            List<Role> roleList = this.getRolesByUserCode(userCode);
            List<Function> funcList = new ArrayList<Function>();
            for (Role role : roleList) {
                funcList.addAll(getFunctionsByRoleCode(role.getRoleCode()));
            }
            for (Function function : funcList) {
                if (functionCode.equals(function.getFunctionCode())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#createFunction(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean createFunction(String functionCode, String functionName, String functionDesc,
        String functionGroupCode, long operatorId) throws AppException {
        try {
            // Cannot create duplicated function with same code
            if (functionDao.getFunctionByCode(functionCode) != null) {
                throw new AppException(UserMgntConstants.DUPLICATE_FUNCTION_CODE,
                    "Cannot create duplicated function with same code");
            }
            Function function = new Function();
            function.setFunctionCode(functionCode);
            function.setFunctionName(functionName);
            function.setFunctionDesc(functionDesc);
            function.setCreateBy(operatorId);
            function.setLastUpdateBy(operatorId);
            FunctionGroup group = functionGroupDao.getFunctionGroupByCode(functionGroupCode);
            function.setFunctionGroup(group);
            functionDao.save(function);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#createFunctionGroup(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public boolean createFunctionGroup(String funcGrpCode, String funcGrpName, String funcGrpDesc,
        long operatorId) throws AppException {
        try {
            // Cannot create duplicated function group with same code
            if (functionGroupDao.getFunctionGroupByCode(funcGrpCode) != null) {
                throw new AppException(UserMgntConstants.DUPLICATE_FUNCTION_GROUP_CODE,
                    "Cannot create duplicated function group with same code");
            }
            FunctionGroup functionGroup = new FunctionGroup();
            functionGroup.setFuncGrpCode(funcGrpCode);
            functionGroup.setFuncGrpName(funcGrpName);
            functionGroup.setFuncGroupDesc(funcGrpDesc);
            functionGroup.setCreateBy(operatorId);
            functionGroup.setLastUpdateBy(operatorId);
            functionGroupDao.save(functionGroup);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#createRole(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String[],
     *      java.lang.String[])
     */
    @Override
    public boolean createRole(String roleCode, String roleName, String roleDesc,
        String[] functionCodes, String[] functionGroupCodes, long operatorId) throws AppException {
        try {
            // Cannot create duplicated role with same code
            if (roleDao.getRoleByCode(roleCode) != null) {
                throw new AppException(UserMgntConstants.DUPLICATE_ROLE_CODE,
                    "Cannot create duplicated role with same code");
            }
            Role role = new Role();
            role.setRoleCode("roleCode");
            role.setRoleName(roleName);
            role.setRoleDesc(roleDesc);
            role.setCreateBy(operatorId);
            role.setLastUpdateBy(operatorId);
            role = roleDao.save(role);
            role.setRoleCode("R" + role.getId());
            roleDao.update(role);
            if (functionCodes == null) {
                functionCodes = new String[0];
            }
            for (String functionCode : functionCodes) {
                Function function = functionDao.getFunctionByCode(functionCode);
                if (function != null) {

                    RoleFunction roleFunction = new RoleFunction();
                    roleFunction.setRole(role);
                    roleFunction.setFunction(function);
                    roleFunction.setCreateBy(operatorId);
                    roleFunction.setLastUpdateBy(operatorId);
                    roleFunctionDao.save(roleFunction);
                }
            }

            if (functionGroupCodes == null) {
                functionGroupCodes = new String[0];
            }
            for (String functionGroupCode : functionGroupCodes) {
                FunctionGroup functionGroup = functionGroupDao
                    .getFunctionGroupByCode(functionGroupCode);
                if (functionGroup != null) {
                    RoleFunctionGroup roleFuncGrp = new RoleFunctionGroup();
                    roleFuncGrp.setRole(role);
                    roleFuncGrp.setFunctionGroup(functionGroup);
                    roleFuncGrp.setCreateBy(operatorId);
                    roleFuncGrp.setLastUpdateBy(operatorId);
                    roleFunctionGroupDao.save(roleFuncGrp);
                }
            }
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#createUser(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String, int,
     *      java.lang.String[])
     */
    @Override
    public boolean createUser(String userCode, String password, String userName,
        String userGroupCode, int userType, String employerOwnerId, String[] roleCodes,
        long operatorId) throws AppException {
        try {
            // Cannot create duplicated user with same Id
            if (getUserByUserCode(userCode) != null) {
                throw new AppException(UserMgntConstants.DUPLICATE_USER_CODE,
                    "Cannot create duplicated user with same Id");
            }
            BaseUser user = new BaseUser();
            user.setUserCode(userCode);
            user.setPassword(password);
            user.setName(userName);
            user.setStatusCode(0);
            user.setUserType(userType);
            user.setEmployerOwnerId(employerOwnerId);

            UserGroup group = userGroupDao.getUserGroupByCode(userGroupCode);
            user.setUserGroup(group);
            user.setCreateBy(operatorId);
            user.setLastUpdateBy(operatorId);

            userDao.save(user);

            if (roleCodes == null) {
                roleCodes = new String[0];
            }
            for (String roleCode : roleCodes) {
                Role role = roleDao.getRoleByCode(roleCode);
                if (role != null) {
                    UserRole userRole = new UserRole();
                    userRole.setUser(user);
                    userRole.setRole(role);
                    userRole.setCreateBy(operatorId);
                    userRole.setLastUpdateBy(operatorId);
                    userRoleDao.save(userRole);
                }
            }
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#createUserGroup(java.lang.String,
     *      java.lang.String, java.lang.String[])
     */
    @Override
    public boolean createUserGroup(String userGroupCode, String userGroupName, String[] roleCodes,
        long operatorId) throws AppException {
        try {
            // Cannot create duplicated user group with same code
            if (userGroupDao.getUserGroupByCode(userGroupCode) != null) {
                throw new AppException(UserMgntConstants.DUPLICATE_USER_GROUP_CODE,
                    "Cannot create duplicated user group with same code");
            }
            UserGroup userGroup = new UserGroup();
            userGroup.setUserGroupCode(userGroupCode);
            userGroup.setUserGroupName(userGroupName);
            userGroup.setCreateBy(operatorId);
            userGroup.setLastUpdateBy(operatorId);
            userGroupDao.save(userGroup);
            if (roleCodes == null) {
                roleCodes = new String[0];
            }
            for (String roleCode : roleCodes) {
                Role role = roleDao.getRoleByCode(roleCode);
                if (role != null) {
                    UserGroupRole ugr = new UserGroupRole();
                    ugr.setRole(role);
                    ugr.setUserGroup(userGroup);
                    ugr.setCreateBy(operatorId);
                    ugr.setLastUpdateBy(operatorId);
                    userGroupRoleDao.save(ugr);
                }
            }
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#deleteFunction(java.lang.String)
     */
    @Override
    public boolean deleteFunction(String functionCode) throws AppException {
        try {
            Function function = functionDao.getFunctionByCode(functionCode);
            if (function == null) {
                throw new AppException(UserMgntConstants.FUNCTION_NOT_FOUND,
                    "Function not found by: " + functionCode);
            }
            roleFunctionDao.deleteRoleFunctionsByFunctionCode(functionCode);
            functionDao.remove(function);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#deleteFunctionGroup(java.lang.String)
     */
    @Override
    public boolean deleteFunctionGroup(String funcGrpCode) throws AppException {
        try {
            FunctionGroup functionGroup = functionGroupDao.getFunctionGroupByCode(funcGrpCode);
            if (functionGroup == null) {
                throw new AppException(UserMgntConstants.FUNCTION_GROUP_NOT_FOUND,
                    "Function group not found by: " + funcGrpCode);
            }
            List<Function> funcs = functionDao.getFunctionsByFuncGrpCode(funcGrpCode);
            if (funcs != null && funcs.size() > 0) {
                throw new AppException(UserMgntConstants.FUNCTION_GROUP_NOT_EMPTY,
                    "Function group is not empty!");
            }
            roleFunctionGroupDao.deleteRoleFunctionGroupsByFuncGrpCode(funcGrpCode);
            functionGroupDao.remove(functionGroup);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#deleteRole(java.lang.String)
     */
    @Override
    public boolean deleteRole(String roleCode) throws AppException {
        try {
            Role role = roleDao.getRoleByCode(roleCode);
            if (role == null) {
                throw new AppException(UserMgntConstants.ROLE_NOT_FOUND, ""
                    + "Role is not found by " + roleCode);
            }

            userRoleDao.deleteUserRolesByRoleCode(roleCode);
            userGroupRoleDao.deleteUserGroupRoleByRoleCode(roleCode);
            roleFunctionGroupDao.deleteRoleFunctionGroupsByRoleCode(roleCode);
            roleFunctionDao.deleteRoleFunctionsByRoleCode(roleCode);
            roleDao.remove(role);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#deleteUser(java.lang.String)
     */
    @Override
    public boolean deleteUser(String userCode) throws AppException {
        try {
            BaseUser user = getUserByUserCode(userCode);
            if (user == null) {
                throw new AppException(UserMgntConstants.USER_NOT_FOUND, "User is not found by "
                    + userCode);
            }

            userRoleDao.deleteUserRolesByUserCode(userCode);
            userDao.remove(user);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#deleteUserGroup(java.lang.String)
     */
    @Override
    public boolean deleteUserGroup(String userGroupCode) throws AppException {
        try {
            UserGroup userGroup = userGroupDao.getUserGroupByCode(userGroupCode);
            if (userGroup == null) {
                throw new AppException(UserMgntConstants.USER_GROUP_NOT_FOUND,
                    "User group is not found by " + userGroupCode);
            }
            List<BaseUser> users = userDao.getUsersByUserGroup(userGroupCode);
            if (users != null && users.size() > 0) {
                return false;
            }

            userGroupRoleDao.deleteUserGroupRoleByUsrGrpCode(userGroupCode);
            userGroupDao.remove(userGroup);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getAllFunctionGroups()
     */
    @Override
    public List<FunctionGroup> getAllFunctionGroups() throws AppException {
        try {
            return functionGroupDao.getAllFunctionGroups();
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getAllFunctions()
     */
    @Override
    public List<Function> getAllFunctions() throws AppException {
        try {
            return functionDao.getAllFunctions();
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getAllRoles()
     */
    @Override
    public List<Role> getAllRoles() throws AppException {
        try {
            return roleDao.getAllRoles();
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getAllUserGroups()
     */
    @Override
    public List<UserGroup> getAllUserGroups() throws AppException {
        try {
            return userGroupDao.getAllUserGroups();
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getFunctionsByFuncGroup(java.lang.String)
     */
    @Override
    public List<Function> getFunctionsByFuncGroup(String funcGrpCode) throws AppException {
        try {
            return functionDao.getFunctionsByFuncGrpCode(funcGrpCode);
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getFunctionsByUserId(java.lang.String)
     */
    @Override
    public List<Function> getFunctionsByUserCode(String userCode) throws AppException {
        try {
            List<Role> roleList = getRolesByUserCode(userCode);
            List<Function> functions = new ArrayList<Function>();
            for (Role role : roleList) {
                List<Function> subFunctions = getFunctionsByRoleCode(role.getRoleCode());
                functions.addAll(subFunctions);
            }
            return functions;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getRolesByUserId(java.lang.String)
     */
    @Override
    public List<Role> getRolesByUserCode(String userCode) throws AppException {
        try {
            return roleDao.getRolesByUserCode(userCode);
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getUserByName(java.lang.String)
     */
    @Override
    public List<BaseUser> getUserByName(String name) throws AppException {
        try {
            List<BaseUser> userList = userDao.getUserByName(name);
            return userList;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getUserByUserId(java.lang.String)
     */
    @Override
    public BaseUser getUserByUserCode(String userCode) throws AppException {
        try {
            return userDao.getUserByUserCode(userCode);
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#updateFunction(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean updateFunction(String functionCode, String functionName, String functionDesc,
        String functionGroupCode, long operatorId) throws AppException {
        try {
            Function function = functionDao.getFunctionByCode(functionCode);
            if (function == null) {
                throw new AppException(UserMgntConstants.FUNCTION_NOT_FOUND,
                    "Function is not found by code: " + functionCode);
            }
            function.setFunctionName(functionName);
            function.setFunctionDesc(functionDesc);
            FunctionGroup group = functionGroupDao.getFunctionGroupByCode(functionGroupCode);
            function.setFunctionGroup(group);
            function.setLastUpdateBy(operatorId);
            functionDao.update(function);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#updateFunctionGroup(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public boolean updateFunctionGroup(String funcGrpCode, String funcGrpName, String funcGrpDesc,
        long operatorId) throws AppException {
        try {
            FunctionGroup functionGroup = functionGroupDao.getFunctionGroupByCode(funcGrpCode);
            if (functionGroup == null) {
                throw new AppException(UserMgntConstants.FUNCTION_GROUP_NOT_FOUND,
                    "Function group is not found: " + funcGrpCode);
            }

            functionGroup.setFuncGrpName(funcGrpName);
            functionGroup.setFuncGroupDesc(funcGrpDesc);
            functionGroup.setLastUpdateBy(operatorId);
            functionGroupDao.update(functionGroup);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#updatePassword(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public boolean updatePassword(String userCode, String password, long operatorId)
        throws AppException {
        try {
            BaseUser user = getUserByUserCode(userCode);
            if (user == null) {
                throw new AppException(UserMgntConstants.UPDATE_PASSWORD_FAILURE,
                    "Failed to update password because user is not found");
            }
            user.setPassword(password);
            user.setLastUpdateBy(operatorId);
            userDao.update(user);
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }

    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#updateRole(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String[],
     *      java.lang.String[])
     */
    @Override
    public boolean updateRole(String roleCode, String roleName, String roleDesc,
        String[] functionCodes, String[] functionGroupCodes, long operatorId) throws AppException {
        try {
            Role role = roleDao.getRoleByCode(roleCode);
            if (role == null) {
                throw new AppException(UserMgntConstants.ROLE_NOT_FOUND,
                    "Failed to update password because role is not found");
            }
            role.setRoleName(roleName);
            role.setRoleDesc(roleDesc);
            role.setLastUpdateBy(operatorId);
            roleDao.update(role);

            roleFunctionDao.deleteRoleFunctionsByRoleCode(roleCode);
            if (functionCodes == null) {
                functionCodes = new String[0];
            }
            for (String functionCode : functionCodes) {
                Function function = functionDao.getFunctionByCode(functionCode);
                if (function != null) {
                    RoleFunction rf = new RoleFunction();
                    rf.setFunction(function);
                    rf.setRole(role);
                    rf.setCreateBy(operatorId);
                    rf.setLastUpdateBy(operatorId);
                    roleFunctionDao.save(rf);
                }
            }

            roleFunctionGroupDao.deleteRoleFunctionGroupsByRoleCode(roleCode);
            if (functionGroupCodes == null) {
                functionGroupCodes = new String[0];
            }
            for (String functionGroupCode : functionGroupCodes) {
                FunctionGroup functionGroup = functionGroupDao
                    .getFunctionGroupByCode(functionGroupCode);
                if (functionGroup != null) {
                    RoleFunctionGroup rfg = new RoleFunctionGroup();
                    rfg.setRole(role);
                    rfg.setFunctionGroup(functionGroup);
                    rfg.setCreateBy(operatorId);
                    rfg.setLastUpdateBy(operatorId);
                    roleFunctionGroupDao.save(rfg);
                }
            }
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#updateUser(java.lang.String,
     *      java.lang.String, int, java.lang.String, int, java.lang.String[])
     */
    @Override
    public boolean updateUser(String userCode, String userName, int statusCode,
        String userGroupCode, int userType, String employerOwnerId, String[] roleCodes,
        long operatorId) throws AppException {
        try {
            BaseUser user = getUserByUserCode(userCode);
            if (user == null) {
                throw new AppException(UserMgntConstants.USER_NOT_FOUND,
                    "Failed to update password because user is not found");
            }
            user.setUserCode(userCode);
            user.setName(userName);
            user.setStatusCode(statusCode);
            user.setLastUpdateBy(operatorId);
            user.setUserType(userType);
            user.setEmployerOwnerId(employerOwnerId);
            UserGroup group = userGroupDao.getUserGroupByCode(userGroupCode);
            user.setUserGroup(group);
            userDao.update(user);

            userRoleDao.deleteUserRolesByUserCode(userCode);
            if (roleCodes == null) {
                roleCodes = new String[0];
            }
            for (String roleCode : roleCodes) {
                Role role = roleDao.getRoleByCode(roleCode);
                if (role != null) {
                    UserRole ur = new UserRole();
                    ur.setUser(user);
                    ur.setRole(role);
                    ur.setCreateBy(operatorId);
                    ur.setLastUpdateBy(operatorId);
                    userRoleDao.save(ur);
                }
            }
            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#updateUserGroup(java.lang.String,
     *      java.lang.String, java.lang.String[])
     */
    @Override
    public boolean updateUserGroup(String userGroupCode, String userGroupName, String[] roleCodes,
        long operatorId) throws AppException {
        try {
            UserGroup userGroup = userGroupDao.getUserGroupByCode(userGroupCode);
            if (userGroup == null) {
                throw new AppException(UserMgntConstants.USER_GROUP_NOT_FOUND,
                    "Failed to update password because user group is not found");
            }

            userGroup.setUserGroupCode(userGroupCode);
            userGroup.setUserGroupName(userGroupName);
            userGroup.setLastUpdateBy(operatorId);
            userGroupDao.update(userGroup);
            userGroupRoleDao.deleteUserGroupRoleByUsrGrpCode(userGroupCode);
            if (roleCodes == null) {
                roleCodes = new String[0];
            }
            for (String roleCode : roleCodes) {
                Role role = roleDao.getRoleByCode(roleCode);
                if (role != null) {
                    UserGroupRole ugr = new UserGroupRole();
                    ugr.setUserGroup(userGroup);
                    ugr.setRole(role);
                    ugr.setCreateBy(operatorId);
                    ugr.setLastUpdateBy(operatorId);
                    userGroupRoleDao.save(ugr);
                }
            }

            return true;
        } catch (AppException ae) {
            throw ae;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see com.trinasolar.framework.usermanagement.service.IUserMgntService#getFunctionsByRoleCode(java.lang.String)
     */
    @Override
    public List<Function> getFunctionsByRoleCode(String roleCode) throws AppException {
        List<Function> funcList = new ArrayList<Function>();
        try {
            // Find functions under the function group
            List<FunctionGroup> funcGroupList = functionGroupDao
                .getFunctionGroupsByRoleCode(roleCode);
            for (FunctionGroup funcGroup : funcGroupList) {
                funcList.addAll(functionDao.getFunctionsByFuncGrpCode(funcGroup.getFuncGrpCode()));
            }

            List<Function> funcsInRole = functionDao.getFunctionsByRoleCode(roleCode);
            for (Function function : funcsInRole) {
                if (!funcList.contains(function)) {
                    funcList.add(function);
                }
            }

            return funcList;
        } catch (Exception e) {
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getLocalizedMessage());
        }
    }


    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.usermgnt.service.IUserMgntService#getAllUsersByOrgCode(java.lang.String)
     **/
    @Override
    public List<BaseUser> getAllUsersByOrgCode(String orgCode) throws AppException {
        getLogger().info("parameter: [orgCode=" + orgCode + "]");
        try {
            return userDao.getAllUsersByOrgCode(orgCode);
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR,
                CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX + e.getMessage());
        }
    }


    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.usermgnt.service.IUserMgntService#getManagerByOrgCode(java.lang.String)
     **/
    @Override
    public List<BaseUser> getManagerByOrgCode(String orgCode) throws AppException {
        getLogger().info("parameter: [orgCode=" + orgCode + "]");

        try {
            return userDao.getManagerUserByOrgCode(orgCode);
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR,
                CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX + e.getMessage());
        }
    }


    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.usermgnt.service.IUserMgntService#getUsersByOrgCode(java.lang.String)
     **/
    @Override
    public List<BaseUser> getUsersByOrgCode(String orgCode) throws AppException {
        getLogger().info("parameter: [orgCode=" + orgCode + "]");
        try {
            return userDao.getUsersByOrgCode(orgCode);
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR,
                CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX + e.getMessage());
        }
    }

    /**
     * 
     * @Date : 2011-6-30
     * @param roleCode
     *            角色CODE
     * @return
     */
    public Role getRoleByRoleCode(String roleCode) {

        return roleDao.getRoleByCode(roleCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-6
     * @see com.xidu.framework.usermgnt.service.IUserMgntService#isManagerUser(java.lang.String,
     *      java.lang.String)
     **/
    @Override
    public boolean isManagerUser(String userCode, String orgCode) throws AppException {
        getLogger().info("parameter: [userCode=" + userCode + ", orgCode=" + orgCode + "]");
        if (StringUtils.isBlank(userCode)) {
            throw new AppException(CommonErrorConstants.COMMON_METHOD_PARAM_ERROR,
                "parameter is illegal.");
        }
        try {
            List<BaseUser> users = userDao.getManagerUserByOrgCode(orgCode);
            if (null != users) {
                for (BaseUser user : users) {
                    if (userCode.equals(user.getUserCode())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR,
                CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX + e.getMessage());
        }
    }


    /**
     * 
     * @Date : 2011-6-30
     * @param userDto
     * @return
     */
    public List<BaseUser> getUsersByDto(UserDto userDto) {
        return userDao.getUsersByDto(userDto);
    }


	@Override
	public void updateUser(BaseUser actualUser) {
		// TODO Auto-generated method stub
		userDao.update(actualUser);
	}

	@Override
	public List<BaseUser> getUsersByParentId(Long id) {
		// TODO Auto-generated method stub
		UserDto userDto=new UserDto();
		userDto.setParentId(id);
		 return userDao.getUsersByDto(userDto);
	}

	@Override
	public List<BaseUser> getUserListByGroupCode(String groupCode) {
		// TODO Auto-generated method stub
		 List<BaseUser> users = userDao.getUsersByUserGroup(groupCode);
		 return users;
	}

	@Override
	public List<RoomDto> getRoomsByGroupId(String groupId) {
		// TODO Auto-generated method stub
		List<GroupRoom> groupRoom=groupRoomDao.getRoomList("("+groupId+")");
		List<Room> rooms=new ArrayList<Room>();
		String roomids="0";
		for(GroupRoom r:groupRoom){
			roomids+=","+r.getRoom();
		}
		if(!roomids.equals("0")){
			rooms=roomDao.getRoomList("("+roomids+")");
		}
		List<RoomDto> roomdtolist=new ArrayList<RoomDto>();
		for(Room room:rooms){
			List<BaseUser> users=userDao.findByRoomId(room.getId());
			RoomDto dto=new RoomDto();
			dto.setId(room.getId());
			dto.setRoomCode(room.getRoomCode());
			dto.setRoomName(room.getRoomName());
			dto.setUserList(users);
			roomdtolist.add(dto);
		}
		return roomdtolist;
	}


}
