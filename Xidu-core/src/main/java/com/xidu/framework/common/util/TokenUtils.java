/************************************************************************************
 * @File name   :      TokenUtils.java
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
 * 2011-2-23 上午11:09:17        Michael Gu        1.0               Initial Version
 ************************************************************************************/
package com.xidu.framework.common.util;

import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xidu.framework.common.util.TokenUtils;

/**
 * Token Utility Class.
 */
public final class TokenUtils {
    // create logger instance
    private static Log logger = LogFactory.getLog(TokenUtils.class);
    // cipher key
    private static final String CIPHER_KEY = "sgm_0987654321";

    /**
     * default private constructor
     * @Date        :      2011-3-28
     */
    private TokenUtils(){
        
    }
    /**
     * Encode a string using MD5 and return the resulting encrypted password. If
     * exception, the plain credentials string is returned
     * 
     * @param token
     *            Token or other credentials to use in authenticating this
     *            username
     * @return encypted password based on the algorithm.
     */
    private static String encryptToken(String token) {
        return encryptToken(token, "MD5");
    }

    /**
     * Encode a string using algorithm and return the resulting encrypted
     * password. If exception, the plain credentials string is returned
     * 
     * @param token
     *            Token or other credentials to use in authenticating this
     *            username
     * @param algorithm
     *            Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    private static String encryptToken(String token, String algorithm) {
        byte[] unencodedToken = token.getBytes();

        MessageDigest md;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            logger.error("Exception: " + e);

            return token;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedToken);

        // now calculate the hash
        byte[] encodedToken = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedToken.length; i++) {
            if (((int) encodedToken[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString((int) encodedToken[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * verify if encryption token is valid.
     * 
     * @Date : 2011-3-23
     * @param plainText
     *            - plain text
     * @param cipherText
     *            - cipher text
     * @return true if token is valid,false if token is invalid.
     */
    public static boolean verifyEncryptionToken(String plainText,
            String cipherText) {
        if (StringUtils.isNotEmpty(cipherText)
                && StringUtils.isNotEmpty(plainText))
        {
            return cipherText.equals(encryptToken(CIPHER_KEY + plainText));
        } else{
            return false;
        }
    }

    /**
     * get encryption token
     * 
     * @Date : 2011-3-23
     * @param plainText
     *            - plain text
     * @return encryption token string
     */
    public static String getEncryptionToken(String plainText) {
        return encryptToken(CIPHER_KEY + plainText);
    }

}
