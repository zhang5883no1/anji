package com.zhibo.mainTain.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.zhibo.mainTain.dao.VideoDao;
import com.zhibo.mainTain.domain.Video;
import com.zhibo.mainTain.dto.QueryVideoDto;

@Repository
public class VideoDaoImpl extends
BaseDaoImpl<Video, Long> implements VideoDao{

	@Autowired
	public VideoDaoImpl(@Value("com.zhibo.mainTain.domain.Video")Class<Video> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BasePagerDto queryVideo(
			QueryVideoDto<Video> queryDto) {
		// TODO Auto-generated method stub
		String sql = "from Video t where  DELETE_FLAG=0 ";

		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if (Utils.notEmpty(queryDto.getId())) {
			wcg.and("t.id", "=",  queryDto.getId());
		}
		if (Utils.notEmpty(queryDto.getSortColumn())) {
			wcg.sort(queryDto.getSortColumn(), queryDto.getAscOrDesc());
		}
		wcg.sort("CREATE_DATE", "desc");
		logger.debug("sql query : " + wcg);

		List<Video> list = getListByQueryWithDefaultPaging(
				wcg.toQuery(), queryDto.getPager());
		queryDto.setResultList(list);
		return queryDto;
	}

}
