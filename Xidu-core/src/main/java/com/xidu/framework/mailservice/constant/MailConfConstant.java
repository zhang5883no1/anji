/************************************************************************************
 * @File name   :      MailConfConstant.java
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
 * 2011-3-14 下午 01:42:47			YIFEGU			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.mailservice.constant;

/**
 * Mail Configuration Constant
 */
public interface MailConfConstant {
    
    /**
     * Constant Key of Mail Service Host 
     */
	public static final String MAIL_SERVICE_HOST = "mail.service.host";
	
	public static final String MAIL_SERVICE_PORT="mail.service.port";
	/**
	 * Constant Key of Mail Service User Name
	 */
	public static final String MAIL_SERVICE_USERNAME = "mail.service.username";
	/**
	 * Constant Key of Mail Service Password
	 */
	public static final String MAIL_SERVICE_PASSWORD = "mail.service.password";
	/**
	 * Constant key of Mail Service Session JNDI Name
	 */
	public static final String MAIL_SERVICE_SESSION_JNDI_NAME = "mail.service.session.jndi.name";
	/**
	 * Constant key of Mail Service Template Location
	 */
	public static final String MAIL_SERVICE_TEMPLATE_LOCATION = "mail.service.template.location";
	/**
	 * Constant key of Mail Service Template Type
	 */
	public static final String MAIL_SERVICE_TEMPLATE_TYPE = "mail.service.template.type";
	/**
     * Constant key of Mail Service Protocol Type
     */
    public static final String MAIL_SERVICE_PROTOCOL_TYPE = "mail.service.protocol.type";

}
