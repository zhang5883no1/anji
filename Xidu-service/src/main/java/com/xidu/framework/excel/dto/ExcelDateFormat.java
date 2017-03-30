package com.xidu.framework.excel.dto;
/**
 * <p>
 * 日期样式枚举类定义
 * </p>
 * @author Jack Yu Capgemini
 * @version 1.0
 */
public enum ExcelDateFormat {
	DATE_FORMAT_YYMMDD("年-月-日", "yyyy-MM-dd", "1"),DATA_FORMAT_YYDDMM("年-日-月","yyyy-dd-MM","2"),DATA_FORMAT_MMDDYY("月-日-年","MM-dd-yyyy","3")
	,DATA_FORMAT_MMYYDD("月-年-日","MM-yyyy-dd","4"),DATA_FORMAT_DDMMYY("日-月-年","dd-MM-yyyy","5"),DATA_FORMAT_DDYYMM("日-年-月","dd-yyyy-MM","6")
	,DATE_FORMAT_SLASH_YYMMDD("年/月/日","yyyy/MM/dd","7"),DATA_FORMAT_SLASH_YYDDMM("年/日/月","yyyy/dd/MM","8"), DATA_FORMAT_SLASH_MMDDYY("月/日/年","MM/dd/yyyy","9")
	,DATA_FORMAT_SLASH_MMYYDD("月/年/日","MM/yyyy/dd","10"),DATA_FORMAT_SLASH_DDMMYY("日/月/年","dd/MM/yyyy","11"),DATA_FORMAT_SLASH_DDYYMM("日/年/月","dd/yyyy/MM","12");
	
	private String formatDisplayName;//日期样式在web页面上的显示名称
	private String formatValue;//日期样式值，用于后台处理
	private String key;//日期样式key,用于获取日期样式实例
	// 构造方法
	private ExcelDateFormat(String formatDisplayName, String formatValue,
			String key) {
		this.formatDisplayName = formatDisplayName;
		this.formatValue = formatValue;
		this.key = key;
	}
	/*
	 * 根据日期样式key值获取日期样式值
	 */
	public static String getFormatValue(String key) {
		for (ExcelDateFormat c : ExcelDateFormat.values()) {
			if (c.getKey().equals(key)) {
				return c.formatValue;
			}
		}
		return null;
	}
	/*
	 * 根据日期样式key值获取日期样式显示名称
	 */
	public static String getFormatDisplayName(String key) {
		for (ExcelDateFormat c : ExcelDateFormat.values()) {
			if (c.getKey().equals(key)) {
				return c.formatDisplayName;
			}
		}
		return null;
	}
/*
 * 根据日期样式key值获取日期样式枚举类实例
 */
	public static ExcelDateFormat getInstance(String key) {
		for (ExcelDateFormat c : ExcelDateFormat.values()) {
			if (c.getKey().equals(key)) {
				return c;
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public String getFormatValue() {
		return formatValue;
	}

	public String getFormatDisplayName() {
		return formatDisplayName;
	}

}
