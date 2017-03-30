package com.xidu.framework.common.view.documenthandler;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * According to model to generate Excel with data filled.
 */
public interface Excel2003DocumentHandler {
	void doExportExcelDocument(Map<String, Object> model, HSSFWorkbook workbook);
}
