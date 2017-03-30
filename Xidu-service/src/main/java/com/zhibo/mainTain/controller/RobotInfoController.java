package com.zhibo.mainTain.controller;

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
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.xidu.framework.usermgnt.dto.UserGroupDto;
import com.zhibo.mainTain.constant.MainTainConstant;
import com.zhibo.mainTain.domain.CustomerBasicInfo;
import com.zhibo.mainTain.domain.RobotBasicInfo;
import com.zhibo.mainTain.dto.QueryCustomerBasicInfoDto;
import com.zhibo.mainTain.dto.QueryRobotrBasicInfoDto;
import com.zhibo.mainTain.dto.RobotBasicInfoDto;
import com.zhibo.mainTain.service.MainTainService;

@Controller
@RequestMapping(value = "/mainTain/robot")
public class RobotInfoController extends BaseController{
	private static Logger logger = Logger.getLogger(CustomerInfoController.class);
	
	@Autowired
	private MainTainService mainTainService;
	
	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init(Model model,HttpServletRequest request){
		if(!mainTainService.validmenu(request)){
			return "/error";
		}
		List<RoomDto> rooms=(List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto1 =new QueryCustomerBasicInfoDto<CustomerBasicInfo>();
		
		SessionUserDto sessionDto=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		List<UserGroupDto> groupList=(List<UserGroupDto>)request.getSession().getAttribute(WorkbenchConstant.GROUPLIST);
		
		queryDto1.setGrouplist(groupList);
		queryDto1.setSessionUser(sessionDto);
		queryDto1.getPager().setPageSize(999);
		queryDto1.setRooms(rooms);
		mainTainService.queryAdminBasicInfo(queryDto1);
		
		QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto=new QueryRobotrBasicInfoDto<RobotBasicInfo>();
		queryDto.setSessionUser(sessionDto);
		queryDto.setCustomerlist(queryDto1.getResultList());
		mainTainService.queryRobotBasicInfo(queryDto);
		queryDto.setGrouplist(groupList);
		model.addAttribute("queryRobotBasicInfoDto", queryDto);
		

		return MainTainConstant.ROBOT_INFO_LIST_PAGE;
	}
	
	
	@RequestMapping(value="queryRobot" ,method = RequestMethod.POST)
	public String queryCustom(QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto,Model model,HttpServletRequest request){
		if(!mainTainService.validmenu(request)){
			return "/error";
		}
	    packageSortInfo(request,queryDto);
	    
	    List<RoomDto> rooms=(List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
	    SessionUserDto sessionDto=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		List<UserGroupDto> groupList=(List<UserGroupDto>)request.getSession().getAttribute(WorkbenchConstant.GROUPLIST);
		
		QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto1 =new QueryCustomerBasicInfoDto<CustomerBasicInfo>();
		queryDto1.setGrouplist(groupList);
		queryDto1.setSessionUser(sessionDto);
		queryDto1.getPager().setPageSize(999);
		queryDto1.setRooms(rooms);
		mainTainService.queryAdminBasicInfo(queryDto1);
		
		queryDto.setSessionUser(sessionDto);
		queryDto.setCustomerlist(queryDto1.getResultList());
		mainTainService.queryRobotBasicInfo(queryDto);
		queryDto.setGrouplist(groupList);
		model.addAttribute("queryRobotBasicInfoDto", queryDto);
		
		return MainTainConstant.ROBOT_INFO_LIST_PAGE;
		
	}
	
	@RequestMapping(value="deleteRobot" ,method = RequestMethod.POST)
	public String deleteCustom(QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto,Model model,HttpServletRequest request) throws AppException {
		if(!mainTainService.validmenu(request)){
			return "/error";
		}
		//delete dto
		mainTainService.deleteRobot(queryDto.getId(),getUserId(request));
		//query list
//		return this.queryCustom(queryDto, model,request);
		return "redirect:init";
	}
	
	@RequestMapping(value="addOrEditRobot" ,method = RequestMethod.POST)
	public String addOrEditRobot(QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto,Model model,HttpServletRequest request) {
		if(!mainTainService.validmenu(request)){
			return "/error";
		}
		RobotBasicInfoDto robotDto=new RobotBasicInfoDto();
		//edit customer
		if(Utils.notEmpty(queryDto.getId())){
			robotDto=mainTainService.getRobotById(queryDto.getId());
		}
		model.addAttribute("robotDto", robotDto);
		
		List<CustomerBasicInfo> customerList=mainTainService.getAllAdminCustomer();
		model.addAttribute("admins", customerList);
		
		return MainTainConstant.ROBOT_ADD_OR_EDIT_PAGE;
	}
	
	@RequestMapping(value="saveRobot" ,method = RequestMethod.POST)
	public String saveRobot(@Valid RobotBasicInfoDto robotDto, BindingResult result,Model model,HttpServletRequest request) {
		if(!mainTainService.validmenu(request)){
			return "/error";
		}
		System.out.println(robotDto.getName()+"************************");
		//save dto
		mainTainService.saveRobot(robotDto,getUserId(request));
		//query list
		QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto=new QueryRobotrBasicInfoDto<RobotBasicInfo>();
//		return this.queryCustom(queryDto, model,request);
		return "redirect:init";
	}
	
	@RequestMapping(value="updateInfo",method=RequestMethod.GET)
	@ResponseBody
	public String valid(Model model,HttpServletRequest request){
		if(!mainTainService.validmenu(request)){
			return "/error";
		}
		String value=mainTainService.updateRobot(request.getParameter("id"),request.getParameter("name"),request.getParameter("value"));
		return value;
	}
	
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getUserId();
		return Long.valueOf(loginId);
	}

}
