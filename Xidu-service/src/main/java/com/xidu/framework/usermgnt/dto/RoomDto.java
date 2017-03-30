package com.xidu.framework.usermgnt.dto;

import java.util.List;

import com.xidu.framework.usermgnt.domain.BaseUser;

public class RoomDto {
	private String roomCode;
	private String roomName;
	private Long id;
	private List<BaseUser> userList;
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<BaseUser> getUserList() {
		return userList;
	}
	public void setUserList(List<BaseUser> userList) {
		this.userList = userList;
	}
	

}
