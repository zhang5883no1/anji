package com.xidu.framework.showcase.sample.auth.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xidu.framework.common.util.CookieUtils;
import com.xidu.framework.common.util.TokenUtils;
import com.xidu.framework.showcase.sample.auth.constant.AuthConstant;

public final class UserAuthenticationUtils {

    private final static String cipherKey = "sgm_0987654321";

    private static int expiry = 1800; // 60 * 30 = 30 minutes

    public static boolean isTrustedUser(HttpServletRequest req, HttpServletResponse res) {
        String user = CookieUtils.getCookie(req, res, AuthConstant.COOKIE_USER_KEY);
        if (user != null && user.length() > 0) {
            String encryption = CookieUtils.getCookie(req, res, AuthConstant.COOKIE_CODE_KEY);
            if (encryption != null && encryption.length() > 0)
                return TokenUtils.verifyEncryptionToken(user, encryption);
            else
                return false;
        } else
            return false;
    }

    public static String getLoginUserId(HttpServletRequest req, HttpServletResponse res) {
        if (isTrustedUser(req, res)) {
            String user = CookieUtils.getCookie(req, res, AuthConstant.COOKIE_USER_KEY);

            return user;
        }
        return null;
    }

    public static void setLoginToCookie(HttpServletRequest req, HttpServletResponse res, String user) {
        String domain = PropertiesUtil.getProperties(AuthConstant.DOSS_DOMAIN_KEY);
        CookieUtils.setCookie(req, res, domain, AuthConstant.COOKIE_USER_KEY, user, expiry);
        CookieUtils.setCookie(req, res, domain, AuthConstant.COOKIE_CODE_KEY, TokenUtils
            .getEncryptionToken(user), expiry);
    }

    public static void setLogout(HttpServletRequest req, HttpServletResponse res, String user) {
        String domain = PropertiesUtil.getProperties(AuthConstant.DOSS_DOMAIN_KEY);
        CookieUtils.setCookie(req, res, domain, AuthConstant.COOKIE_USER_KEY, "abandon", 0);
        CookieUtils.setCookie(req, res, domain, AuthConstant.COOKIE_CODE_KEY, "abandon", 0);
    }

    /**
     * this method set value from propertie file
     * 
     */
    public static void initProValue() {
        expiry = Integer.valueOf(PropertiesUtil.getProperties(AuthConstant.COOKIE_EXPIRY_TIME_KEY));
    }

}
