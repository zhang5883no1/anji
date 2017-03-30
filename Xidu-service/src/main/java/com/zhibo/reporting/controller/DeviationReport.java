/******************************************************************************
 * @File name   :      DeviationReport.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 下午4:34:32        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.reporting.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.reporting.constant.ReportConstant;
import com.zhibo.reporting.dto.DeviationFileLoadingProcess;
import com.zhibo.reporting.dto.DeviationInfoDto;
import com.zhibo.reporting.service.ReportingService;

public class DeviationReport {
	private static Logger logger = Logger
			.getLogger(GlobalIntransitController.class);

//	@Autowired
//	ReportingService service;
//
//	@RequestMapping(value = "init", method = RequestMethod.GET)
//	public String init(Model model){
//		model.addAttribute("month", "");
//		model.addAttribute("errorinfo", "");
//		model.addAttribute("year","");
//		return ReportConstant.DOWNLOAD_DEVIATIOIN_REPORT_PAGE;
//	}
//	
//	@RequestMapping(value="downloadDeviationReport")
//	public String uploadDeviationReport(final DeviationFileLoadingProcess deviationUploadDto,DeviationInfoDto<?> queryDto,HttpServletResponse response,Model model,HttpServletRequest request) throws FileNotFoundException, IOException{
//		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
//		String errorMsg="";
//		String batchNo = "1000000";
//		deviationUploadDto.setBatchNo(batchNo);
//		deviationUploadDto.setSessionUser(sessionUser);
//		
//		service.downLoadDeviationReport(deviationUploadDto,queryDto,sessionUser);
//		
//		if("".equals(queryDto.getFileName())){
//			model.addAttribute("month", queryDto.getMonth());
//			model.addAttribute("year",queryDto.getYear());
//			logger.debug("m:"+queryDto.getMonth()+",y:"+queryDto.getYear());
//			model.addAttribute("errorinfo", "file is not exist");
//			return ReportConstant.DOWNLOAD_DEVIATIOIN_REPORT_PAGE;
//		}else{
//			String downLoadPath = queryDto.getFileName();
//			System.out.println(downLoadPath);
//			String name=downLoadPath.substring(downLoadPath.lastIndexOf("/")+1,downLoadPath.length());
//			response.setContentType("multipart/form-data");
//			response.setHeader("Content-Disposition", "attachment;fileName="+name);
//			FileCopyUtils.copy(new FileInputStream(downLoadPath),response.getOutputStream());
//			return null;
//		}
//	}
//	

}
