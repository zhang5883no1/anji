package com.xidu.framework.validation.rule;

		/**
 *
 */
public interface ValidationRegExp {
	/**
	 * 电话
	 */
	final static String TELEPHONE = "^[+]{0,1}[0-9\\-\\(\\)\\ ]+$";
	/**
	 * 无特殊字符
	 */
	final static String NO_SPECIAL_CARACTERS = "^[0-9a-zA-Z]+$";
	/**
	 * 数字
	 */
	final static String ONLY_LETTER = "^[a-zA-Z\\ \\']+$";
	/**
	 * 货币
	 */
	final static String CURRENCY = "^([+-])?(\\d+(,\\d\\d\\d)*(\\.\\d{1,2})?|\\d+(\\.\\d{1,2})?)$";
	/**
	 * 手机号
	 */
	final static String MOBILE_PHONE = "^1[0-9]{10}$";
	/**
	 * 建议零售价
	 */
	final static String MSRP = "^\\d{0,12}(\\.\\d{1,4})?$";
	/**
	 * zizou wang test
	 */
	final static String ZIZOU_TEST = "^\\d+wang$";
}
