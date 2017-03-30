package com.xidu.framework.usermgnt.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.usermgnt.domain.GroupRoom;

public interface IGroupRoomDao  extends IBaseDao<GroupRoom, Long> {

	public List<GroupRoom> getRoomList(String groupId);
}
