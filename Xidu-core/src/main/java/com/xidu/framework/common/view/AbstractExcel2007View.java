/******************************************************************************
 * @File name   :      AbstractExcel2007View.java
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
 * 2011-3-29 下午04:11:22        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.common.view;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 *
 */
public abstract class AbstractExcel2007View extends AbstractView {

    /** The content type for an Excel 2003 response */
    // private static final String CONTENT_TYPE = "application/vnd.ms-excel";
    private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument"
        + ".spreadsheetml.sheet";
    /** The extension to look for existing templates */
    private static final String EXTENSION = ".xlsx";

    private String url;

    /**
     * Default Constructor. Sets the content type of the view to
     * "application/vnd.ms-excel".
     */
    public AbstractExcel2007View() {
        setContentType(CONTENT_TYPE);
    }

    /**
     * Set the URL of the Excel workbook source, without localization part nor
     * extension.
     * 
     * @Date : 2011-4-14
     * @param url - template url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-14
     * @see org.springframework.web.servlet.view.AbstractView#generatesDownloadContent()
     * 
     */
    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    /**
     * Renders the Excel view, given the specified model. {@inheritDoc}
     * overridden:
     * 
     * @Date : 2011-4-14
     * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     * 
     */
    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model,
        HttpServletRequest request, HttpServletResponse response) throws Exception {

        XSSFWorkbook workbook;
        if (this.url != null) {
            workbook = getTemplateSource(this.url, request);
        } else {
            workbook = new XSSFWorkbook();
            logger.debug("Created Excel Workbook from scratch");
        }

        buildExcelDocument(model, workbook, request, response);

        // Set the content type.
        response.setContentType(getContentType());

        // Should we set the content length here?
        // response.setContentLength(workbook.getBytes().length);

        // Flush byte array to servlet output stream.
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * Creates the workbook from an existing XLS document.
     * 
     * @param url
     *            the URL of the Excel template without localization part nor
     *            extension
     * @param request
     *            current HTTP request
     * @return the HSSFWorkbook
     * @throws Exception
     *             in case of failure
     */
    protected XSSFWorkbook getTemplateSource(String url, HttpServletRequest request)
        throws Exception {
        LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
        Locale userLocale = RequestContextUtils.getLocale(request);
        Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);

        // Create the Excel document from the source.
        if (logger.isDebugEnabled()) {
            logger.debug("Loading Excel workbook from " + inputFile);
        }
        return new XSSFWorkbook(inputFile.getInputStream());
    }

    /**
     * Subclasses must implement this method to create an Excel HSSFWorkbook
     * document, given the model.
     * 
     * @param model
     *            the model Map
     * @param workbook
     *            the Excel workbook to complete
     * @param request
     *            in case we need locale etc. Shouldn't look at attributes.
     * @param response
     *            in case we need to set cookies. Shouldn't write to it.
     * @throws Exception
     *             in case of failure
     */
    protected abstract void buildExcelDocument(Map<String, Object> model, XSSFWorkbook workbook,
        HttpServletRequest request, HttpServletResponse response) throws Exception;

}
