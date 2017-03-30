/************************************************************************************
 * @File name   :      Utils.java
 *
 * @Author      :      Brenda Yin
 *
 * @Date        :      2011-1-24
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
 * 2011-1-24 下午12:43:43        Brenda Yin         1.0                Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;

/**
 * This class contains the common utilities methods
 */
public final class Utils {
	public static Log log = LogFactory.getLog(Utils.class);

	/**
	 * default private constructor
	 * 
	 * @Date : 2011-3-28
	 */
	private Utils() {

	}

	/**
	 * Check if the input str is null or ""
	 * 
	 * @Date : 2011-3-21
	 * @param str
	 *            - string
	 * @return true if str is null or empty; otherwise, false
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * Check if the input str is not null and ""
	 * 
	 * @Date : 2011-3-21
	 * @param str
	 *            - string
	 * @return true if str is not null and empty
	 */
	public static boolean notEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}
	/**
	 * Parse the date with the given date string and date format.
	 * 
	 * @Date : 2011-6-9
	 * @param dateStr
	 *            a string of date
	 * @param format
	 *            a string of date format
	 * @return an instance of Date after parsed
	 * @throws ParseException
	 */
	public static Date parseDate(String dateStr, String format)
			throws ParseException {
		if (isNullOrEmpty(format) || isNullOrEmpty(dateStr)) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		return dateFormat.parse(dateStr.trim());
	}
	/**
	 * Formats the date input with the given format
	 * 
	 * @Date : 2011-1-25
	 * @param format
	 *            - date format strign
	 * @param date
	 *            - instance of java.util.Date
	 * @return string representation of date
	 */
	public static String formatDate(String format, Date date) {
		if (isNullOrEmpty(format) || date == null) {
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * Transfer \\ to / in path
	 * 
	 * @Date : 2011-3-9
	 * @param path
	 *            - uri path string
	 * @return String
	 */
	public static String transferPath(String path) {
		if (path == null) {
			return null;
		}
		path = path.replace("\\", "/");
		return path;
	}

	public static Object getProperty(Object owner, String fieldName)
			throws Exception {
		Class ownerClass = owner.getClass();
		String functionName = "get";
		if (fieldName.indexOf(0) > 97) {
			functionName += fieldName;
		} else {
			functionName += (fieldName.substring(0, 1).toUpperCase())
					+ fieldName.substring(1);
		}
		log.debug("function name" + functionName);
		Method method = ownerClass.getDeclaredMethod(functionName);
		return method.invoke(owner);
	}

	public static void setProperty(Object owner, String fieldName, String value)
			throws Exception {
		Class ownerClass = owner.getClass();
		String functionName = "set";
		if (fieldName.indexOf(0) > 97) {
			functionName += fieldName;
		} else {
			functionName += (fieldName.substring(0, 1).toUpperCase())
					+ fieldName.substring(1);
		}
		log.debug("function name" + functionName);

		Method method = ownerClass.getDeclaredMethod(functionName,
				java.lang.String.class);
		Object[] args = { value };
		method.invoke(owner, args);
	}

	public static List<String> arrayToList(String[] arr) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}

	/*
	 * 将装有Domain实例的List转换为装有Dto实例的list
	 * 
	 * @param domainList 原List
	 * 
	 * @param dtoClass 目录标Dto实例Class实例
	 * 
	 * @return convertedList 转换后的DtoList
	 */
	public static List<?> convertDomainListToDtoList(List<?> origDomainList,
			Class<?> dtoClass) {
		if (null == origDomainList) {
			return null;
		}
		List<Object> convertedList = new ArrayList<Object>();
		for (Object orig : origDomainList) {
			Object dest;
			try {
				dest = dtoClass.newInstance();
				ConvertUtils.register(new DateConverter(null), Date.class);
				BeanUtils.copyProperties(dest, orig);
				convertedList.add(dest);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return convertedList;
	}

	public static List<?> convertDtoListToDomainList(List<?> origDtoList,
			Class<?> domainClass) {
		if (null == origDtoList) {
			return null;
		}
		List<Object> convertedList = new ArrayList<Object>();
		for (Object orig : origDtoList) {
			Object dest;
			try {
				dest = domainClass.newInstance();
				ConvertUtils.register(new DateConverter(null), Date.class);
				BeanUtils.copyProperties(dest, orig);
				convertedList.add(dest);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return convertedList;
	}

	/*
	 * 将Domain实例转换为Dto实例
	 * 
	 * @param origDomain 原Domain实例
	 * 
	 * @param dtoClass 目标Dto实例Class实例
	 * 
	 * @return dest 转换后的Dto实例
	 */
	public static Object convertDomainToDto(Object origDomain, Class<?> dtoClass) {
		if (null == origDomain) {
			return null;
		}
		Object dest = null;
		try {
			dest = dtoClass.newInstance();
			ConvertUtils.register(new DateConverter(null), Date.class);
			BeanUtils.copyProperties(dest, origDomain);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dest;
	}

	public static Object convertDtoToDomain(Object origDto, Class<?> domainClass) {
		if (null == origDto) {
			return null;
		}
		Object dest = null;
		try {
			dest = domainClass.newInstance();
			ConvertUtils.register(new DateConverter(null), Date.class);
			BeanUtils.copyProperties(dest, origDto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dest;
	}
	
	public static Object copyDomain(Object origDomain,Class<?> domainClass){
		if(null == origDomain){
			return null;
		}
		Object dest = null;
		try {
			dest = domainClass.newInstance();
			ConvertUtils.register(new DateConverter(null), Date.class);
			BeanUtils.copyProperties(dest, origDomain);
		} catch (Exception e) {
			return null;
		}
		return dest;
	}

	/**
	 * Check if the input long id is not null and 0
	 * 
	 * @Date : 2011-7-19
	 * @param id
	 * @return
	 */
	public static boolean notNullAndZero(Long id) {
		return id != null && id != 0;
	}

	public static String randomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		char[] randBuffer = new char[length];
			randGen = new Random();
			numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
			int i = 0;
			for (i = 0; i < randBuffer.length; i++) {
				randBuffer[i] = numbersAndLetters[randGen.nextInt(26)];
			}
		return new String(randBuffer);
	}
	
	

}
