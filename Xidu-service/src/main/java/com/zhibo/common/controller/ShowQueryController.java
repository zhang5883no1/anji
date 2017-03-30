/**
 * 
 */
package com.zhibo.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.common.util.PropertiesUtil;
import com.xidu.framework.common.util.Utils;
import com.zhibo.common.constant.CommonConstant;
import com.zhibo.common.dto.QueryCommonDto;
import com.zhibo.common.service.CommonService;

/**
 * @author WEICWANG
 *
 */
@Controller
@RequestMapping(value = "/common/showQuery")
public class ShowQueryController extends BaseController{
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init(Model model,HttpServletRequest request) {
		String queryKey=request.getParameter(CommonConstant.QUYER_KEY);
		String targetId=request.getParameter(CommonConstant.TARGET_ID);
		List queryTitleList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".queryTitle").split(","));
		List queryNameList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".queryName").split(","));
		List queryValueList=new ArrayList();
		for(int i=0;i<queryNameList.size();i++){
			queryValueList.add("");
		}
		List tableTitleList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".tableTitle").split(","));
		List tableColumnList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".tableColumn").split(","));
		
		QueryCommonDto queryCommonDto=new QueryCommonDto();
		//if domain name is not null query jpa ,else query by sql
		String domainName=PropertiesUtil.getPropertyValueByKey(queryKey+".domainName");
		if(domainName!=null){
			queryCommonDto.setDomainName(domainName);
		}else{
			queryCommonDto.setSql(PropertiesUtil.getPropertyValueByKey(queryKey+".sql"));
			queryCommonDto.setProperties(PropertiesUtil.getPropertyValueByKey(queryKey+".properties"));
			queryCommonDto.setReturnDto(PropertiesUtil.getPropertyValueByKey(queryKey+".returnDto"));
		}
		try {
			commonService.getResultListByCondition(queryCommonDto);
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		
		request.setAttribute("title", PropertiesUtil.getPropertyValueByKey(queryKey+".title"));
		request.setAttribute("queryTitleList", queryTitleList);
		request.setAttribute("queryNameList", queryNameList);
		request.setAttribute("queryValueList", queryValueList);
		request.setAttribute("titleList",tableTitleList);
		request.setAttribute("propertyList",tableColumnList);
		request.setAttribute("resultList", queryCommonDto.getResultList());
		request.setAttribute(CommonConstant.QUYER_KEY, queryKey);
		request.setAttribute(CommonConstant.TARGET_ID,targetId);
		request.setAttribute("returnColumn", PropertiesUtil.getPropertyValueByKey(queryKey+".returnColumn"));
		model.addAttribute("queryCommonDto",queryCommonDto);
		return CommonConstant.SHOW_QUERY_PAGE;
	}
	
	@RequestMapping(value="query" ,method = RequestMethod.POST)
	public String queryPackageInfo(QueryCommonDto queryCommonDto,Model model,HttpServletRequest request){
		String queryKey=request.getParameter(CommonConstant.QUYER_KEY);
		String targetId=request.getParameter(CommonConstant.TARGET_ID);
		List<String> queryTitleList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".queryTitle").split(","));
		List<String> queryNameList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".queryName").split(","));
		List queryValueList=new ArrayList();
		List<String> tableTitleList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".tableTitle").split(","));
		List<String> tableColumnList=Utils.arrayToList(PropertiesUtil.getPropertyValueByKey(queryKey+".tableColumn").split(","));
		
		//if domain name is not null query jpa ,else query by sql
		String domainName=PropertiesUtil.getPropertyValueByKey(queryKey+".domainName");
		if(domainName!=null){
			queryCommonDto.setDomainName(domainName);
		}else{
			queryCommonDto.setSql(PropertiesUtil.getPropertyValueByKey(queryKey+".sql"));
			queryCommonDto.setProperties(PropertiesUtil.getPropertyValueByKey(queryKey+".properties"));
			queryCommonDto.setReturnDto(PropertiesUtil.getPropertyValueByKey(queryKey+".returnDto"));
		}
		
		Map properties=new HashMap();
		for(int i=0;i<queryNameList.size();i++){
			String value=request.getParameter( queryNameList.get(i));
			properties.put(queryNameList.get(i), value);
			queryValueList.add(value);
		}
        queryCommonDto.setQueryProperties(properties);
		
		try {
			commonService.getResultListByCondition(queryCommonDto);
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		
		request.setAttribute("title", PropertiesUtil.getPropertyValueByKey(queryKey+".title"));
		request.setAttribute("queryTitleList", queryTitleList);
		request.setAttribute("queryNameList", queryNameList);
		request.setAttribute("queryValueList", queryValueList);
		request.setAttribute("titleList",tableTitleList);
		request.setAttribute("propertyList",tableColumnList);
		request.setAttribute("resultList", queryCommonDto.getResultList());
		request.setAttribute(CommonConstant.QUYER_KEY, queryKey);
		request.setAttribute(CommonConstant.TARGET_ID,targetId);
		request.setAttribute("returnColumn", PropertiesUtil.getPropertyValueByKey(queryKey+".returnColumn"));
		model.addAttribute("queryCommonDto",queryCommonDto);
		return CommonConstant.SHOW_QUERY_PAGE;//------------根据以上conditionMap从request中获取
		
	}
}
