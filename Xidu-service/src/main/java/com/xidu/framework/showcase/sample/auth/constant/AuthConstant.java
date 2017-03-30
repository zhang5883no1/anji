/************************************************************************************
 * @File name   :      DossConstant.java
 *
 * @Author      :      caijunyi
 *
 * @Date        :      2011-1-26
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
 * 2011-1-26 下午04:38:41			caijunyi			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.showcase.sample.auth.constant;
/**
 * This interface contains the constant.
 */
public interface AuthConstant {
	// key for get doss properties
public static final String DOSS_DOMAIN_KEY = "doss.server.domain";
    
    public static final String DOSS_SERVER_POET_KEY = "doss.server.port";
    
    public static final String DOSS_SERVER_DEFAULT_SITE_KEY = "doss.default.site";
    
    public static final String DOSS_PROTOCAL_PREFIX_KEY = "doss.protocal.prefix";
    
    public static final String COOKIE_EXPIRY_TIME_KEY = "cookie.expiry.time";
    
    //constant for login page
    public static final String LOGIN_PAGE_PARAM = "loginPage";
    
    public static final String URI_STRING = "uriString";
    public static final String QUERY_STRING = "queryString";
    public static final String PREVIOUS_URL = "previousurl";
    
	   
    //constant for logout 
    public static final String LOGOUT_PAGE_URL = "/logout";
    //constant for logout 
    public static final String LOGIN_PAGE_URL = "/login";
	
	//constant for cookie key
	public static final String COOKIE_USER_KEY = "user";
	public static final String COOKIE_CODE_KEY = "code";
	
	//constant for workbench
	public static final String WORKBENCH_LOGIN_URL_KEY = "workbench.login.url";
	
}
