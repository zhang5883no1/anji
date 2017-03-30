/**
 * 
 */
package com.zhibo.common.dto;

import com.xidu.framework.common.dto.BaseDto;

/**
 * @author WEICWANG
 *
 */
public class SupplierReturnDto extends BaseDto {
    private String id;
    private String supplierCode;
    private String supplierNameCn;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierNameCn() {
		return supplierNameCn;
	}
	public void setSupplierNameCn(String supplierNameCn) {
		this.supplierNameCn = supplierNameCn;
	}
	
	
}
