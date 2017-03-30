package com.xidu.framework.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.xidu.framework.common.view.documenthandler.Excel2003DocumentHandler;

/**
 *
 */
public class Excel2003View extends AbstractExcel2003View {
	

	private Excel2003DocumentHandler excelDocumentHandler;
	/**
	 * {@inheritDoc} 
	 * overridden:
	 * @Date        :      2011-10-10
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 **/
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		excelDocumentHandler.doExportExcelDocument(model, workbook);

	}
	/**
	 * @Date        :      2011-10-10
	 *
	 * @return the excelDocumentHandler
	 */
	public Excel2003DocumentHandler getExcelDocumentHandler() {
		return excelDocumentHandler;
	}
	/**
	 * @Date        :      2011-10-10
	 *
	 * @param excelDocumentHandler the excelDocumentHandler to set
	 */
	public void setExcelDocumentHandler(
			Excel2003DocumentHandler excelDocumentHandler) {
		this.excelDocumentHandler = excelDocumentHandler;
	}
	
	
	

}
