/******************************************************************************
 * @File name   :      CommonConstants.java
 *
 * @Author      :      Junyi Cai
 *
 * @Date        :      2011-3-28
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
 * 2011-3-28 下午04:18:22        Junyi Cai     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.common.constant;

/**
 * Define the common constants for common service
 */
public interface CommonConstants {
    
    /**
     * Constant for Multiple Wildcard Character of SQL Like Condition
     */
    public static final String MULTIPLE_WILDCARD_CHARACTER = "%";
    
    /**
     * Constant for Single Wildcard Character of SQL Like Condition
     */
    public static final String SINGLE_WILDCARD_CHARACTER = "_";
    
    
    /**
     * Date Format Pattern 'yyyy-MM-dd'
     */
    public static final String DATE_FORMATE_YYMMDD = "yyyy-MM-dd";
    
    /*common*/
	public static final String EC_I="ec_i";
}
