package com.xidu.framework.common.util;

/************************************************************************************
 * @File name   :      Snippet.java
 *
 * @Author      :      JUNJZHU
 *
 * @Date        :      2013-3-18
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
 * 2013-3-18 下午4:05:14			JUNJZHU			1.0				Initial Version
 ************************************************************************************/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ServerUtil {
	private static Logger _log = LoggerFactory.getLogger(ServerUtil.class);

	public static String getDefaultLiferayHome() {
		String defaultLiferayHome = null;

		if (ServerDetector.isGeronimo()) {
			defaultLiferayHome = System
					.getProperty("org.apache.geronimo.home.dir") + "/..";
		} else if (ServerDetector.isGlassfish()) {
			defaultLiferayHome = System.getProperty("com.sun.aas.installRoot")
					+ "/..";
		} else if (ServerDetector.isJBoss()) {
			defaultLiferayHome = System.getProperty("jboss.home.dir") + "/..";
		} else if (ServerDetector.isJOnAS()) {
			defaultLiferayHome = System.getProperty("jonas.base") + "/..";
		} else if (ServerDetector.isWebLogic()) {
			defaultLiferayHome = System.getProperty("env.DOMAIN_HOME") + "/..";
		} else if (ServerDetector.isJetty()) {
			defaultLiferayHome = System.getProperty("jetty.home") + "/..";
		} else if (ServerDetector.isResin()) {
			defaultLiferayHome = System.getProperty("resin.home") + "/..";
		} else if (ServerDetector.isTomcat()) {
//			defaultLiferayHome = System.getProperty("catalina.base") + "/..";
			return System.getProperty("user.dir").substring(0,System.getProperty("user.dir").length()-4)+"\\webapps\\vcc\\WEB-INF\\classes";
		} else {
			defaultLiferayHome = System.getProperty("user.dir") + "/src/main/resource";
		}

		defaultLiferayHome = defaultLiferayHome.replace('\\', '/');

		defaultLiferayHome = defaultLiferayHome.replace("//", "/");

		if (defaultLiferayHome.endsWith("/..")) {
			int pos = defaultLiferayHome.lastIndexOf('/',
					defaultLiferayHome.length() - 4);

			if (pos != -1) {
				defaultLiferayHome = defaultLiferayHome.substring(0, pos);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Default Liferay home " + defaultLiferayHome);
		}
		return defaultLiferayHome;
	}

}
