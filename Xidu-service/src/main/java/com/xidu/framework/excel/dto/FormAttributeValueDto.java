package com.xidu.framework.excel.dto;
public class FormAttributeValueDto {
	
	private Long rowIndex;
	private Long columnIndex;
	private String attributeName;
	private String attributeValue;
	
	public Long getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(Long rowIndex) {
		this.rowIndex = rowIndex;
	}	
	
	public Long getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(Long columnIndex) {
		this.columnIndex = columnIndex;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	

}
