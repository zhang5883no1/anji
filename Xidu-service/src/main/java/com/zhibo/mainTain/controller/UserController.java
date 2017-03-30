package com.zhibo.mainTain.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.UserGroup;
import com.xidu.framework.usermgnt.domain.UserRole;
import com.xidu.framework.usermgnt.dto.QueryRoleDto;
import com.xidu.framework.usermgnt.dto.QueryUserInfoDto;
import com.xidu.framework.usermgnt.dto.RoleDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.xidu.framework.usermgnt.dto.UserDto;
import com.zhibo.mainTain.constant.MainTainConstant;
import com.zhibo.mainTain.domain.CustomerBasicInfo;
import com.zhibo.mainTain.dto.QueryCustomerBasicInfoDto;
import com.zhibo.mainTain.service.MainTainService;


@Controller
@RequestMapping(value="/maintain/user")
public class UserController extends BaseController{

private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	MainTainService service;
	
	@RequestMapping(value="init")
	public String initOperationMode(Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		QueryUserInfoDto<BaseUser> queryDto=new QueryUserInfoDto<BaseUser>();
//		queryDto.setUserGroup(sessionUser.getUserGroup());
		service.queryUser(queryDto);
		logger.debug("resultsize="+queryDto.getResultList().size());
		model.addAttribute("userDto",queryDto);
		return MainTainConstant.USER_LIST_PAGE;
	}
	
	@RequestMapping(value="queryUser" ,method = RequestMethod.POST)
	public String queryUser(QueryUserInfoDto<BaseUser> queryDto,Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		logger.debug("query start");
	    packageSortInfo(request,queryDto);
		queryDto.setUserGroup(sessionUser.getUserGroup());
	    service.queryUser(queryDto);
		model.addAttribute("userDto", queryDto);
		return MainTainConstant.USER_LIST_PAGE;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="addOrEdituser" ,method = RequestMethod.POST)
	public String addOrEdituser(QueryUserInfoDto<BaseUser> queryDto,Model model,HttpServletRequest request) {
		if(!service.validmenu(request)){
			return "/error";
		}
		SessionUserDto sessionDto=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		logger.debug("edit user id===>>>"+queryDto.getId());
		UserDto userDto=new UserDto();
		//edit user
		if(Utils.notEmpty(queryDto.getId())){
			userDto=service.getUserById(queryDto.getId());
			//get selected role
			List<UserRole> urid=service.getUserRoleByUserId(queryDto.getId());
			logger.debug("urid*"+urid.size());
			List<Long> selroles=new ArrayList<Long>();
			for(int i=0;i<urid.size();i++){
				selroles.add(urid.get(i).getRole().getId());
			}
			model.addAttribute("userRole",selroles);
		}
		model.addAttribute("userDto", userDto);
		
		List<UserGroup> grouplist=service.getAllUserGroup();
		List<CustomerBasicInfo> customerList=service.getAllAdminCustomer();
		
		//get role list
		List<Role> list=service.getallRole();
		model.addAttribute("rolelist",list);
		
		model.addAttribute("grouplist",grouplist);
		model.addAttribute("customerlist",customerList);
		
		return MainTainConstant.USER_ADD_OR_EDIT_PAGE;
	}
	
	@RequestMapping(value="saveuser",method=RequestMethod.POST)
	public String saveUser(@Valid UserDto userDto, BindingResult result,Model model,HttpServletRequest request) throws AppException{
		if(!service.validmenu(request)){
			return "/error";
		}
		Role isrole=null;
		if(userDto.getId()!=null){
			List<UserRole> list=service.getUserRoleByUserId(String.valueOf(userDto.getId()));
			for(int j=0;j<list.size();j++){
				isrole=list.get(j).getRole();
			}
		}
		//save dto
		BaseUser user=service.saveUser(userDto,getUserId(request));
		//save user role
		service.deleteUserRole(user.getId());
		String[] ids=request.getParameterValues("rolelist");
		for(int i=0;i<ids.length;i++){
			RoleDto role=service.getRoleById(ids[i]);
			service.saveUserRole(user,role,getUserId(request));
		}
		//query list
		QueryUserInfoDto<BaseUser> queryDto=new QueryUserInfoDto<BaseUser>();
		return this.queryUser(queryDto, model,request);
//		return "redirect:init";
	}
	
	@RequestMapping(value="deleteuser",method=RequestMethod.POST)
	public String deleteUser(QueryUserInfoDto<BaseUser> queryDto,Model model,HttpServletRequest request) throws AppException{
		if(!service.validmenu(request)){
			return "/error";
		}
		//delete dto\
		service.deleteUserRole(Long.valueOf(queryDto.getId()));
		service.deleteUser(queryDto.getId(),getUserId(request));

		//query list
		return this.queryUser(queryDto, model,request);
//		return "redirect:init";
	}
	
	@RequestMapping(value="valid",method=RequestMethod.GET)
	@ResponseBody
	public String valid(Model model,HttpServletRequest request){
		logger.debug("********start valid*********");
		String value=service.getUserByUserCode(request.getParameter("code"));
		logger.debug("********value:"+value+"*********");
		if(!"".equals(value)){
			return ""+value;
		}
		return "false";
	}
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getUserId();
		return Long.valueOf(loginId);
	}
	
	@RequestMapping(value="initpassword")
	public String init(Model model){
		return "mainTain/user/password";
	}
	
	@RequestMapping(value="updatePwd" ,method=RequestMethod.GET)
	@ResponseBody
	public String updatePwd(Model model,HttpServletRequest request) throws NoSuchAlgorithmException{
		if(!service.validmenu(request)){
			return "/error";
		}
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getUserId();
		String result=service.updatePwd(loginId,request.getParameter("pwd"));
		return result;
	}
}
