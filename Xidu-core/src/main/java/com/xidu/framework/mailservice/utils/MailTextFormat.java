/************************************************************************************
 * @File name   :      MailTextFormat.java
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
 * 2011-3-4 ����05:40:13			YIFEGU			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Mail Text Format Util Class
 */
public class MailTextFormat {
    
	/**
	 * Format Mail By Template
	 * @Date        :      2011-4-6
	 * @param templateContent template content before being formated
	 * @param editPointMap content points to be edit or replace according template
	 * @return Formated String
	 *  
	 */
	public static String formatMailByTemplate(String templateContent, Map editPointMap){
		String formattedContent = templateContent;
		if(editPointMap == null){
			return templateContent;
		}
		Set key = editPointMap.keySet();
		Iterator it = key.iterator();
		while(it.hasNext()){
			Object editPoint = it.next();
			formattedContent = formattedContent.replace("{" + editPoint + "}", (String)editPointMap.get(editPoint));
			
		}
		return formattedContent;
	}

}
