/************************************************************************************
 * @File name   :      FileConstants.java
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
 * 2011-3-21 上午10:57:39        junyi cai     1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.file.constant;

/**
 * constant for file service
 */
public interface FileConstants {

    /**
     * Transfer type for download
     */
    public static final int DOWNLOAD = 1;

    /**
     * Transfer type for upload
     */
    public static final int UPLOAD = 2;

    /**
     * Transfer status when successful
     */
    public static final String TRANSFER_SUCCESSFUL = "Success";

    /**
     * Transfer status when failure
     */
    public static final String TRANSFER_FAILURE = "Failed";
    
    /**
     * Key for sftp
     */
    public static final String SFTP = "sftp";
    
    /**
     * constant for .
     */
    public static final String DOT = ".";
    
    /**
     * constant for ..
     */
    public static final String DOUBLE_DOT = "..";
    
    /**
     * constant for StrictHostKeyChecking
     */
    public static final String STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
    
    /**
     * constant for no value
     */
    public static final String NO = "no";
    
    /**
     * Key for host name
     */
    public static final String HOST_KEY = "file.transfer.host";

    /**
     * Key for port
     */
    public static final String PORT_KEY = "file.transfer.port";

    /**
     * Key for user name
     */
    public static final String USER_NAME_KEY = "file.transfer.username";

    /**
     * Key for password
     */
    public static final String PASSWORD_KEY = "file.transfer.password";
    
    /**
     * Key for max worker count
     */
    public static final String MAX_WORKER_COUNT_KEY = "file.transfer.maxWorkerCount";
    
    /**
     * file config file name
     */
    public static final String CONFIG_NAME = "file_transfer.properties";
    
    
}
