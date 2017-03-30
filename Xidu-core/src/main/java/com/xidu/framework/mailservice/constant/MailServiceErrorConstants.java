/******************************************************************************
 * @File name   :      MailServiceErrorConstants.java
 *
 * @Author      :      guyifeng
 *
 * @Date        :      2011-4-19
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
 * 2011-4-19 上午09:01:14        guyifeng     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.mailservice.constant;

import com.xidu.framework.common.constant.CommonErrorConstants;

/**
 *  Constant for Mail Service Exception
 */
public interface MailServiceErrorConstants extends CommonErrorConstants {
    
    /**
     *  Error key of get session exception
     */
    final String GET_MAIL_SESSION_FROM_JNDI_EXCEPTION = "GET_MAIL_SESSION_FROM_JNDI_EXCEPTION";
    
    /**
     *  Error key of read mail template exception
     */
    final String READ_MAIL_TEMPLATE_EXCEPTION = "READ_MAIL_TEMPLATE_EXCEPTION";

}
