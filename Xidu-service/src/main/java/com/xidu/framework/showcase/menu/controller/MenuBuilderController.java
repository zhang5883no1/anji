/******************************************************************************
 * @File name   :      MenuBuilderController.java
 *
 * @Author      :      Eric Zhang
 *
 * @Date        :      2011-4-7
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2011-4-7 下午02:23:40        Eric Zhang     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.showcase.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.service.IMenuService;
import com.xidu.framework.showcase.sample.dto.MenuItemDto;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.SessionUserDto;

/**
 *
 */
@Controller
public class MenuBuilderController {

    @Autowired
    private IMenuService menuService;
    private Log log=LogFactory.getLog(this.getClass());
    
    @RequestMapping(value = "/buildMenu")
    public ModelAndView buildMenu(HttpServletRequest req) {
        
    	SessionUserDto currUser = (SessionUserDto) req.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
        
        List<Menu> menus;
        try {
            menus = menuService.getGrantedMenusByUserCode(currUser.getUserCode());
        } catch (AppException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ModelAndView(WorkbenchConstant.INEX_PAGE);
        }
        if (menus == null) {
            return new ModelAndView(WorkbenchConstant.INEX_PAGE);
        }
        /*req.getSession().setAttribute("menus", menus);
        ModelAndView view = new ModelAndView(WorkbenchConstant.INEX_PAGE);
        //set menus to session
       
        
        //after get menus
       // currUser.setMenuList(menus);
        
   
        return view;*/
        
        Map<String, MenuItemDto> displayMenus = new HashMap<String, MenuItemDto>();
        MenuItemDto rootDto = new MenuItemDto();
        rootDto.setMenuCode("root");
        displayMenus.put("root", rootDto);
        
        for (Menu menu: menus) {
            MenuItemDto currentMenuDto = null;
            //First to check if current menu is already in the Map or not
            if (displayMenus.containsKey(menu.getMenuCode())) { //update node in the map
                currentMenuDto = displayMenus.get(menu.getMenuCode());
                currentMenuDto.setMenuName(menu.getMenuName()); //update name
            } else { //Create a new current menu node and put in map
                currentMenuDto = new MenuItemDto();
                currentMenuDto.setMenuCode(menu.getMenuCode());
                currentMenuDto.setMenuName(menu.getMenuName());
                currentMenuDto.setUrl(menu.getUrl());
            }
            displayMenus.put(menu.getMenuCode(), currentMenuDto);
            String parentCode = menu.getParentMenuCode();
            //Check if current menu is first level menu node
            if (!"0".equals(parentCode)) { //if not a first level menu
              //check if Parent node in Map already?
                if (displayMenus.containsKey(parentCode)) { //Add current to parent directly
                    MenuItemDto parentMenuDto = displayMenus.get(parentCode); 
                    currentMenuDto.setParentMenu(parentMenuDto);
                    parentMenuDto.addSubMenus(currentMenuDto);
                } else { //Create an empty parent node and add current to parent
                    MenuItemDto parentMenuDto = new MenuItemDto();
                    parentMenuDto.setMenuCode(parentCode);
                    displayMenus.put(parentCode, parentMenuDto);
                                         
                    parentMenuDto.addSubMenus(currentMenuDto);
                   
                    currentMenuDto.setParentMenu(parentMenuDto);
                }    
            } else { //first level menu, directly add to root
                currentMenuDto.setParentMenu(rootDto);
                rootDto.addSubMenus(currentMenuDto);
            }
        }
        List<MenuItemDto> mainMenuList = (displayMenus.get("root")).getSubMenus();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuItems", mainMenuList);
        log.debug("get menu :" + mainMenuList);
        
        log.debug("get menu : " + mainMenuList.size());
        req.getSession().setAttribute("menuItems", mainMenuList);
        return new ModelAndView(WorkbenchConstant.INEX_PAGE, map);
    }
}
