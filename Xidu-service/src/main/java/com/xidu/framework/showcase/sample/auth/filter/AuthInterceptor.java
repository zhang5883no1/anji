/******************************************************************************
 * @File name   :      AuthInterceptor.java
 *
 * @Author      :      junyi cai
 *
 * @Date        :      2011-4-8
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2011-4-8 下午05:02:34        junyi cai     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.showcase.sample.auth.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    
    private static Logger logger = Logger.getLogger(AuthInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest req,
            HttpServletResponse res, Object handler) throws Exception {
    	
    	
    	//in this filter you should check if the user is login
    	return true;
    	
    }
}
