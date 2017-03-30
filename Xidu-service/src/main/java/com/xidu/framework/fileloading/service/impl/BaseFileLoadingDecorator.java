/******************************************************************************
 * @File name   :      BaseFileUploadServiceImpl.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午11:53:35        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.fileloading.service.impl;

import com.xidu.framework.fileloading.dto.BaseLoadingFileProcessDto;
import com.xidu.framework.fileloading.service.FileLoadingComponent;


public class BaseFileLoadingDecorator implements FileLoadingComponent {
	
	private FileLoadingComponent fileLoadingService;
	
	public BaseFileLoadingDecorator(FileLoadingComponent fileLoadingService){
		this.fileLoadingService = fileLoadingService;
	}

	@Override
	public void doUploading(BaseLoadingFileProcessDto blfDto) {
		fileLoadingService.doUploading(blfDto);
	}

	@Override
	public void doDownloading(BaseLoadingFileProcessDto blfDto) {
		fileLoadingService.doDownloading(blfDto);
	}
}
