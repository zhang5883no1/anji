package com.xidu.framework.excel.util;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.excel.service.ExcelParser;
import com.xidu.framework.excel.service.impl.Excel2003ParserEndWithEmptyRowImpl;
import com.xidu.framework.excel.service.impl.Excel2003ParserImpl;
import com.xidu.framework.excel.service.impl.Excel2007ParserEndWithEmptyRowImpl;
import com.xidu.framework.excel.service.impl.Excel2007ParserImpl;

/**
 *
 */
public final class ExcelParserFactory {

    private static final String EXCEL2007_SUFFIX = "xlsx";
    private static final String EXCEL2003_SUFFIX = "xls";
    public static ExcelParser getExcelParser(String orginalFileName){
        
        if(StringUtils.isBlank(orginalFileName)){
            return null;
        }
        if(orginalFileName.endsWith(EXCEL2003_SUFFIX)){
            return new Excel2003ParserImpl();
        }
        if(orginalFileName.endsWith(EXCEL2007_SUFFIX)){
            return new Excel2007ParserImpl();
        }
        return null;
    }
    
    public static ExcelParser getExcelParserEndWithEmptyRow(String orginalFileName){
        
        if(StringUtils.isBlank(orginalFileName)){
            return null;
        }
        if(orginalFileName.endsWith(EXCEL2003_SUFFIX)){
            return new Excel2003ParserEndWithEmptyRowImpl();
        }
        if(orginalFileName.endsWith(EXCEL2007_SUFFIX)){
            return new Excel2007ParserEndWithEmptyRowImpl();
        }
        return null;
    }
}
