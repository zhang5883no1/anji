package com.xidu.framework.menu.dto;

/******************************************************************************
 * @File name   :      CreateMenuDto.java
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


import com.xidu.framework.common.dto.BaseDto;

/**
 *
 */
public class CreateMenuDto extends BaseDto{

    private static final long serialVersionUID = 3078382750077364568L;
    private String menuCode;
    private String menuName;
    private String parentMenuCode;
    private Integer menuLevel;
    private Integer isLeaf;
    private String url;
    private Long operatorId;
    
    private Long id;
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * @Date        :      2012-5-25
     *
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the parentMenuCode
     */
    public String getParentMenuCode() {
        return parentMenuCode;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param parentMenuCode the parentMenuCode to set
     */
    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the menuLevel
     */
    public Integer getMenuLevel() {
        return menuLevel;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param menuLevel the menuLevel to set
     */
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the isLeaf
     */
    public Integer getIsLeaf() {
        return isLeaf;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param isLeaf the isLeaf to set
     */
    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the operatorId
     */
    public Long getOperatorId() {
        return operatorId;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param operatorId the operatorId to set
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
