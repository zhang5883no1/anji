package com.zhibo.mainTain.dto;

import java.util.List;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.usermgnt.dto.RoomDto;

public class QueryQQBasicInfoDto<T> extends BasePagerDto<T>{
	private String id;
	private String roomId;
	private List<RoomDto> rooms=null;
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public List<RoomDto> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomDto> rooms) {
		this.rooms = rooms;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
