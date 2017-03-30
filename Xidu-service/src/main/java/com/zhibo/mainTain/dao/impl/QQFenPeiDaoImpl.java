package com.zhibo.mainTain.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.zhibo.mainTain.dao.QQFenPeiDao;
import com.zhibo.mainTain.domain.QQFenPei;
import com.zhibo.mainTain.dto.QueryQQBasicInfoDto;

@Repository
public class QQFenPeiDaoImpl extends BaseDaoImpl<QQFenPei, Long> implements QQFenPeiDao{
	
	@Autowired
	public QQFenPeiDaoImpl(@Value("com.zhibo.mainTain.domain.QQFenPei") Class<QQFenPei> clazz){
		super(clazz);
	}

	
	@Override
	public BasePagerDto queryqqbasicinfo(QueryQQBasicInfoDto<QQFenPei> querydto) {
		String queryroomid = "";
		if(Utils.notEmpty(querydto.getRoomId())){
			queryroomid = querydto.getRoomId();
		}else{
			queryroomid = querydto.getRooms().get(0).getId().toString();
		}		
		
		String sql = "from QQFenPei q where DELETE_FLAG=0 and q.roomId="+queryroomid;
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if(Utils.notEmpty(queryroomid)){
			wcg.and("q.roomId",  "=",  queryroomid);
		}
		
		logger.debug("sql query : " + wcg);
		List<QQFenPei> list = getListByQuery(sql);
		querydto.setResultList(list);
		return querydto;
	}

	@Override
	public List<QQFenPei> getAllQQfenpei() {
		// TODO Auto-generated method stub
		return null;
	}

}
