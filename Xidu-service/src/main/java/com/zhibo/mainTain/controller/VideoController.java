package com.zhibo.mainTain.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.mainTain.constant.MainTainConstant;
import com.zhibo.mainTain.domain.Video;
import com.zhibo.mainTain.dto.QueryVideoDto;
import com.zhibo.mainTain.dto.VideoDto;
import com.zhibo.mainTain.service.MainTainService;
import com.zhibo.mainTain.service.VideoService;
import com.zhibo.mainTain.util.PropertiesConfig;

@Controller
@RequestMapping(value = "/mainTain/video")
public class VideoController extends BaseController{
	private static Logger logger = Logger.getLogger(VideoController.class);
	@Autowired
	MainTainService service;
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init(Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
		QueryVideoDto<Video> queryDto=new QueryVideoDto<Video>();
		videoService.queryVideo(queryDto);
		model.addAttribute("queryVideoDto", queryDto);
		return MainTainConstant.VIDEO_INFO_LIST_PAGE;
	}
	
	
	@RequestMapping(value="queryVideo" ,method = RequestMethod.POST)
	public String queryVideo(QueryVideoDto<Video> queryDto,Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
	    packageSortInfo(request,queryDto);
	    videoService.queryVideo(queryDto);
		model.addAttribute("queryVideoDto", queryDto);
		return MainTainConstant.VIDEO_INFO_LIST_PAGE;
		
	}
	
	@RequestMapping(value="deleteVideo" ,method = RequestMethod.POST)
	public String deleteVideo(QueryVideoDto<Video> queryDto,Model model,HttpServletRequest request) throws AppException {
		if(!service.validmenu(request)){
			return "/error";
		}
		//delete dto
		videoService.deleteVideo(queryDto.getId(),getUserId(request));
		//query list
//		return this.queryCustom(queryDto, model,request);
		return "redirect:init";
	}
	
	@RequestMapping(value="addOrEditVideo" ,method = RequestMethod.POST)
	public String addOrEditRobot(QueryVideoDto<Video> queryDto,Model model,HttpServletRequest request) {
		if(!service.validmenu(request)){
			return "/error";
		}
		VideoDto videoDto=new VideoDto();
		//edit customer
		if(Utils.notEmpty(queryDto.getId())){
			videoDto=videoService.getVideoById(queryDto.getId());
		}
		model.addAttribute("videoDto", videoDto);
		
		return MainTainConstant.VIDEO_ADD_OR_EDIT_PAGE;
	}
	
	@RequestMapping(value="saveVideo" ,method = RequestMethod.POST)
	public String saveVideo(@Valid VideoDto videoDto, BindingResult result,Model model,HttpServletRequest request) {
		if(!service.validmenu(request)){
			return "/error";
		}
		//save dto
		videoService.saveVideo(videoDto,getUserId(request));
//		return this.queryCustom(queryDto, model,request);
		return "redirect:init";
	}
	
	@RequestMapping(value="initDefault" )
	public String initDefault(Model model,HttpServletRequest request){
		if(!service.validmenu(request)){
			return "/error";
		}
		model.addAttribute("defaultUrl", PropertiesConfig.readData("", "xidu.defaut.videoUrl"));
		model.addAttribute("defaultUrl2", PropertiesConfig.readData("", "xidu.defaut.videoUrl2"));
		return MainTainConstant.VIDEO_EDIT_DEFAULT_PAGE;
	}
	
	@RequestMapping(value="updateDefaultUrl" ,method=RequestMethod.GET)
	@ResponseBody
	public String updatePwd(Model model,HttpServletRequest request) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(!service.validmenu(request)){
			return "/error";
		}
		PropertiesConfig.writeData("","xidu.defaut.videoUrl",request.getParameter("defaultUrl"));
		PropertiesConfig.writeData("","xidu.defaut.videoUrl2",request.getParameter("defaultUrl2"));
		return "success";
	}
	
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getUserId();
		return Long.valueOf(loginId);
	}

}
