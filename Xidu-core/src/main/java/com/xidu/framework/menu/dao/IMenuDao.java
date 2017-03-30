/******************************************************************************
 * @File name   :      IMenuDao.java
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
 * 2011-4-1 上午08:57:29        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.menu.dto.QueryMenuDto;

/**
 * DAO of Menu
 */
public interface IMenuDao extends IBaseDao<Menu, Long> {
    /**
     * get Menu by menu code
     * 
     * @Date : 2011-4-2
     * @param menuCode
     *            - menu code
     * @return instance of Menu
     */
    Menu getMenuByMenuCode(String menuCode);

    /**
     * get granted menu list by user code
     * 
     * @Date : 2011-4-2
     * @param userCode
     *            - user code
     * @return list of Menu instances
     */
    List<Menu> getGrantedMenusByUserCode(String userCode);

    /**
     * get granted menu list by role code
     * 
     * @Date : 2011-4-2
     * @param roleCode
     *            - role code
     * @return list of Menu instances
     */
    List<Menu> getGrantedMenusByRoleCode(String roleCode);

    /**
     * get granted menu list by function code
     * 
     * @Date : 2011-4-2
     * @param functionCode
     *            - function code
     * @return list of Menu instances
     */
    List<Menu> getGrantedMenusByFunctionCode(String functionCode);

    /**
     * count menus by user code and menu code
     * 
     * @Date : 2011-4-2
     * @param userCode
     *            - user code
     * @param menuCode
     *            - menu code
     * @return count
     */
    int countMenusByUserAndMenuCode(String userCode, String menuCode);

    /**
     * count menus by role code and menu code
     * 
     * @Date : 2011-4-2
     * @param roleCode
     *            - role code
     * @param menuCode
     *            - menu code
     * @return count
     */
    int countMenusByRoleAndMenuCode(String roleCode, String menuCode);

    /**
     * get granted menu list including parent menus by array of menu codes
     * 
     * @Date : 2011-4-26
     * @param menuCodes
     *            - array of menu codes
     * @return list of menu instances
     */
    List<Menu> getAllGrantedMenusByLeafMenuCodes(String[] menuCodes);
    
    
    public List<Menu> findChildrenMenuByMenuCode(String menuCode);
    
    public List<Menu> queryMenu(QueryMenuDto queryMenuDto);
    
    List<Menu> getMenuListByMenuFunctionList(List<MenuFunction> menuFunctionList);

	List<Menu> distinctParentMenuCode(Long groupid);

	List<Menu> getMenuByParentMenuId(String id);
}
