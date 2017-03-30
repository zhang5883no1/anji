package com.zhibo.mainTain.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

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
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.mainTain.domain.QQFenPei;
import com.zhibo.mainTain.dto.QQBasicInfoDto;
import com.zhibo.mainTain.dto.QueryQQBasicInfoDto;
import com.zhibo.mainTain.service.MainTainService;
import com.zhibo.mainTain.util.PropertiesUtilss;

@Controller
@RequestMapping("/mainTain/qqmanager")
public class QQFenPeiController extends BaseController{
	private static Logger logger = Logger.getLogger(QQFenPeiController.class);
	
	@Autowired
	private MainTainService mainservice;
	
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest req) throws FileNotFoundException{
		QueryQQBasicInfoDto<QQFenPei> queryqq = new QueryQQBasicInfoDto<QQFenPei>();
		List<RoomDto> rooms = (List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		queryqq.setRooms(rooms);
		mainservice.queryQQBasicInfo(queryqq);
		model.addAttribute("queryQQBasicInfoDto", queryqq);
		
		QQFenPei qqfp = new QQFenPei();
		String fangjian = "room"+rooms.get(0).getId().toString();
		String profile = "D:\\currentqq.properties";
		try {
			if("".equals(PropertiesUtilss.readPropertiesValue(profile, fangjian+".roomId"))){
				
			}else{
				Long id = Long.parseLong(PropertiesUtilss.readPropertiesValue(profile, fangjian+".id"));
				String name = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name");
				String QQ1 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ1");
				String name1 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name1");
				String QQ2 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ2");
				String name2 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name2");
				String QQ3 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ3");
				String name3 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name3");
				String QQ4 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ4");
				String name4 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name4");
				String QQ5 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ5");
				String name5 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name5");
				String QQ6 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ6");
				String name6 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name6");
				String roomId = PropertiesUtilss.readPropertiesValue(profile, fangjian+".roomId");
				
				qqfp.setId(id);
				qqfp.setName(name);
				qqfp.setQQ1(QQ1);
				qqfp.setName1(name1);
				qqfp.setQQ2(QQ2);
				qqfp.setName2(name2);
				qqfp.setQQ3(QQ3);
				qqfp.setName3(name3);
				qqfp.setQQ4(QQ4);
				qqfp.setName4(name4);
				qqfp.setQQ5(QQ5);
				qqfp.setName5(name5);
				qqfp.setQQ6(QQ6);
				qqfp.setName6(name6);
				qqfp.setRoomId(roomId);
				qqfp.setCreateBy(1);
				qqfp.setLastUpdateBy(1);
			}
		} catch (Exception e) {
			logger.error("Could not read properties file", e);
		}
		model.addAttribute("currentQQBasicInfoDto", qqfp);
		return "mainTain/qqmanager/qqlist";
	}
	
	@RequestMapping(value = "queryQQ", method = RequestMethod.POST)
	public String queryQQ(QueryQQBasicInfoDto<QQFenPei> querydto, Model model, HttpServletRequest req) throws FileNotFoundException{
		List<RoomDto> rooms=(List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		querydto.setRooms(rooms);
		packageSortInfo(req, querydto);
		mainservice.queryQQBasicInfo(querydto);
		model.addAttribute("queryQQBasicInfoDto", querydto);
		
		QQFenPei qqfp = new QQFenPei();
		String fangjian = "room"+querydto.getRoomId();
		String profile = "D:\\currentqq.properties";
		try {
			if("".equals(PropertiesUtilss.readPropertiesValue(profile, fangjian+".roomId"))){
				
			}else{
				Long id = Long.parseLong(PropertiesUtilss.readPropertiesValue(profile, fangjian+".id"));
				String name = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name");
				String QQ1 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ1");
				String name1 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name1");
				String QQ2 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ2");
				String name2 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name2");
				String QQ3 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ3");
				String name3 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name3");
				String QQ4 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ4");
				String name4 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name4");
				String QQ5 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ5");
				String name5 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name5");
				String QQ6 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".QQ6");
				String name6 = PropertiesUtilss.readPropertiesValue(profile, fangjian+".name6");
				String roomId = PropertiesUtilss.readPropertiesValue(profile, fangjian+".roomId");
				qqfp.setId(id);
				qqfp.setName(name);
				qqfp.setQQ1(QQ1);
				qqfp.setQQ1(QQ1);
				qqfp.setName1(name1);
				qqfp.setQQ2(QQ2);
				qqfp.setName2(name2);
				qqfp.setQQ3(QQ3);
				qqfp.setName3(name3);
				qqfp.setQQ4(QQ4);
				qqfp.setName4(name4);
				qqfp.setQQ5(QQ5);
				qqfp.setName5(name5);
				qqfp.setQQ6(QQ6);
				qqfp.setName6(name6);
				qqfp.setRoomId(roomId);
				qqfp.setCreateBy(1);
				qqfp.setLastUpdateBy(1);
			}
		} catch (Exception e) {
			logger.error("Could not read properties file", e);
		}
		model.addAttribute("currentQQBasicInfoDto", qqfp);
		return "mainTain/qqmanager/qqlist";
	}
	
	@RequestMapping(value = "addQQ", method = RequestMethod.POST)
	public String addQQ(QueryQQBasicInfoDto<QQFenPei> querydto, Model model, HttpServletRequest req){
		QQBasicInfoDto qqbf = new QQBasicInfoDto();
		List<RoomDto> rooms=(List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		model.addAttribute("roomlist", rooms);
		model.addAttribute("qqbasicInfodto", qqbf);
		return "mainTain/qqmanager/addqq";
	}
	
	@RequestMapping(value = "saveQQ", method = RequestMethod.POST)
	public String saveQQ(@Valid QQBasicInfoDto qqbfDto, BindingResult result,Model model,HttpServletRequest request){
		mainservice.saveQQ(qqbfDto, getUserId(request));
		return "redirect:init";
	}
	
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getAssignUser()+"";
		return Long.valueOf(loginId);
	}
	
	@RequestMapping(value = "editQQ", method = RequestMethod.POST)
	public String editQQ(QueryQQBasicInfoDto<QQFenPei> querydto, Model model, HttpServletRequest req) throws FileNotFoundException{
		QQBasicInfoDto qqDto=new QQBasicInfoDto();
		//edit customer
		if(Utils.notEmpty(querydto.getId())){
			qqDto  = mainservice.getQQInfoById(querydto.getId());
			
			Properties pt = null;
			
			QQFenPei qqfp = new QQFenPei();
			String fangjian = "room"+qqDto.getRoomId();
			String profile = "D:\\currentqq.properties";
			try {
				//write in properties
				PropertiesUtilss.writePropertiesFileObj(profile, pt, fangjian, qqDto);
	            
	            qqfp.setId(qqDto.getId());
				qqfp.setName(qqDto.getName());
				qqfp.setQQ1(qqDto.getQQ1());
				qqfp.setName1(qqDto.getName1());
				qqfp.setQQ2(qqDto.getQQ2());
				qqfp.setName2(qqDto.getName2());
				qqfp.setQQ3(qqDto.getQQ3());
				qqfp.setName3(qqDto.getName3());
				qqfp.setQQ4(qqDto.getQQ4());
				qqfp.setName4(qqDto.getName4());
				qqfp.setQQ5(qqDto.getQQ5());
				qqfp.setName5(qqDto.getName5());
				qqfp.setQQ6(qqDto.getQQ6());
				qqfp.setName6(qqDto.getName6());
				qqfp.setRoomId(qqDto.getRoomId());
				qqfp.setCreateBy(getUserId(req));
				qqfp.setLastUpdateBy(getUserId(req));
				
			} catch (Exception e) {
				logger.error("Could not read properties file", e);
			}
			model.addAttribute("currentQQBasicInfoDto", qqfp);
			
			List<RoomDto> rooms=(List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
			querydto.setRooms(rooms);
			packageSortInfo(req, querydto);
			mainservice.queryQQBasicInfo(querydto);
			model.addAttribute("queryQQBasicInfoDto", querydto);
			return "mainTain/qqmanager/qqlist";
		}else{
			return "";
		}	
	}
	
	@RequestMapping(value = "deleteQQ", method = RequestMethod.POST)
	public String deleteQQ(QueryQQBasicInfoDto<QQFenPei> querydto, Model model, HttpServletRequest req) throws AppException{
		mainservice.deleteQQ(querydto.getId(), getUserId(req));
		
		return "redirect:init";
	}
	
}
