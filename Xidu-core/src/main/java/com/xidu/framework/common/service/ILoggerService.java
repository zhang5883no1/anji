/************************************************************************************
 * @File name   :      LoggerService.java
 *
 * @Author      :      guyifeng
 *
 * @Date        :      2011-2-16
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                             Who                 Version         Comments
 * 2011-2-16 上午09:43:36            guyifeng            1.0           Initial Version
 ************************************************************************************/
package com.xidu.framework.common.service;

import com.xidu.framework.common.domain.Log;
import com.xidu.framework.common.exception.AppException;

/**
 * Common interface for Logger Service
 */
public interface ILoggerService {

    /**
     * Initialization method
     * 
     * @Date : 2011-3-28
     * @return true/false for initialization result
     */
    public boolean init();

    /**
     * Do logging method
     * 
     * @Date : 2011-3-28
     * @param log
     *            instance
     * @return true/false for logging result
     * @throws AppException 
     */
    public boolean log(Log log) throws AppException;

}
