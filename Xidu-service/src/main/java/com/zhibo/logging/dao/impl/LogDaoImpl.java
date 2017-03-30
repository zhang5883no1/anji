/******************************************************************************
 * @File name   :      LogDaoImpl.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午10:57:36        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.logging.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.zhibo.logging.dao.LogDao;
import com.zhibo.logging.domain.LogDomain;
@Repository
public class LogDaoImpl extends BaseDaoImpl<LogDomain, Long> implements LogDao {

	@Autowired
	public LogDaoImpl(@Value("com.zhibo.logging.domain.LogDomain")Class<LogDomain> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	

}
