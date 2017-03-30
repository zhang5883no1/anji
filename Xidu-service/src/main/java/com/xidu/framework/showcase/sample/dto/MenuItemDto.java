/******************************************************************************
 * @File name   :      MenuItem.java
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
 * 2011-4-7 下午02:19:34        Eric Zhang     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.showcase.sample.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuItem
 */
public class MenuItemDto {

    private String menuCode;
    private String menuName;
    private MenuItemDto parentMenu;
    private String url;
    private List<MenuItemDto> subMenus = new ArrayList<MenuItemDto>();
    /**
     * @Date        :      2011-4-7
     *
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }
    /**
     * @Date        :      2011-4-7
     *
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    /**
     * @Date        :      2011-4-7
     *
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * @Date        :      2011-4-7
     *
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * @Date        :      2011-4-7
     *
     * @return the parentMenu
     */
    public MenuItemDto getParentMenu() {
        return parentMenu;
    }
    /**
     * @Date        :      2011-4-7
     *
     * @param parentMenu the parentMenu to set
     */
    public void setParentMenu(MenuItemDto parentMenu) {
        this.parentMenu = parentMenu;
    }
    /**
     * @Date        :      2011-4-7
     *
     * @return the subMenus
     */
    public List<MenuItemDto> getSubMenus() {
        return subMenus;
    }
    /**
     * @Date        :      2011-4-7
     *
     * @param subMenus the subMenus to set
     */
    public void addSubMenus(MenuItemDto subMenu) {
        this.subMenus.add(subMenu);
    }
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
    
}
