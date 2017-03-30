/************************************************************************************
 * @File name   :      ApplicationContextUtil.java
 *
 * @Author      :      Junyi Cai
 *
 * @Date        :      2011-3-3
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                         Who             Version            Comments
 * 2011-3-3 05:28:03            Juncai Cai          1.0                Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * overridden: {@inheritDoc}
     * 
     * @Date : 2011-3-28
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     * 
     */
    @Override
    public void setApplicationContext(ApplicationContext ac) {

        context = ac;
    }

    /**
     * get application context instance
     * 
     * @Date : 2011-3-28
     * @return application context instance
     */
    public static ApplicationContext getApplicationContext() {

        return context;

    }

    /**
     * return bean instance in spring ioc container
     * 
     * @Date : 2011-4-14
     * @param beanName
     *            - bean name defined in spring application context
     * @return bean instance
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * return bean instance with bean's Class in spring ioc container
     * @Date : 2011-4-14
     * @param <T> bean type
     * @param beanName - bean name defined in spring application context
     * @param clazz - bean's Class
     * @return bean instance
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }
}
