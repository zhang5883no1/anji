/******************************************************************************
 * @File name   :      MenuDaoImpl.java
 *
 * @Author      :      Jianxi Wu
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
 * 2011-4-1 上午08:58:00        Jianxi Wu     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.util.QueryUtils;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.xidu.framework.menu.dao.IMenuDao;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.menu.dto.QueryMenuDto;

/**
 * IMenuDao implementation
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu, Long> implements IMenuDao {

    /**
     * @Date : 2011-4-1
     * @param clazz
     *            - Domain Menu's Class
     */
    @Autowired
    public MenuDaoImpl(@Value("com.xidu.framework.menu.domain.Menu") Class<Menu> clazz) {
        super(clazz);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.dao.IMenuDao#getMenuByMenuCode(java.lang.String)
     **/
    @Override
    public Menu getMenuByMenuCode(String menuCode) {
        String queryStr = "from Menu m where m.menuCode = ?1 ";
        List<Menu> menuList = getListByQueryWithVaParam(queryStr, menuCode);
        if (null != menuList && menuList.size() > 0) {
            return menuList.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.dao.IMenuDao#getGrantedMenusByUserCode(java.lang.String)
     **/
    @Override
    public List<Menu> getGrantedMenusByUserCode(String userCode) {
        String queryStr = "select distinct m.* from (select u.user_code, ur.role_id from ts_fmwk_user u,tr_fmwk_user_role ur "
            + "where u.id = ur.user_id union select u.user_code,ugr.role_id "
            + "from ts_fmwk_user u ,tr_fmwk_usrgrp_role ugr where u.user_group_id = ugr.user_group_id) userrole"
            + ",(select rf.role_id,rf.function_id from tr_fmwk_role_function rf "
            + "union select rfg.role_id,f.id as function_id from ts_fmwk_function f,tr_fmwk_role_funcgrp rfg "
            + "where rfg.function_group_id = f.function_group_id) rolefunc,tr_fmwk_menu_function mf, ts_fmwk_menu m "
            + "where userrole.role_id = rolefunc.role_id and rolefunc.function_id = mf.function_id "
            + "and mf.menu_id = m.id and userrole.user_code = ?1";

        return getListByNativeQueryWithVaParam(queryStr, userCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.dao.IMenuDao#getGrantedMenusByRoleCode(java.lang.String)
     **/
    @Override
    public List<Menu> getGrantedMenusByRoleCode(String roleCode) {
        String queryStr = " select menu from Role role"
            + ",RoleFunction rolefunc,MenuFunction menufunc, Menu menu "
            + "where role.id = rolefunc.role.id "
            + "and rolefunc.function.id = menufunc.functionId and menufunc.menuId = menu.id "
            + "and role.roleCode = ?1 " + " union " + " select menu from Role role"
            + ",RoleFunctionGroup rolefuncgrp,Function func, MenuFunction menufunc, Menu menu "
            + "where role.id = rolefuncgrp.role.id "
            + "and rolefuncgrp.functionGroup.id = func.functionGroup.id "
            + "and func.id = menufunc.functionId and menufunc.menuId = menu.id "
            + "and role.roleCode = ?1  ";
        return getListByQueryWithVaParam(queryStr, roleCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.dao.IMenuDao#getGrantedMenusByFunctionCode(java.lang.String)
     **/
    @Override
    public List<Menu> getGrantedMenusByFunctionCode(String functionCode) {
        String queryStr = "select menu from Function func" + ", MenuFunction menufunc, Menu menu "
            + "where func.id = menufunc.functionId and menufunc.menuId = menu.id "
            + "and func.functionCode = ?1";
        return getListByQueryWithVaParam(queryStr, functionCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.dao.IMenuDao#countMenusByRoleAndMenuCode(java.lang.String,
     *      java.lang.String)
     **/
    @Override
    public int countMenusByRoleAndMenuCode(String roleCode, String menuCode) {
        String nativeQuerySql = "select count(*) from  (" + "select menu.* from ts_fmwk_role role"
            + ",tr_fmwk_role_function rolefunc,tr_fmwk_menu_function menufunc, ts_fmwk_menu menu "
            + "where role.id = rolefunc.role_id "
            + "and rolefunc.function_id = menufunc.function_id and menufunc.menu_id = menu.id "
            + "and role.role_code = ?1 and menu.menu_code = ?2 " + " union "
            + "select menu.* from ts_fmwk_role role"
            + ",tr_fmwk_role_funcgrp rolefuncgrp,ts_fmwk_function func"
            + ",tr_fmwk_menu_function menufunc, ts_fmwk_menu menu "
            + "where role.id = rolefuncgrp.role_id "
            + "and rolefuncgrp.function_group_id = func.function_group_id "
            + "and func.id = menufunc.function_id and menufunc.menu_id = menu.id "
            + "and role.role_code = ?1 and menu.menu_code = ?2 )";
        return countByNativeQueryWithVaParam(nativeQuerySql, roleCode, menuCode);
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.dao.IMenuDao#countMenusByUserAndMenuCode(java.lang.String,
     *      java.lang.String)
     **/
    @Override
    public int countMenusByUserAndMenuCode(String userCode, String menuCode) {
        String queryStr = "select count(m.*) from (select u.user_code, ur.role_id from ts_fmwk_user u,tr_fmwk_user_role ur "
            + "where u.id = ur.user_id union select u.user_code,ugr.role_id "
            + "from ts_fmwk_user u ,tr_fmwk_usrgrp_role ugr where u.user_group_id = ugr.user_group_id) userrole"
            + ",(select rf.role_id,rf.function_id from tr_fmwk_role_function rf "
            + "union select rfg.role_id,f.id as function_id from ts_fmwk_function f,tr_fmwk_role_funcgrp rfg "
            + "where rfg.function_group_id = f.function_group_id) rolefunc,tr_fmwk_menu_function mf, ts_fmwk_menu m "
            + "where userrole.role_id = rolefunc.role_id and rolefunc.function_id = mf.function_id "
            + "and mf.menu_id = m.id and userrole.user_code = ?1";
        return countByNativeQueryWithVaParam(queryStr, userCode, menuCode);
    }

    /**
     * 
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-26
     * @see com.xidu.framework.menu.dao.IMenuDao#getAllGrantedMenusByLeafMenuList(java.lang.String[])
     * 
     */
    @Override
    public List<Menu> getAllGrantedMenusByLeafMenuCodes(String[] menuCodes) {
        if (null == menuCodes || menuCodes.length == 0) {
            return null;
        }
        String sql = "select distinct * from ts_fmwk_menu where menu_code in " + QueryUtils.generateInOperand(menuCodes)+" order by id";
        return getListByNativeQuery(sql);
    }
    
    @Override
    public List<Menu> queryMenu(QueryMenuDto dto) {
        String sql = "select distinct(m) from Menu m ,MenuFunction mf, Function f where m.deleteFlag=0 and m.id=mf.menuId and mf.functionId=f.id ";
        WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
        if(Utils.notEmpty(dto.getMenuCode())){
            wcg.and(" m.menuCode", "like", "%" + dto.getMenuCode() + "%");
        }
        if(Utils.notEmpty(dto.getMenuName())){
            wcg.and(" m.menuName", "like", "%" + dto.getMenuName() + "%");
        }
        if(Utils.notEmpty(dto.getParentMenuCode())){
            wcg.and(" m.parentMenuCode " ,"like", "%"+ dto.getParentMenuCode()+"%" );
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if(dto.getFromhDate() != null){
            String dateStr =  sf.format(dto.getFromhDate());
            wcg.andDate("m.createDate", ">", dateStr, "yyyy-MM-dd");
        }
        if(dto.getToDate() != null){
            String dateStr =  sf.format(dto.getToDate());
            wcg.andDate("m.createDate", "<", dateStr, "yyyy-MM-dd");
        }
        if(Utils.notEmpty(dto.getType())){
        	wcg.and("f.functionGroup","=",dto.getType());
        }
        logger.debug("sql query : " + wcg);
        return getListByQueryWithDefaultPaging(wcg.toQuery(), dto.getPager());
    }

    @Override
    public List<Menu> findChildrenMenuByMenuCode(String menuCode) {
        // TODO Auto-generated method stub
        String sql="select b.* from ts_fmwk_menu t ,"+
                      "ts_fmwk_menu b where 1=1 and t.menu_code=b.parent_menu_code" +
                      "t.menu_code='"+menuCode+"'";
        return  getListByNativeQuery(sql);
    }

    
	@Override
	public List<Menu> getMenuListByMenuFunctionList(List<MenuFunction> menuFunctionList) {
		// TODO Auto-generated method stub
		String sql = "from Menu t where DELETE_FLAG=0";
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
			for(int i=0;i<menuFunctionList.size();i++){
				if(Utils.notEmpty(String.valueOf(menuFunctionList.get(i).getMenuId()))&&i==0){
					wcg.and("t.id", "=", menuFunctionList.get(i).getMenuId());
				}else{
					wcg.or("t.id", "=", menuFunctionList.get(i).getMenuId());
				}
			}
			logger.debug("print sql:"+wcg);
			return getListByQuery(wcg.toQuery());
	}

	@Override
	public List<Menu> distinctParentMenuCode(Long groupid) {
		// TODO Auto-generated method stub
		//String sql="select s.* from ts_fmwk_menu s where s.menu_code in(select distinct(t.parent_menu_code) from ts_fmwk_menu t)";
		String sql="select distinct(m) from Menu m ,MenuFunction mf, Function f where m.deleteFlag=0 and m.id=mf.menuId and mf.functionId=f.id";
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		wcg.and("m.parentMenuCode", "=", "0");
		wcg.and("f.functionGroup", "=", groupid);
		logger.debug("print sql:"+wcg);
		List list= getListByQuery(wcg.toQuery());
		return list;
	}

	@Override
	public List<Menu> getMenuByParentMenuId(String id) {
		// TODO Auto-generated method stub
		String sql="from Menu t where DELETE_FLAG=0";
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		wcg.and("t.parentMenuCode", "=", id);
		logger.debug("print sql:"+wcg);
		return getListByQuery(wcg.toQuery());
	}
}
