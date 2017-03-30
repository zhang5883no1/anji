/************************************************************************************
 * @File name   :      FunctionGroup.java
 *
 * @Author      :      Eric Zhang
 *
 * @Date        :      2011-3-21
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date                             Who                 Version         Comments
 * 2011-3-21 上午09:56:25            Eric Zhang            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.domain;

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
 * Function Group
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "ts_fmwk_func_group")
@AttributeOverrides({
        @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")),
        @AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")) })
public class FunctionGroup extends BaseDomain {

    private static final long serialVersionUID = 1L;
    private String funcGrpCode;
    private String funcGrpName;
    private String funcGrpDesc;

    /**
     * Get Id
     * 
     * @Date : 2011-3-21
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Set Id
     * 
     * @Date : 2011-3-21
     * 
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get function group code
     * 
     * @Date : 2011-3-21
     * 
     * @return the funcGrpCode
     */
    @Column(name = "function_group_code", nullable = false)
    public String getFuncGrpCode() {
        return funcGrpCode;
    }

    /**
     * Set function group code
     * 
     * @Date : 2011-3-21
     * 
     * @param funcGrpCode
     *            the funcGrpCode to set
     */
    public void setFuncGrpCode(String funcGrpCode) {
        this.funcGrpCode = funcGrpCode;
    }

    /**
     * Get function group name
     * 
     * @Date : 2011-3-21
     * 
     * @return the funcGrpName
     */
    @Column(name = "function_group_name", nullable = false)
    public String getFuncGrpName() {
        return funcGrpName;
    }

    /**
     * Set function group name
     * 
     * @Date : 2011-3-21
     * 
     * @param funcGrpName
     *            the funcGrpName to set
     */
    public void setFuncGrpName(String funcGrpName) {
        this.funcGrpName = funcGrpName;
    }

    /**
     * Get function group desc
     * 
     * @Date : 2011-3-21
     * 
     * @return the funcGrpDesc
     */
    @Column(name = "function_group_desc", nullable = true)
    public String getFuncGroupDesc() {
        return funcGrpDesc;
    }

    /**
     * Set function group desc
     * 
     * @Date : 2011-3-21
     * 
     * @param funcGrpDesc
     *            the funcGrpDesc to set
     */
    public void setFuncGroupDesc(String funcGrpDesc) {
        this.funcGrpDesc = funcGrpDesc;
    }

}
