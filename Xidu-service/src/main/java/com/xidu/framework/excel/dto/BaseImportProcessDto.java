/******************************************************************************
 * @File name   :      BaseImportProcessDto.java
 * @Author      :      XINLYU
 * @Date        :      2013-5-28
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-5-28 下午3:55:23        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.excel.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.xidu.framework.common.dto.BasePagerDto;


public class BaseImportProcessDto<T> extends  BasePagerDto<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1247346399344228776L;
	private String operationUser;
	private String batchNo;
	private String memo;
	private Date operationDate;
	private String errorMsg;
	private Integer statusNo;
	protected String statusDesc;
	
	
	protected String createUser;
	protected Date createDate;
	protected String lastUpdateUser;
	protected Date lastUpdateDate;
	protected Integer excelRow;
	protected Integer excelColumn;
	
	//
	
	
	private MultipartFile multipartFile;
	private String onlyValidate="false";

	private String operationDateFrom;
	private String operationDateTo;
	
	
	protected String createDateStr;
	protected String lastUpdateDateStr;
	
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getLastUpdateDateStr() {
		return lastUpdateDateStr;
	}
	public void setLastUpdateDateStr(String lastUpdateDateStr) {
		this.lastUpdateDateStr = lastUpdateDateStr;
	}
	public String getOnlyValidate() {
		return onlyValidate;
	}
	public void setOnlyValidate(String onlyValidate) {
		this.onlyValidate = onlyValidate;
	}
	public String getOperationDateFrom() {
		return operationDateFrom;
	}
	public void setOperationDateFrom(String operationDateFrom) {
		this.operationDateFrom = operationDateFrom;
	}
	public String getOperationDateTo() {
		return operationDateTo;
	}
	public void setOperationDateTo(String operationDateTo) {
		this.operationDateTo = operationDateTo;
	}
	public String getStatusDesc() {
		return BatchProcessStatusEnum.getDescByStatus(String.valueOf(statusNo));
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public Integer getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(Integer statusNo) {
		this.statusNo = statusNo;
	}
	public String getErrorMsg() {
		if(this.errorMsg == null){
			this.errorMsg = new String();
		}
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getOperationUser() {
		return operationUser;
	}
	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Integer getExcelRow() {
		return excelRow;
	}
	public void setExcelRow(Integer excelRow) {
		this.excelRow = excelRow;
	}
	public Integer getExcelColumn() {
		return excelColumn;
	}
	public void setExcelColumn(Integer excelColumn) {
		this.excelColumn = excelColumn;
	}

}
