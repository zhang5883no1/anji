/**
 * 
 */
package com.xidu.framework.common.filter;

/**
 * @author WEICWANG
 *
 */

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 
public class UserLoginFilter implements Filter {
	Log log=LogFactory.getLog(UserLoginFilter.class);
 
    public void destroy() {
        // TODO Auto-generated method stub
 
    }
 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
    	//exception /login .js .css images 
        HttpServletRequest req    = (HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        String url=req.getRequestURL().toString();
        if(url.endsWith(".png") || url.endsWith(".jpg") || url.indexOf("/login")>0){
        	chain.doFilter(request, response);  
        	return;
        }
        log.debug("==============="+url);     
        log.debug("=====context path:"+req.getContextPath());
        if(null!=req.getSession()&&null!=req.getSession().getAttribute("currentUser")){
            chain.doFilter(request, response);            
        }else{
            log.debug("not login");
            res.sendRedirect(req.getContextPath()+"/login");
        }
    }
 
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
 
    }
 
}
