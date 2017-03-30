/************************************************************************************
 * @File name   :      Function.java
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
 * 2011-3-21 上午09:56:10            Eric Zhang            1.0            Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xidu.framework.common.domain.BaseDomain;

/**
 * Function domain
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "ts_fmwk_function")
@AttributeOverrides({
        @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")),
        @AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")) })
public class Function extends BaseDomain {

    private static final long serialVersionUID = 1L;
    private String functionCode;
    private String functionName;
    private String functionDesc;
    private FunctionGroup functionGroup;

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
     * Get function code
     * 
     * @Date : 2011-3-21
     * 
     * @return the functionCode
     */
    @Column(name = "function_code", nullable = false)
    public String getFunctionCode() {
        return functionCode;
    }

    /**
     * Set function code
     * 
     * @Date : 2011-3-21
     * 
     * @param functionCode
     *            the functionCode to set
     */
    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    /**
     * Get function name
     * 
     * @Date : 2011-3-21
     * 
     * @return the functionName
     */
    @Column(name = "function_name", nullable = false)
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Set function name
     * 
     * @Date : 2011-3-21
     * 
     * @param functionName
     *            the functionName to set
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * Get function desc
     * 
     * @Date : 2011-3-21
     * 
     * @return the functionDesc
     */
    @Column(name = "function_desc", nullable = true)
    public String getFunctionDesc() {
        return functionDesc;
    }

    /**
     * Set function desc
     * 
     * @Date : 2011-3-21
     * 
     * @param functionDesc
     *            the functionDesc to set
     */
    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }

    /**
     * Get function group
     * 
     * @Date : 2011-3-21
     * 
     * @return the functionGroup
     */
    @ManyToOne(optional = false, targetEntity = FunctionGroup.class)
    @JoinColumn(name = "function_group_id", referencedColumnName = "id")
    public FunctionGroup getFunctionGroup() {
        return functionGroup;
    }

    /**
     * Set function group
     * 
     * @Date : 2011-3-21
     * 
     * @param functionGroup
     *            the functionGroup to set
     */
    public void setFunctionGroup(FunctionGroup functionGroup) {
        this.functionGroup = functionGroup;
    }

}
