/******************************************************************************
 * @File name   :      FileUploadFacade.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午11:46:58        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.fileloading.service;

import com.xidu.framework.fileloading.dto.BaseLoadingFileProcessDto;

public interface FileLoadingFacade {
	
	void validating(BaseLoadingFileProcessDto blfDto);
	void logging(BaseLoadingFileProcessDto blfDto);
	void locating(BaseLoadingFileProcessDto blfDto);

}
