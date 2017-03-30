/******************************************************************************
 * @File name   :      DeviationFileLoadingConcreteDecorator.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 下午2:36:27        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.reporting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;

import com.xidu.framework.common.util.ApplicationContextUtil;
import com.xidu.framework.fileloading.dto.BaseLoadingFileProcessDto;
import com.xidu.framework.fileloading.service.FileLoadingComponent;
import com.xidu.framework.fileloading.service.impl.BaseFileLoadingDecorator;
import com.zhibo.mainTain.dao.CustomerBasicInfoDao;
import com.zhibo.reporting.dto.DeviationFileLoadingProcess;

public class DeviationFileLoadingConcreteDecorator extends
		BaseFileLoadingDecorator implements FileLoadingComponent {
	@Autowired
	private CustomerBasicInfoDao customerBasicInfoDao;
	private AbstractMessageSource messageSource = (AbstractMessageSource) ApplicationContextUtil
			.getBean("messageSource");

	public DeviationFileLoadingConcreteDecorator(
			FileLoadingComponent fileLoadingService) {
		super(fileLoadingService);
	}

	@Override
	public void doUploading(BaseLoadingFileProcessDto blfDto) {
		if (validation((DeviationFileLoadingProcess) blfDto)) {
			logDeviationInfo((DeviationFileLoadingProcess) blfDto);
			super.doUploading(blfDto);
		}
	}

	@Override
	public void doDownloading(BaseLoadingFileProcessDto blfDto) {
		super.doDownloading(blfDto);
	}

	private boolean validation(DeviationFileLoadingProcess dflDto) {
//		if(!dflDto.getSelectedMonth().equals(dflDto.getMonth())){
//			
//			dflDto.setErrorMsg(messageSource.getMessage("upload.deviation.report", null,Locale.ENGLISH));
//			return false;
//			
//		}
//		if (FileLoadingConstant.UPLOAD_OPERATION.equals(dflDto
//				.getOperationType()) && null != dflDto.getSelectedMonth()&& (dflDto.getSelectedMonth()).equals(dflDto.getMonth())) {
//			return true;
//		} else if (FileLoadingConstant.DOWNLOAD_OPERATION.equals(dflDto
//				.getOperationType())
//				&& null != dflDto.getMonth()
//				&& null != dflDto.getSelectedMonth()) {
//			if (dflDto.getMonth().equals(dflDto.getSelectedMonth())) {
//				return true;
//			} else {
//				return true;
//			}
//		} else {
//			return false;
//		}
		return true;
	}
	private void logDeviationInfo(DeviationFileLoadingProcess dflDto){
//		DeviationInfoDomain deviationInfoDomain = (DeviationInfoDomain) Utils.convertDtoToDomain(dflDto.getDeviationInfoDto(), DeviationInfoDomain.class);
//		Long customerId = dflDto.getDeviationInfoDto().getCustomerId();
//		CustomerBasicInfo customerBasicInfo =  customerBasicInfoDao.findById(customerId);
//		deviationInfoDomain.setId(null);
//		deviationInfoDomain.setCustomerBasicInfo(customerBasicInfo);
//		deviationDao.save(deviationInfoDomain);
		
	}

}
