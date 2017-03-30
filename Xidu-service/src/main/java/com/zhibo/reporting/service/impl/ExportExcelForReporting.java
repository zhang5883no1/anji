package com.zhibo.reporting.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Component;

import com.xidu.framework.Constants;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.DateUtil;
import com.xidu.framework.common.util.PropertiesUtil;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.view.ViewConstants;
import com.xidu.framework.common.view.documenthandler.AbstractExcel2003DocumentHandler;
import com.xidu.framework.excel.domain.ColumnMapping;
import com.xidu.framework.excel.domain.ExcelMapping;
import com.xidu.framework.excel.domain.SheetMapping;
import com.xidu.framework.excel.dto.ExcelDateFormat;
import com.xidu.framework.excel.dto.SheetStyleDto;
import com.xidu.framework.excel.util.ExcelMappingGenerator;
import com.zhibo.reporting.service.ReportingService;

/**
 * <p>
 * 导出EXCLE2003格式文件【.xls】时处理数据的方法
 * </p>
 * 
 * @author Jack Yu Capgemini
 * @version 1.0
 * @see com.crc.framework.view.documenthandler.AbstractExcel2003DocumentHandler
 * @see com.crc.framework.view.documenthandler.Excel2003DocumentHandler
 */
//@Component("exportExcelforReporting")
public class ExportExcelForReporting{// extends
	//	AbstractExcel2003DocumentHandler {
//	private static final String PREFIX_GET = "get";// 使用反射机制调用方法时，调用方法的前缀
//	private static final int MAX_WITH = 35;// 导出单表的最大列宽
//
//	@Override
//	public void doExportExcelDocument(Map<String, Object> model,
//			HSSFWorkbook workbook) {
//		List<?> dataList = (List<?>) model.get(Constants.EXPORT_XLS_DATA_LIST);// 从springMVC的model中获取即将处理的数据
//		if (null == dataList) {
//			return;
//		}
//		String mappingFileName = (String) model.get(Constants.EXPORT_XLS_MAPPING_FILE);
//		String templateName = (String) model.get(Constants.EXPORT_XLS_TEMPLATE_NAME);
//		SheetStyleDto sheetStyleDto = (SheetStyleDto)model.get(Constants.EXPORT_XLS_SHEET_STYLE_DTO);
//		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ExcelMappingGenerator.EXCEL_MAPPINGS_DIR + mappingFileName);
//		ExcelMapping excelMapping = null;
//		try {
//			excelMapping = ExcelMappingGenerator.getInstance().getExcelMapping(inputStream, templateName);
//			if (null == excelMapping) {
//				return;
//			}
//		} catch (AppException e) {
//			e.printStackTrace();
//		}
//		List<SheetMapping> sheetMappings = excelMapping.getSheetMappings();
//		if (null == sheetMappings || sheetMappings.size() == 0) {
//			return;
//		}
//		SheetMapping sheetMapping = sheetMappings.get(0);
//		List<ColumnMapping> columnMappings = sheetMapping.getColumnMappings();
//		if (null == columnMappings) {
//			return;
//		}
//		sortColumnMappings(columnMappings);
//		HSSFSheet sheet = workbook.createSheet(templateName);// 根据模板名称创建一个新单表
//		int columnMappingsSize = columnMappings.size();
//		int dataListSize = dataList.size();
//		HSSFFont font = workbook.createFont();
//		HSSFCellStyle setBorder = workbook.createCellStyle();
//		font.setFontName("Calibri");
//		setBorder.setFont(font);
//		if(model.get("isOcean")!=null){
//			setFirstRowByDefultExcel(sheet,workbook,model.get("isOcean"));
//			generateOtherRows1(sheet,dataList,dataListSize,columnMappings,columnMappingsSize);
//			System.out.println(columnMappingsSize+"--------"+dataListSize);
//			autoSizeColumn(sheet, columnMappingsSize, dataListSize + 6);// dataListSize + 1 ：单表的数据行数 + 单表的第一行 = 单表总共行数
//			setFontStyle(sheet, columnMappingsSize, dataListSize+3,setBorder);
//		}else if(model.get("Domestic")!=null){
//			exportDomestic(sheet, dataList, columnMappings,workbook,templateName);
//			List list=(List)dataList.get(10);
////			autoSizeColumn(sheet, 13, list.size() + 6);// dataListSize + 1 ：单表的数据行数 + 单表的第一行 = 单表总共行数
////			setFontStyle(sheet, 13, list.size()+6,setBorder);
//		}else{
//			generateFirstRow(sheet,columnMappings,columnMappingsSize);
//			generateOtherRows(sheet,dataList,dataListSize,columnMappings,columnMappingsSize);
//			autoSizeColumn(sheet, columnMappingsSize, dataListSize + 1);// dataListSize + 1 ：单表的数据行数 + 单表的第一行 = 单表总共行数
//			setFontStyle(sheet, columnMappingsSize, dataListSize,setBorder);
//			if(null != sheetStyleDto){//功能开关：当单表样式不为空时，进行自定义单表格式处理，否则该功能关闭
//				customStyleSheet(workbook,columnMappings,columnMappingsSize,dataListSize,sheetStyleDto);
//			}
//		}
//		
//	}
//
//	private void setFontStyle(HSSFSheet sheet, int columnCount, int rowCount, HSSFCellStyle setBorder) {
//		// TODO Auto-generated method stub
//			for(int i=0;i<rowCount+1;i++){
//				for(int j=0;j<columnCount;j++){
//					System.out.println("row:"+i);
//					System.out.println("cell:"+j);
//					System.out.println("--------------");
//					sheet.getRow(i).getCell(j).setCellStyle(setBorder);
//				}
//			}
//	}
//
//	/*
//	 * Excel sheet 自定义格式
//	 * 
//	 * @param workbook 需要自定义样式EXCEL工作簿
//	 * @param columnMappings 列信息list
//	 * @param columnMappingsSize 列信息list大小
//	 * @param dataListSize 数据信息list大小
//	 * @param sheetStyleDto 参数封装了定义单表格式样式的所需的属性
//	 */
//	private void customStyleSheet(HSSFWorkbook workbook,List<ColumnMapping> columnMappings,int columnMappingsSize,int dataListSize,SheetStyleDto sheetStyleDto) {
//		HSSFSheet sheet = workbook.getSheetAt(0);
//		HSSFCell cell = null;
//		for(int i = 0; i < columnMappingsSize; i ++){
//			String columnType = columnMappings.get(i).getType();
//			for(int j = 1; j <= dataListSize; j ++){
//				cell = sheet.getRow(j).getCell(i);
//				if(!"".equals(cell.getStringCellValue())){//excel单表当前单元格为空则不进行处理
//					if(ViewConstants.EXCEL_COLUMN_DATE_TYPE.equals(columnType)){
//						formatDateValue(workbook, cell,sheetStyleDto.getExcelDateFormat());
//						continue;
//					}
////					if(ViewConstants.NUMBER_TYPE.equals(columnType)){
////						//do nothing
////						continue;
////					}
//				}
//			}
//		}
//	}
///*
// * 将描述日期的字符串转换为适用于EXCEL的日期类型，可自定义日期显示风格
// */
//	private void formatDateValue(HSSFWorkbook workbook, HSSFCell cell,ExcelDateFormat excelDateFormat) {
//		HSSFCellStyle cellStyle;
//		HSSFDataFormat format;
//		String value = null;
//		Date date = null;
//		if(excelDateFormat instanceof ExcelDateFormat){
//			try {
//				value = cell.getStringCellValue();
//				date = Utils.parseDate(value, Constants.DATE_FORMAT_YYMMDD);//将描述日期的字符串转换为java.util.Date类类型
//				cell.setCellValue(date);
//			} catch (ParseException e) {
//				e.printStackTrace();
//				cell.setCellValue(value);
//			}finally{
//				cellStyle = workbook.createCellStyle();
//				format = workbook.createDataFormat();
//				cellStyle.setDataFormat(format.getFormat(excelDateFormat.getFormatValue()));//custom date display type for excel
//				cell.setCellStyle(cellStyle);
//			}
//		}
//	}
//
//	/*
//	 * Excel sheet 自适应列宽
//	 * @param sheet 需要自适应列宽的单表
//	 * @param columnCount 需要处理单表前n列【计数方式1，2，3，4....n】
//	 * @param rowCount 需要处理单表前n行【计数方式1，2，3，4，....n】
//	 */
//	private void autoSizeColumn(HSSFSheet sheet, int columnCount, int rowCount) {
//		for (int i = 0; i < columnCount; i++) {
//			sheet.autoSizeColumn(i);
//			int greaterWith = sheet.getColumnWidth(i)/512;// 初始化greaterWith【列宽度】
//			for (int j = 0; j < rowCount; j++) {
//				int currentWith = 0;// 初始化当前列宽度
//				try {
//					currentWith = sheet.getRow(j).getCell(i).getStringCellValue().getBytes().length;
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					greaterWith = Math.max(greaterWith, currentWith);// 更新greaterWith取较大值
//					if (MAX_WITH < greaterWith) {
//						greaterWith = MAX_WITH;
//						break;// 最大列宽不超过MAX_WITH
//					}
//				}
//			}
//			if(greaterWith<5){
//				sheet.setColumnWidth(i, greaterWith * 512);// Excel实际列宽度 = 最大字符宽度 * 256
//			}else{
//				sheet.setColumnWidth(i, greaterWith * 256);// Excel实际列宽度 = 最大字符宽度 * 256
//			}
//			
//		}
//	}
//
//	/*
//	 * 通过反射机制，生成Excel sheet表格其他行（除第一行以外）信息
//	 */
//	private void generateOtherRows(HSSFSheet sheet,List<?> dataList, int dataListSize,List<ColumnMapping> columnMappings,int columnMappingsSize) {
//		HSSFRow row;
//		for (int k = 0; k < dataListSize; k++) {
//			Object obj = dataList.get(k);
//			row = sheet.createRow(k + 1);
//			for (int i = 0; i < columnMappingsSize; i++) {
//				ColumnMapping columnMapping = columnMappings.get(i);
//				String getter = PREFIX_GET
//						+ StringUtils.capitalize(columnMapping.getField());
//				String columnVal = getActualVal(columnMapping.getMsgkey(),
//						getObjValByField(obj, getter));
//				row.createCell(i).setCellValue(columnVal);
//			}
//		}
//	}
//	
//	/*
//	 * 生成Excel sheet表格第一行信息
//	 */
//	private void generateFirstRow(HSSFSheet sheet,List<ColumnMapping> columnMappings, int columnMappingsSize) {
//		HSSFRow row = sheet.createRow(0);
//		for (int i = 0; i < columnMappingsSize; i++) {
//			row.createCell(i).setCellValue(columnMappings.get(i).getHeader());
//		}
//	}
//	
//	private void exportDomestic(HSSFSheet sheet,List<?> dataList,List<ColumnMapping> columnMappings, HSSFWorkbook workbook, String templateName) {
//		//style
//		HSSFCellStyle cellstyle=workbook.createCellStyle();
//		cellstyle.setWrapText(true);
//		cellstyle.setAlignment((short)0);
//		
//		workbook.removeSheetAt(0);
//		//datelist
//		List<TDomesticSupplierDemand> pagelist=(List)dataList.get(10);
//		int issize=pagelist.size()%10==0?pagelist.size()/10:(pagelist.size()/10)+1;
//		for(int index=0;index<issize;index++){
//				sheet=workbook.createSheet(templateName+(index+1));
//				HSSFRow row = sheet.createRow(0);
//				for (int i = 0; i < 14; i++) {
//					if(i==1){
//						row.createCell(i).setCellValue("Domestic Supplier 发运单");
//						HSSFCellStyle isstyle=workbook.createCellStyle();
//						isstyle.setFillPattern(XSSFCellStyle.FINE_DOTS );
//						HSSFPalette palette=workbook.getCustomPalette();
//						palette.setColorAtIndex(HSSFColor.AQUA.index, (byte)100, (byte)111, (byte)223);
//						isstyle.setFillBackgroundColor(HSSFColor.AQUA.index);
//						isstyle.setFillForegroundColor(HSSFColor.AQUA.index);
//						//284199
//						isstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//						HSSFFont font=workbook.createFont();
//						font.setColor(HSSFColor.WHITE.index);
//						font.setFontHeight((short)500);
//						isstyle.setFont(font);
//						row.getCell(i).setCellStyle(isstyle);
//					}else if(i==13){
//						row.createCell(i).setCellValue("v1.1");
//						HSSFCellStyle isstyle=workbook.createCellStyle();
//						isstyle.setFillPattern(XSSFCellStyle.FINE_DOTS );
//						HSSFPalette palette=workbook.getCustomPalette();
//						isstyle.setFillBackgroundColor(HSSFColor.ORANGE.index);
//						isstyle.setFillForegroundColor(HSSFColor.ORANGE.index);
//						//284199
//						isstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//						HSSFFont font=workbook.createFont();
//						font.setColor(HSSFColor.WHITE.index);
//						font.setFontHeight((short)500);
//						isstyle.setFont(font);
//						row.getCell(i).setCellStyle(isstyle);
//					}else{
//						row.createCell(i).setCellValue("");
//					}
//					if(i==1){
//						sheet.setColumnWidth(i,  15*256);
//					}else if(i==2){
//						sheet.setColumnWidth(i,  2560);
//					}else if(i==3){
//						sheet.setColumnWidth(i,  2560);
//					}else if(i==6){
//						sheet.setColumnWidth(i,  2560);
//					}else if(i==8){
//						sheet.setColumnWidth(i,  2560);
//					}else{
//						sheet.setColumnWidth(i,  20*256);
//					}
//				}
//				Region region=new Region(0, (short)1,0,(short)12);
//				sheet.addMergedRegion(region);
//				
//				row = sheet.createRow(1);
//				for (int i = 0; i < 14; i++) {
//					if(i==1){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Pickup Date\r\n 计划提货时间*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==2){
//						row.createCell(i).setCellValue(dataList.get(0)!=null?String.valueOf(dataList.get(0)):"");
//					}else if(i==5){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Delivery Note No.\r\n发运单编号*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==6){
//						row.createCell(i).setCellValue(dataList.get(1)!=null?String.valueOf(dataList.get(1)):"");
//					}else if(i==8){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Invoice No.\r\n发票号 ");
//						cell.setCellStyle(cellstyle);
//					}else if(i==9){
//						row.createCell(i).setCellValue(dataList.get(2)!=null?String.valueOf(dataList.get(2)):"");
//					}else if(i==12){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Carrier\r\n物流承运商");
//						cell.setCellStyle(cellstyle);
//					}else if(i==13){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Anji-CEVA\r\n安吉汽车零部件物流有限公司 ");
//						cell.setCellStyle(cellstyle);
//					}else {
//						row.createCell(i).setCellValue("");
//					}
//				}
//				
//				sheet.addMergedRegion(new Region(1, (short)2,1,(short)4));
//				sheet.addMergedRegion(new Region(1, (short)6,1,(short)7));
//				sheet.addMergedRegion(new Region(1, (short)9,1,(short)11));
//				
//				sheet.addMergedRegion(new Region(0, (short)0,1,(short)0));
//				
//				row = sheet.createRow(2);
//				for (int i = 0; i < 14; i++) {
//					HSSFCellStyle isstyle=workbook.createCellStyle();
//					isstyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//					isstyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//					isstyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//					isstyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//					row.createCell(i).setCellStyle(isstyle);
//				}
//				sheet.addMergedRegion(new Region(2, (short)0,2,(short)13));
//				
//				row = sheet.createRow(3);
//				for (int i = 0; i < 14; i++) {
//					if(i==0){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Shipping\r\n供应商*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==1){
//						row.createCell(i).setCellValue(dataList.get(3)!=null?String.valueOf(dataList.get(3)):"");
//					}else if(i==7){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Shipping  Adderss\r\n供应商地址*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==8){
//						row.createCell(i).setCellValue(dataList.get(4)!=null?String.valueOf(dataList.get(4)):"");
//					}else {
//						row.createCell(i).setCellValue("");
//					}
//				}
//				sheet.addMergedRegion(new Region(3, (short)1,3,(short)6));
//				sheet.addMergedRegion(new Region(3, (short)8,3,(short)13));
//				
//				row = sheet.createRow(4);
//				for (int i = 0; i < 14; i++) {
//					if(i==0){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Shipping Code\r\n供应商 编号*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==1){
//						row.createCell(i).setCellValue(dataList.get(5)!=null?String.valueOf(dataList.get(5)):"");
//					}else if(i==7){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Shipping Contact\r\n供应 商联系人*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==8){
//						row.createCell(i).setCellValue(dataList.get(6)!=null?String.valueOf(dataList.get(6)):"");
//					}else if(i==10){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Shipping Tel\r\n供应商电话*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==11){
//						row.createCell(i).setCellValue(dataList.get(7)!=null?String.valueOf(dataList.get(7)):"");
//					}else {
//						row.createCell(i).setCellValue("");
//					}
//				}
//				sheet.addMergedRegion(new Region(4, (short)1,4,(short)6));
//				sheet.addMergedRegion(new Region(4, (short)8,4,(short)9));
//				sheet.addMergedRegion(new Region(4, (short)11,4,(short)13));
//				
//				row = sheet.createRow(5);
//				for (int i = 0; i < 14; i++) {
//					if(i==0){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Consignee\r\n收货方 ");
//						cell.setCellStyle(cellstyle);
//					}else if(i==1){
//						row.createCell(i).setCellValue(dataList.get(8)!=null?String.valueOf(dataList.get(8)):"");
//					}else if(i==7){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Consignee Address\r\n收货 方地址");
//						cell.setCellStyle(cellstyle);
//					}else if(i==8){ 
//						row.createCell(i).setCellValue(dataList.get(9)!=null?String.valueOf(dataList.get(9)):"");
//					}else {
//						row.createCell(i).setCellValue("");
//					}
//				}
//				sheet.addMergedRegion(new Region(5, (short)1,5,(short)6));
//				sheet.addMergedRegion(new Region(5, (short)8,5,(short)13));
//				
//				row = sheet.createRow(6);
//				for (int i = 0; i < 14; i++) {
//					HSSFCellStyle isstyle=workbook.createCellStyle();
//					isstyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//					isstyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//					isstyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//					isstyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//					row.createCell(i).setCellStyle(isstyle);
//				}
//				sheet.addMergedRegion(new Region(6, (short)0,6,(short)13));
//				
//				row = sheet.createRow(7);
//				for (int i = 0; i < 14; i++) {
//					if(i==0){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Call-off Demands\r\n订单需求*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==8){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("订单需求* 	Booking\r\n供应商确认量 ");
//						cell.setCellStyle(cellstyle);
//					}else if(i==11){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("LSP\r\n承运商接收数量");
//						cell.setCellStyle(cellstyle);
//					}else if(i==12){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Receiver\r\n工厂接收量");
//						cell.setCellStyle(cellstyle);
//					}else if(i==13){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Comment\r\n备注 ");
//						cell.setCellStyle(cellstyle);
//					}else {
//						row.createCell(i).setCellValue("");
//					}
//				}
//				sheet.addMergedRegion(new Region(7, (short)0,7,(short)7));
//				sheet.addMergedRegion(new Region(7, (short)1,7,(short)11));
//				
//				row = sheet.createRow(8);
//				for (int i = 0; i < 14; i++) {
//					if(i==0){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("IND\r\n序号 ");
//						cell.setCellStyle(cellstyle);
//					}else if(i==1){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Part No.\r\n零件号 *");
//						cell.setCellStyle(cellstyle);
//					}else if(i==2){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Calling \r\nDate* ");
//						cell.setCellStyle(cellstyle);
//					}else if(i==3){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Description\r\n零件名*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==4){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Total Qty.\r\n零件数 量*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==5){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Packing Type\r\n料箱种 类*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==6){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Pcs/pallet\r\n件数/托盘*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==7){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Total Pallets\r\n托盘 数量*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==8){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Total Qty.\r\n零件数 量*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==9){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Total Pallets\r\n托盘 数量*");
//						cell.setCellStyle(cellstyle);
//					}else if(i==10){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Weight(KG)\r\n总重量*（千克）");
//						cell.setCellStyle(cellstyle);
//					}else if(i==11){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Total Pallets\r\n托盘数量 ");
//						cell.setCellStyle(cellstyle);
//					}else if(i==12){
//						HSSFCell cell=row.createCell(i);
//						cell.setCellValue("Total Pallets\r\n托盘数量 ");
//						cell.setCellStyle(cellstyle);
//					}else if(i==13){
//						row.createCell(i).setCellValue("");
//					}
//				}
//				
//				List<TDomesticSupplierDemand> list=(List)dataList.get(10);
//				
//				if(list!=null&&list.size()>0){
//					for(int p=0;p<10;p++){
//						row = sheet.createRow(9+p);
//						if(index*10+p<list.size()){
//							for (int i = 0; i < 14; i++) {
//								if(i==0){
//									row.createCell(i).setCellValue(p+1);
//									HSSFCellStyle isstyle=workbook.createCellStyle();
//									isstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//									row.getCell(i).setCellStyle(isstyle);
//								}else if(i==1){
//									row.createCell(i).setCellValue(list.get(index*10+p).getPartNo());
//								}else if(i==2){
//									row.createCell(i).setCellValue(list.get(index*10+p).getCallingDate());
//								}else if(i==3){
//									row.createCell(i).setCellValue(list.get(index*10+p).getDescription());
//								}else if(i==4){
//									row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getTotalQtyA()));
//								}else if(i==5){
//									row.createCell(i).setCellValue(list.get(index*10+p).getPackingType());
//								}else if(i==6){
//									row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getPosPallet()));
////									row.getCell(i).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//								}else if(i==7){
//									row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getTotalPalletsA()));
////									row.getCell(i).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//								}else if(i==8){
//									row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getTotalQtyB()));
////									row.getCell(i).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//								}else if(i==9){
//									row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getTotalPalletsB()));
////									row.getCell(i).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//								}else if(i==10){
//									row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getWeight()));
////									row.getCell(i).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//								}else if(i==11){
//									if(Utils.notEmpty(list.get(index*10+p).getTotalPalletsC())){
//										row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getTotalPalletsC()));
//									}else{
//										row.createCell(i).setCellValue("");
//									}
////									row.getCell(i).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//								}else if(i==12){
//									if(Utils.notEmpty(list.get(index*10+p).getTotalPalletsD())){
//										row.createCell(i).setCellValue(Double.valueOf(list.get(index*10+p).getTotalPalletsD()));
//									}else{
//										row.createCell(i).setCellValue("");
//									}
////									row.getCell(i).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//								}else if(i==13){
//									row.createCell(i).setCellValue(list.get(index*10+p).getComments());
//								}
//							}
//						}else{
//							for (int i = 0; i < 14; i++) {
//								if(i==0){
//									row.createCell(i).setCellValue(p+1);
//									HSSFCellStyle isstyle=workbook.createCellStyle();
//									isstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//									row.getCell(i).setCellStyle(isstyle);
//								}else if(i==1){
//									row.createCell(i).setCellValue("");
//								}else if(i==2){
//									row.createCell(i).setCellValue("");
//								}else if(i==3){
//									row.createCell(i).setCellValue("");
//								}else if(i==4){
//									row.createCell(i).setCellValue("");
//								}else if(i==5){
//									row.createCell(i).setCellValue("");
//								}else if(i==6){
//									row.createCell(i).setCellValue("");
//								}else if(i==7){
//									row.createCell(i).setCellValue("");
//								}else if(i==8){
//									row.createCell(i).setCellValue("");
//								}else if(i==9){
//									row.createCell(i).setCellValue("");
//								}else if(i==10){
//									row.createCell(i).setCellValue("");
//								}else if(i==11){
//									row.createCell(i).setCellValue("");
//								}else if(i==12){
//									row.createCell(i).setCellValue("");
//								}else if(i==13){
//									row.createCell(i).setCellValue("");
//								}
//							}
//						}
//						
//					}
//				}
//				
//				row = sheet.createRow(19);
//				for (int i = 0; i < 14; i++) {
//						HSSFCellStyle isstyle=workbook.createCellStyle();
//						isstyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//						isstyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//						isstyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//						isstyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//						row.createCell(i).setCellStyle(isstyle);
//					}
//				sheet.addMergedRegion(new Region(19, (short)0,19,(short)13));
//				
//				row = sheet.createRow(20);
//				for(int i=0;i<14;i++){
//					if(i==0){
//						row.createCell(i).setCellValue("Supplier confirm \r\n供应商确认");
//						row.getCell(i).setCellStyle(cellstyle);
//					}else if(i==4){
//						row.createCell(i).setCellValue("LSP confirm	\r\n 物流服务承运商确认");
//						row.getCell(i).setCellStyle(cellstyle);
//					}else if(i==9){
//						row.createCell(i).setCellValue("Receiver Confirm	\r\n 收货人确认");
//						row.getCell(i).setCellStyle(cellstyle);
//					}
//				}
//				sheet.addMergedRegion(new Region(20, (short)0,20,(short)3));
//				sheet.addMergedRegion(new Region(20, (short)4,20,(short)8));
//				sheet.addMergedRegion(new Region(20, (short)9,20,(short)13));
//
//				row = sheet.createRow(21);
//				for(int i=0;i<14;i++){
//					if(i==0){
//						row.createCell(i).setCellValue("Signature: \r\n\r\n 签名:");
//						row.getCell(i).setCellStyle(cellstyle);
//					}else if(i==4){
//						row.createCell(i).setCellValue("Signature: \r\n\r\n 签名:");
//						row.getCell(i).setCellStyle(cellstyle);
//					}else if(i==9){
//						row.createCell(i).setCellValue("Signature: \r\n\r\n 签名:");
//						row.getCell(i).setCellStyle(cellstyle);
//					}
//				}
//				sheet.addMergedRegion(new Region(21, (short)0,21,(short)3));
//				sheet.addMergedRegion(new Region(21, (short)4,21,(short)8));
//				sheet.addMergedRegion(new Region(21, (short)9,21,(short)13));
//				
//				row = sheet.createRow(22);
//				for(int i=0;i<14;i++){
//					if(i==0){
//						row.createCell(i).setCellValue("Date: \r\n 时间:");
//						row.getCell(i).setCellStyle(cellstyle);
//					}else if(i==4){
//						row.createCell(i).setCellValue("Date: \r\n 时间:");
//						row.getCell(i).setCellStyle(cellstyle);
//					}else if(i==9){
//						row.createCell(i).setCellValue("Date: \r\n 时间:");
//						row.getCell(i).setCellStyle(cellstyle);
//					}
//				}
//				sheet.addMergedRegion(new Region(22, (short)0,22,(short)3));
//				sheet.addMergedRegion(new Region(22, (short)4,22,(short)8));
//				sheet.addMergedRegion(new Region(22, (short)9,22,(short)13));
//				
//				for(int i=0;i<23;i++){
//					if(i==0){
//						sheet.getRow(i).setHeight((short)1000);
//					}else if(i==21){
//						sheet.getRow(i).setHeight((short)1500);
//					}else if(i==1){
//						sheet.getRow(i).setHeight((short)800);
//					}else if(i==2){
//						sheet.getRow(i).setHeight((short)100);
//					}else if(i==6){
//						sheet.getRow(i).setHeight((short)100);
//					}else if(i==19){
//						sheet.getRow(i).setHeight((short)100);
//					}else{
//						sheet.getRow(i).setHeight((short)600);
//					}
//					
//				}
//			}
//		
//	}
//	
//	
//private void exportSupplier(HSSFSheet sheet,List<?> dataList,List<ColumnMapping> columnMappings) {
//		HSSFRow row = sheet.createRow(0);
//		for (int i = 0; i < 8; i++) {
//			if(i==0){
//				row.createCell(i).setCellValue("Domestic Supplier 发运单");
//			}else {
//				row.createCell(i).setCellValue("");
//			}
//		}
//		Region region=new Region(0, (short)0,0,(short)13);
//		sheet.addMergedRegion(region);
//		
//}
//
//
//	private String getValue(List<?> dataList,List<ColumnMapping> columnMappings,int index){
//		Object obj = dataList.get(index);
//		ColumnMapping columnMapping = columnMappings.get(index);
//		String getter = PREFIX_GET
//				+ StringUtils.capitalize(columnMapping.getField());
//		String columnVal = getActualVal(columnMapping.getMsgkey(),
//				getObjValByField(obj, getter));
//		return columnVal;
//	}
//	
//	private void sortColumnMappings(List<ColumnMapping> columnMappings) {
//		Collections.sort(columnMappings, new Comparator<ColumnMapping>() {
//			@Override
//			public int compare(ColumnMapping o1, ColumnMapping o2) {
//				if (o1.getColumnIndex() > o2.getColumnIndex()) {
//					return 1;
//				}
//				if (o1.getColumnIndex() < o2.getColumnIndex()) {
//					return -1;
//				}
//				return 0;
//			}
//		});
//	}
//
//	private String getObjValByField(Object obj, String getter) {
//		String str = "";
//		try {
//			Method method = obj.getClass().getMethod(getter, null);
//			Object returnedObj = method.invoke(obj, null);
//			if (null == returnedObj) {
//				return "";
//			}
//			if (returnedObj instanceof Date) {
//				str = DateUtil.dfDate.format(returnedObj);
//			} else {
//				str = String.valueOf(returnedObj);
//			}
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		return str;
//	}
//
//	private String getActualVal(String msgKey, String str) {
//		if (StringUtils.isNotBlank(msgKey)) {
//		}
//		return str;
//	}
//	private void setFirstRowByDefultExcel(HSSFSheet sheet,HSSFWorkbook workbook, Object object){
//		FileInputStream fi = null;
//		HSSFWorkbook hwb = null;
//		HSSFRow bookrow = null;// 行
//		HSSFCell bookcell = null;// 列
//		try {
//			//String path=ExportExcel2003DocumentHandler.class.getClassLoader().getResource("").getPath()+PropertiesUtil.getPropertyValueByKey("excel_template_ocean");
//			String path=System.getProperty("user.dir");
//			path=path.substring(0, path.length()-4);
//			path=path+PropertiesUtil.getPropertyValueByKey("excel_template_ocean");
////			String path="D:/zctest.xls";
//			fi = new FileInputStream(path);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			hwb = new HSSFWorkbook(fi);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		HSSFSheet booksheet = null;
//		if(String.valueOf(object).equals("CEP")){
//			booksheet=hwb.getSheetAt(3);
//		}else{
//			booksheet=hwb.getSheetAt(0);
//		}
//		
//		int rows = booksheet.getLastRowNum();//行数, base 0
//		int cols = -1;
//		for (int i = 0 ; i <= rows ; i++) {
//		HSSFRow row = sheet.createRow((short) i);
//		bookrow = booksheet.getRow(i);//取得行
//			cols = bookrow.getLastCellNum();//取得最后一列有序号 base 1
//			for (int j = 0 ; j < cols ; j++) {//遍历列
//				if(i==0){
//					sheet.setColumnWidth(j, booksheet.getColumnWidth(j));
//				}
//				HSSFCell cell = row.createCell(j); // row.createCell(0);
//				bookcell = bookrow.getCell(j);
//				String value = null;
//					 if (null != bookcell) {
//						 value=bookcell.getRichStringCellValue().toString();
//						 cell.setCellValue(value);// 设置cell的整数类型的值
//						 HSSFCellStyle style=bookcell.getCellStyle();
//						 if(style!=null){
//							 HSSFCellStyle isstyle = workbook.createCellStyle();
//							 isstyle.setFillForegroundColor(style.getFillForegroundColor());
//							 isstyle.setFillPattern(style.getFillPattern());
//							 
//							 cell.setCellStyle(isstyle);
//						 }
//					 }
//		
//			}
//		}
//		int length=booksheet.getNumMergedRegions();
//		for(int index=0;index<length;index++){
//			sheet.addMergedRegion(booksheet.getMergedRegion(index));
//		}
//	}
//	 
//	private void generateOtherRows1(HSSFSheet sheet,List<?> dataList, int dataListSize,List<ColumnMapping> columnMappings,int columnMappingsSize) {
//		HSSFRow row;
//		for (int k = 0; k < dataListSize; k++) {
//			Object obj = dataList.get(k);
//			row = sheet.createRow(k + 4);
//			for (int i = 0; i < columnMappingsSize; i++) {
//				ColumnMapping columnMapping = columnMappings.get(i);
//				String getter = PREFIX_GET
//						+ StringUtils.capitalize(columnMapping.getField());
//				String columnVal = getActualVal(columnMapping.getMsgkey(),
//						getObjValByField(obj, getter));
//				row.createCell(i).setCellValue(columnVal);
//			}
//		}
//	}

}
