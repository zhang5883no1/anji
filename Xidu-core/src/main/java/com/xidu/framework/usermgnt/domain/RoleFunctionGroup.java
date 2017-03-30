/******************************************************************************
 * @File name   :      RoleFunctionGroup.java
 *
 * @Author      :      <Jianxi Wu>
 *
 * @Date        :      2011-3-31
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
 * 2011-3-31 下午02:16:43        <Jianxi Wu>     1.0            Initial Version
 *****************************************************************************/
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
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "tr_fmwk_role_funcgrp")
@AttributeOverrides({
        @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", nullable = false, columnDefinition = "number(18) default 0")),
        @AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", nullable = false, columnDefinition = "number(18) default 0")) })
public class RoleFunctionGroup extends BaseDomain {

    private static final long serialVersionUID = 1L;
    private Role role;
    private FunctionGroup functionGroup;

    /**
     * @Date : 2011-3-31
     * 
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * @Date : 2011-3-31
     * 
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @Date : 2011-3-31
     * 
     * @return the role
     */
    @ManyToOne(optional = false, targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    /**
     * @Date : 2011-3-31
     * 
     * @param role
     *            the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @Date : 2011-3-31
     * 
     * @return the functionGroup
     */
    @ManyToOne(optional = false, targetEntity = FunctionGroup.class)
    @JoinColumn(name = "function_group_id")
    public FunctionGroup getFunctionGroup() {
        return functionGroup;
    }

    /**
     * @Date : 2011-3-31
     * 
     * @param functionGroup
     *            the functionGroup to set
     */
    public void setFunctionGroup(FunctionGroup functionGroup) {
        this.functionGroup = functionGroup;
    }

}
