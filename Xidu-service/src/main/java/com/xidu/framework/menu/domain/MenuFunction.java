/******************************************************************************
 * @File name   :      MenuFunction.java
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
 * 2011-4-1 下午01:44:56        <Jianxi Wu>     1.0            Initial Version
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

import com.xidu.framework.common.domain.BaseDomain;

/**
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "tr_fmwk_menu_function")
public class MenuFunction extends BaseDomain {
    /**
     * 
     */
    private static final long serialVersionUID = -9107662587528603949L;
    private long id;
    private long menuId;
    private long functionId;
    /**
     * @Date        :      2011-4-1
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    /**
     * @Date        :      2011-4-1
     *
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @Date        :      2011-4-1
     *
     * @return the menuId
     */
    @Column(name="menu_id")
    public long getMenuId() {
        return menuId;
    }
    /**
     * @Date        :      2011-4-1
     *
     * @param menuId the menuId to set
     */
    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }
    /**
     * @Date        :      2011-4-1
     *
     * @return the functionId
     */
    @Column(name="function_id")
    public long getFunctionId() {
        return functionId;
    }
    /**
     * @Date        :      2011-4-1
     *
     * @param functionId the functionId to set
     */
    public void setFunctionId(long functionId) {
        this.functionId = functionId;
    }
}
