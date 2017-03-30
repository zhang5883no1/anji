/******************************************************************************
 * @File name   :      IMenuFunctionDao.java
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
 * 2011-4-1 下午02:24:46        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.usermgnt.domain.RoleFunction;

/**
 * IMenuFunctionDao
 */
public interface IMenuFunctionDao extends IBaseDao<MenuFunction, Long> {
    void deleteMenuFunctionsByMenuId(long menuId);

    public MenuFunction getMenuFunByFuncId(Long id);

    public MenuFunction getMenuFunByMenuId(Long id);
    
    public List<MenuFunction> findByFunctionList(List<RoleFunction> roleFunctionList);

    public List<MenuFunction> findByMenuList(List<Menu> menuList);

    public String[] getfunctionidsByMenuIds(String[] ids);
}
