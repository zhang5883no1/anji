package com.zhibo.mainTain.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.xidu.framework.usermgnt.domain.BaseUser;
import com.xidu.framework.usermgnt.dto.UserGroupDto;
import com.zhibo.mainTain.dao.RobotBasicInfoDao;
import com.zhibo.mainTain.domain.CustomerBasicInfo;
import com.zhibo.mainTain.domain.RobotBasicInfo;
import com.zhibo.mainTain.dto.QueryRobotrBasicInfoDto;

@Repository
public class RobotBasicInfoDaoImpl extends
BaseDaoImpl<RobotBasicInfo, Long> implements RobotBasicInfoDao{

	@Autowired
	public RobotBasicInfoDaoImpl(@Value("com.zhibo.mainTain.domain.RobotBasicInfo")Class<RobotBasicInfo> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasePagerDto queryRobotBasicInfo(
			QueryRobotrBasicInfoDto<RobotBasicInfo> queryDto) {
		// TODO Auto-generated method stub
		String sql = "from RobotBasicInfo t where DELETE_FLAG=0 ";
		String usersql="";
		if(Utils.notEmpty(queryDto.getUserId())){
			usersql="("+queryDto.getUserId();
		}else{
			usersql="(0";
			for(CustomerBasicInfo c:queryDto.getCustomerlist()){
				usersql+=","+c.getId();
			}
		}
		sql +=" and t.userId in "+usersql+")";

		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if (Utils.notEmpty(queryDto.getId())) {
			wcg.and("t.id", "=",  queryDto.getId());
		}
		
		if (Utils.notEmpty(queryDto.getSortColumn())) {
			wcg.sort(queryDto.getSortColumn(), queryDto.getAscOrDesc());
		}
		wcg.sort("CREATE_DATE", "desc");
		logger.debug("sql query : " + wcg);

		List<RobotBasicInfo> list = getListByQueryWithDefaultPaging(
				wcg.toQuery(), queryDto.getPager());
		queryDto.setResultList(list);
		return queryDto;
	}

}
