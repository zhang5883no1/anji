/************************************************************************************
 * @File name   :      SpringInit.java
 *
 * @Author      :      Michael Gu
 *
 * @Date        :      2011-3-9
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                         Who                 Version         Comments
 * 2011-3-9 下午02:03:02         Michael Gu            1.0          Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class SpringInit implements ServiceLifeCycle {

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see org.apache.axis2.engine.ServiceLifeCycle#shutDown(org.apache.axis2.context.ConfigurationContext,
     *      org.apache.axis2.description.AxisService)
     * 
     */
    @Override
    public void shutDown(ConfigurationContext arg0, AxisService arg1) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-3-28
     * @see org.apache.axis2.engine.ServiceLifeCycle#startUp(org.apache.axis2.context.ConfigurationContext,
     *      org.apache.axis2.description.AxisService)
     * 
     */
    @Override
    public void startUp(ConfigurationContext ignore, AxisService service) {
        try {
            System.out.println("Starting spring init");
            ClassLoader classLoader = service.getClassLoader();
            ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(
                new String[] { "file:F:/workspace/common/src/test/spring-context_component.xml" },
                false);
            appCtx.setClassLoader(classLoader);
            appCtx.refresh();
            System.out.println("Spring Loaded");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
