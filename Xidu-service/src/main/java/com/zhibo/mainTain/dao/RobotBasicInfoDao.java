package com.zhibo.mainTain.dao;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dto.BasePagerDto;
import com.zhibo.mainTain.domain.RobotBasicInfo;
import com.zhibo.mainTain.dto.QueryRobotrBasicInfoDto;

public interface RobotBasicInfoDao extends IBaseDao<RobotBasicInfo, Long>{

	BasePagerDto queryRobotBasicInfo(
			QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto);
}
