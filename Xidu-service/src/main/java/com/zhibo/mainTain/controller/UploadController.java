/*package com.zhibo.mainTain.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.mainTain.service.MainTainService;

@Controller
@RequestMapping(value = "/mainTain/kcb")
public class UploadController extends BaseController{
	private static Logger logger = Logger.getLogger(UploadController.class);
	@Autowired
	MainTainService service;
	
	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init(Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
		return "mainTain/upload/kcb";
	}
	
	
	
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getUserId();
		return Long.valueOf(loginId);
	}

}*/
