package com.zhibo.mainTain.dto;

import java.util.List;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.usermgnt.dto.RoomDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.xidu.framework.usermgnt.dto.UserGroupDto;

public class QueryCustomerBasicInfoDto<T> extends BasePagerDto<T>{
	private String userName;
	private String nickName;
	private String status;
	private String level;
	private String id;
	private String roomId;
	private String groupCode="";
	private String userId="";
	private SessionUserDto sessionUser=null;
	private List<UserGroupDto> grouplist=null;
	private List<RoomDto> rooms=null;
	public List<RoomDto> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomDto> rooms) {
		this.rooms = rooms;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
