package com.xidu.framework.excel.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.excel.dto.FormAttributeValueDto;

public interface ExcelParser {

    void parse(InputStream importExcelInputStream, String mappingFileName, String templateName) throws AppException;
    
    public List<FormAttributeValueDto> parse(InputStream importExcelInputStream, String sheetName, int startRow)
	throws AppException;
    
    Map<String,List> getDataMap();
    
    Map<String,Map<String, List<String>>> getErrorMap();
}
