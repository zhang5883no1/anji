package com.xidu.framework;

import java.util.Hashtable;



public class Constants {
	
	public final static String  OPERATE_TYPE_ADD = "0";
	public final static String  OPERATE_TYPE_UPDATE = "1";
	public final static String  OPERATE_TYPE_DELETE = "2";
	
	public final static String  RESULT_FAILED = "0";
	public final static String  RESULT_SUCCESS = "1";
	
	public final static String LOGIN_INFO_SESSION = "loginSessionInfo";
	public final static String LOGIN_NAME_SESSION = "loginNameSession";
	public final static String VALID_CODE_SESSION = "validCodeSession";
	public final static String OIM_CLIENT_SESSION = "oimClientSession";
	
	public final static String DB_USER = "idc";
    
    
    public static Hashtable<String,String> env;
    
    public static String systemFlag = System.getProperty("os.name").toLowerCase();
    
    
    public static final String EXPORT_CSV_DATA_LIST = "EXPORT_CSV_DATA_LIST";
    
    public static final String EXPORT_CSV_MAPPING_FILE = "EXPORT_CSV_MAPPING_FILE";
    
    public static final String EXPORT_CSV_TEMPLATE_NAME = "EXPORT_CSV_TEMPLATE_NAME";
    public static final String EXPORT_XLS_DATA_LIST = "EXPORT_XLS_DATA_LIST";
    
    public static final String EXPORT_PDF_DATA_LIST = "EXPORT_PDF_DATA_LIST";
    
    public static final String EXPORT_XLS_MAPPING_FILE = "EXPORT_XLS_MAPPING_FILE";
    
    public static final String EXPORT_PDF_MAPPING_FILE = "EXPORT_PDF_MAPPING_FILE";
    
    public static final String EXPORT_XLS_TEMPLATE_NAME = "EXPORT_XLS_TEMPLATE_NAME";
    
    public static final String EXPORT_PDF_TEMPLATE_NAME = "EXPORT_PDF_TEMPLATE_NAME";
    
    public static final String EXPORT_XLS_SHEET_STYLE_DTO="EXPORT_XLS_SHEET_STYLE_DTO";
    
    public static final String DATE_FORMAT_YYMMDD = "yyyy-MM-dd";
    
    public static final String DATE_FORMATE_ORACLE_DB = "yyyy-MM-dd";
    
    public static final String DATE_FORMATE_LOGGING = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";
    
    public static final String EXCEL_IMPORT_DEFAULT_SHEET="Sheet1";
    
    public static final String EXCEL_IMPORT_LINE_BREAK=";";
    
    public static final String JSON_RESULT_TOTAL_COUNT="totalCount";
    
    public static final String JSON_RESULT_ROWS="rows";
    
    public static final int UPLOAD_FILE_MAX_SIZE=2*1024*1024;
    
    public static final String UPLOAD_FILE_MAX_SIZE_NAME="2M";
    
    public static final String OPERATION_RESULT_SUCCESS="operationSuccess";
    
}
