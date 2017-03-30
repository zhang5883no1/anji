package com.zhibo.mainTain.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.zhibo.mainTain.dto.CustomerBasicInfoDto;
import com.zhibo.mainTain.dto.QueryCustomerBasicInfoDto;
import com.zhibo.mainTain.service.MainTainService;
import com.zhibo.mainTain.util.AppendFile;
import com.zhibo.mainTain.util.AutoUtil;

@Controller
@RequestMapping(value = "/mainTain/adminInfo")
public class AdminInfoController extends BaseController{
	private static Logger logger = Logger.getLogger(CustomerInfoController.class);
	
	@Autowired
	private MainTainService mainTainService;
	
	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init(Model model,HttpServletRequest request){
		QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto=new QueryCustomerBasicInfoDto<CustomerBasicInfo>();
		SessionUserDto sessionDto=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		List<UserGroupDto> groupList=(List<UserGroupDto>)request.getSession().getAttribute(WorkbenchConstant.GROUPLIST);
		List<RoomDto> rooms=(List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		queryDto.setGrouplist(groupList);
		queryDto.setSessionUser(sessionDto);
		queryDto.setRooms(rooms);
		mainTainService.queryAdminBasicInfo(queryDto);
		model.addAttribute("querycustomerBasicInfoDto", queryDto);
		return MainTainConstant.ADMIN_INFO_LIST_PAGE;
	}
	
	
	@RequestMapping(value="queryCustom" ,method = RequestMethod.POST)
	public String queryCustom(QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto,Model model,HttpServletRequest request){
		SessionUserDto sessionDto=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		List<UserGroupDto> groupList=(List<UserGroupDto>)request.getSession().getAttribute(WorkbenchConstant.GROUPLIST);
		List<RoomDto> rooms=(List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		queryDto.setGrouplist(groupList);
		queryDto.setSessionUser(sessionDto);
		queryDto.setRooms(rooms);
	    packageSortInfo(request,queryDto);
	    mainTainService.queryAdminBasicInfo(queryDto);
		model.addAttribute("querycustomerBasicInfoDto", queryDto);
		return MainTainConstant.ADMIN_INFO_LIST_PAGE;
		
	}
	
	@RequestMapping(value="deleteCustom" ,method = RequestMethod.POST)
	public String deleteCustom(QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto,Model model,HttpServletRequest request) throws AppException {
		//delete dto
		mainTainService.deleteCustom(queryDto.getId(),getUserId(request));
		
		//query list
//		return this.queryCustom(queryDto, model,request);
		return "redirect:init";
	}
	
	@RequestMapping(value="addOrEditCustom" ,method = RequestMethod.POST)
	public String addOrEditCustom(QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto,Model model,HttpServletRequest request) {
		CustomerBasicInfoDto customerDto=new CustomerBasicInfoDto();
		//edit customer
		if(Utils.notEmpty(queryDto.getId())){
			customerDto=mainTainService.getCustomInfoById(queryDto.getId());
		}
		
		List<UserGroupDto> groupList=(List<UserGroupDto>)request.getSession().getAttribute(WorkbenchConstant.GROUPLIST);
		List<RoomDto> rooms=(List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		
		model.addAttribute("customerDto", customerDto);
		model.addAttribute("groupList", groupList);
		model.addAttribute("rooms", rooms);
		return MainTainConstant.ADMIN_ADD_OR_EDIT_PAGE;
	}
	
	@RequestMapping(value="saveCustomer" ,method = RequestMethod.POST)
	public String saveCustomer(@Valid CustomerBasicInfoDto customerDto, BindingResult result,Model model,HttpServletRequest request) {
		//save dto
		mainTainService.saveCustomer(customerDto,getUserId(request));
		//query list
		QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto=new QueryCustomerBasicInfoDto<CustomerBasicInfo>();
//		return this.queryCustom(queryDto, model,request);
		return "redirect:init";
	}
	
	@RequestMapping(value="updateInfo",method=RequestMethod.GET)
	@ResponseBody
	public String valid(Model model,HttpServletRequest request){
		String value=mainTainService.updateCustomer(request.getParameter("id"),request.getParameter("name"),request.getParameter("value"));
		return value;
	}
	
	
	private long getUserId(HttpServletRequest request){
		String loginId=((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getAssignUser()+"";
		return Long.valueOf(loginId);
	}
	
	/**
	 * @throws FileNotFoundException ********************/
	@RequestMapping(value = "roomadmin", method = RequestMethod.GET)
	public String getRoomMagager(Model model,HttpServletRequest request) throws FileNotFoundException{
		RoomDto room = new RoomDto();
		List<RoomDto> rooms=(List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		room = rooms.get(0);
		model.addAttribute("RoomManagers", room);
		model.addAttribute("RoomsList", rooms);
		
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("D:\\admin.properties");
		String check [] = null;
		try {
			pro.load(fis);
			if(null==pro.getProperty("room"+room.getId().toString())||"".equals(pro.getProperty("room"+room.getId().toString()))){
				
			}else{
				String checked = pro.getProperty("room"+room.getId().toString());
				check = checked.split(",");
			}
			model.addAttribute("CheckedId", check);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "mainTain/adminfenpei/adminlist";
	}
	
	
	@RequestMapping(value = "queryadmin", method = RequestMethod.POST)
	public String queryRoomMagager(RoomDto roomdto, Model model,HttpServletRequest request) throws FileNotFoundException{
		RoomDto room = new RoomDto();
		List<RoomDto> rooms=(List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		for(RoomDto r : rooms){
			if(roomdto.getId().toString().equals(r.getId().toString())){
				room = r;
			}
		}
		model.addAttribute("RoomManagers", room);
		model.addAttribute("RoomsList", rooms);
		
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("D:\\admin.properties");
		String check [] = null;
		try {
			pro.load(fis);
			if(null==pro.getProperty("room"+room.getId().toString())||"".equals(pro.getProperty("room"+room.getId().toString()))){
				
			}else{
				String checked = pro.getProperty("room"+room.getId().toString());
				check = checked.split(",");
				
			}
			model.addAttribute("CheckedId", check);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "mainTain/adminfenpei/adminlist";
	}
	
	
	@RequestMapping(value = "saveadmin", method = RequestMethod.POST)
	public String saveadmin(@Valid RoomDto roomdto, BindingResult result,Model model,HttpServletRequest request) throws FileNotFoundException{
		String a = request.getParameter("checklist");
		//System.out.println("**************************"+a);
		//String b [] = a.split(",");
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("D:\\admin.properties");
		try {
			pro.load(fis);
			fis.close();
			String fangjian = "room"+roomdto.getId().toString();
			String value = "";
			OutputStream fos = new FileOutputStream("D:\\admin.properties");
			value = a;
			pro.setProperty(fangjian, value); 
			pro.store(fos, "Update '" + fangjian + "' value");
			AppendFile.write("houtai.txt","D:\\houtairizhi", "update assign admin --- room :"+"room"+roomdto.getId().toString()+" value : "+a+" id :"+((SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER)).getUserId()+"\r\n");
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:roomadmin";
	}
}
