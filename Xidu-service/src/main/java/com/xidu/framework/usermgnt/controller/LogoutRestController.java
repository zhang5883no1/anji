/**
 * 
 */
package com.xidu.framework.usermgnt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xidu.framework.common.util.TokenUtils;
import com.xidu.framework.showcase.sample.auth.constant.AuthConstant;
import com.xidu.framework.showcase.sample.auth.util.UserAuthenticationUtils;
import com.xidu.framework.showcase.sample.auth.util.WebUtils;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;

/**
 * @author Marshal
 * 
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutRestController {

    private static Logger logger = Logger.getLogger(LogoutRestController.class);

    @RequestMapping
    public void logout(HttpServletRequest req, HttpServletResponse res, Model model) {
    	//clear session
    	if(req.getSession().getAttribute(WorkbenchConstant.SESSION_USER)!=null){
    		req.getSession().invalidate();
    	}
    	
        // redirect to the login page
        try {
            WebUtils.redirectToLogin(req, res,  AuthConstant.LOGIN_PAGE_URL);
            // WebUtils.redirectToLogin(req, res, WebUtils.DEFAULT_SITE,
            // req.getContextPath());
        } catch (ServletException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }

    }
}
