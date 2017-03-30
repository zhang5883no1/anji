/************************************************************************************
 * @File name   :      EnDecoderUtils.java
 *
 * @Author      :      Michael Gu
 *
 * @Date        :      2011-2-23
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
 * 2011-2-23 上午11:00:58        Michael Gu        1.0                Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

/**
 *Provide encode/decode utility.
 */
public final class EnDecoderUtils {

    /**
     * default private constructor
     * 
     * @Date : 2011-3-28
     */
    private EnDecoderUtils() {

    }

    /**
     * encode input string by UTF-8 coding.
     * 
     * @Date : 2011-3-23
     * @param input
     *            - string
     * @return encoded string
     */
    public static String encodeUTF8(String input) {
        return encode(input, "UTF-8");
    }

    /**
     * encode input string by UTF-8 coding.
     * 
     * @Date : 2011-3-23
     * @param input
     *            - string
     * @return encoded string
     */
    public static String encode(String input) {
        return encodeUTF8(input);
    }

    /**
     * encode input string by specific coding.
     * 
     * @Date : 2011-3-23
     * @param input
     *            - string
     * @param coding
     *            - the coding name
     * @return encoded string
     */
    public static String encode(String input, String coding) {
        if (StringUtils.isBlank(input)){
            return "";
        }
        try {
            return URLEncoder.encode(input, coding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return input;
    }

    /**
     * decode input string by UTF-8 coding.
     * 
     * @Date : 2011-3-23
     * @param input
     *            - string
     * @return encoded string
     */
    public static String decode(String input) {
        return decodeUTF8(input);
    }

    /**
     * decode input string by UTF-8 coding.
     * 
     * @Date : 2011-3-23
     * @param input
     *            - string
     * @return encoded string
     */
    public static String decodeUTF8(String input) {
        return decode(input, "UTF-8");
    }

    /**
     * decode input string by specific coding.
     * 
     * @Date : 2011-3-23
     * @param input
     *            - string
     * @param coding
     *            - coding string
     * @return encoded string
     */
    public static String decode(String input, String coding) {
        if (StringUtils.isBlank(input)){
            return "";
        }
        try {
            return URLDecoder.decode(input, coding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return input;
    }

}
