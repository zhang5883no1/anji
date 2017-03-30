/************************************************************************************
 * @File name   :      Role.java
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
 * 2011-3-21 上午09:55:46            Eric Zhang            1.0            Initial Version
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
 * Role
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "ts_fmwk_role")
@AttributeOverrides({
        @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")),
        @AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")),
    		@AttributeOverride(name = "deleteFlag", column = @Column(name = "DELETE_FLAG", nullable = false, columnDefinition = "number(18) default 1")) })
public class Role extends BaseDomain {

    private static final long serialVersionUID = 1L;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private String rolegroup;
//    private List<Function> functions;
//    private List<FunctionGroup> functionGroups;
   

    
    /**
     * Get Id
     * 
     * @Date : 2011-3-21
     * 
     * @return the roleId
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
     * @param roleId
     *            the roleId to set
     */
    public void setId(Long roleId) {
        this.id = roleId;
    }

    /**
     * Get role name
     * 
     * @Date : 2011-3-21
     * 
     * @return the roleName
     */
    @Column(name = "role_name", nullable = false)
    public String getRoleName() {
        return roleName;
    }

    /**
     * Set role name
     * 
     * @Date : 2011-3-21
     * 
     * @param roleName
     *            the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Get role desc
     * 
     * @Date : 2011-3-21
     * 
     * @return the roleDesc
     */
    @Column(name = "role_desc", nullable = true)
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * Set role desc
     * 
     * @Date : 2011-3-21
     * 
     * @param roleDesc
     *            the roleDesc to set
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

//    /**
//     * Get functions
//     * 
//     * @Date : 2011-3-21
//     * 
//     * @return the functions
//     */
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "tr_fmwk_role_function", joinColumns = { @JoinColumn(name = "role_id") }, 
//        inverseJoinColumns = { @JoinColumn(name = "function_id") })
//    public List<Function> getFunctions() {
//        return functions;
//    }
//
//    /**
//     * Set functions
//     * 
//     * @Date : 2011-3-21
//     * 
//     * @param functions
//     *            the functions to set
//     */
//    public void setFunctions(List<Function> functions) {
//        this.functions = functions;
//    }
//
//    /**
//     * Get function groups
//     * 
//     * @Date : 2011-3-21
//     * 
//     * @return the functionGroups
//     */
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "tr_fmwk_role_funcgrp", joinColumns = { @JoinColumn(name = "role_id") }, 
//        inverseJoinColumns = { @JoinColumn(name = "function_group_id") })
//    public List<FunctionGroup> getFunctionGroups() {
//        return functionGroups;
//    }
//
//    /**
//     * Set function groups
//     * 
//     * @Date : 2011-3-21
//     * 
//     * @param functionGroups
//     *            the functionGroups to set
//     */
//    public void setFunctionGroups(List<FunctionGroup> functionGroups) {
//        this.functionGroups = functionGroups;
//    }

    /**
     * Get role code
     * 
     * @Date : 2011-3-21
     * 
     * @return the roleCode
     */
    @Column(name = "role_code", nullable = false)
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Set role code
     * 
     * @Date : 2011-3-21
     * 
     * @param roleCode
     *            the roleCode to set
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Column(name="ROLE_GROUP")
	public String getRolegroup() {
		return rolegroup;
	}

	public void setRolegroup(String rolegroup) {
		this.rolegroup = rolegroup;
	}

}
