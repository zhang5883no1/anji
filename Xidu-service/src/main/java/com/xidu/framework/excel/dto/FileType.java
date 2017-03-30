package com.xidu.framework.excel.dto;

/**
 *
 */
public enum FileType {

	xlsx(".xlsx"), xls(".xls");

	private String value;

	private FileType(String value) {
		this.value = value;
	}

	public String getValue() {

		return value;
	}

}
