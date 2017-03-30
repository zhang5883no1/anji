package com.zhibo.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.common.util.PropertiesUtil;
import com.zhibo.common.service.CommonService;


@Controller
@RequestMapping(value = "/common/validDistinct")
public class ValidDistinctController extends BaseController {
	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "valid", method = RequestMethod.GET)
	@ResponseBody
	public String valid(Model model, HttpServletRequest request) {
		String value = request.getParameter("code");
		String type= request.getParameter("type");
		String id=request.getParameter("id");
		String table=PropertiesUtil.getPropertyValueByKey("valid_"+type+"_table");
		String column=PropertiesUtil.getPropertyValueByKey("valid_"+type+"_column");
		if(id!=null&&!"".equals(id)){
			String sql="select t."+column+" from "+table+" t where t.id="+id;
			String code=commonService.getEntityBySql(sql);
			System.out.println(code+":"+value);
			if(code!=null&&!"".equals(code)&&code.equals(value)){
				return "isnotExist";
			}
		}
		String countsql="select count(1) from "+table+" t where t."+column+"='"+value.trim()+"' and t.DELETE_FLAG='0'";
		return commonService.validBysql(countsql);
	}
	
}
