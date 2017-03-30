package com.zhibo.mainTain.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.impl.BaseServiceImpl;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.menu.dao.IMenuDao;
import com.xidu.framework.menu.dao.IMenuFunctionDao;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dao.IFunctionDao;
import com.xidu.framework.usermgnt.dao.IRoleDao;
import com.xidu.framework.usermgnt.dao.IRoleFunctionDao;
import com.xidu.framework.usermgnt.dao.IUserDao;
import com.xidu.framework.usermgnt.dao.IUserGroupDao;
import com.xidu.framework.usermgnt.dao.IUserRoleDao;
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
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.xidu.framework.usermgnt.dto.UserDto;
import com.xidu.framework.usermgnt.util.MD5Util;
import com.zhibo.common.exception.ExceptionConstant;
import com.zhibo.mainTain.dao.CustomerBasicInfoDao;
import com.zhibo.mainTain.dao.KCBExcleDao;
import com.zhibo.mainTain.dao.QQFenPeiDao;
import com.zhibo.mainTain.dao.RobotBasicInfoDao;
import com.zhibo.mainTain.domain.CustomerBasicInfo;
import com.zhibo.mainTain.domain.KCBExcle;
import com.zhibo.mainTain.domain.QQFenPei;
import com.zhibo.mainTain.domain.RobotBasicInfo;
import com.zhibo.mainTain.dto.CustomerBasicInfoDto;
import com.zhibo.mainTain.dto.KCBBasicInfoDto;
import com.zhibo.mainTain.dto.QQBasicInfoDto;
import com.zhibo.mainTain.dto.QueryCustomerBasicInfoDto;
import com.zhibo.mainTain.dto.QueryKCBDto;
import com.zhibo.mainTain.dto.QueryQQBasicInfoDto;
import com.zhibo.mainTain.dto.QueryRobotrBasicInfoDto;
import com.zhibo.mainTain.dto.RobotBasicInfoDto;
import com.zhibo.mainTain.service.MainTainService;

@Transactional(rollbackFor = AppException.class)
@Service
public class MainTainSerivceImpl extends BaseServiceImpl implements
		MainTainService {

	@Autowired
	private CustomerBasicInfoDao customerBasicInfoDao;
	@Autowired
	private IRoleFunctionDao roleWithFunctiondao;
	@Autowired
	private IMenuFunctionDao menuWithFunctiondao;
	@Autowired
	private IMenuDao menudao;
	@Autowired
	private IFunctionDao functiondao;
	@Autowired
	private IRoleDao IroleDao;
	@Autowired
	private IUserDao IuserDao;
	@Autowired
	private IUserGroupDao userGroupDao;
	@Autowired
	private IUserRoleDao userroleDao;
	@Autowired
	private RobotBasicInfoDao robotBasicInfoDao;
	@Autowired
	private IUserGroupDao usergroupDao;
	@Autowired
	private QQFenPeiDao qqfenpeidao;
	@Autowired
	private KCBExcleDao kcbexcledao;

	@Override
	public BasePagerDto queryCustomerBasicInfo(
			QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto) {
		customerBasicInfoDao.queryCustomerBasicInfo(queryDto);
		return queryDto;
	}

	@Override
	public void deleteQQ(String qqId,long userId) throws AppException{
		QQFenPei entity = qqfenpeidao.findById(Long.parseLong(qqId));
		if(entity ==null){
			throw new AppException(
					ExceptionConstant.ERROR_BASEINFO_SUPPLER_ENTITY_NOTEXIST,
					"QQ is not exist.");
		}
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setDeleteFlag(1L);
		qqfenpeidao.update(entity);
	}
	
	@Override
	public void deleteCustom(String customId, long userId) throws AppException {
		CustomerBasicInfo entity = customerBasicInfoDao.findById(Long
				.parseLong(customId));
		if (entity == null) {
			throw new AppException(
					ExceptionConstant.ERROR_BASEINFO_SUPPLER_ENTITY_NOTEXIST,
					"Customer is not exist.");
		}
		// customerBasicInfoDao.remove(entity);
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setDeleteFlag(1L);
		customerBasicInfoDao.update(entity);

	}

	@Override
	public CustomerBasicInfoDto getCustomInfoById(String customId) {
		CustomerBasicInfoDto dto = new CustomerBasicInfoDto();
		CustomerBasicInfo entity = customerBasicInfoDao.findById(Long
				.parseLong(customId));
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	@Override
	public CustomerBasicInfo saveCustomer(CustomerBasicInfoDto customerDto, long userId) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		CustomerBasicInfo entity = null;
		if (customerDto.getId() != null) {// edit
			entity = customerBasicInfoDao.findById(customerDto.getId());
		} else {// save
			entity = new CustomerBasicInfo();
			entity.setCreateBy(userId);
			entity.setCreateDate(new Date());
		}
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		if(entity.getPwd()!=null&&customerDto.getPwd()!=null&&entity.getPwd().equals(customerDto.getPwd())){
			
		}else{
			customerDto.setPwd(customerDto.getPwd());
//			customerDto.setPwd(MD5Util.MD5(customerDto.getPwd()));
		}
		BeanUtils.copyProperties(customerDto, entity);
		if (customerDto.getId() != null) {// edit
			customerBasicInfoDao.update(entity);
			return null;
		}else{
			return customerBasicInfoDao.save(entity);
		}
	}

	// Role
	@Override
	public BasePagerDto queryRole(QueryRoleDto<Role> queryDto) {
		IroleDao.queryRole(queryDto);
		return queryDto;
	}

	@Override
	public RoleDto getRoleById(String id) {
		RoleDto dto = new RoleDto();
		Role entity = IroleDao.findById(Long.valueOf(id));
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public Role saveRole(RoleDto dto, long userId) {
		Role entity = null;
		if (dto.getId() != null) {
			entity = IroleDao.findById(dto.getId());
			entity.setLastUpdateDate(new Date());
		} else {
			entity = new Role();
		}
		BeanUtils.copyProperties(dto, entity);
		entity.setCreateBy(userId);
		entity.setCreateDate(new Date());
		// entity.setId(1L);
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setRolegroup(String.valueOf(IuserDao.findById(userId)
				.getUserGroup().getId()));
		return IroleDao.save(entity);

	}

	@Override
	public void deleteRole(String id, long userId) throws AppException {
		Role entity = IroleDao.findById(Long.parseLong(id));
		if (entity == null) {
			throw new AppException(
					ExceptionConstant.ERROR_BASEINFO_SUPPLER_ENTITY_NOTEXIST,
					"role is not exist.");
		}
		// IroleDao.remove(entity);
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setDeleteFlag(1L);
		IroleDao.update(entity);
		roleWithFunctiondao.deleteRoleFunctionsByRole(entity);
	}

	// Role Function
	@Override
	public List<RoleFunction> queryRoleWithFunction(Long id) {
		List<RoleFunction> list = new ArrayList<RoleFunction>();
		list = roleWithFunctiondao.queryRoleFunctionByRoleId(id);
		return list;
	}

	@Override
	public void saveRoleFunction(List<Function> functionlist, Role role,
			long userId) {
		List<RoleFunction> list = roleWithFunctiondao
				.queryRoleFunctionByRoleId(role.getId());
		for (int i = 0; i < list.size(); i++) {
			roleWithFunctiondao.remove(list.get(i).getId());
		}

		for (int i = 0; i < functionlist.size(); i++) {
			RoleFunction entity = new RoleFunction();
			entity.setCreateDate(new Date());
			entity.setRole(role);
			entity.setFunction(functionlist.get(i));
			entity.setCreateBy(userId);
			entity.setLastUpdateBy(userId);
			entity.setLastUpdateDate(new Date());
			roleWithFunctiondao.save(entity);
		}

	}

	// Menu Function
	@Override
	public List<MenuFunction> getMenuFunctionByFunctionId(
			List<RoleFunction> roleFunctionList) {
		List<MenuFunction> list = new ArrayList<MenuFunction>();
		list = menuWithFunctiondao.findByFunctionList(roleFunctionList);
		return list;
	}

	@Override
	public List<MenuFunction> getMenuFunctionByMenuId(List<Menu> menuList) {
		List<MenuFunction> list = new ArrayList<MenuFunction>();
		list = menuWithFunctiondao.findByMenuList(menuList);
		return list;
	}

	@Override
	public String[] getMenuFunctionByMenuIds(String[] ids) {
		return menuWithFunctiondao.getfunctionidsByMenuIds(ids);
	}

	// Menu
	@Override
	public List<Menu> getMenuById(List<MenuFunction> menuFunctionList) {
		return menudao.getMenuListByMenuFunctionList(menuFunctionList);
	}

	// Function
	@Override
	public List<Function> getFunctionById(String[] ids) {
		List<Function> list = new ArrayList<Function>();
		for (int i = 0; i < ids.length; i++) {
			list.add(functiondao.findById(Long.valueOf(ids[i])));
		}
		return list;
	}

	// User
	@Override
	public BasePagerDto queryUser(QueryUserInfoDto<BaseUser> queryDto) {
		return IuserDao.queryUser(queryDto);
	}

	@Override
	public UserDto getUserById(String id) {
		UserDto dto = new UserDto();
		BaseUser entity = IuserDao.findById(Long.valueOf(id));
		BeanUtils.copyProperties(entity, dto);
		dto.setGroupId(entity.getUserGroup().getId().toString());
		return dto;
	}

	@Override
	public BaseUser saveUser(UserDto userDto, long userId) {
		BaseUser entity = null;
		if (userDto.getId() != null) {// edit save
			entity = IuserDao.findById(userDto.getId());
			userDto.setLastUpdateBy(userId);
			userDto.setLastUpdateDate(new Date());
		} else {
			entity = new BaseUser();
			userDto.setCreateBy(userId);
			userDto.setCreateDate(new Date());
		}
		String pwd = null;
		if (userDto.getPassword().equals(entity.getPassword())) {
			pwd = entity.getPassword();
		}
		UserGroup group = usergroupDao.findById(Long.valueOf(userDto.getGroupId()));
		BeanUtils.copyProperties(userDto, entity);

		if (group == null) {
			entity.setUserGroup(IuserDao.findById(userId).getUserGroup());
		} else {
			entity.setUserGroup(group);
		}

		if (pwd != null) {
			entity.setPassword(pwd);
		} else {
			pwd = entity.getPassword();
			MessageDigest messageDigest;
//				messageDigest = MessageDigest.getInstance("SHA-512");
//				messageDigest.update(pwd.getBytes());
//				String digestedPwdString = new String(
//						Base64.encode(messageDigest.digest()));
				String digestedPwdString=MD5Util.MD5(pwd);
				entity.setPassword(digestedPwdString);
		}

		return IuserDao.save(entity);
	}

	@Override
	public void deleteUser(String id, long userId) throws AppException {
		BaseUser entity = IuserDao.findById(Long.parseLong(id));
		if (entity == null) {
			throw new AppException(
					ExceptionConstant.ERROR_BASEINFO_SUPPLER_ENTITY_NOTEXIST,
					"Ctnsize is not exist.");
		}
		// IuserDao.remove(entity);
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setDeleteFlag(1L);

		IuserDao.update(entity);
	}

	// User Role
	@Override
	public void saveUserRole(BaseUser user, RoleDto role, long userId) {
		UserRole entity = new UserRole();
		entity.setCreateDate(new Date());
		entity.setUser(user);
		entity.setCreateBy(userId);
		entity.setCreateDate(new Date());
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		Role newrole = new Role();
		BeanUtils.copyProperties(role, newrole);
		entity.setRole(newrole);
		userroleDao.save(entity);
	}

	@Override
	public void deleteUserRole(Long id) throws AppException {
		userroleDao.removeByUserId(id);
		// UserRole entity= userroleDao.findById(id);
		// if(entity==null){
		// throw new
		// AppException(ExceptionConstant.ERROR_BASEINFO_SUPPLER_ENTITY_NOTEXIST,"user role is not exist.");
		// }
		// entity.setDeleteFlag(1L);
		// userroleDao.update(entity);
	}

	@Override
	public List<UserRole> getUserRoleByUserId(String id) {
		return userroleDao.findByUserId(Long.valueOf(id));
	}

	@Override
	public List<CustomerBasicInfo> fetchAllCustomerBasicInfo() {
		return customerBasicInfoDao.findAll();
	}

	@Override
	public String getUserByUserCode(String parameter) {
		// TODO Auto-generated method stub
		BaseUser user = IuserDao.getUserByUserCode(parameter);
		if (user != null) {
			return user.getUserCode();
		}
		return "";
	}

	@Override
	public CustomerBasicInfo findById(Long customId) {
		// TODO Auto-generated method stub
		return customerBasicInfoDao.findById(customId);
	}

	@Override
	public String updatePwd(String loginId, String ispwd)
			throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		if (loginId != null && Utils.notEmpty(loginId)) {
			BaseUser user = IuserDao.findById(Long.valueOf(loginId));
			if (user != null) {
//				MessageDigest messageDigest = MessageDigest
//						.getInstance("SHA-512");
//				messageDigest.update(ispwd.getBytes());
//				String digestedPwdString = new String(
//						Base64.encode(messageDigest.digest()));
				String digestedPwdString=MD5Util.MD5(ispwd);
				user.setPassword(digestedPwdString);
				user.setLastUpdateBy(Long.valueOf(loginId));
				user.setLastUpdateDate(new Date());
				IuserDao.update(user);
				return "success";
			}
		}
		return "faild";
	}

	@Override
	public String updateCustomer(String id, String name, String value) {
		// TODO Auto-generated method stub
		try {
			CustomerBasicInfo entity = customerBasicInfoDao.findById(Long
					.valueOf(id));
			if("level".equals(name)){
				entity.setLevel(value);
			}else if("status".equals(name)){
				entity.setStatus(value);
			}else if("userId".equals(name)){
				entity.setUserId(value);
			}
			customerBasicInfoDao.update(entity);
		} catch (Exception e) {
			// TODO: handle exception
			return "falid";
		}
		return "success";
	}

	@Override
	public BasePagerDto queryRobotBasicInfo(
			QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto) {
		robotBasicInfoDao.queryRobotBasicInfo(queryDto);
		return queryDto;
	}

	@Override
	public void deleteRobot(String id, long userId) {
		RobotBasicInfo entity = robotBasicInfoDao.findById(Long.parseLong(id));
		if (entity == null) {
			return;
		}
		// customerBasicInfoDao.remove(entity);
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setDeleteFlag(1L);
		robotBasicInfoDao.update(entity);
	}

	@Override
	public RobotBasicInfoDto getRobotById(String id) {
		RobotBasicInfoDto dto = new RobotBasicInfoDto();
		RobotBasicInfo entity = robotBasicInfoDao.findById(Long
				.parseLong(id));
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public RobotBasicInfo saveRobot(RobotBasicInfoDto robotDto, long userId) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		RobotBasicInfo entity = null;
		if (robotDto.getId() != null) {// edit
			entity = robotBasicInfoDao.findById(robotDto.getId());
		} else {// save
			entity = new RobotBasicInfo();
			entity.setCreateBy(userId);
			entity.setCreateDate(new Date());
			Random rd=new Random();
			String r=rd.nextInt(40)+"";
			if(r.length()==1){
				if(r.equals("0")){
					r="01";
				}else{
					r="0"+r;
				}
			}
			robotDto.setFaceImg(r+".png");
		}
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setFaceImg(robotDto.getFaceImg());
		entity.setLevel(robotDto.getLevel());
		entity.setName(robotDto.getName());
		entity.setUserId(robotDto.getUser_id());
		return  robotBasicInfoDao.save(entity);
	}

	@Override
	public String updateRobot(String id, String name, String value) {
		try {
			RobotBasicInfo entity = robotBasicInfoDao.findById(Long.valueOf(id));
			if("level".equals(name)){
				entity.setLevel(Integer.parseInt(value));
			}else if("userId".equals(name)){
				entity.setUserId(value);
			}
			robotBasicInfoDao.update(entity);
		} catch (Exception e) {
			// TODO: handle exception
			return "falid";
		}
		return "success";
	}

	@Override
	public boolean validmenu(HttpServletRequest request) {
		// TODO Auto-generated method stub
		SessionUserDto sessionDto=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		List<Menu> list=sessionDto.getMenuList();
		String url=request.getRequestURL().toString();
		String finalUrl=url.substring(0,url.lastIndexOf("/"));
		for(Menu menu:list){
			String murl="";
			if(menu.getUrl().equals("#")){
				
			}else{
				murl=menu.getUrl().substring(0,menu.getUrl().lastIndexOf("/"));
			}
			if(finalUrl.indexOf(murl)!=-1){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<UserGroup> getAllUserGroup() {
		// TODO Auto-generated method stub
		return usergroupDao.findAll();
	}

	@Override
	public List<CustomerBasicInfo> getAllAdminCustomer() {
		// TODO Auto-generated method stub
		return customerBasicInfoDao.getAllAdminCustomer();
	}

	@Override
	public List<Role> getallRole() {
		// TODO Auto-generated method stub
		return IroleDao.getAllRoles();
	}

	/*  ***QQ Fen Pei***  */
	@Override
	public BasePagerDto queryQQBasicInfo(QueryQQBasicInfoDto<QQFenPei> querydto){
		qqfenpeidao.queryqqbasicinfo(querydto);
		return querydto;
	}
	
	@Override
	public QQFenPei saveQQ(QQBasicInfoDto qqbfDto, long userId){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		QQFenPei entity = null;
		if(qqbfDto.getId()!=null){
			entity = qqfenpeidao.findById(Long.parseLong(qqbfDto.getId()+""));
		}else{
			entity = new QQFenPei();
			entity.setCreateBy(userId);
			entity.setCreateDate(new Date());
		}
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		BeanUtils.copyProperties(qqbfDto, entity);
		if (qqbfDto.getId() != null) {// edit
			qqfenpeidao.update(entity);
			return null;
		}else{
			return qqfenpeidao.save(entity);
		}
	}

	@Override
	public QQBasicInfoDto getQQInfoById(String QQId){
		QQBasicInfoDto dto = new QQBasicInfoDto();
		QQFenPei entity = qqfenpeidao.findById(Long.parseLong(QQId));
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	/*  ***Ke Cheng Biao***  */
	@Override
	public BasePagerDto queryKCBInfo(QueryKCBDto<KCBExcle> querykcb){
		kcbexcledao.queryKCBInfo(querykcb);
		return querykcb;
	}
	
	@Override
	public KCBExcle saveKCB(KCBBasicInfoDto kcbinfodto,long userId){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		KCBExcle entity = null;
		if(kcbinfodto.getId()!=null){
			entity = kcbexcledao.findById(Long.parseLong(kcbinfodto.getId()+""));
		}else{
			entity = new KCBExcle();
			entity.setCreateBy(userId);
			entity.setCreateDate(new Date());
		}
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		BeanUtils.copyProperties(kcbinfodto, entity);
		if(kcbinfodto.getId()!=null){// edit
			kcbexcledao.update(entity);
			return null;
		}else{
			return kcbexcledao.save(entity);
		}
	}

	@Override
	public BasePagerDto queryAdminBasicInfo(
			QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto) {
		// TODO Auto-generated method stub
		customerBasicInfoDao.queryAdminBasicInfo(queryDto);
		return queryDto;
	}




}
