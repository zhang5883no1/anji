/************************************************************************************
 * @File name   :      MailTemplate.java
 *
 * @Author      :      YIFEGU
 *
 * @Date        :      2011-3-4
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
 * 2011-3-4 下午05:32:01			YIFEGU			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Mail Template Domain
 */
public class MailTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,String> editPointMap;
	private String templateName;
	/**
	 * Get Template Name
	 *
	 * @Date        :      2011-3-4
	 *
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}
	/**
	 * Set Template Name
	 *
	 * @Date        :      2011-3-4
	 *
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**
	 * Get Edit PointMap
	 *
	 * @Date        :      2011-3-4
	 *
	 * @return the editPointMap
	 */
	public Map<String,String> getEditPointMap() {
		return editPointMap;
	}
	/**
	 * Set Edit PointMap
	 *
	 * @Date        :      2011-3-4
	 *
	 * @param editPointMap the editPointMap to set
	 */
	public void setEditPointMap(Map<String, String> editPointMap) {
		this.editPointMap = editPointMap;
	}


}
