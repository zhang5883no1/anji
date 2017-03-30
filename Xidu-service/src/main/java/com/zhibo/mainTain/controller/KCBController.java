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

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.mainTain.domain.KCBExcle;
import com.zhibo.mainTain.dto.KCBBasicInfoDto;
import com.zhibo.mainTain.dto.QueryKCBDto;
import com.zhibo.mainTain.service.MainTainService;

@Controller
@RequestMapping("mainTain/kcb")
public class KCBController extends BaseController{
	private static Logger logger = Logger.getLogger(KCBController.class);
	@Autowired
	private MainTainService mainservice;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest req){
		QueryKCBDto<KCBExcle> querykcb = new QueryKCBDto<KCBExcle>();
		List<RoomDto> rooms = (List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		querykcb.setRooms(rooms);
		mainservice.queryKCBInfo(querykcb);
		model.addAttribute("queryKCBInfoDto", querykcb);
		
		KCBBasicInfoDto kcbinfodto = new KCBBasicInfoDto();
		model.addAttribute("kcbInfoDto", kcbinfodto);
		return "mainTain/upload/kcb";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "queryKCB", method = RequestMethod.POST)
	public String queryKCB(QueryKCBDto<KCBExcle> querykcb, Model model, HttpServletRequest req){
		List<RoomDto> rooms=(List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		querykcb.setRooms(rooms);
		packageSortInfo(req, querykcb);
		mainservice.queryKCBInfo(querykcb);
		model.addAttribute("queryKCBInfoDto", querykcb);
		
		KCBBasicInfoDto kcbinfodto = new KCBBasicInfoDto();
		model.addAttribute("kcbInfoDto", kcbinfodto);
		return "mainTain/upload/kcb";
	}
	
	@RequestMapping(value = "saveKCB", method = RequestMethod.POST)
	public String saveKCB(@Valid KCBBasicInfoDto kcbinfodto, BindingResult result,Model model,HttpServletRequest request){
		mainservice.saveKCB(kcbinfodto, getUserId(request));
		return "redirect:init";
	}
	
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getAssignUser()+"";
		return Long.valueOf(loginId);
	}
}
