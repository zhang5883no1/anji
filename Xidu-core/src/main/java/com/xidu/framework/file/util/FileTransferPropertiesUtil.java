/************************************************************************************
 * @File name   :      FileTransferPropertiesUtil.java
 *
 * @Author      :      junyi cai
 *
 * @Date        :      2011-3-2
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                         Who         Version        Comments
 * 2011-3-2 下午03:27:17        junyi cai     1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.file.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xidu.framework.file.constant.FileConstants;

/**
 * This class is used to load and get value from properties
 */
public final class FileTransferPropertiesUtil {

    private static Logger logger = Logger.getLogger(FileTransferPropertiesUtil.class);
    private static Properties prop;

    // load the properties value from file

    static {
        InputStream in = null;
        try {
            in = FileTransferPropertiesUtil.class.getClassLoader().getResourceAsStream(
                FileConstants.CONFIG_NAME);
            // in = new FileInputStream("conf/file_transfer.properties");
            prop = new Properties();
            prop.load(in);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Protect the constructor method for this util class.
     * 
     * @Date : 2011-3-28
     */
    private FileTransferPropertiesUtil() {

    }

    /**
     * Get value from properties by key.
     * 
     * @Date : 2011-3-18
     * @param key
     *            key in the properties file
     * @return value of the key
     */
    public static String getProperties(String key) {
        return prop.getProperty(key);
    }

}
