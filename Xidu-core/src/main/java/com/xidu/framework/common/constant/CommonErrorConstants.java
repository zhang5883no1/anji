/************************************************************************************
 * @File name   :      CommonErrorConstants.java
 *
 * @Author      :      Brenda Yin
 *
 * @Date        :      2011-1-11
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                             Who             Version         Comments
 * 2011-1-11 下午02:22:10            Brenda Yin         1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.constant;

/**
 * Defines all the error codes of common exceptions.
 */
public interface CommonErrorConstants {

    /**
     * Constant to represent not found exception.
     */
    final String NOT_FOUND = "NOT_FOUND"; // or using number to specify each
                                          // error, e.g.1001

    /**
     * Constant to represent null exception.
     */
    final String IS_NULL = "IS_NULL";
    
    
    /**
     * Constant to represent system exception
     */
    final String SYSTEM_ERROR = "SYSTEM_ERROR";
    
    /**
     * Constant to represent common method parameter error
     */
    final String COMMON_METHOD_PARAM_ERROR = "COMMON_METHOD_PARAM_ERROR";

    /**
     * 
     */
    final String SYSTEM_EXCEPTION_MESSAGE_PREFIX = "System's Exception:";
}
