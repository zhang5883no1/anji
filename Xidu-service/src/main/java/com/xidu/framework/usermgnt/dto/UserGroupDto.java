package com.xidu.framework.usermgnt.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.domain.Room;

public class UserGroupDto {
	private String userGroupCode;
	private String userGroupName;
	private String parentId;
	private List<BaseUser> userlist = new ArrayList<BaseUser>();
	private Set<Room> rooms;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserGroupCode() {
		return userGroupCode;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public void setUserGroupCode(String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<BaseUser> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<BaseUser> userlist) {
		this.userlist = userlist;
	}
}
