/************************************************************************************
 * @File name   :      FileErrorConstants.java
 *
 * @Author      :      junyi cai
 *
 * @Date        :      2011-3-21
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                          Who         Version        Comments
 * 2011-3-21 上午11:49:10        junyi cai     1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.file.constant;

import com.xidu.framework.common.constant.CommonErrorConstants;

/**
 * This is the error constants for file service
 */
public interface FileErrorConstants extends CommonErrorConstants {
   
    /**
     * Key for init file service exception.
     */
    public static final String INIT_FILE_SERVICE_JSCH_EXCEPTION = 
        "INIT_FILE_SERVICE_JSCH_EXCEPTION";
    
    /**
     * Key for get channel for file service exception.
     */
    public static final String CONNECT_FILE_SERVICE_JSCH_EXCEPTION = 
        "CONNECT_FILE_SERVICE_JSCH_EXCEPTION";
   
    /**
     * Key for list file content from file service exception.
     */
    public static final String LIST_FILE_SERVICE_JSCH_EXCEPTION = 
        "LIST_FILE_SERVICE_JSCH_EXCEPTION";
    
    /**
     * Key for null exception.
     */
    public static final String FILE_SERVICE_NULL_EXCEPTION = 
        "FILE_SERVICE_NULL_EXCEPTION";
    
    /**
     * Key for download exception.
     */
    public static final String FILE_SERVICE_DOWNLOAD_EXCEPTION = 
        "FILE_SERVICE_DOWNLOAD_EXCEPTION";
    
    /**
     * Key for upload exception.
     */
    public static final String FILE_SERVICE_UPLOAD_EXCEPTION = 
        "FILE_SERVICE_UPLOAD_EXCEPTION";
    
    /**
     * Key for save file log exception.
     */
    public static final String FILE_SERVICE_LOG_EXCEPTION = 
        "FILE_SERVICE_LOG_EXCEPTION";
    
}
