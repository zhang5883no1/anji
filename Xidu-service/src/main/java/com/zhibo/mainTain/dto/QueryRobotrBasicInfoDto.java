package com.zhibo.mainTain.dto;

import java.util.ArrayList;
import java.util.List;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.xidu.framework.usermgnt.dto.UserGroupDto;
import com.zhibo.mainTain.domain.CustomerBasicInfo;

public class QueryRobotrBasicInfoDto<T> extends BasePagerDto<T>{
	
	private String id;
	private String userId;
	private String groupId;
	private SessionUserDto sessionUser=null;
	private List<UserGroupDto> grouplist=null;
	private List<RoomDto> rooms=null;
	private List<CustomerBasicInfo> customerlist=null;
	public List<CustomerBasicInfo> getCustomerlist() {
		return customerlist;
	}
	public void setCustomerlist(List<CustomerBasicInfo> customerlist) {
		this.customerlist = customerlist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public SessionUserDto getSessionUser() {
		return sessionUser;
	}
	public void setSessionUser(SessionUserDto sessionUser) {
		this.sessionUser = sessionUser;
	}
	public List<UserGroupDto> getGrouplist() {
		return grouplist;
	}
	public void setGrouplist(List<UserGroupDto> grouplist) {
		this.grouplist = grouplist;
	}
	public List<RoomDto> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomDto> rooms) {
		this.rooms = rooms;
	}
	
	
	

}
