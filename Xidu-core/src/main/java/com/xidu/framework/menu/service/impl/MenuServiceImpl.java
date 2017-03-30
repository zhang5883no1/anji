/******************************************************************************
 * @File name   :      MenuServiceImpl.java
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
 * 2011-4-1 涓婂崍09:49:42        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidu.framework.common.constant.CommonErrorConstants;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.impl.BaseServiceImpl;
import com.xidu.framework.menu.constant.MenuErrorConstants;
import com.xidu.framework.menu.dao.IMenuDao;
import com.xidu.framework.menu.dao.IMenuFunctionDao;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.domain.MenuFunction;
import com.xidu.framework.menu.dto.CreateMenuDto;
import com.xidu.framework.menu.dto.MenuListDto;
import com.xidu.framework.menu.dto.QueryMenuDto;
import com.xidu.framework.menu.service.IMenuService;
import com.xidu.framework.usermgnt.dao.IFunctionDao;
import com.xidu.framework.usermgnt.dao.IFunctionGroupDao;
import com.xidu.framework.usermgnt.domain.Function;
import com.xidu.framework.usermgnt.domain.UserGroup;

/**
 * IMenuService's implementation
 */
@Service("menuService")
@Transactional(rollbackFor = AppException.class)
public class MenuServiceImpl extends BaseServiceImpl implements IMenuService {

    private IMenuDao menuDao;

    private IMenuFunctionDao menuFunctionDao;

    private IFunctionDao functionDao;
    
    private IFunctionGroupDao functionGroupDao;

    /**
     * @Date : 2011-4-2
     * 
     * @return the functionDao
     */
    public IFunctionDao getFunctionDao() {
        return functionDao;
    }

    public IFunctionGroupDao getFunctionGroupDao() {
		return functionGroupDao;
	}
    @Resource(name = "functionGroupDao")
	public void setFunctionGroupDao(IFunctionGroupDao functionGroupDao) {
		this.functionGroupDao = functionGroupDao;
	}

	/**
     * @Date : 2011-4-2
     * 
     * @param functionDao
     *            the functionDao to set
     */
    @Resource(name = "functionDao")
    public void setFunctionDao(IFunctionDao functionDao) {
        this.functionDao = functionDao;
    }

    /**
     * @Date : 2011-4-2
     * 
     * @return the menuFunctionDao
     */
    public IMenuFunctionDao getMenuFunctionDao() {
        return menuFunctionDao;
    }

    /**
     * @Date : 2011-4-2
     * 
     * @param menuFunctionDao
     *            the menuFunctionDao to set
     */
    @Resource(name = "menuFunctionDao")
    public void setMenuFunctionDao(IMenuFunctionDao menuFunctionDao) {
        this.menuFunctionDao = menuFunctionDao;
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#checkMenuAccessByRoleCode(java.lang.String,
     *      java.lang.String)
     **/
    @Override
    public boolean checkMenuAccessByRoleCode(String menuCode, String roleCode) throws AppException {
        // TODO Auto-generated method stub
        int count;
        try {
            count = menuDao.countMenusByRoleAndMenuCode(roleCode, menuCode);

            getLogger().info("variable:[count=" + count + "]");

            if (count > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }

    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#checkMenuAccessByUserCode(java.lang.String,
     *      java.lang.String)
     **/
    @Override
    public boolean checkMenuAccessByUserCode(String menuCode, String userCode) throws AppException {
        // TODO Auto-generated method stub
        try {
            int count = menuDao.countMenusByUserAndMenuCode(userCode, menuCode);
            if (count > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }

    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#createMenu(java.lang.String,
     *      java.lang.String, java.lang.String, int, int, java.lang.String,
     *      long)
     **/
    @Override
    public Menu createMenu(String menuCode, String menuName, String parentMenuCode, int menuLevel,
        int isLeaf, String url, long operatorId, UserGroup userGroup) throws AppException {
        // TODO Auto-generated method stub
        Menu menu = new Menu();
        menu.setMenuCode(menuCode);
        menu.setMenuName(menuName);
        menu.setParentMenuCode(parentMenuCode);
        menu.setMenuLevel(menuLevel);
        menu.setIsLeaf(isLeaf);
        menu.setUrl(url);
        menu.setCreateBy(operatorId);
        menu.setCreateDate(Calendar.getInstance().getTime());
        
        Function function=new Function();
        function.setFunctionCode(menuCode);
        function.setFunctionDesc("menu");
        function.setFunctionGroup(functionGroupDao.findById(userGroup.getId()));
        function.setFunctionName(menuName);
        function.setCreateBy(operatorId);
        function.setCreateDate(Calendar.getInstance().getTime());
        
        try {
            menuDao.save(menu);
            functionDao.save(function);
            
            MenuFunction menuFunction=new MenuFunction();
            menuFunction.setMenuId(menu.getId());
            menuFunction.setFunctionId(function.getId());
            menuFunction.setCreateBy(operatorId);
            menuFunction.setCreateDate(Calendar.getInstance().getTime());
            menuFunctionDao.save(menuFunction);
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }
        getLogger().info("variable: [menu.id=" + menu.getId() + "]");
        return menu;
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#deleteMenu(java.lang.String)
     **/
    @Override
    public void deleteMenu(String menuCode) throws AppException {
        // TODO Auto-generated method stub
        try {
            getLogger().info("parameter: [menuCode=" + menuCode + "]");
            Menu menu = menuDao.getMenuByMenuCode(menuCode);
            if (null != menu) {

                getLogger().info("variable: [menu is null]");
                menuDao.remove(menu);
            }
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }
    }

    private String[] generateMenuCodeArrayByMenuList(List<Menu> menuList){
        if(null == menuList || menuList.size() == 0){
            return new String[0];
        }
        String[] strs = new String[menuList.size()];
        for(int i=0; i<strs.length; i++){
            strs[i] = menuList.get(i).getMenuCode();
        }
        return strs;
    }
    
    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#getGrantedMenusByFunctionCode(java.lang.String)
     **/
    @Override
    public List<Menu> getGrantedMenusByFunctionCode(String functionCode) throws AppException {
        // TODO Auto-generated method stub
        getLogger().info("parameter: [functionCode=" + functionCode + "]");
        try {
            List<Menu> menuList = menuDao.getGrantedMenusByFunctionCode(functionCode);
                
            return menuDao.getAllGrantedMenusByLeafMenuCodes(generateMenuCodeArrayByMenuList(menuList));
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#getGrantedMenusByRoleCode(java.lang.String)
     **/
    @Override
    public List<Menu> getGrantedMenusByRoleCode(String roleCode) throws AppException {
        // TODO Auto-generated method stub
        getLogger().info("parameter: [roleCode=" + roleCode + "]");

        try {
            List<Menu> menuList = menuDao.getGrantedMenusByRoleCode(roleCode);
            return menuDao.getAllGrantedMenusByLeafMenuCodes(generateMenuCodeArrayByMenuList(menuList));
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }

    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#getGrantedMenusByUserCode(java.lang.String)
     **/
    @Override
    public List<Menu> getGrantedMenusByUserCode(String userCode) throws AppException {
        // TODO Auto-generated method stub
        getLogger().info("parameter: [userCode=" + userCode + "]");

        try {
            List<Menu> menuList = menuDao.getGrantedMenusByUserCode(userCode);
            return menuDao.getAllGrantedMenusByLeafMenuCodes(generateMenuCodeArrayByMenuList(menuList));
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }

    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-1
     * @see com.trinasolar.framework.usermanagement.service.IMenuService#updateMenu(java.lang.String,
     *      java.lang.String, java.lang.String, int, int, java.lang.String,
     *      long)
     **/
    @Override
    public void updateMenu(String menuCode, String menuName, String parentMenuCode, int menuLevel,
        int isLeaf, String url, long operatorId, UserGroup userGroup) throws AppException {
        // TODO Auto-generated method stub
        try {
            Menu menu = menuDao.getMenuByMenuCode(menuCode);

            if (null == menu) {
                return;
            }
            if (null != menuName) {
                menu.setMenuName(menuName);
            }

            if (null != parentMenuCode) {
                menu.setParentMenuCode(parentMenuCode);
            }
            menu.setMenuLevel(menuLevel);
            menu.setIsLeaf(isLeaf);
            if (null != url) {
                menu.setUrl(url);
            }

            menu.setLastUpdateBy(operatorId);
            menu.setLastUpdateDate(Calendar.getInstance().getTime());

            menuDao.update(menu);
            String[] param=new String[1];
            param[0]=String.valueOf(menu.getId());
         
            String[] functions=menuFunctionDao.getfunctionidsByMenuIds(param);
            if(functions==null || functions.length==0){
            	Function function=new Function();
                function.setFunctionCode(menuCode);
                function.setFunctionDesc("menu");
                function.setFunctionGroup(functionGroupDao.findById(userGroup.getId()));
                function.setFunctionName(menuName);
                function.setCreateBy(operatorId);
                function.setCreateDate(Calendar.getInstance().getTime());
                functionDao.save(function);
                
                MenuFunction menuFunction=new MenuFunction();
                menuFunction.setMenuId(menu.getId());
                menuFunction.setFunctionId(function.getId());
                menuFunction.setCreateBy(operatorId);
                menuFunction.setCreateDate(Calendar.getInstance().getTime());
                menuFunctionDao.save(menuFunction);
            }
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * @Date : 2011-4-1
     * 
     * @return the menuDao
     */
    public IMenuDao getMenuDao() {
        return menuDao;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param menuDao
     *            the menuDao to set
     */
    @Resource(name = "menuDao")
    public void setMenuDao(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-2
     * @see com.xidu.framework.menu.service.IMenuService#getMenuByMenuCode(java.lang.String)
     **/
    @Override
    public Menu getMenuByMenuCode(String menuCode) throws AppException {
        // TODO Auto-generated method stub
        getLogger().info("parameter: [menuCode=" + menuCode + "]");
        try {
            return menuDao.getMenuByMenuCode(menuCode);
        } catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * {@inheritDoc} overridden:
     * 
     * @Date : 2011-4-2
     * @see com.xidu.framework.menu.service.IMenuService#createMenuFunctions(com.xidu.framework.menu.domain.Menu,
     *      java.lang.String[])
     **/
    @Override
    public Menu createMenuFunctions(String menuCode, String[] functionCodes) throws AppException {
        // TODO Auto-generated method stub
        Menu menu;
        try {
            menu = menuDao.getMenuByMenuCode(menuCode);
            if(null == menu){
                logger.error("menu not found![menuCode="+ menuCode +"]");
                throw new AppException(MenuErrorConstants.MENU_NOT_FOUND, "menu not found![menuCode="+ menuCode +"]");
            }
            getLogger().info("variable: [menu.id=" + menu.getId() + "]");
            
            menuFunctionDao.deleteMenuFunctionsByMenuId(menu.getId());
            
            if (null == functionCodes) {
                functionCodes = new String[0];
            }
            for (String functionCode : functionCodes) {
                Function function = functionDao.getFunctionByCode(functionCode);
                if (null != function) {

                    getLogger().info("variable: [function.id=" + function.getId() + "]");
                    MenuFunction menuFunction = new MenuFunction();
                    menuFunction.setMenuId(menu.getId());
                    menuFunction.setFunctionId(function.getId());

                    menuFunctionDao.save(menuFunction);

                    getLogger().info("variable: [MenuFunction.id=" + menuFunction.getId() + "]");
                }
            }
        }catch(AppException appException){
            throw appException;
        }
        catch (Exception e) {
            getLogger().error(CommonErrorConstants.SYSTEM_EXCEPTION_MESSAGE_PREFIX, e);
            throw new AppException(CommonErrorConstants.SYSTEM_ERROR, e.getMessage());
        }
        return menu;
    }

    @Override
    public List<Menu> queryMenu(QueryMenuDto queryMenuDto) {
        // TODO Auto-generated method stub
        return menuDao.queryMenu(queryMenuDto);
    }

    @Override
    public List<Menu> findChildrenMenuByMenuCode(String menuCode) {
        // TODO Auto-generated method stub
        return menuDao.findChildrenMenuByMenuCode(menuCode);
    }

	@Override
	public CreateMenuDto getMenuByMenuId(String menuId) {
		// TODO Auto-generated method stub
		Menu menu = menuDao.findById(Long.parseLong(menuId));
		if(menu==null){
			return null;
		}
		CreateMenuDto menuDto=new CreateMenuDto();
		BeanUtils.copyProperties(menu, menuDto);
		return menuDto;
	}

	@Override
	public void deleteMenuById(long menuId) {
		// TODO Auto-generated method stub
		Menu menu = menuDao.findById(menuId);
		menuDao.remove(menu);
	}

	@Override
	public List<MenuListDto> getMenuListOrderByParentCode(Long groupid) {
		// TODO Auto-generated method stub
		List<MenuListDto> result=new ArrayList<MenuListDto>();
		List<Menu> list=menuDao.distinctParentMenuCode(groupid);
		logger.debug("menulist:"+list.size()+"---------");
		for(int i=0;i<list.size();i++){
			MenuListDto dto=new MenuListDto();
			BeanUtils.copyProperties(list.get(i), dto);
			dto.setList(menuDao.getMenuByParentMenuId(dto.getMenuCode()));
			result.add(dto);
		}
		return result;
	}

}
