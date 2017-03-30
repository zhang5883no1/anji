/******************************************************************************
 * @File name   :      QueryUserDto.java
 *
 * @Author      :      KASHAO
 *
 * @Date        :      2012-5-25
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
 * 2012-5-25 上午9:31:46        KASHAO     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.usermgnt.dto;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Pager;
import com.xidu.framework.usermgnt.domain.Role;

/**
 *
 */
public class QueryUserDto extends BasePagerDto{

    private static final long serialVersionUID = -288468373183151740L;
    private String userCode;
    
    private String name;
    private int statusCode;
   
    private String employerOwnerId;
    private Role role;
    private Pager pager;
    
    /**
     * @Date        :      2012-5-25
     *
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @Date        :      2012-5-25
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the employerOwnerId
     */
    public String getEmployerOwnerId() {
        return employerOwnerId;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param employerOwnerId the employerOwnerId to set
     */
    public void setEmployerOwnerId(String employerOwnerId) {
        this.employerOwnerId = employerOwnerId;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @return the pager
     */
    public Pager getPager() {
        return pager;
    }
    /**
     * @Date        :      2012-5-25
     *
     * @param pager the pager to set
     */
    public void setPager(Pager pager) {
        this.pager = pager;
    }
    
}
