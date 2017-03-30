/************************************************************************************
 * @File name   :      CookieUtils.java
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
 * 2011-2-23 上午10:44:28        Michael Gu        1.0                Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xidu.framework.common.util.CookieUtils;
import com.xidu.framework.common.util.EnDecoderUtils;

/**
 *Provide Cookie Utility.
 */
public final class CookieUtils {
    // create logger instance.
    private static Log logger = LogFactory.getLog(CookieUtils.class);

    /**
     * default private Constructor
     * 
     * @Date : 2011-3-28
     */

    private CookieUtils() {

    }

    /**
     * set the name/value entry to the cookie
     * 
     * @Date : 2011-3-23
     * @param req
     *            - HttpServletRequest's instance
     * @param res
     *            - HttpServletResponse's instance
     * @param domain
     *            - the domain of sites
     * @param name
     *            - cookie's entry name
     * @param value
     *            - cookie's entry value
     * @param expiry
     *            - cookie's expired time
     */
    public static void setCookie(HttpServletRequest req, HttpServletResponse res, String domain,
        String name, String value, int expiry) {
        value = EnDecoderUtils.encode(value);
        if (StringUtils.isBlank(domain) || StringUtils.isBlank(name) 
            || StringUtils.isBlank(value)) {
            return;
        }
        Cookie cookie = null;
        Cookie[] cookies = req.getCookies();
        boolean isNew = true;
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (name.equals(cookie.getName())) {
                    isNew = false;
                    setCookie(res, name, value, "/", domain, expiry);
                }
            }
        }
        if (isNew) {
            setCookie(res, name, value, "/", domain, expiry);
        }
    }

    /**
     * set the name/value entry to the cookie
     * 
     * @Date : 2011-3-23
     * @param response
     *            - HttpServletResponse's instance
     * @param name
     *            - Cookie's Entry key
     * @param value
     *            - Cookie's Entry value
     * @param path
     *            - Cookie's path
     * @param domain
     *            - Cookie' domain
     * @param maxAge
     *            - Cookie's max age
     */
    public static void setCookie(HttpServletResponse response, String name, String value,
        String path, String domain, int maxAge) {
        logger.debug("cookie value:" + value);
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        if (StringUtils.isNotBlank(path)) {
            cookie.setPath(path);
        }
        cookie.setMaxAge(maxAge);
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }

    /**
     * get the value from cookie vai the name
     * 
     * @param req
     *            - HttpServletRequest's instance
     * @param res
     *            - HttpServletResponse's instance
     * @param name
     *            - Cookie's entry key
     * @return cookie's entry value
     */
    public static String getCookie(HttpServletRequest req, HttpServletResponse res, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        Cookie cookie = null;
        String value = null;
        Cookie[] cookies = req.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                logger.debug("Cookie name:" + cookie.getName() + " value:" + cookie.getValue());
                if (name.equals(cookie.getName())) {
                    value = cookie.getValue();
                    break;
                }
            }
        }
        logger.debug("Getting cookie value:" + value);
        return EnDecoderUtils.decode(value);
    }

}
