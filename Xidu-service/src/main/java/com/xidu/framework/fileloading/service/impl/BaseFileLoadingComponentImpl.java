/******************************************************************************
 * @File name   :      BaseFileLoadingServiceImpl.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 下午1:54:43        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.fileloading.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xidu.framework.fileloading.constant.FileLoadingConstant;
import com.xidu.framework.fileloading.dto.BaseLoadingFileProcessDto;
import com.xidu.framework.fileloading.service.FileLoadingComponent;
import com.xidu.framework.fileloading.service.FileLoadingFacade;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.logging.domain.LogDomain;
import com.zhibo.logging.service.LogService;
@Component
public class BaseFileLoadingComponentImpl implements FileLoadingComponent,FileLoadingFacade {
	private String originalFileName;
	private InputStream in;
	private MultipartFile mpf;
	private static Logger logger = Logger.getLogger(BaseFileLoadingComponentImpl.class);
	private SessionUserDto sessionUser;
	@Autowired
	private LogService logService;
	private LogDomain logDomain;
	

	@Override
	public void doUploading(BaseLoadingFileProcessDto blfDto) {
		validating(blfDto);
		locating(blfDto);
//		logging(blfDto);
	}

	@Override
	public void doDownloading(BaseLoadingFileProcessDto blfDto) {
		validating(blfDto);
		locating(blfDto);
		logging(blfDto);

	}

	@Override
	public void validating(BaseLoadingFileProcessDto blfDto) {
		this.init(blfDto);
		this.setLogDomain(new LogDomain());
		
	}

	@Override
	public void logging(BaseLoadingFileProcessDto blfDto) {
		if(this.logDomain != null){
		logDomain.setCreateBy(new Long(this.sessionUser.getUserId()));
		logDomain.setLastUpdateBy(new Long(this.sessionUser.getUserId()));
		if("".equals(blfDto.getFileLocation())){
			logDomain.setFileLocation("");
		}else{
			logDomain.setFileLocation(blfDto.getFileLocation());
		}
		logDomain.setFunctionCode(blfDto.getFunctionCode());
		logDomain.setModuleCode(blfDto.getModuleCode());
		logDomain.setOperationDate(blfDto.getOperationDate());
		logDomain.setOperationEndDate(new Date());
		logDomain.setOperationDesc(blfDto.getOperationDesc());
		logDomain.setOperationType(blfDto.getOperationType());
		logDomain.setReason(blfDto.getReason());
		logDomain.setStatus(blfDto.getStatus());
		logDomain.setUsrLogin(new Long(sessionUser.getUserId()));
		logService.save(logDomain);
		}
	}

	@Override
	public void locating(BaseLoadingFileProcessDto blfDto) {
		if(null != logDomain && FileLoadingConstant.UPLOAD_OPERATION.equals(blfDto.getOperationType())){
			 String dirPath = blfDto.getFileLocation();  
	            File dirFile = new File(dirPath);  
	            if (!dirFile.exists()) {  
	                dirFile.mkdirs();  
	            }  
	            File uploadFile = new File(dirFile,  
	                    blfDto.getMultipartFile().getOriginalFilename());  
	            try {
					FileCopyUtils.copy(this.mpf.getBytes(), uploadFile);
				} catch (IOException e) {
					logger.debug("File upload Failed");
					blfDto.setReason("File upload Failed");
					blfDto.setStatus(FileLoadingConstant.DEVIATION_REPORT_UPLOAD_FAILED);
					e.printStackTrace();
				}
			
		}else if(null != logDomain && FileLoadingConstant.DOWNLOAD_OPERATION.equals(blfDto.getOperationType())){
			if("".equals(blfDto.getFileLocation())){
				blfDto.setReason("File download Failed");
				blfDto.setStatus(FileLoadingConstant.DEVIATION_REPORT_UPLOAD_FAILED);
			}
		}
	}
	
	private void init(BaseLoadingFileProcessDto blfDto){
		if(null!=blfDto&&null!=blfDto.getMultipartFile()&&null == this.originalFileName&&null == this.in){
			this.originalFileName = blfDto.getMultipartFile().getOriginalFilename();
			this.mpf = blfDto.getMultipartFile();
			try {
				this.in = blfDto.getMultipartFile().getInputStream();
			} catch (IOException e) {
				logger.debug("IO ERROR BaseFileLoadingComponentImpl initFileInfo");
				e.printStackTrace();
			}
		}
		this.sessionUser = blfDto.getSessionUser();
		if(null == sessionUser){
			blfDto.setReason("Base Upload File sessionUser init Failed");
			logger.debug("Base Upload File sessionUser init Failed");
		}
	}

	private void setLogDomain(LogDomain logDomain) {
		this.logDomain = logDomain;
	}
}
