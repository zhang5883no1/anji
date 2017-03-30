/******************************************************************************
 * @File name   :      LogDomain.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 上午10:35:19        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.logging.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xidu.framework.common.domain.BaseDomain;
public class LogDomain extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 328742981526780736L;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MODULE_CODE")
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	@Column(name = "FUNCTION_CODE")
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	@Column(name = "OPERATION_TYPE")
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	@Column(name = "OPERATION_DESC")
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	@Column(name = "FILE_LOCATION")
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	@Column(name = "USR_LOGIN")
	public Long getUsrLogin() {
		return usrLogin;
	}

	public void setUsrLogin(Long usrLogin) {
		this.usrLogin = usrLogin;
	}
	@Column(name = "OPERATION_DATE")
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	@Column(name = "OPERATION_END_DATE")
	public Date getOperationEndDate() {
		return operationEndDate;
	}

	public void setOperationEndDate(Date operationEndDate) {
		this.operationEndDate = operationEndDate;
	}
	
	

}
