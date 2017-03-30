package com.xidu.framework.showcase.sample.auth.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xidu.framework.showcase.sample.auth.constant.AuthConstant;


public class WebUtils {

	/**
	 * The <code>Log</code> instance for this class.
	 */
	private static Log logger = LogFactory.getLog(WebUtils.class);

	public static String DOMAIN = ".shanghaigm.com";
	public static String DEFAULT_SITE = "doss";

	public static String PROTOCAL_PREFIX = "http://";

	public static String PORT = "8080";

	public static final String STORED_REQUEST_PARAMETERS = "STORED_REQUEST_PARAMETERS";
	
	
	
	public static boolean isIpAccess(HttpServletRequest req,
			HttpServletResponse res) {
		String serverName = req.getServerName();
		return serverName.matches("(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)");
	}

	public static void redirectToDomainNameAccess(HttpServletRequest req,
			HttpServletResponse res) {
		String uriString = req.getRequestURI();
		String queryString = "";
		if (StringUtils.isNotBlank(req.getQueryString()))
			queryString = req.getQueryString();
		redirectAccess(res, DEFAULT_SITE, req.getContextPath(), uriString,
				queryString);
	}
	
	
	public static void redirectAccess(HttpServletResponse res, String site,
			String context, String uriString, String queryString) {
		String url = WebUtils.PROTOCAL_PREFIX
				+ site
				+ WebUtils.DOMAIN
				+ (PORT.equals("80") ? "" : ":" + PORT)
				+ ((context != null && context.length() > 0) ? context : "")
				+ "/"
				+ ((uriString != null && uriString.length() > 0) ? uriString
						: "")
				+ ((queryString != null && queryString.length() > 0) ? "?"
						+ queryString : "");
		try {
			logger.debug("redirect to:" + url);
			res.sendRedirect(url);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public static void redirectToLogin(HttpServletRequest req,
			HttpServletResponse res, String site, String context)
			throws ServletException, IOException {
		String uriString = req.getRequestURI();
		String queryString = "";
		
		if (StringUtils.isNotBlank(req.getQueryString()))
			queryString = req.getQueryString();
		//TODO: send the uriString and queryString to cache store
		logger.debug("input uri and query string : " + uriString + "  " + queryString);
		if(uriString.indexOf(AuthConstant.LOGOUT_PAGE_URL) < 0 && uriString.indexOf(AuthConstant.LOGIN_PAGE_URL) < 0){
			req.getSession().setAttribute(AuthConstant.URI_STRING, uriString);
			req.getSession().setAttribute(AuthConstant.QUERY_STRING, queryString);
		}
		redirectAccess(res, site, context, AuthConstant.LOGIN_PAGE_URL, "");
	}
	
	/**
	 * this method redirect to login page
	 * 
	 */
	public static void redirectToLogin(HttpServletRequest req,
			HttpServletResponse res, String loginUrlSuffix)
			throws ServletException, IOException {
	
		String uriString = req.getRequestURI();
		String queryString = "";
		if (StringUtils.isNotBlank(req.getQueryString()))
			queryString = req.getQueryString();
		//TODO: send the uriString and queryString to cache store
		logger.debug("input uri and query string : " + uriString + "  " + queryString);
		
		
		String Url = ((uriString != null && uriString.length() > 0) ? uriString : "")
		+((queryString != null && queryString.length() > 0) ? "?" + queryString : "");
		logger.debug("url:" +  Url);
		
		//ignore logout
		if(uriString.indexOf(AuthConstant.LOGOUT_PAGE_URL) < 0){
			req.getSession().setAttribute(AuthConstant.URI_STRING, uriString);
			req.getSession().setAttribute(AuthConstant.QUERY_STRING, queryString);			
			req.getSession().setAttribute(AuthConstant.PREVIOUS_URL, Url);
		}
		redirectAccess(req,res,req.getContextPath(),loginUrlSuffix.substring(1),"");
		
	}
	
	
	/**
     * this method redirect to login page
     * 
     */
    public static void redirectToWorkBenchLogin(HttpServletRequest req,
            HttpServletResponse res,String workBenchLoginUrl)
            throws ServletException, IOException {
    
        try {
            logger.debug("redirect to: " + workBenchLoginUrl);
            res.sendRedirect(workBenchLoginUrl);
        } catch (IOException e) {
            logger.error(e);
        }
    }
	
	
	/** 
	 * @Author      :      YIFEGU
	 * @Date        :      2011-2-15
	 * 
	 **/
	public static void recordRequestURL(HttpServletRequest request,
			HttpServletResponse response){
		
		String uriString = request.getRequestURI();
		String queryString = "";
		if (StringUtils.isNotBlank(request.getQueryString()))
			queryString = request.getQueryString();
		//TODO: send the uriString and queryString to cache store
		
		logger.debug("input uri and query string : " + uriString + "  " + queryString);
		
		String Url = ((uriString != null && uriString.length() > 0) ? uriString : "")
		+((queryString != null && queryString.length() > 0) ? "?" + queryString : "");
		logger.debug("url:" +  Url);
		
		logger.debug("input uri and query string : " + uriString + "  " + queryString);
		request.getSession().setAttribute("uriString", uriString);
		request.getSession().setAttribute("queryString", queryString);
		request.getSession().setAttribute(AuthConstant.PREVIOUS_URL, Url);
	}
	
	/**
	 * this method redirect to login page
	 * 
	 */
	public static void redirectAccess(HttpServletRequest req,
			HttpServletResponse res,String context,String uriString, String queryString){
		
		String port =  (("" + req.getServerPort()).equals("80") ? "" : ":" + req.getServerPort());
				
		String fullUrl = req.getScheme() + "://" + req.getServerName() 
		+ port
		+ ((context != null && context.length() > 0) ? context : "")
		+ "/"
		+ ((uriString != null && uriString.length() > 0) ? uriString
				: "")
		+ ((queryString != null && queryString.length() > 0) ? "?"
				+ queryString : "");
	
		try {
			logger.debug("redirect to: " + fullUrl);
			res.sendRedirect(fullUrl);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	/**
	 * this method set value from propertie file
	 * 
	 */
	public static void initProValue(){
		
		DOMAIN = PropertiesUtil.getProperties(AuthConstant.DOSS_DOMAIN_KEY);
		DEFAULT_SITE = PropertiesUtil.getProperties(AuthConstant.DOSS_SERVER_DEFAULT_SITE_KEY);
		PORT = PropertiesUtil.getProperties(AuthConstant.DOSS_SERVER_POET_KEY);
		PROTOCAL_PREFIX = PropertiesUtil.getProperties(AuthConstant.DOSS_PROTOCAL_PREFIX_KEY);
	}
	
}