package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.Room;

public interface IRoomDao  extends IBaseDao<Room, Long> {

	 public List<Room> getRoomList(String roomId);
}
