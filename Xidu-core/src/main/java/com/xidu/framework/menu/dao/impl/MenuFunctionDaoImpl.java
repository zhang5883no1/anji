/******************************************************************************
 * @File name   :      MenuFunctionDaoImpl.java
 *
 * @Author      :      <Jianxi Wu>
 *
 * @Date        :      2011-4-2
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
 * 2011-4-2 上午10:27:57        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.dao.impl;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.xidu.framework.menu.dao.IMenuFunctionDao;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.usermgnt.domain.RoleFunction;

/**
 * IMenuFunctionDao's implementation
 */
@Repository("menuFunctionDao")
public class MenuFunctionDaoImpl extends BaseDaoImpl<MenuFunction, Long> implements
    IMenuFunctionDao {

    /**
     * @Date : 2011-4-2
     * @param clazz
     *            - Domain MenuFuction's Class
     */
    @Autowired
    public MenuFunctionDaoImpl(
        @Value("com.xidu.framework.menu.domain.MenuFunction") Class<MenuFunction> clazz) {
        super(clazz);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void deleteMenuFunctionsByMenuId(long menuId) {
        // TODO Auto-generated method stub
        String queryStr = "delete from MenuFunction mf where mf.menuId = ?1";
        removeByQueryAndVaParam(queryStr, menuId);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-8-2
     * @see com.trinasolar.set.system.dao.IMenuDtoDao#getMenuFunById(java.lang.Long)
     **/
    @Override
    public MenuFunction getMenuFunByFuncId(Long id) {
        // TODO Auto-generated method stub
        String sql = "from MenuFunction mf where mf.functionId=" + id;
        List<MenuFunction> list = this.getListByQuery(sql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-8-2
     * @see com.xidu.framework.menu.dao.IMenuFunctionDao#getMenuFunByMenuId(java.lang.Long)
     **/
    @Override
    public MenuFunction getMenuFunByMenuId(Long id) {
        String sql = "from MenuFunction mf where mf.menuId=" + id;
        List<MenuFunction> list = this.getListByQuery(sql);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
    
	@Override
	public List<MenuFunction> findByFunctionList(List<RoleFunction> roleFunctionList) {
		// TODO Auto-generated method stub
	String sql = "from MenuFunction t where DELETE_FLAG=0";
	List<MenuFunction> result=new ArrayList<MenuFunction>();
		for(int i=0;i<roleFunctionList.size();i++){
			WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
			if(Utils.notEmpty(String.valueOf(roleFunctionList.get(i).getFunction().getId()))){
				wcg.and("t.functionId", "=", roleFunctionList.get(i).getFunction().getId());
			}
			logger.debug("sql query : " + wcg);
			 List<MenuFunction> list=getListByQuery(wcg.toQuery());
			 for(int j=0;j<list.size();j++){
				 result.add(list.get(j));
			 }
		}
		//List<RoleFunction> list= getListByQueryWithDefaultPaging(wcg.toQuery(), new Pager());
		return result;
	}

	@Override
	public List<MenuFunction> findByMenuList(List<Menu> menuList) {
		// TODO Auto-generated method stub
		String sql = "from MenuFunction t where DELETE_FLAG=0";
		List<MenuFunction> result=new ArrayList<MenuFunction>();
			for(int i=0;i<menuList.size();i++){
				WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
				if(Utils.notEmpty(String.valueOf(menuList.get(i).getId()))){
					wcg.and("t.menuId", "=", menuList.get(i).getId());
				}
				logger.debug("sql query : " + wcg);
				 List<MenuFunction> list=getListByQuery(wcg.toQuery());
				 for(int j=0;j<list.size();j++){
					 result.add(list.get(j));
				 }
			}
			//List<RoleFunction> list= getListByQueryWithDefaultPaging(wcg.toQuery(), new Pager());
			return result;
	}

	@Override
	public String[] getfunctionidsByMenuIds(String[] ids) {
		// TODO Auto-generated method stub
		String sql = "from MenuFunction t where DELETE_FLAG=0";
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if(ids.length>0){
			for(int i=0;i<ids.length;i++){
				if(Utils.notEmpty(String.valueOf(ids[i]))&&i==0){
					wcg.and("t.menuId", "=",ids[i]);
				}
				if(Utils.notEmpty(String.valueOf(ids[i]))){
					wcg.or("t.menuId", "=",ids[i]);
				}
			}
		}
		
		logger.debug("sql query : " + wcg);
		 List<MenuFunction> list=getListByQuery(wcg.toQuery());
		 String[] idlist=new String[list.size()];
		 for(int j=0;j<list.size();j++){
			 idlist[j]=String.valueOf(list.get(j).getFunctionId());
		 }
		return idlist;
	}

}
