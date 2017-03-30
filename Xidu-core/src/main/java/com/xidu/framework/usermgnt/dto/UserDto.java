/******************************************************************************
 * @File name   :      UserDto.java
 *
 * @Author      :      xuyaning
 *
 * @Date        :      2011-6-30
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 Trinasolar, Inc. All  Rights Reserved.
 * This software is published under the terms of the Trinasolar Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2011-6-30 下午12:48:40        xuyaning     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dto;

import com.xidu.framework.common.util.Pager;
import com.xidu.framework.usermgnt.domain.BaseUser;

/**
 *
 */
public class UserDto extends BaseUser {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String userName;
    private String realName;
    private String department;
    private String region;
    private Pager pager;
    private String roleCodeStr;
    private String regionId;
    private String oldCode;
    private Long parentId;
    private String groupId;

    public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRoleCodeStr() {
        return roleCodeStr;
    }

    public void setRoleCodeStr(String roleCodeStr) {
        this.roleCodeStr = roleCodeStr;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
