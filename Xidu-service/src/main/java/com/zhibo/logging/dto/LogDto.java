/******************************************************************************
 * @File name   :      LogDto.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午10:38:38        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.logging.dto;

import java.util.Date;

import com.xidu.framework.common.dto.BasePagerDto;

public class LogDto<T> extends BasePagerDto<T>{
	private static final long serialVersionUID = 328742981526780736L;
	private Long id;
	private Long usrLogin;//USR_LOGIN
	private String moduleCode;//MODULE_CODE
	private String functionCode;//FUNCTION_CODE
	private String operationType;//OPERATION_TYPE
	private String operationDesc;//OPERATION_DESC
	private String status;//STATUS
	private String reason;//REASON
	private Date operationDate;//OPERATION_DATE
	private Date operationEndDate;//OPERATION_END_DATE
	private String fileLocation;//FILE_LOCATION
	private Long createBy;

	// protected Date createDate
	private Date createDate;

	// modifier id of last modifier
	private Long lastUpdateBy;

	// protected Date updateDate;
	private Date lastUpdateDate;
	private Long deleteFlag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsrLogin() {
		return usrLogin;
	}
	public void setUsrLogin(Long usrLogin) {
		this.usrLogin = usrLogin;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public Date getOperationEndDate() {
		return operationEndDate;
	}
	public void setOperationEndDate(Date operationEndDate) {
		this.operationEndDate = operationEndDate;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
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
	public Long getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Long deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	

}
