package com.xidu.framework.usermgnt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IRoomDao;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.domain.Room;

@Repository("roomDao")
public class RoomDaoImpl extends BaseDaoImpl<Room, Long> implements IRoomDao {

	@Autowired
	public RoomDaoImpl(@Value("com.xidu.framework.usermgnt.domain.Room") Class<Room> clazz) {
        super(clazz);
    }

	@Override
	public List<Room> getRoomList(String roomId) {
		// TODO Auto-generated method stub
        List<Room> rooms = getListByQuery("from Room d where  d.id in "+roomId);
        return rooms;
	}

}
