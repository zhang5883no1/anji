/************************************************************************************
 * @File name   :      SampleErrorConstants.java
 *
 * @Author      :      Brenda Yin
 *
 * @Date        :      2011-1-19
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
 * 2011-1-19 下午02:31:15            Brenda Yin         1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.common.constant;

import com.xidu.framework.common.constant.CommonErrorConstants;

/**
 * This interface defines the error codes of the exceptions thrown in samples.
 */
public interface SampleErrorConstants extends CommonErrorConstants {
    /**
     * Constant to indicate that person is not found.
     */
    final String PERSON_NOT_FOUND = "PERSON_NOT_FOUND";

}
