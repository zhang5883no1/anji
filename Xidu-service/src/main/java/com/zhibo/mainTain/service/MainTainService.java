package com.zhibo.mainTain.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhibo.mainTain.domain.CustomerBasicInfo;
import com.zhibo.mainTain.domain.KCBExcle;
import com.zhibo.mainTain.domain.QQFenPei;
import com.zhibo.mainTain.dto.CustomerBasicInfoDto;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.domain.Function;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.RoleFunction;
import com.xidu.framework.usermgnt.domain.UserGroup;
import com.xidu.framework.usermgnt.domain.UserRole;
import com.xidu.framework.usermgnt.dto.QueryRoleDto;
import com.xidu.framework.usermgnt.dto.QueryUserInfoDto;
import com.xidu.framework.usermgnt.dto.RoleDto;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.UserDto;
import com.zhibo.mainTain.domain.RobotBasicInfo;
import com.zhibo.mainTain.dto.KCBBasicInfoDto;
import com.zhibo.mainTain.dto.QQBasicInfoDto;
import com.zhibo.mainTain.dto.QueryCustomerBasicInfoDto;
import com.zhibo.mainTain.dto.QueryKCBDto;
import com.zhibo.mainTain.dto.QueryQQBasicInfoDto;
import com.zhibo.mainTain.dto.QueryRobotrBasicInfoDto;
import com.zhibo.mainTain.dto.RobotBasicInfoDto;

public interface MainTainService {

	//CustomerBasic Info
	BasePagerDto queryCustomerBasicInfo(QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto);

	void deleteCustom(String customId,long userId) throws AppException;

	CustomerBasicInfoDto getCustomInfoById(String customId);
	CustomerBasicInfo findById(Long customId);

	CustomerBasicInfo saveCustomer(CustomerBasicInfoDto customerDto,long userId);
	
	List<CustomerBasicInfo> fetchAllCustomerBasicInfo();

	//Role 
	BasePagerDto queryRole(QueryRoleDto<Role> queryDto);

	RoleDto getRoleById(String id);
	
	void deleteRole(String id,long userId)throws AppException;
	
	//Role Function
	List<RoleFunction> queryRoleWithFunction(Long id);
	
	Role saveRole(RoleDto roleDto,long userId);
	

	//Menu Function
	List<MenuFunction> getMenuFunctionByFunctionId(List<RoleFunction> roleFunctionList);
	
	List<MenuFunction> getMenuFunctionByMenuId(List<Menu> menuList);
	
	String[] getMenuFunctionByMenuIds(String[] ids);

	//Menu
	List<Menu> getMenuById(List<MenuFunction> menuFunctionList);

	void saveRoleFunction(List<Function> list, Role role,long userId);
	
	boolean validmenu(HttpServletRequest request);

	//Function
	List<Function> getFunctionById(String[] ids);

	//User
	BasePagerDto queryUser(QueryUserInfoDto<BaseUser> queryDto);

	UserDto getUserById(String id);

	BaseUser saveUser(UserDto userDto,long userId);

	void deleteUser(String id,long userId) throws AppException;
	
	//User Role
	void saveUserRole(BaseUser user, RoleDto role,long userId);
	
	List<UserRole> getUserRoleByUserId(String id);
	
	void deleteUserRole(Long id) throws AppException ;

	String getUserByUserCode(String parameter);

	String  updatePwd(String loginId, String digestedPwdString)throws NoSuchAlgorithmException ;

	String updateCustomer(String id,String name, String value);

	BasePagerDto queryRobotBasicInfo(QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto);

	void deleteRobot(String id, long userId);

	RobotBasicInfoDto getRobotById(String id);

	RobotBasicInfo saveRobot(RobotBasicInfoDto robotDto, long userId);

	String updateRobot(String parameter, String parameter2, String parameter3);

	List<UserGroup> getAllUserGroup();

	List<CustomerBasicInfo> getAllAdminCustomer();

	List<Role> getallRole();


		BasePagerDto queryQQBasicInfo(QueryQQBasicInfoDto<QQFenPei> querydto);
		
		QQFenPei saveQQ(QQBasicInfoDto qqbfDto, long userId);
		BasePagerDto queryAdminBasicInfo(
				QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto);

		QQBasicInfoDto getQQInfoById(String QQId);
		
		void deleteQQ(String qqId,long userId) throws AppException;

		//query kechengbiao

		
		BasePagerDto queryKCBInfo(QueryKCBDto<KCBExcle> querykcb);
		
		KCBExcle saveKCB(KCBBasicInfoDto kcbinfodto, long userId);

}
