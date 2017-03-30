/************************************************************************************
 * @File name   :      User.java
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
 * 2011-3-21 上午09:55:37            Eric Zhang            1.0             Initial Version
 ************************************************************************************/
package com.xidu.framework.usermgnt.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xidu.framework.common.domain.BaseDomain;

/**
 * User
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "ts_fmwk_user")
@AttributeOverrides({
		@AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
		@AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
		@AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", nullable = false, columnDefinition = "number(18) default 0")),
		@AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", nullable = false, columnDefinition = "number(18) default 0")),
		@AttributeOverride(name = "deleteFlag", column = @Column(name = "DELETE_FLAG", nullable = false, columnDefinition = "number(18) default 1")) })
public class BaseUser extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private String userCode;
	private String password;
	private String name;
	private int statusCode;
	private int userType;
	private String employerOwnerId;
	private UserGroup userGroup;
	private Role role;
	private Long parentId;

	/**
	 * Get Id
	 * 
	 * @Author : Eric Zhang
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
	 * @Author : Eric Zhang
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
	 * Get user Id
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @return the userCode
	 */
	@Column(name = "user_code", nullable = false)
	public String getUserCode() {
		return userCode;
	}

	/**
	 * Set user Id
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @param userCode
	 *            the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * Get password
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @return the password
	 */
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get name
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @return the name
	 */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get status code
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @return the statusCode
	 */
	@Column(name = "status_code", nullable = false)
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Set status code
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Get user group
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @return the userGroup
	 */
	@ManyToOne(optional = false, targetEntity = UserGroup.class)
	@JoinColumn(name = "user_group_id", referencedColumnName = "id")
	public UserGroup getUserGroup() {
		return userGroup;
	}

	/**
	 * Set user group
	 * 
	 * @Author : Eric Zhang
	 * 
	 * @Date : 2011-3-21
	 * 
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * @Date : 2011-4-2
	 * 
	 * @return the userType
	 */
	@Column(name = "user_type", nullable = false)
	public int getUserType() {
		return userType;
	}

	/**
	 * @Date : 2011-4-2
	 * 
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(int userType) {
		this.userType = userType;
	}

	/**
	 * @Date : 2011-4-21
	 * 
	 * @return the employerOwnerId
	 */
	@Column(name = "employer_owner_id", nullable = false)
	public String getEmployerOwnerId() {
		return employerOwnerId;
	}

	/**
	 * @Date : 2011-4-21
	 * 
	 * @param employerOwnerId
	 *            the employerOwnerId to set
	 */
	public void setEmployerOwnerId(String employerOwnerId) {
		this.employerOwnerId = employerOwnerId;
	}

	/**
	 * @Date : 2011-8-11
	 * 
	 * @return the role
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "tr_fmwk_user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Role getRole() {
		return role;
	}

	/**
	 * @Date : 2011-8-11
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "parent_id")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
