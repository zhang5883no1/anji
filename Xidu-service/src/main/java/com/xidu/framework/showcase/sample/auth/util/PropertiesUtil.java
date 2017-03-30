/************************************************************************************
 * @File name   :      PropertiesUtil.java
 *
 * @Author      :      JUNCAI
 *
 * @Date        :      2011-1-19
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
 * 2011-1-19 ����01:13:16			JUNCAI			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.showcase.sample.auth.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class is used to get properties from properties files
 */
public class PropertiesUtil {
	
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	private static Properties prop;
	
	static{
		InputStream is = PropertiesUtil.class.getClassLoader()
		.getResourceAsStream("config.properties");
		 prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			logger.error("Could not read properties file", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("close inputstream error", e);
				}
				is = null;
			}
		}
	}
	
	//get properties from config file
	public static String getProperties(String key){
		String value = prop.getProperty(key);
		return value;
	}
	
}
