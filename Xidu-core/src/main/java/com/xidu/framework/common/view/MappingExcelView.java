/******************************************************************************
 * @File name   :      MappingExcelView.java
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
 * 2011-3-29 下午03:35:05        Jianxi Wu     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.xidu.framework.common.view.documenthandler.Excel2007DocumentHandler;

/**
 *
 */
public class MappingExcelView extends AbstractExcel2007View {

    private Excel2007DocumentHandler excelDocumentHandler;
    /**
     * {@inheritDoc} 
     * overridden:
     * @Date        :      2011-3-29
     * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     **/
    @Override
    protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook,
        HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        
        excelDocumentHandler.doExportExcelDocument(model, workbook);
    }
    /**
     * @Date        :      2011-3-30
     *
     * @return the excelDocumentHandler
     */
    public Excel2007DocumentHandler getExcelDocumentHandler() {
        return excelDocumentHandler;
    }
    /**
     * @Date        :      2011-3-30
     *
     * @param excelDocumentHandler the excelDocumentHandler to set
     */
    public void setExcelDocumentHandler(Excel2007DocumentHandler excelDocumentHandler) {
        this.excelDocumentHandler = excelDocumentHandler;
    }

}
