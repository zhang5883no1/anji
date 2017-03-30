/************************************************************************************
 * @File name   :      WhereConditionGenerator.java
 *
 * @Author      :      Jinaxi Wu
 *
 * @Date        :      2011-3-4
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                         Who             Version            Comments
 * 2011-3-4 上午09:43:36         Jianxi Wu        1.0                Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

import java.math.BigDecimal;

import com.xidu.framework.common.util.WhereConditionGenerator;

/**
 * JP-Query helper class. Generate JP-Query String.
 */
public final class WhereConditionGenerator {
	// JP-Query string's StringBuilder's instance
	private StringBuilder sb = new StringBuilder();

	// sql key word "and"
	private static final String AND = "and";

	// sql key word "or"
	private static final String OR = "or";

	// sql key word "like"
	private static final String LIKE = "like";

	// sql default where condition
	private static final String WHERE_DEFAULT = " where 1=1 ";

	// empty string
	private static final String EMPTY_STRING = "";

	/**
	 * default constructor.
	 * 
	 * @Date : 2011-3-23
	 */
	public WhereConditionGenerator() {

	}

	/**
	 * Construct WhereConditionGenerator by itself.
	 * 
	 * @Date : 2011-3-23
	 * @param wcg
	 *            - self-instance.
	 */
	public WhereConditionGenerator(WhereConditionGenerator wcg) {
		this.sb = wcg.sb;
	}

	/**
	 * Construct WhereConditionGenerator by String.
	 * 
	 * @Date : 2011-3-23
	 * @param str
	 *            - string
	 */
	public WhereConditionGenerator(String str) {
		this.sb.append(str);
	}

	/**
	 * init WhereConditionGenerator by String.
	 * 
	 * @Date : 2011-3-23
	 * @param str
	 *            - string
	 * @return current instance
	 */
	public WhereConditionGenerator init(String str) {
		this.sb.append(str);
		return this;
	}

	/**
	 * append string to WhereConditionGenerator.
	 * 
	 * @Date : 2011-3-23
	 * @param str
	 *            - string
	 * @return current instance
	 */
	public WhereConditionGenerator append(String str) {
		this.sb.append(str);
		return this;
	}

	/**
	 * append default where condition to current WhereConditionGenerator
	 * instance.
	 * 
	 * @Date : 2011-3-23
	 * @return current instance
	 */
	public WhereConditionGenerator where() {
		this.sb.append(WHERE_DEFAULT);
		return this;
	}

	/**
	 * append and clause to where condition.
	 * 
	 * @Date : 2011-3-23
	 * @param property
	 *            - Entity's field's string representation.
	 * @param operator
	 *            - operator,like ">","=" and so on.
	 * @param obj
	 *            - specific value
	 * @return current instance
	 */
	public WhereConditionGenerator and(String property, String operator,
			Object obj) {
		return linkBy(AND, property, operator, EMPTY_STRING, obj, EMPTY_STRING);
	}

	/**
	 * append or clause to where condition.
	 * 
	 * @Date : 2011-3-23
	 * @param property
	 *            - Entity's field's string representation.
	 * @param operator
	 *            - operator,like ">","=" and so on.
	 * @param obj
	 *            - specific value
	 * @return current instance
	 */
	public WhereConditionGenerator or(String property, String operator,
			Object obj) {
		return linkBy(OR, property, operator, EMPTY_STRING, obj, EMPTY_STRING);
	}

	/**
	 * append date compare expression to where condition.
	 * 
	 * @Date : 2011-3-23
	 * @param property
	 *            - Entity's field's string representation.
	 * @param operator
	 *            - operator,like ">","=" and so on.
	 * @param dateObj
	 *            - string representation of date.
	 * @param formatStr
	 *            - date format pattern.
	 * @return current instance
	 */
	public WhereConditionGenerator andDate(String property, String operator,
			String dateObj, String formatStr) {
		if (null == property || "".equals(property.trim())) {
			throw new IllegalArgumentException("property is null!");
		}
		if (null == dateObj || "".equals(dateObj.trim())) {
			return this;
		}

		sb.append(" ").append(AND).append(" ").append(property).append(" ")
				.append(operator).append(" ").append("to_date('")
				.append(dateObj).append("','").append(formatStr).append("') ");

		return this;
	}

	/**
	 * append like expressioin to where condition.
	 * 
	 * @param property
	 *            - Entity's field's string representation.
	 * @param leftWildcard
	 *            - sql left wildcard "%"
	 * @param obj
	 *            - specific value
	 * @param rightWildcard
	 *            - sql right wildcard "%"
	 * @return current instance
	 */
	public WhereConditionGenerator andLike(String property,
			String leftWildcard, Object obj, String rightWildcard) {
		return linkBy(AND, property, LIKE, leftWildcard, obj, rightWildcard);
	}

	/**
	 * Generate compare expression.
	 * 
	 * @Date : 2011-3-23
	 * @param linkOperator
	 *            - link operator, ex: and,or
	 * @param property
	 *            - Entity's field's string representation.
	 * @param operator
	 *            - operator,like ">","=" and so on.
	 * @param obj
	 *            - specific value
	 * @return current instance.
	 */
	private WhereConditionGenerator linkBy(String linkOperator,
			String property, String operator, String leftWildcard, Object obj,
			String rightWildcard) {
		if (null == property || "".equals(property.trim())) {
			throw new IllegalArgumentException("property is null!");
		}

		if (null == obj) {
			return this;
		}

		this.sb.append(" ").append(linkOperator).append(" ").append(property)
				.append(" ").append(operator).append(" ");

		if (obj instanceof String) {
			sb.append("'").append(leftWildcard)
					.append(String.valueOf(obj).trim()).append(rightWildcard)
					.append("'");
		} else if (obj instanceof Byte || obj instanceof Integer
				|| obj instanceof Short || obj instanceof Long
				|| obj instanceof Float || obj instanceof Double
				|| obj instanceof BigDecimal) {
			sb.append(String.valueOf(obj));
		} else {
			sb.append(obj);
		}
		return this;
	}

	/**
	 * get string representation of where cluase.
	 * 
	 * @Date : 2011-3-23
	 * @return string representation of where cluase.
	 */
	public String toQuery() {
		return this.sb.toString();
	}

	/**
	 * 
	 * {@inheritDoc} overridden:
	 * 
	 * @Date : 2011-3-28
	 * @see java.lang.Object#toString()
	 * 
	 */
	@Override
	public String toString() {
		return sb.toString();
	}

	/**
	 * sort
	 * @param sortColumn
	 * @param ascOrDesc
	 */
	public void sort(String sortColumn, String ascOrDesc) {
		// TODO Auto-generated method stub
		if(sortColumn==null || "".equals(sortColumn)){
			this.sb.append(" order by id desc");
		}else{
			this.sb.append(" order by "+sortColumn+" " +ascOrDesc);
		}
	}
}
