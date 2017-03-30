/******************************************************************************
 * @File name   :      MenuRestController.java
 *
 * @Author      :      LOZHANG
 *
 * @Date        :      2012-5-24
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 Capgemini, Inc. All  Rights Reserved.
 * This software is published under the terms of the Capgemini Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2012-5-24 上午9:47:42        LOZHANG     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.showcase.menu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.Pager;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.dto.CreateMenuDto;
import com.xidu.framework.menu.dto.QueryMenuDto;
import com.xidu.framework.menu.service.IMenuService;
import com.xidu.framework.showcase.sample.dto.TreeNodeDto;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.SessionUserDto;

/**
 *
 */
@Controller
public class MenuRestController extends BaseController{

    @Autowired
    private IMenuService menuService;
    @InitBinder  
    public void initBinder(WebDataBinder binder) {  
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
         dateFormat.setLenient(false);           
         binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }   
    /**
     * 转向menu列表页
     * @Date        :      2012-5-25
     * @param req
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView storage(HttpServletRequest req) {
        return new ModelAndView("menu/query");
    }
    @RequestMapping(value="/menuTree")
    @ResponseBody
    public List<TreeNodeDto> getTree(HttpServletRequest req) throws AppException {
        String orgCode=req.getParameter("orgCode");
        List<Menu> orgList=menuService.findChildrenMenuByMenuCode(orgCode);
        List<TreeNodeDto> treeNodeList=this.packageTreeNodeList(orgList);
        return treeNodeList;
    }
    /**
     * 请求menu列表数据
     * @Date        :      2012-5-25
     * @param queryMenuDto
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/menu/query") @ResponseBody
    public Map<String,Object> queryMenu(@Valid QueryMenuDto queryMenuDto,BindingResult result,
        Model model){      
        if(queryMenuDto.getPager() == null){
            queryMenuDto.setPager(new Pager());
        }
        Map<String,Object> rows = new HashMap<String, Object>();
        List<Menu> menus = menuService.queryMenu(queryMenuDto);
        rows.put("rows", menus);
        return rows;
    }

    /**
     * 异步添加菜单
     * @Date        :      2012-5-27
     * @param createMenuDto
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/menu/create") @ResponseBody
    public Map<String,Object> createMenu(@Valid CreateMenuDto createMenuDto,BindingResult result,
        Model model,HttpServletRequest request){
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
        Map<String,Object> message = new HashMap<String, Object>();
        Menu menu = null;
        try {
            menu = menuService.createMenu(createMenuDto.getMenuCode(), createMenuDto.getMenuName(), 
                createMenuDto.getParentMenuCode(), createMenuDto.getMenuLevel(), 
                createMenuDto.getIsLeaf(), createMenuDto.getUrl(), createMenuDto.getOperatorId(),sessionUser.getUserGroup());
        } catch (AppException e) {
            e.printStackTrace();
        }
        message.put("result", menu.getMenuName());
        return message;
    }
    
    @RequestMapping(value = "/menu/update") @ResponseBody
    public Map<String,Object> updateMenu(@Valid CreateMenuDto createMenuDto,BindingResult result,
        Model model,HttpServletRequest request){
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
    	Map<String,Object> message = new HashMap<String, Object>();
        try {
            menuService.updateMenu(createMenuDto.getMenuCode(), createMenuDto.getMenuName(), createMenuDto.getParentMenuCode(), createMenuDto.getMenuLevel(),createMenuDto.getIsLeaf(), createMenuDto.getUrl(), createMenuDto.getOperatorId(),sessionUser.getUserGroup());
        } catch (AppException e) {
            e.printStackTrace();
        }
        message.put("result", createMenuDto.getMenuName());
        return message;
    }
    
    @RequestMapping("/menu/delete/{menuCode}")
	@ResponseBody
	public Map<String,Object> deleteMenu(@PathVariable("menuCode") String menuCode,
			Model model) {
    	Map<String,Object> message = new HashMap<String, Object>();
    	try {
    		Menu menu = menuService.getMenuByMenuCode(menuCode);
    		if(menu!=null){
    			menuService.deleteMenu(menuCode);
    			message.put("result", "["+menu.getMenuName()+"]已经删除");
    		}else{
    			message.put("result", "没有找到对应的菜单");
    		}
		} catch (AppException e) {
			message.put("result", "系统异常，请联系管理员");
		}
    	return message;
	}
    
    private List<TreeNodeDto> packageTreeNodeList(List<Menu> orgList){
        List<TreeNodeDto> treeNodeList=new ArrayList<TreeNodeDto>();
        for(Menu org : orgList){
            TreeNodeDto treeNodeDto=new TreeNodeDto();
            treeNodeDto.setId(org.getMenuCode());
            treeNodeDto.setText(org.getMenuName());
            if(org.getIsLeaf()==0){
                treeNodeDto.setState("closed");
            }else{
                treeNodeDto.setState("open");
            }
            Map<String,String> attributes=new HashMap<String,String>();
            treeNodeDto.setAttributes(attributes);
            //treeNodeDto.setChildren(packageTreeNodeList(org.getChildOrgs()));
            treeNodeList.add(treeNodeDto);
        }
        return treeNodeList;
    }
}
