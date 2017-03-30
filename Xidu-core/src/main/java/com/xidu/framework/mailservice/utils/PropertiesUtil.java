/************************************************************************************
 * @File name   :      PropertiesUtil.java
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
 * 2011-3-14下午01:36:42			YIFEGU			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;



/**
 * Properties Utility Class
 */
public class PropertiesUtil {
	
private static Properties prop;
private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	
	static{
		InputStream is = PropertiesUtil.class.getClassLoader()
		.getResourceAsStream("mail_service.properties");
		 prop = new Properties();
		try {
			prop.load(is);
		} catch (Exception e) {
		    logger.error("Can not read Mail_Service.properties!" + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				    logger.error("Close inputstream error!" + e);
				}
				is = null;
			}
		}
	}
	
	/**
	 * Get property value by key
	 * @Date        :      2011-4-6
	 * @param key property key
	 * @return property value
	 */
	public static String getProperties(String key){
		String value = prop.getProperty(key);
		return value;
	}	

}
