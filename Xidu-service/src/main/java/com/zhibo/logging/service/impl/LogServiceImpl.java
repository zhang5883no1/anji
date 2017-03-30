/******************************************************************************
 * @File name   :      LogServiceImpl.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午11:04:40        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.logging.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.impl.BaseServiceImpl;
import com.xidu.framework.common.util.Utils;
import com.zhibo.logging.dao.LogDao;
import com.zhibo.logging.domain.LogDomain;
import com.zhibo.logging.dto.LogDto;
import com.zhibo.logging.service.LogService;
@Transactional(rollbackFor = AppException.class)
@Service
public class LogServiceImpl extends BaseServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	@Override
	public void updateLog(LogDomain updateDomain) {
		logDao.update(updateDomain);
	}

	@Override
	public void removeLog(LogDomain removeDomain) {
		logDao.remove(removeDomain);
	}

	@Override
	public LogDto searchLogById(LogDomain queryDto) {
		LogDto resultDto = (LogDto) Utils.convertDomainToDto(logDao.findById(queryDto.getId()), LogDto.class);
		return resultDto;
	}

	@Override
	public void save(LogDomain saveDomain) {
		logDao.save(saveDomain);
	}
}
