/******************************************************************************
 * @File name   :      StringUtil.java
 * @Author      :      XINLYU
 * @Date        :      2013-8-5
 * @Copyright Notice: 
 * Copyright (c) 2012 Capgemini, Inc. All  Rights Reserved.
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2013-8-5 上午9:20:16        XINLYU     1.0            Initial Version
 *****************************************************************************/
package com.zhibo.common.util;

public class StringUtil {

	public static String strToIntStr(String invalidateNumStr) {
		if (!"".equals(invalidateNumStr)) {
			if (StringUtil.isNumber(invalidateNumStr)) {
				if (!invalidateNumStr.contains(".")) {
					Integer intStr = Integer.valueOf(invalidateNumStr);
					return intStr.toString();
				} else {
					Long number = Math.round(Double
							.parseDouble(invalidateNumStr));
					return number.toString();
				}
			} else {
				throw new NumberFormatException();
			}
		} else {
			return invalidateNumStr;
		}
	}

	public static String strToDoubleStr(String invalidateNumStr) {
		if (!"".equals(invalidateNumStr)) {
			if (StringUtil.isNumber(invalidateNumStr)) {
				Double doubleStr = Double.valueOf(invalidateNumStr);
				return doubleStr.toString();
			} else {
				throw new NumberFormatException();
			}
		} else {
			return invalidateNumStr;
		}
	}

	public static boolean isNumber(String needValidateStr) {
		String reg = "\\d+(\\.\\d+)?";
		return needValidateStr.matches(reg);
	}
}
