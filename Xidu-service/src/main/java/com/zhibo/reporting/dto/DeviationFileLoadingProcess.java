/******************************************************************************
 * @File name   :      DeviationFileLoadingProcess.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-21
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-21 下午4:40:29        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.reporting.dto;

import com.xidu.framework.fileloading.dto.BaseLoadingFileProcessDto;

public class DeviationFileLoadingProcess extends BaseLoadingFileProcessDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1858678557980914950L;
	private String year;
	private String month;
	private String selectedMonth;
	private String customerId;
	private DeviationInfoDto deviationInfoDto;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSelectedMonth() {
		return selectedMonth;
	}
	public void setSelectedMonth(String selectedMonth) {
		this.selectedMonth = selectedMonth;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public DeviationInfoDto getDeviationInfoDto() {
		return deviationInfoDto;
	}
	public void setDeviationInfoDto(DeviationInfoDto deviationInfoDto) {
		this.deviationInfoDto = deviationInfoDto;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
