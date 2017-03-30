/******************************************************************************
 * @File name   :      IMenuService.java
 *
 * @Author      :      <Jianxi Wu>
 *
 * @Date        :      2011-4-1
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
 * 2011-4-1 上午09:00:19        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.service;

import java.util.List;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.IBaseService;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.dto.CreateMenuDto;
import com.xidu.framework.menu.dto.MenuListDto;
import com.xidu.framework.menu.dto.QueryMenuDto;
import com.xidu.framework.usermgnt.domain.UserGroup;

/**
 * Service of Menu
 */
public interface IMenuService extends IBaseService {

    /**
     * create Menu by menu code, menu name, parent menu code, menu level, is
     * leaf, url and operator id
     * 
     * @Date : 2011-4-1
     * @param menuCode
     *            - menu code
     * @param menuName
     *            - menu name
     * @param parentMenuCode
     *            - parent menu code
     * @param menuLevel
     *            - menu level
     * @param isLeaf
     *            - is leaf
     * @param url
     *            - url
     * @param operatorId
     *            - operator id
     * @return persisted menu instance
     * @throws AppException
     *             - application exception
     */
    Menu createMenu(String menuCode, String menuName, String parentMenuCode, int menuLevel,
        int isLeaf, String url, long operatorId, UserGroup userGroup) throws AppException;

    /**
     * update menu by by menu code, menu name, parent menu code, menu level, is
     * leaf, url and operator id
     * 
     * @Date : 2011-4-1
     * @param menuCode
     *            - menu code
     * @param menuName
     *            - menu name
     * @param parentMenuCode
     *            - parent menu code
     * @param menuLevel
     *            - menu level
     * @param isLeaf
     *            - is leaf
     * @param url
     *            - url
     * @param operatorId
     *            - operator id
     * @param userGroup 
     * @throws AppException
     *             - application exception
     */
    void updateMenu(String menuCode, String menuName, String parentMenuCode, int menuLevel,
        int isLeaf, String url, long operatorId, UserGroup userGroup) throws AppException;

    /**
     * delete menu by menu code
     * 
     * @Date : 2011-4-1
     * @param menuCode
     *            - menu code
     * @throws AppException
     *             - application exception
     */
    void deleteMenu(String menuCode) throws AppException;

    /**
     * get granted menu list including parent menu by user code
     * 
     * @Date : 2011-4-1
     * @param userCode
     *            - user code
     * @return menu list
     * @throws AppException
     *             - application exception
     */
    List<Menu> getGrantedMenusByUserCode(String userCode) throws AppException;

    /**
     * get granted menu list by role code
     * 
     * @Date : 2011-4-1
     * @param roleCode
     *            - role code
     * @return menu list
     * @throws AppException
     *             - application exception
     */
    List<Menu> getGrantedMenusByRoleCode(String roleCode) throws AppException;

    /**
     * get granted menu list by function code
     * 
     * @Date : 2011-4-1
     * @param functionCode
     *            - function code
     * @return menu list
     * @throws AppException
     *             - application exception
     */
    List<Menu> getGrantedMenusByFunctionCode(String functionCode) throws AppException;

    /**
     * check if user with this user code can access a menu
     * 
     * @Date : 2011-4-1
     * @param menuCode
     *            - menu code
     * @param userCode
     *            - user code
     * @return true if access successfully; otherwise, false
     * @throws AppException
     *             - application exception
     */
    boolean checkMenuAccessByUserCode(String menuCode, String userCode) throws AppException;

    /**
     * check if user with this role can access a menu
     * 
     * @Date : 2011-4-1
     * @param menuCode
     *            - menu code
     * @param roleCode
     *            - role code
     * @return true if access successfully; otherwise, false
     * @throws AppException
     *             - application exception
     */
    boolean checkMenuAccessByRoleCode(String menuCode, String roleCode) throws AppException;

    /**
     * get menu by menu code
     * 
     * @Date : 2011-4-2
     * @param menuCode
     *            - menu code
     * @return menu instance
     * @throws AppException
     *             - application exception
     */
    Menu getMenuByMenuCode(String menuCode) throws AppException;

   /**
    * create menu and menu-function relationship
    * @Date        :      2011-4-2
    * @param menu - menu instance
    * @param functionCodes - function code array
    * @return persisted menu instance with positive id
    * @throws AppException - application exception
    */
    Menu createMenuFunctions(String menuCode, String[] functionCodes) throws AppException;
    
    public List<Menu> queryMenu(QueryMenuDto queryMenuDto);
    
    public List<Menu> findChildrenMenuByMenuCode(String menuCode);

    /**
     * get menu by id
     * @param menuId
     * @return
     */
	public CreateMenuDto getMenuByMenuId(String menuId);

	/**
	 * delete menu by menu id
	 * @param parseLong
	 */
	public void deleteMenuById(long parseLong);

	List<MenuListDto> getMenuListOrderByParentCode(Long long1);
    
}
