/******************************************************************************
 * @File name   :      DeviationInfoDto.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 下午5:17:21        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.reporting.dto;

import java.util.Date;

import com.xidu.framework.common.dto.BasePagerDto;
import com.zhibo.mainTain.domain.CustomerBasicInfo;

public class DeviationInfoDto<T> extends  BasePagerDto<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4505251399248243545L;
	private Long id;//ID
	private Long customerId;//CUSTOMER_ID
	private Long month;// MONTH
	private String fileName;//FILE_NAME
	private Long createBy;//CREATE_BY
	private Date createDate;//CREATE_DATE
	private Long lastUpdateBy;//LAST_UPDATE_BY
	private Date lastUpdateDate;//LAST_UPDATE_DATE
	private CustomerBasicInfo customerBasicInfo;
	
	private Long year;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public CustomerBasicInfo getCustomerBasicInfo() {
		return customerBasicInfo;
	}
	public void setCustomerBasicInfo(CustomerBasicInfo customerBasicInfo) {
		this.customerBasicInfo = customerBasicInfo;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	
}
