package com.xidu.framework.excel.constant;


public interface ExcelErrorConstants extends CommonErrorConstants{
    
    /**
     * excel template name is emtpy.
     */
    final String TEMPLATE_NAME_EMPTY = "TEMPLATE_NAME_EMPTY";
    
    /**
     * excel mapping file error
     */
    final String EXCEL_MAPPING_FILE_ERROR = "EXCEL_MAPPING_FILE_ERROR";
    
    /**
     * can't find excel mapping in mappings xml
     */
    final String EXCEL_MAPPING_NOT_FOUND = "EXCEL_MAPPING_NOT_FOUND";
    
    /**
     * excel read error
     */
    final String EXCEL_FILE_READ_ERROR = "EXCEL_FILE_READ_ERROR";
    
    /**
     * EXCEL dto class can't be found.
     */
    final String EXCEL_DTO_CLASS_NOT_FOUND = "EXCEL_DTO_CLASS_NOT_FOUND";
    /**
     * EXCEL dto class can't be initialized.
     */
    final String EXCEL_DTO_CLASS_NOT_INITIAL = "EXCEL_DTO_CLASS_NOT_INITIAL";

}
