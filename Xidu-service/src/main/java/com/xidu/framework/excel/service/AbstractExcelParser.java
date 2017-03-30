package com.xidu.framework.excel.service;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.ApplicationContextUtil;
import com.xidu.framework.excel.constant.ExcelConstants;
import com.xidu.framework.excel.constant.ExcelErrorConstants;
import com.xidu.framework.excel.domain.ColumnMapping;
import com.xidu.framework.excel.domain.ExcelMapping;
import com.xidu.framework.excel.domain.SheetMapping;
import com.xidu.framework.excel.dto.FormAttributeValueDto;
import com.xidu.framework.excel.util.ExcelMappingGenerator;

public abstract class AbstractExcelParser implements ExcelParser {
    public static final String PREFIX_GETTER = "get";

    public static final String PREFIX_SETTER = "set";

    protected Map<String, Map<String, List<String>>> errorMap;
    protected Map<String, List> dataMap;

    public void parse(InputStream importExcelInputStream, String mappingFileName,
        String templateName) throws AppException {
        InputStream mappingFileInputStream = this.getClass().getClassLoader()
            .getResourceAsStream(ExcelMappingGenerator.EXCEL_MAPPINGS_DIR + mappingFileName);
        //mappingFileInputStream is null
        parseFile(importExcelInputStream, mappingFileInputStream, templateName);

    }
    
    /**
     * Added by Michael
     * @param importExcelInputStream
     * @throws AppException
     */
    public List<FormAttributeValueDto> parse(InputStream importExcelInputStream, String sheetName, int startRow) throws AppException{
    	return parseFile(importExcelInputStream,sheetName,startRow);
    }
    
    /**
     * Added by Michael
     * @param importExcelInputStream
     * @param startRow
     * @throws AppException
     */
    private List<FormAttributeValueDto> parseFile(InputStream importExcelInputStream, String sheetName, int startRow) throws AppException {           
        return parseExcel(importExcelInputStream,sheetName, startRow);
    }
    
    /**
     * Added by Michael
     * @param importExcelInputStream
     * @param startRow
     * @throws AppException
     */
    public abstract List<FormAttributeValueDto> parseExcel(InputStream importExcelInputStream, String sheetName,int startRow)
    throws AppException;
    

    private void parseFile(InputStream importExcelInputStream, InputStream mappingFileInputStream,
        String templateName) throws AppException {
        ExcelMapping excelMapping = ExcelMappingGenerator.getInstance().getExcelMapping(
            mappingFileInputStream, templateName);

        if (null == excelMapping) {
            throw new AppException(ExcelErrorConstants.EXCEL_MAPPING_NOT_FOUND, "Can't find Excel Configuration XML File.");
        }

        if (null == errorMap) {
            errorMap = new HashMap<String, Map<String, List<String>>>();
        }
        if (null == dataMap) {
            dataMap = new HashMap<String, List>();
        }

        Map<String, List<String>> validationErrorList = validateExcelMapping(excelMapping);
        if (null != validationErrorList && validationErrorList.size() > 0) {
            errorMap.put(ExcelConstants.VALIDATION_EXCEL_ERROR_LIST, validationErrorList);
        }

        parseExcel(importExcelInputStream, excelMapping);
    }

    private Map<String, List<String>> validateExcelMapping(ExcelMapping excelMapping) {

        Map<String, List<String>> validationErrorMap = new HashMap<String, List<String>>();
        List<String> validationErrorList = new LinkedList<String>();

        MessageSource messageSource = (MessageSource)ApplicationContextUtil.getBean("messageSource");
        List<SheetMapping> sheetMappings = excelMapping.getSheetMappings();
        if (null == sheetMappings || sheetMappings.size() == 0) {
            validationErrorList.add("");
        }

        for (SheetMapping sheetMapping : sheetMappings) {
            if (sheetMapping.getStartRowIndex() < 0) {
                validationErrorList
                    .add(messageSource.getMessage("excel.parser.configfile.notfound", null, Locale.ENGLISH));
            }
            Class<?> clazz = null;
            try {
                clazz = Class.forName(sheetMapping.getClassName());
            } catch (ClassNotFoundException e) {
                validationErrorList.add(messageSource.getMessage("excel.parser.domainclass.notfound", null, Locale.ENGLISH));
            }

            if (null != clazz) {
                List<ColumnMapping> columnMappings = sheetMapping.getColumnMappings();

                if (null == columnMappings || columnMappings.size() == 0) {
                    validationErrorList
                        .add(messageSource.getMessage("excel.parser.columnmapping.notfound", null, Locale.ENGLISH));
                }

                for (ColumnMapping columnMapping : columnMappings) {

                    if (StringUtils.isBlank(columnMapping.getHeader())) {
                        validationErrorList
                            .add(messageSource.getMessage("excel.parser.attrheader.notfound", null, Locale.ENGLISH));
                    }

                    if (columnMapping.getColumnIndex() < 0) {
                        validationErrorList
                            .add(messageSource.getMessage("excel.parser.attrcolumnindex.negative", null, Locale.ENGLISH));
                    }

                    String field = columnMapping.getField();

                    if (StringUtils.isBlank(field)) {
                        validationErrorList
                            .add(messageSource.getMessage("excel.parser.attrfield.empty", null, Locale.ENGLISH));
                    }
                    String getterMethodName = generateMethodName(field, PREFIX_GETTER);
                    String setterMethodName = generateMethodName(field, PREFIX_SETTER);
                    try {
                        Method[] methods = clazz.getDeclaredMethods();
                        if (!doesMethodExist(getterMethodName, methods)
                            || !doesMethodExist(setterMethodName, methods)) {
                            validationErrorList.add(messageSource.getMessage("excel.parser.domainclass.fielderror", new Object[]{sheetMapping.getClassName(),field}, Locale.ENGLISH));
                        }
                    } catch (SecurityException e) {
                        validationErrorList.add(messageSource.getMessage("excel.parser.domainclass.getseterror", new Object[]{sheetMapping.getClassName()}, Locale.ENGLISH));
                    }
                }

            }
        }

        validationErrorMap.put(ExcelConstants.VALIDATION_EXCEL_ERROR_LIST, validationErrorList);

        return validationErrorMap;

    }

    private boolean doesMethodExist(String methodName, Method[] methods) {
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return true;
            }
        }
        return false;
    }

    public abstract void parseExcel(InputStream importExcelInputStream, ExcelMapping excelMapping)
        throws AppException;

    protected String generateMethodName(String field, String prefix) {
        return prefix + StringUtils.capitalize(field);
    }

    public Map<String, List> getDataMap() {
        return dataMap;
    }

    public Map<String, Map<String, List<String>>> getErrorMap() {
        return errorMap;
    }

}
