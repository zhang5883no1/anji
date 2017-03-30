/******************************************************************************
 * @File name   :      BaseServiceImpl.java
 *
 * @Author      :      Jianxi Wu
 *
 * @Date        :      2011-4-1
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
 * 2011-4-1 下午04:06:44        Jianxi Wu     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.common.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;

import com.xidu.framework.common.service.IBaseService;

/**
 *
 */
public class BaseServiceImpl implements IBaseService {

    protected Logger logger;

    /**
     * @Date : 2011-4-2
     * 
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * @Date : 2011-4-2
     * 
     * @param logger
     *            the logger to set
     */
    @Resource(name = "logger")
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
