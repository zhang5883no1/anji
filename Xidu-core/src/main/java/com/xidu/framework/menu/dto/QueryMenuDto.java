

/******************************************************************************
 * @File name   :      QueryMenuDto.java
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
 * 2012-5-24 上午9:54:43        LOZHANG     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.dto;

import java.util.Date;

import com.xidu.framework.common.dto.BasePagerDto;

/**
 *
 */
public class QueryMenuDto<T> extends BasePagerDto<T>{

    private static final long serialVersionUID = 3078382750077364568L;
    //
    private String menuId;
    
    private String menuCode;
    private String menuName;
    private String parentMenuCode;
    private String type;
    private Date fromhDate;
    private Date toDate;
    /**
     * @Date        :      2012-5-27
     *
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @return the parentMenuCode
     */
    public String getParentMenuCode() {
        return parentMenuCode;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @param parentMenuCode the parentMenuCode to set
     */
    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }
    
    /**
     * @Date        :      2012-5-27
     *
     * @return the fromhDate
     */
    public Date getFromhDate() {
        return fromhDate;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @param fromhDate the fromhDate to set
     */
    public void setFromhDate(Date fromhDate) {
        this.fromhDate = fromhDate;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }
    /**
     * @Date        :      2012-5-27
     *
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
    
}
