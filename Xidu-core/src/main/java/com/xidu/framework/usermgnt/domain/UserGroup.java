/************************************************************************************
 * @File name   :      UserGroup.java
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
 * Date                             Who                 Version          Comments
 * 2011-3-21 上午09:56:33            Eric Zhang            1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.xidu.framework.common.domain.BaseDomain;

/**
 * User Group
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "ts_fmwk_user_group")
@AttributeOverrides({
        @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")),
        @AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")) })
public class UserGroup extends BaseDomain {

    private static final long serialVersionUID = 1L;
    private String userGroupCode;
    private String userGroupName;
    private String parentId;

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
     * Get user group code
     * 
     * @Date : 2011-3-21
     * 
     * @return the userGroupCode
     */
    @Column(name = "user_group_code", nullable = false)
    public String getUserGroupCode() {
        return userGroupCode;
    }

    /**
     * Set user group code
     * 
     * @Date : 2011-3-21
     * 
     * @param userGroupCode
     *            the userGroupCode to set
     */
    public void setUserGroupCode(String userGroupCode) {
        this.userGroupCode = userGroupCode;
    }

    /**
     * Get group name
     * 
     * @Date : 2011-3-21
     * 
     * @return the userGroupName
     */
    @Column(name = "user_group_name", nullable = false)
    public String getUserGroupName() {
        return userGroupName;
    }

    /**
     * Set group name
     * 
     * @Date : 2011-3-21
     * 
     * @param userGroupName
     *            the userGroupName to set
     */
    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    /**
     * Get roles
     * 
     * @Date : 2011-3-21
     * 
     * @return the roles
     */

    @Column(name = "parent_group_id", nullable = false)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
