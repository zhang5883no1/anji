package com.xidu.framework.excel.constant;

/**
 * Defines all the error codes of common exceptions.
 */
public interface CommonErrorConstants {
	
	final String CHECKED_ERROR_PAGE="function_error";

    /**
     * Constant to represent not found exception.
     */
    final String NOT_FOUND = "NOT_FOUND"; // or using number to specify each
                                          // error, e.g.1001

    /**
     * Constant to represent null exception.
     */
    final String IS_NULL = "IS_NULL";
    
    final String BRAND_NOT_FOUND_ERROR = "BRAND_NOT_FOUND_ERROR";//CommonErrorConstants.BRAND_NOT_FOUND_ERROR
    
    
    /**
     * Constant to represent system exception
     */
    final String SYSTEM_ERROR = "SYSTEM_ERROR";
    
    /**
     * Constant to represent common method parameter error
     */
    final String COMMON_METHOD_PARAM_ERROR = "COMMON_METHOD_PARAM_ERROR";
    
    //from TestDrivingErrorConstants.java
    final String COMMON_UPLOAD_FILE_FAILED_EXCEPTION = "COMMON_UPLOAD_FILE_FAILED_EXCEPTION";


    /**
     * 
     */
    final String SYSTEM_EXCEPTION_MESSAGE_PREFIX = "System's Exception:";
    
    final String BEAN_COPY_EXCEPTION = "BEAN_COPY_EXCEPTION";
    
    final String IMPORT_FILE_EXCEPTION = "Import file exception";
    
    final String UPLOAD_FILE_IO_EXCEPTION = "upload file io exception"; 
    
    final String COMMON_UPLOAD_FILE_OVERSIZE_EXCEPTION = "COMMON_UPLOAD_FILE_OVERSIZE_EXCEPTION";
    
    final String COMMON_UPLOAD_FILE_OVERSIZE_20M_EXCEPTION = "COMMON_UPLOAD_FILE_OVERSIZE_20M_EXCEPTION";
    
    final String COMMON_UPLOAD_FILE_OVERSIZE_5M_EXCEPTION = "COMMON_UPLOAD_FILE_OVERSIZE_5M_EXCEPTION";
    
    final String COMMON_UPLOAD_FILE_OVERSIZE_8M_EXCEPTION = "COMMON_UPLOAD_FILE_OVERSIZE_8M_EXCEPTION";
    
    final String COMMON_USER_IS_NOT_USERGROUP = "COMMON_USER_IS_NOT_USERGROUP";
    
    final String COMMON_USER_IS_NOT_ORGANIZATION = "COMMON_USER_IS_NOT_ORGANIZATION";
    
    final String COMMON_DELETE_EMPLOYEE_STATUS = "COMMON_DELETE_EMPLOYEE_STATUS";
    
    final String COMMON_INVALID_TEMPLATE_FILE = "COMMON_INVALID_TEMPLATE_FILE";
    

}
