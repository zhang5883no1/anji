/******************************************************************************
 * @File name   :      DeviationReportUpload.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 下午4:34:05        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.reporting.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.xidu.framework.common.util.ApplicationContextUtil;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.mainTain.service.MainTainService;
import com.zhibo.reporting.constant.ReportConstant;
import com.zhibo.reporting.dto.DeviationFileLoadingProcess;
import com.zhibo.reporting.dto.DeviationInfoDto;
import com.zhibo.reporting.service.ReportingService;

@Controller
@RequestMapping(value = "/mainTain/upload")
public class DeviationReportUploadController {
private static Logger logger = Logger.getLogger(GlobalIntransitController.class);
	
	@Autowired
	ReportingService service;
	@Autowired
	private MainTainService mservice;
	
	@RequestMapping(value = "init", method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest request){
		List<RoomDto> rooms = (List<RoomDto>)request.getSession().getAttribute(WorkbenchConstant.ROOMLIST);
		model.addAttribute("RoomsList", rooms);
		return ReportConstant.UPLOAD_DEVIATIOIN_REPORT_PAGE;
	}
	
	@RequestMapping(value="uploadDeviationReport", method = RequestMethod.POST)
	public String uploadDeviationReport(HttpServletRequest request) throws IllegalStateException, IOException{
		/*if(!mservice.validmenu(request)){
			return "/error";
		}*/
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		String roomId = request.getParameter("roomId");
		CommonsMultipartResolver mutilpartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//request如果是Multipart类型

		if (mutilpartResolver.isMultipart(request)) {
		//强转成 MultipartHttpServletRequest
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//获取文件名
			Iterator<String> it = multiRequest.getFileNames();
			
			while (it.hasNext()) {
				String fileName = "";
				//要上传的目录			
				String root = "";
				//获取MultipartFile类型文件
				MultipartFile fileDetail = multiRequest.getFile(it.next());
				String imgname = fileDetail.getName();
				if (fileDetail != null) {
					fileName = fileDetail.getOriginalFilename();
					if(null==fileName||"".equals(fileName)){
						
					}else{
						String format = fileName.substring(fileName.lastIndexOf(".")+1);
						//判断头像的图片格式
						if(format.equals("jpg")||format.equals("png")||format.equals("gif")||format.equals("JPG")||format.equals("PNG")||format.equals("GIF")){
							root = PropertiesConfig.readPro("D:\\images.properties", "room"+roomId+imgname);
							//判断文件夹目录是否存在
							File newfile = new File(root);
							if(!newfile.exists()){
								newfile.mkdirs();
							}else{
								
							}
							//将上传文件写入到指定文件处(核心)！
							fileDetail.transferTo(newfile);
						}else{
							request.setAttribute("error", "Image format is not correct !");
						}					
						/*String keyname = "room"+roomId+imgname;
						String value = root;
						PropertiesConfig.writeData(keyname, value);*/
					}
				}		
			}
		}
				
		return ReportConstant.UPLOAD_DEVIATIOIN_REPORT_PAGE;
	}
}
