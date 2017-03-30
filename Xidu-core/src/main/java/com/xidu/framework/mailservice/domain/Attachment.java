/************************************************************************************
 * @File name   :      Attachment.java
 *
 * @Author      :      YIFEGU
 *
 * @Date        :      2011-3-14
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2011-3-14 上午 09:42:14			YIFEGU			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.domain;

import java.io.Serializable;

/**
 * Attachment Domain Class
 */
public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filename;
	private String filepath;
	/**
	 * Get File Name
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * Set File Name
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * Get File Path
	 *
	 * @Date        :      2011-3-14
	 *
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * Set File Path
	 *
	 * @Date        :      2011-3-14
	 *
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}



}
