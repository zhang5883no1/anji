/******************************************************************************
 * @File name   :      LogController.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午10:32:12        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.logging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zhibo.logging.service.LogService;

@Controller
public class LogController {
	@Autowired
	private LogService logService;
}
