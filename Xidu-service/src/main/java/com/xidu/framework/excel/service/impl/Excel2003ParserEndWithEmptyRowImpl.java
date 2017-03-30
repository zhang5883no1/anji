package com.xidu.framework.excel.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.cglib.beans.BulkBean;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.ApplicationContextUtil;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.excel.constant.ExcelConstants;
import com.xidu.framework.excel.constant.ExcelErrorConstants;
import com.xidu.framework.excel.constant.StringTypeRegistry;
import com.xidu.framework.excel.domain.ColumnMapping;
import com.xidu.framework.excel.domain.ExcelMapping;
import com.xidu.framework.excel.domain.SheetMapping;
import com.xidu.framework.excel.dto.FormAttributeValueDto;
import com.xidu.framework.excel.service.AbstractExcelParser;
import com.xidu.framework.excel.validator.ExcelDtoValidator;

public class Excel2003ParserEndWithEmptyRowImpl extends AbstractExcelParser {

	private static final String COLON = ": ";
	
	private MessageSource messageSource = (MessageSource)ApplicationContextUtil.getBean("messageSource");
	@Override
    public void parseExcel(InputStream importExcelInputStream, ExcelMapping excelMapping) throws AppException {

        if (errorMap.keySet().size() > 1) {
            return;
        }

        try {
            ConversionService conversionService = (ConversionService) ApplicationContextUtil
                .getBean("conversionService");

            HSSFWorkbook workbook = new HSSFWorkbook(importExcelInputStream);

            List<SheetMapping> sheetMappings = excelMapping.getSheetMappings();

            for (SheetMapping sheetMapping : sheetMappings) {

                List<ColumnMapping> columnMappings = sheetMapping.getColumnMappings();

                int size = columnMappings.size();

                int[] columnIndexArray = new int[size];
                String[] getters = new String[size];
                String[] setters = new String[size];
                Class<?>[] types = new Class<?>[size];

                for (int i = 0; i < size; i++) {
                    ColumnMapping columnMapping = columnMappings.get(i);

                    columnIndexArray[i] = columnMapping.getColumnIndex();
                    getters[i] = generateMethodName(columnMapping.getField(), PREFIX_GETTER);
                    setters[i] = generateMethodName(columnMapping.getField(), PREFIX_SETTER);
                    types[i] = StringTypeRegistry.getClass(columnMapping.getType());
                }

                HSSFSheet sheet = workbook.getSheet(sheetMapping.getName());

                if(null == sheet){
                    errorMap.get(ExcelConstants.VALIDATION_EXCEL_ERROR_LIST).get(ExcelConstants.VALIDATION_EXCEL_ERROR_LIST).add(messageSource.getMessage("excel.validation.sheet.notfound", new Object[]{sheetMapping.getName()}, Locale.ENGLISH));
                    continue;
                }
                
                int rowIndex = sheetMapping.getStartRowIndex();

                Map<String, List<String>> rowErrorMap = new LinkedHashMap<String, List<String>>();
                List sheetDataList = new LinkedList();

                while (rowIndex > -1) {

                    Object dtoInstance = null;
                    Map<String, String> dtoFieldMap = new HashMap<String, String>();

                    List<String> dtoValidationList = new ArrayList<String>();

                    int columnNum = rowIndex + 1;
                    try {
                        Object[] propValues = new String[size];
                        String[] cellValues = new String[size];
                        for (int i = 0; i < columnIndexArray.length; i++) {
                            String cellValue = getCellValue(workbook, sheet, columnIndexArray[i],
                                rowIndex);
                            cellValues[i] = cellValue;
                            propValues[i] = conversionService.convert(cellValue, types[i]);
                        }
                        if (isEmptyRowInSheet(cellValues)
                            && rowIndex == sheetMapping.getStartRowIndex()) {
                            errorMap.get(ExcelConstants.VALIDATION_EXCEL_ERROR_LIST)
                                .get(ExcelConstants.VALIDATION_EXCEL_ERROR_LIST).add(messageSource.getMessage("excel.validation.excel.empty", null, Locale.ENGLISH));
                        }

                        if (isEmptyRowInSheet(cellValues)) {
                            break;
                        }

                        Class clazz = (Class) Class.forName(sheetMapping.getClassName());
                        dtoInstance = clazz.newInstance();

                        BulkBean bulkBean = BulkBean.create(clazz, getters, setters, types);

                        bulkBean.setPropertyValues(dtoInstance, propValues);
                        ExcelDtoValidator excelDtoValidator = (ExcelDtoValidator) dtoInstance;
                        dtoFieldMap = excelDtoValidator.validate();
                        dtoValidationList = getRowValidationInfoList(dtoFieldMap, columnMappings);
                    } catch (ClassNotFoundException e) {
                        dtoValidationList.add(messageSource.getMessage("excel.validation.domainclass.loaderror", new Object[]{sheetMapping.getClassName()} ,Locale.ENGLISH));
                    } catch (InstantiationException e) {
                        dtoValidationList.add(messageSource.getMessage("excel.validation.domainclass.initialerror", new Object[]{sheetMapping.getClassName()} ,Locale.ENGLISH));
                    }

                    if (null != dtoValidationList && dtoValidationList.size() > 0) {
                        rowErrorMap.put(messageSource.getMessage("excel.validation.row.errormsg", new Object[]{columnNum}, Locale.ENGLISH)
                            ,dtoValidationList);
                    }
                    if (null != dtoInstance) {
                        sheetDataList.add(dtoInstance);
                    }
                    rowIndex++;

                }
                dataMap.put(sheetMapping.getName(), sheetDataList);
                errorMap.put(sheetMapping.getName(), rowErrorMap);

            }

        } catch (IOException e) {
            throw new AppException(ExcelErrorConstants.EXCEL_FILE_READ_ERROR,
                "Read Excel File Failure.");
        } catch (IllegalAccessException e) {

        }
    
    }
    
    private List<String> getRowValidationInfoList(Map<String, String> dtoFieldMap,
            List<ColumnMapping> columnMappings) {
            List<String> rowValidationInfoList = new ArrayList<String>();

            for (int i = 0; i < columnMappings.size(); i++) {
                ColumnMapping columnMapping = columnMappings.get(i);
                String field = columnMapping.getField();
                if (dtoFieldMap.containsKey(field)) {
                    if (StringUtils.isNotBlank(columnMapping.getHeader())) {
                        rowValidationInfoList.add(columnMapping.getHeader() + COLON
                            + dtoFieldMap.get(field));
                    } else {
                        int columnNum = columnMapping.getColumnIndex() + 1;
                        rowValidationInfoList.add(messageSource.getMessage("excel.validation.column.errormsg", new Object[]{columnNum}, Locale.ENGLISH)
                            + dtoFieldMap.get(field));
                    }
                }
            }

            return rowValidationInfoList;
        }

        private String getCellValue(HSSFWorkbook workbook, HSSFSheet sheet, int col, int row) {
            HSSFRow sheetRow = sheet.getRow(row);

            if (null == sheetRow) {
                return "";
            }

            HSSFCell cell = sheetRow.getCell(col);
            if (null == cell) {
                return "";
            }
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return StringUtils.trimToEmpty(cell.getStringCellValue());
            case Cell.CELL_TYPE_FORMULA:
                HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(workbook);
                //FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                CellValue cellVal;
				try {
					cellVal = evaluator.evaluate(cell);
				} catch (Exception e) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
	                return StringUtils.trimToEmpty(cell.getStringCellValue());
				}
                switch (cellVal.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    return StringUtils.trimToEmpty(cell.getStringCellValue());
                case Cell.CELL_TYPE_STRING:
                	if(Utils.isNullOrEmpty(cell.getStringCellValue())){
                		return "";
                	}
                	cell.setCellType(Cell.CELL_TYPE_STRING);                	
                    return StringUtils.trimToEmpty(cell.getStringCellValue());
                case Cell.CELL_TYPE_ERROR:
                	return "";
                default:
                    return StringUtils.trimToEmpty(cell.getStringCellValue());
                }
            default:
                return StringUtils.trimToEmpty(cell.getStringCellValue());
            }
        }

        private boolean isEmptyRowInSheet(String[] cellValues) {
            for (int i = 0; i < cellValues.length; i++) {
                if (StringUtils.isNotBlank(cellValues[i])) {
                    return false;
                }
            }
            return true;
        }

        @Override
		public List<FormAttributeValueDto> parseExcel(
				InputStream importExcelInputStream, String sheetName,
				int startRow) throws AppException {

			try {
				List<FormAttributeValueDto> formAttributeValueList = new ArrayList<FormAttributeValueDto>();
				HSSFWorkbook workbook = new HSSFWorkbook(importExcelInputStream);
				HSSFSheet sheet = workbook.getSheet(sheetName);
				
				int rowIndex = startRow;
                int total=sheet.getLastRowNum();
                int columnNum;
                
                Map<Integer,String> columnAttributeMap = new HashMap<Integer,String>(); 

                while (rowIndex > -1) {
                    
                    if(rowIndex>total)
                    {
                        break;
                    }
                    
                    try {
                        columnNum  = -1;
                        while(true) {
                        	columnNum ++;
                            String cellValue = getCellValue(workbook, sheet, columnNum,
                                rowIndex);
                            if(rowIndex < 2){//Table Head End with empty cell
	                            if(cellValue != null && !cellValue.trim().equals("")){
	                            	//Read hidden row of Attribute Name
	                            	if(rowIndex == 0){
	                            		columnAttributeMap.put(columnNum, cellValue);
	                            	}else if(rowIndex == 1){//Skip read of display column name
	                            		continue;
	                            	}else{// Read data row
	                            		if(columnAttributeMap.size() > 0){//Got column hidden attribute 
		                            		FormAttributeValueDto formAttributeValueDto = new FormAttributeValueDto();
		                            		formAttributeValueDto.setRowIndex(new Long(rowIndex - 1));
		                            		formAttributeValueDto.setColumnIndex(new Long(columnNum));
		                            		formAttributeValueDto.setAttributeName(columnAttributeMap.get(columnNum));
		                            		formAttributeValueDto.setAttributeValue(cellValue);
		                            		formAttributeValueList.add(formAttributeValueDto);
	                            		}else{
	                            			throw new AppException("Get column hidden attributes failed.","Get column hidden attributes failed.");
	                            		}
	                            	}
	                            }else{
	                            	break;
	                            }
                            }else{
                            	if(columnNum < columnAttributeMap.size()){//read to the max column num of head
                            		FormAttributeValueDto formAttributeValueDto = new FormAttributeValueDto();
                            		formAttributeValueDto.setRowIndex(new Long(rowIndex - 1));
                            		formAttributeValueDto.setColumnIndex(new Long(columnNum));
                            		formAttributeValueDto.setAttributeName(columnAttributeMap.get(columnNum));
                            		formAttributeValueDto.setAttributeValue(cellValue);
                            		formAttributeValueList.add(formAttributeValueDto);
                            	}else{
                            		break;
                            	}
                            }
                        }
                        //Has empty row, then break
                        if(rowIndex > 1 && ((rowIndex - 1) * columnAttributeMap.size() != formAttributeValueList.size())){
                        	break;
                        }
                       
                    } catch(Exception e){
                    	throw new AppException("File Analyze Failed.",e.getCause().toString());
                    }
                   
                    rowIndex++;
                }
                
                return formAttributeValueList;
                
			} catch (IOException e) {
				throw new AppException("File Analyze Failed.",e.getCause().toString());
			}	
		
		}

}
