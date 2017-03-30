/******************************************************************************
 * @File name   :      FileModeInfo.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-20
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-20 下午1:02:51        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.fileloading.dto;

public enum FileModeInfo {
	DEVIATION_REPORT("fileupload_location_deviation_report","Reporting","Deviation Report");
	private String locateKey;
	private String menu;
	private String function;
	
	private FileModeInfo (String locateKey,String menu,String function){
		this.locateKey = locateKey;
		this.menu = menu;
		this.function =  function;
	}
	
	public static FileModeInfo getInstance(String locateKey){
		for (FileModeInfo c : FileModeInfo.values()) {
			if (c.getLocateKey().equals(locateKey)) {
				return c;
			}
		}
		return null;
	}
	
	public String getLocateKey(){
		return this.locateKey;
	}

	public String getMenu() {
		return menu;
	}

	public String getFunction() {
		return function;
	}
	
	

}
