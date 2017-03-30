/******************************************************************************
 * @File name   :      Constants.java
 * @Author      :      XINLYU
 * @Date        :      2013-5-28
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-5-28 下午4:33:46        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.excel.constant;

import java.util.Hashtable;

public class Constants {
	public final static String  OPERATE_TYPE_ADD = "0";
	public final static String  OPERATE_TYPE_UPDATE = "1";
	public final static String  OPERATE_TYPE_DELETE = "2";
	
	public final static String  RESULT_FAILED = "FAILED";
	public final static String  RESULT_SUCCESS = "SUCCESS";
	
	public final static String LOGIN_INFO_SESSION = "loginSessionInfo";
	public final static String LOGIN_NAME_SESSION = "loginNameSession";
	public final static String VALID_CODE_SESSION = "validCodeSession";
	public final static String OIM_CLIENT_SESSION = "oimClientSession";
	
	public final static String DB_USER = "idc";
    
    
    public static Hashtable<String,String> env;
    
    public static String systemFlag = System.getProperty("os.name").toLowerCase();
    
    
    public static final String EXPORT_CSV_DATA_LIST = "EXPORT_CSV_DATA_LIST";
    
    public static final String EXPORT_XLS_DATA_LIST = "EXPORT_XLS_DATA_LIST";
    
    public static final String EXPORT_CSV_MAPPING_FILE = "EXPORT_CSV_MAPPING_FILE";
    
    public static final String EXPORT_XLS_MAPPING_FILE = "EXPORT_XLS_MAPPING_FILE";
    
    public static final String EXPORT_CSV_TEMPLATE_NAME = "EXPORT_CSV_TEMPLATE_NAME";
    
    public static final String EXPORT_XLS_TEMPLATE_NAME = "EXPORT_XLS_TEMPLATE_NAME";
    
    public static final String DATE_FORMAT_YYMMDD = "yyyy-MM-dd";
    
    public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";
    
    public static final String EXCEL_IMPORT_DEFAULT_SHEET="Sheet1";
    
    public static final String EXCEL_IMPORT_PACKING_LIST_SHEET = "P_LIST ATTACH";
    
    public static final String EXCEL_EXPORT_DEFAULT_SHEET="Sheet1";
    
    public static final String EXCEL_IMPORT_LINE_BREAK=";";
    
    public static final String JSON_RESULT_TOTAL_COUNT="totalCount";
    
    public static final String JSON_RESULT_ROWS="rows";
    
    public static final int UPLOAD_FILE_MAX_SIZE=2*1024*1024;
    
    public static final String UPLOAD_FILE_MAX_SIZE_NAME="2M";
    
    public static final int MAX_IMPORT_RESULTS=1000;
    
    public static final int MAX_EXPORT_RESULTS=1000;
    
    public static final int MAX_OIM_EXPORT_RESULTS = 1000;
    
	//身份证后4位正则表达式
    public static final String IDENTITY_LAST_REG = "[a-zA-Z0-9]{4}";
    
    public static final String BU_CODE = "CR001";//利润中心代码 CROO1为集团代码
    
    public static final String EXPORT_USER_TYPE_NO="EXPORT_USER_TYPE_NO";

}
