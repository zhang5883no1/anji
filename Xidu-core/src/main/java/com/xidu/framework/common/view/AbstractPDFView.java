/******************************************************************************
 * @File name   :      AbstractPDFView.java
 * @Author      :      XINLYU
 * @Date        :      2013-6-12
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-6-12 下午9:30:15        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractPDFView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		// TODO Auto-generated method stub

	}

}
