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
package com.xidu.framework.usermgnt.constant;

/**
 * This interface contains the constant.
 */
public interface WorkbenchConstant {
	
	public static final String LOGIN_PAGE = "login";
	public static final String INEX_PAGE = "index";
	public static final String LOCKED_PAGE = "locked";
	 
	// error message key
    public static final String NAME_PWD_ERROR_KEY = "user.name.pwd.wrong";
    public static final String NAME_PWD_LOCKED_KEY = "user.name.pwd.locked";
    public static final String NAME_PWD_TO_LOCKED_KEY = "user.name.pwd.to.locked";
    
    //session 
    public static final String SESSION_USER="currentUser";
    public static final String GROUPLIST="groupList";
    public static final String ROOMLIST="roomList";
    
}
