package com.xidu.framework.common.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xidu.framework.common.constant.CommonConstants;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.Utils;



public class BaseController {
	protected Log log=LogFactory.getLog(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public String handleAppException(Exception ex,HttpServletRequest request){
		
		if(ex instanceof AppException){
			String errCode = ((AppException)ex).getErrorCode();
			if(Utils.notEmpty(errCode)){
				request.setAttribute("errCode", errCode);
				
			}
		} else{
			String errMsg = ex.getMessage();
			if(Utils.notEmpty(errMsg)){
				request.setAttribute("errCode", "system.error");
			}
			//request.setAttribute("exception", ex);
		}
		request.setAttribute("errMsg", ExceptionUtils.getFullStackTrace(ex));
		log.error(ex);
		return "error";
	}
	
	protected void packageSortInfo(HttpServletRequest request,BasePagerDto pageDto){
		String ecI=request.getParameter(CommonConstants.EC_I);	
		Map map=request.getParameterMap();
		Set keys=map.keySet();
		Iterator iter=keys.iterator();
		System.out.println("-----------------------------------");
		while(iter.hasNext()){
			String param=(String) iter.next();
			//sort column
			if(param.contains(ecI+"_s_")){
				if(map.get(param)!=null && !"".equals(map.get(param))){
					String[] s= (String[]) map.get(param);
					String value=request.getParameter(param);
					System.out.println(param+":"+value);
					if(Utils.notEmpty(value)){
						if(value.equals("default")){
							return;
						}
						log.debug("====================="+param.substring((ecI+"_s_").length()));
						log.debug("====================="+s[0]);
						pageDto.setSortColumn(param.substring((ecI+"_s_").length()));	
						pageDto.setAscOrDesc(value);	
						return;
					}
				}
			}
		}
	}
	
}

