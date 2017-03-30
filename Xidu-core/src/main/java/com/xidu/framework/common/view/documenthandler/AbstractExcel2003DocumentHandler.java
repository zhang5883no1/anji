package com.xidu.framework.common.view.documenthandler;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 *
 */
public abstract class AbstractExcel2003DocumentHandler implements
		Excel2003DocumentHandler {

	/**
     * Convenient method to obtain the cell in the given sheet, row and column.
     * <p>Creates the row and the cell if they still doesn't already exist.
     * Thus, the column can be passed as an int, the method making the needed downcasts.
     * @param sheet a sheet object. The first sheet is usually obtained by workbook.getSheetAt(0)
     * @param row thr row number
     * @param col the column number
     * @return the HSSFCell
     */
    protected HSSFCell getCell(HSSFSheet sheet, int row, int col) {
        HSSFRow sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }
        HSSFCell cell = sheetRow.getCell(col);
        if (cell == null) {
            cell = sheetRow.createCell(col);
        }
        return cell;
    }

    /**
     * Convenient method to set a String as text content in a cell.
     * @param cell the cell in which the text must be put
     * @param text the text to put in the cell
     */
    protected void setText(HSSFCell cell, String text) {
        cell.setCellType(cell.getCellType());
        cell.setCellValue(text);
    }
    /**
     * Convenient method to set a String as text content in a cell.
     * @param cell the cell in which the text must be put
     * @param text the text to put in the cell
     */
    protected void setNumber(HSSFCell cell, Integer text) {
        cell.setCellType(cell.getCellType());
        if(text!=null)
        	cell.setCellValue(text);
        else
        	cell.setCellValue(0);
    }
}
