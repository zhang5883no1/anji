/************************************************************************************
 * @File name   :      LoginDto.java
 *
 * @Author      :      caijunyi
 *
 * @Date        :      2011-1-26
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 SGM, Inc. All  Rights Reserved.
 * This software is published under the terms of the SGM Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2011-1-26 下午04:46:24			caijunyi			1.0				Initial Version
 ************************************************************************************/
package com.xidu.framework.showcase.sample.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * login dto to transfer data between client and server
 */
public class LoginDto {
	
	@NotEmpty
	private String userCode; 
	
	@NotEmpty
	private String passwd;
	
	/**
     * @Date        :      2011-4-7
     *
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @Date        :      2011-4-7
     *
     * @param userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
	 * @Author      :      caijunyi
	 *
	 * @Date        :      2011-1-26
	 *
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @Author      :      caijunyi
	 *
	 * @Date        :      2011-1-26
	 *
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
