package com.xidu.framework.usermgnt.dto;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.UserGroup;

public class QueryUserInfoDto<T> extends BasePagerDto<T>{

	private String id;
	private String userCode;
	private String password;
	private String name;
	private int statusCode;
	private int userType;
	private String employerOwnerId;
	private UserGroup userGroup;
    private String parentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getEmployerOwnerId() {
		return employerOwnerId;
	}
	public void setEmployerOwnerId(String employerOwnerId) {
		this.employerOwnerId = employerOwnerId;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
