package com.xidu.framework.excel.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.excel.constant.ExcelErrorConstants;
import com.xidu.framework.excel.domain.ColumnMapping;
import com.xidu.framework.excel.domain.ExcelMapping;
import com.xidu.framework.excel.domain.ExcelMappings;
import com.xidu.framework.excel.domain.SheetMapping;

public class ExcelMappingGenerator {
    public static final String EXCEL_MAPPINGS_DIR = "excel/mappings/";

    
    private Map<String, ExcelMapping> excelMappingMap;

    private static ExcelMappingGenerator instance = new ExcelMappingGenerator();

    private ExcelMappingGenerator() {

    }

    public static ExcelMappingGenerator getInstance() {
        return instance;
    }

    public ExcelMapping getExcelMapping(InputStream mappingFileInputStream, String templateName) throws AppException{

        if(StringUtils.isBlank(templateName)){
            throw new AppException(ExcelErrorConstants.TEMPLATE_NAME_EMPTY, "template name is empty.");
        }
        
        if (null == excelMappingMap) {
            excelMappingMap = new HashMap<String, ExcelMapping>();
        }

        ExcelMapping returnedExcelMapping = excelMappingMap.get(templateName);
        if (null != returnedExcelMapping) {
            return returnedExcelMapping;
        }
        
        if(null == mappingFileInputStream){
            throw new AppException(ExcelErrorConstants.EXCEL_MAPPING_FILE_ERROR, "excel mapping file error.");
        }
        
        XStream xstream = new XStream(new DomDriver());

        xstreamBinding(xstream);

        ExcelMappings excelMappings = (ExcelMappings) xstream.fromXML(mappingFileInputStream);

        if (null == excelMappings) {
            return null;
        }

        List<ExcelMapping> excelMappingList = excelMappings.getExcelMappings();
        
        for(ExcelMapping excelMapping : excelMappingList){
            if(templateName.equals(excelMapping.getTemplateName())){
                returnedExcelMapping = excelMapping;
                break;
            }
        }
        excelMappingMap.put(returnedExcelMapping.getTemplateName(), returnedExcelMapping);
        
        return returnedExcelMapping;
    }

    private void xstreamBinding(XStream xstream) {
        xstream.alias("excelMappings", ExcelMappings.class);
        xstream.aliasAttribute(ExcelMappings.class, "namespace", "namespace");
        xstream.addImplicitCollection(ExcelMappings.class, "excelMappings");

        xstream.alias("excelMapping", ExcelMapping.class);
        xstream.aliasAttribute(ExcelMapping.class, "templateName", "templateName");
        xstream.addImplicitCollection(ExcelMapping.class, "sheetMappings");

        xstream.alias("sheetMapping", SheetMapping.class);
        xstream.aliasAttribute(SheetMapping.class, "name", "name");
        xstream.aliasAttribute(SheetMapping.class, "startRowIndex", "startRowIndex");
        xstream.aliasAttribute(SheetMapping.class, "className", "className");
        xstream.addImplicitCollection(SheetMapping.class, "columnMappings");

        xstream.alias("columnMapping", ColumnMapping.class);
        xstream.aliasAttribute(ColumnMapping.class, "header", "header");
        xstream.aliasAttribute(ColumnMapping.class, "columnIndex", "columnIndex");
        xstream.aliasAttribute(ColumnMapping.class, "field", "field");
        xstream.aliasAttribute(ColumnMapping.class, "type", "type");
        xstream.aliasAttribute(ColumnMapping.class, "msgkey", "msgkey");

    }
    
}
