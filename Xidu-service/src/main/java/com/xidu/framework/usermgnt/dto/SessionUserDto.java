/**
 * 
 */
package com.xidu.framework.usermgnt.dto;

import java.util.Date;
import java.util.List;

import com.xidu.framework.common.dto.BaseDto;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.usermgnt.domain.Role;
import com.xidu.framework.usermgnt.domain.UserGroup;

/**
 * @author WEICWANG
 *
 */
public class SessionUserDto extends BaseDto{
	private String userId;
	private String userCode;
	private String UserName;
	private Date lastupdatedata;
	private UserGroup userGroup;
	private Role role;
	private List<Menu> menuList;
	private long assignUser;
	private String childrenId;
	private String parentId;
	private String employer_owner_id;
	public String getEmployer_owner_id() {
		return employer_owner_id;
	}
	public void setEmployer_owner_id(String employer_owner_id) {
		this.employer_owner_id = employer_owner_id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Date getLastupdatedata() {
		return lastupdatedata;
	}
	public void setLastupdatedata(Date lastupdatedata) {
		this.lastupdatedata = lastupdatedata;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public long getAssignUser() {
		return assignUser;
	}
	public void setAssignUser(long assignUser) {
		this.assignUser = assignUser;
	}
	public String getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(String childrenId) {
		this.childrenId = childrenId;
	}
	
	
}
