package com.zhibo.mainTain.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.common.util.WhereConditionGenerator;
import com.zhibo.mainTain.dao.KCBExcleDao;
import com.zhibo.mainTain.domain.KCBExcle;
import com.zhibo.mainTain.dto.QueryKCBDto;

@Repository
public class KCBExcleDaoImpl extends BaseDaoImpl<KCBExcle, Long> implements KCBExcleDao{
	
	@Autowired
	public KCBExcleDaoImpl(@Value("com.zhibo.mainTain.domain.KCBExcle") Class<KCBExcle> clazz){
		super(clazz);
	}
	
	@Override
	public BasePagerDto queryKCBInfo(QueryKCBDto<KCBExcle> querykcb){
		String queryroomid = "";
		if(Utils.notEmpty(querykcb.getRoomId())){
			queryroomid = querykcb.getRoomId();
		}else{
			queryroomid = querykcb.getRooms().get(0).getId().toString();
		}
		String sql = "from KCBExcle k where DELETE_FLAG=0 and k.roomId="+queryroomid;
		WhereConditionGenerator wcg = new WhereConditionGenerator(sql);
		if(Utils.notEmpty(queryroomid)){
			wcg.and("q.roomId",  "=",  queryroomid);
		}
		logger.debug("sql query : " + wcg);
		List<KCBExcle> list = getListByQuery(sql);
		if(list.size()!=0){
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+list.get(0).getId().toString());
			querykcb.setId(list.get(0).getId().toString());
		}else{
			
		}
		querykcb.setResultList(list);
		return querykcb;
	}

	@Override
	public List<KCBExcle> getAllKCB() {
		// TODO Auto-generated method stub
		return null;
	}

}
