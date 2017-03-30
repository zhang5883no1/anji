package com.xidu.framework.excel.util;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.support.AbstractMessageSource;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.ApplicationContextUtil;
import com.xidu.framework.excel.constant.CommonErrorConstants;
import com.xidu.framework.excel.constant.Constants;
import com.xidu.framework.excel.dto.FormAttributeValueDto;
import com.xidu.framework.excel.service.ExcelParser;


/**
 *	File Import Utility Class
 */
public class FileImportUtils {
	
	/**
	 * Get Imported Records for Excel File
	 * @Date        :      2011-8-30
	 * @param fileName imported file name
	 * @param in inputStream
	 * @param templateFileName xml template file name
	 * @param templateName template name
	 * @param sheetName sheet name in Excel to be imported
	 * @return List of DTO Converted From Excel File
	 * @throws AppException
	 */
	public static <T> List<T> getImportedRecords(String fileName,InputStream in, String templateFileName, String templateName, String sheetName) throws AppException{
		List<T> dtoList = null;
		StringBuilder errorMsg = new StringBuilder("");
		AbstractMessageSource messageSource = (AbstractMessageSource) ApplicationContextUtil
				.getBean("messageSource");
		try {
			
			ExcelParser excelParser = ExcelParserFactory.getExcelParser(fileName);
			excelParser.parse(in, templateFileName,templateName); 
			Map<String, List<String>> sheetErrorMap = excelParser.getErrorMap().get(sheetName);
			if (sheetErrorMap == null || sheetErrorMap.isEmpty())
			{
			    Map<String, List> dataMap = excelParser.getDataMap();
			    dtoList = dataMap.get(sheetName);
			}else{
//				errorMsg = messageSource.getMessage("file.import.error",null, Locale.CHINA);
				Iterator<String> keys=sheetErrorMap.keySet().iterator();
				while(keys.hasNext()){
					String nextKey=keys.next();
					errorMsg.append(nextKey)  ;
					List<String> values=sheetErrorMap.get(nextKey);
					for (Iterator<String> iterator = values.iterator(); iterator
							.hasNext();) {
						String string = (String) iterator.next();
//						errorMsg=errorMsg+string;
						errorMsg.append(string)  ;
					}
					errorMsg.append(Constants.EXCEL_IMPORT_LINE_BREAK)  ;
//					errorMsg.append("\r\n")  ;
				}
				/*Iterator it = sheetErrorMap.values().iterator();
				while(it.hasNext()){
					errorMsg = errorMsg + it.next()+";";
				}*/
				throw new AppException(CommonErrorConstants.IMPORT_FILE_EXCEPTION,String.valueOf(errorMsg));
			}
		} catch (AppException e) {
			throw e;			
		} catch (Exception ex){
			ex.printStackTrace();
			errorMsg .append( messageSource.getMessage("file.import.error",null, Locale.ENGLISH));
			throw new AppException(CommonErrorConstants.IMPORT_FILE_EXCEPTION,String.valueOf(errorMsg),ex.getCause());
		}
		return dtoList;
	}
	
	/**
	 * Get imported records
	 * @param fileName file name
	 * @param in input stream
	 * @param sheetName sheet name
	 * @param startRow row num to be read
	 * @return List of FormAttributeValueDto
	 * @throws AppException
	 */
	public static List<FormAttributeValueDto> getImportedRecords(String fileName,InputStream in,String sheetName, int startRow) throws AppException{
		ExcelParser excelParser = ExcelParserFactory.getExcelParserEndWithEmptyRow(fileName);
		return excelParser.parse(in, sheetName, startRow);	
	}

}
