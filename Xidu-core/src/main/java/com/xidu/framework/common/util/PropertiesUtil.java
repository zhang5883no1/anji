package com.xidu.framework.common.util;

/************************************************************************************
 * @File name   :      PropertiesUtil.java
 *
 * @Author      :      weichu wang
 *
 * @Date        :      2012-12-3
 *
 * @Copyright Notice: 
 * Copyright (c) 2012 Shanghai OnStar, Inc. All  Rights Reserved.
 * This software is published under the terms of the Shanghai OnStar Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2012-12-3 上午11:11:47			weichu wang			1.0				Initial Version
 ************************************************************************************/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * get propert info from sos-common/commonConfig.properties
 */
public class PropertiesUtil {
	
	private static Properties gloableConfig = null;
	
	static Log log = LogFactory.getLog(PropertiesUtil.class);

	// init gloableconfig from .propertis
	static {
		if (gloableConfig == null) {
			InputStream is = null;
			try {
//				 is = new FileInputStream(ServerUtil.getDefaultLiferayHome()+"/config.properties");
				log.debug("==============classpath:"+Thread.currentThread().getContextClassLoader().getResource(""));
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
				gloableConfig = new Properties();
				gloableConfig.load(is);
			} catch (IOException e) {
				log.error(ExceptionUtils.getFullStackTrace(e));
			}finally {
				try {
					if(is!=null) {
						is.close();
					}
				} catch (IOException e) {
					log.error(ExceptionUtils.getFullStackTrace(e));
				}
			}
		}
	}

	// get value by key
	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param key
	 * @return value
	 */
	public static String getPropertyValueByKey(String key) {
		return gloableConfig.getProperty(key);
	}
	
}
