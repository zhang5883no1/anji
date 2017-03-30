package com.zhibo.mainTain.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.zhibo.mainTain.service.MainTainService;
import com.zhibo.mainTain.util.PropertiesUtilss;

@Controller
@RequestMapping("/mainTain/footgonggao")
public class FootGongGaoController extends BaseController{
	private static Logger logger = Logger.getLogger(QQFenPeiController.class);
	
	@Autowired
	MainTainService mainservice;
	
	@RequestMapping(value="init", method=RequestMethod.GET)
	public String init(Model model, HttpServletRequest req) throws FileNotFoundException{
		List<RoomDto> rooms = (List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		String key = "";
		String value = "";
		key = "room"+rooms.get(0).getId();
		value = PropertiesUtilss.readPropertiesValue("D:\\footnotice.properties", key);
		
		model.addAttribute("footgonggao", value);
		model.addAttribute("footrooms", rooms);
		return "mainTain/foot/footlist";
	}
	
	@RequestMapping(value="queryfoot", method=RequestMethod.POST)
	public String query(Model model, HttpServletRequest req){
		List<RoomDto> rooms = (List<RoomDto>)req.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		String key = "";
		String value = "";
		int id = 0;
		if(null==req.getParameter("roomId")||"".equals(req.getParameter("roomId"))){
			
		}else{
			id = Integer.parseInt(req.getParameter("roomId"));
		}
		key = "room"+id;
		value = PropertiesUtilss.readPropertiesValue("D:\\footnotice.properties", key);
		model.addAttribute("footgonggao", value);
		model.addAttribute("footrooms", rooms);
		return "mainTain/foot/footlist";
	}
	
	@RequestMapping(value="editfoot", method=RequestMethod.POST)
	public String edit(Model model, HttpServletRequest req){
		int id = 0;
		String value = "";
		String key = "";
		if(null==req.getParameter("roomId")||"".equals(req.getParameter("roomId"))){
			
		}else{
			id = Integer.parseInt(req.getParameter("roomId"));
		}
		try {
			//read before
			Properties pro = new Properties();
			FileInputStream is = new FileInputStream("D:\\footnotice.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, "utf-8"));
			pro.load(bf);
			bf.close();
			is.close();
			key = "room"+id;
			value = req.getParameter("footcontent");
			//then write or edit
			OutputStream out = new FileOutputStream("D:\\footnotice.properties");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
			pro.setProperty(key, value);
			pro.store(bw, "Update '" + "room"+id + "' value");
			bw.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:init";
	}
}
