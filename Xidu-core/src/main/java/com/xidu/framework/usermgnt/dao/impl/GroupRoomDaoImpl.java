package com.xidu.framework.usermgnt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.usermgnt.dao.IGroupRoomDao;
import com.xidu.framework.usermgnt.domain.GroupRoom;
import com.xidu.framework.usermgnt.domain.Room;

@Repository("groupRoomDao")
public class GroupRoomDaoImpl extends BaseDaoImpl<GroupRoom, Long> implements IGroupRoomDao {

	@Autowired
	public GroupRoomDaoImpl(@Value("com.xidu.framework.usermgnt.domain.GroupRoom") Class<GroupRoom> clazz) {
        super(clazz);
    }

	@Override
	public List<GroupRoom> getRoomList(String groupId) {
		// TODO Auto-generated method stub
        List<GroupRoom> rooms = getListByQuery("from GroupRoom d where  d.group in "+groupId);
        return rooms;
	}

}
