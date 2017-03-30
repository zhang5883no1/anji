/******************************************************************************
 * @File name   :      Menu.java
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
 * 2011-4-1 上午08:38:48        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.menu.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.xidu.framework.common.domain.BaseDomain;

/**
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "ts_fmwk_menu")
public class Menu extends BaseDomain {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String menuCode;
    private String menuName;
    private String parentMenuCode;
    private String url;
    private int isLeaf;
    private int menuLevel;

    /**
     * @Date : 2011-4-1
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @return the is_leaf
     */
    @Column(name = "is_leaf", nullable = false)
    public int getIsLeaf() {
        return isLeaf;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @return the menuCode
     */
    @Column(name = "menu_code", nullable = false)
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @return the menuLevel
     */
    @Column(name = "menu_level", nullable = false)
    public int getMenuLevel() {
        return menuLevel;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @return the menuName
     */
    @Column(name = "menu_name", nullable = false)
    public String getMenuName() {
        return menuName;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @return the parentMenuCode
     */
    @Column(name = "parent_menu_code", nullable = false)
    public String getParentMenuCode() {
        return parentMenuCode;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @return the url
     */
    @Column(name = "url", nullable = false)
    public String getUrl() {
        return url;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param isLeaf
     *            the is_leaf to set
     */
    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param menuCode
     *            the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param menuLevel
     *            the menuLevel to set
     */
    public void setMenuLevel(int menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param menuName
     *            the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param parentMenuCode
     *            the parentMenuCode to set
     */
    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    /**
     * @Date : 2011-4-1
     * 
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
