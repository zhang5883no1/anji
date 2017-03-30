/******************************************************************************
 * @File name   :      LogService.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午11:02:25        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.logging.service;

import com.xidu.framework.common.service.IBaseService;
import com.zhibo.logging.domain.LogDomain;
import com.zhibo.logging.dto.LogDto;

public interface LogService extends IBaseService{
	void updateLog(LogDomain updateDomain);
	
	void removeLog(LogDomain removeDomain);
	
	LogDto searchLogById(LogDomain queryDto);
	
	void save(LogDomain saveDomain);
	

}
