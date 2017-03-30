/******************************************************************************
 * @File name   :      ExcelDocumentHandler.java
 *
 * @Author      :      Jianxi Wu
 *
 * @Date        :      2011-3-29
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
 * 2011-3-29 下午03:58:30        Jianxi Wu     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.common.view.documenthandler;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * According to model to generate Excel with data filled.
 */
public interface Excel2007DocumentHandler {
    void doExportExcelDocument(Map<String, Object> model, XSSFWorkbook workbook);
}
