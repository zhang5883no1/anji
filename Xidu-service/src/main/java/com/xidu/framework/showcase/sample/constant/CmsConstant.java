/************************************************************************************
 * @File name   :      DossConstant.java
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
 * 2011-1-19 ����02:53:50			JUNCAI			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.showcase.sample.constant;

/**
 * This interface contains the constant.
 */
public interface CmsConstant {
	
	// key for get cms properties
	public static final String CMS_DOMAIN_KEY = "cms.server.domain";
	
	public static final String CMS_SERVER_POET_KEY = "cms.server.port";
	
	public static final String CMS_SERVER_DEFAULT_SITE_KEY = "cms.default.site";
	
	public static final String CMS_PROTOCAL_PREFIX_KEY = "cms.protocal.prefix";
	
	public static final String COOKIE_EXPIRY_TIME_KEY = "cookie.expiry.time";
	// key for doss
	public static final String CMS_SITE_USED_BY_DOSS_KEY = "cms.server.site.used.by.doss";
	
	public static final String DOSS_LOGIN_URL = "doss.server.login.url";
	
	//constant for login page
	public static final String LOGIN_PAGE_PARAM = "loginPage";
	public static final String LOGIN_PAGE_URL = "/login";
	
	//constant for logout page
	public static final String LOGOUT_PAGE_URL = "/logout";
	
	public static final String URI_STRING = "uriString";
	public static final String QUERY_STRING = "queryString";
	public static final String PREVIOUS_URL = "previousurl";
	
	//filter css and jpg script
	public static final String CSS_SUFFIX = ".css";
	public static final String JPG_SUFFIX = ".jpg";
	
	
	//page name for controller
	
	public static final String HEADER_PAGE = "common/header";
	public static final String LEFTNAV_PAGE = "common/leftnav";
	public static final String CONTENT_PAGE = "common/content";
	public static final String FOOTER_PAGE = "common/footer";
	
	public static final String CMS_CONTENT = "sample/cmscontent";
	public static final String CMS_CONTENT2 = "sample/cmscontent2";
	
	
	// page name for dealer
	public static final String DEALER_CREATE = "dealer/create";
	public static final String DEALER_SAVE_SUCCESS = "dealer/savesuccessfull";
	public static final String DEALER_QUERY = "dealer/query";
	public static final String DEALER_UPDATE = "dealer/update";	
	public static final String DEALER_UPDATE_SUCCESS = "dealer/updatesuccessfull";
	
	public static final String LOGIN_PAGE = "login";
	public static final String INEX_PAGE = "index";
	
	//constant for cookie key
	public static final String COOKIE_USER_KEY = "user";
	public static final String COOKIE_CODE_KEY = "code";
	
	// error message key
	public static final String NAME_PWD_ERROR_KEY = "user.name.pwd.wrong";
	
	public static final String REQUEST_ATTRIBUTE_PAGER = "pager";
	
	public static final String ERROR_PAGER = "error";
	
}
