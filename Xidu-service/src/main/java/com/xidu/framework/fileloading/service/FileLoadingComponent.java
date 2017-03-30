/******************************************************************************
 * @File name   :      FileUploadService.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 下午1:20:06        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.fileloading.service;

import com.xidu.framework.fileloading.dto.BaseLoadingFileProcessDto;

public interface FileLoadingComponent {
	void doUploading(BaseLoadingFileProcessDto blfDto);
	
	void doDownloading(BaseLoadingFileProcessDto blfDto);

}
