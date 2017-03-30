package com.zhibo.mainTain.controller;

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

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.menu.dto.MenuListDto;
import com.xidu.framework.menu.service.IMenuService;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.domain.Function;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.RoleFunction;
import com.xidu.framework.usermgnt.dto.QueryRoleDto;
import com.xidu.framework.usermgnt.dto.RoleDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.mainTain.constant.MainTainConstant;
import com.zhibo.mainTain.service.MainTainService;

@Controller
@RequestMapping(value="/maintain/role")
public class RoleController extends BaseController{

private static Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	MainTainService service;
	@Autowired
	IMenuService menuService;
	
	@RequestMapping(value="init")
	public String initOperationMode(Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		QueryRoleDto<Role> queryDto=new QueryRoleDto<Role>();
		queryDto.setGroupid(sessionUser.getUserGroup().getId());
		service.queryRole(queryDto);
		model.addAttribute("RoleDto", queryDto);
		logger.debug("page number:"+queryDto.getPager().getTotalPages());
		logger.debug("page current:"+queryDto.getPager().getCurrentPage());
		logger.debug("page size:"+queryDto.getPager().getPageSize());
		logger.debug("page size:"+queryDto.getPager().getTotalSize());
		logger.debug("size:"+queryDto.getResultList().size());
		return MainTainConstant.ROLE_LIST_PAGE;
	}
	
	@RequestMapping(value="queryrole", method=RequestMethod.POST)
	public String queryRole(QueryRoleDto<Role> queryDto,Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		queryDto.setGroupid(sessionUser.getUserGroup().getId());
		logger.debug("-----------query role start--------");
		packageSortInfo(request,queryDto);
		service.queryRole(queryDto);
		model.addAttribute("RoleDto",queryDto);
		return MainTainConstant.ROLE_LIST_PAGE;
	}
	
	@RequestMapping(value="addOrEditRole" ,method = RequestMethod.POST)
	public String addOrEditCustom(QueryRoleDto<Role> queryDto,Model model,HttpServletRequest request) {
		if(!service.validmenu(request)){
			return "/error";
		}
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		logger.debug("edit customId===>>>"+queryDto.getId());
		RoleDto roleDto=new RoleDto();
		//edit role
		if(Utils.notEmpty(queryDto.getId())){
			roleDto=service.getRoleById(queryDto.getId());
		}
		model.addAttribute("roleDto", roleDto);
		//get selected menu
		List<RoleFunction> roleFunctionList=new ArrayList<RoleFunction>(); 
		List<MenuFunction> menuFunctionList=new ArrayList<MenuFunction>();
		List<Menu> menuList=new ArrayList<Menu>();
		if(roleDto.getId()!=null){
			roleFunctionList=service.queryRoleWithFunction(roleDto.getId());
			if(roleFunctionList.size()>0){
				menuFunctionList=service.getMenuFunctionByFunctionId(roleFunctionList);
				if(menuFunctionList.size()>0){
					menuList=service.getMenuById(menuFunctionList);
					model.addAttribute("isSelectMenu",menuList);
				}
			}
		}
		//get menu list
		List<MenuListDto> menuList1=new ArrayList<MenuListDto>();
		menuList1=menuService.getMenuListOrderByParentCode(sessionUser.getUserGroup().getId());
		model.addAttribute("menuList1", menuList1);
		return MainTainConstant.ADD_OR_EDIT_ROLE_PAGE;
	}
	
	@RequestMapping(value="saverole" ,method = RequestMethod.POST)
	public String saverole(@Valid RoleDto roleDto, BindingResult result,Model model,HttpServletRequest request) {
		if(!service.validmenu(request)){
			return "/error";
		}
		String[] ids=request.getParameterValues("menucheckbox");
		//save role
		Role role=service.saveRole(roleDto,getUserId(request));
		//get function ids by menu ids
		String[] functionids=service.getMenuFunctionByMenuIds(ids);
		//get function list by id
		List<Function> functionlist=service.getFunctionById(functionids);
		if(functionlist.size()>0){
			//remove and save role function
			logger.debug("begin save role with function");
			service.saveRoleFunction(functionlist,role,getUserId(request));
		}
		//query list
		QueryRoleDto<Role> queryDto=new QueryRoleDto<Role>();
		return this.queryRole(queryDto, model,request);
//		return "redirect:init";
	}
	
	@RequestMapping(value="deleterole" ,method = RequestMethod.POST)
	public String deleterole(QueryRoleDto<Role> queryDto,Model model,HttpServletRequest request) throws AppException {
		if(!service.validmenu(request)){
			return "/error";
		}
		//delete dto
		service.deleteRole(queryDto.getId(),getUserId(request));
		//query list
		return this.queryRole(queryDto, model,request);
//		return "redirect:init";
	}
	
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getUserId();
		return Long.valueOf(loginId);
	}
}
