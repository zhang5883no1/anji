package com.zhibo.mainTain.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dto.BasePagerDto;
import com.zhibo.mainTain.domain.KCBExcle;
import com.zhibo.mainTain.dto.QueryKCBDto;

public interface KCBExcleDao extends IBaseDao<KCBExcle, Long>{
	
	BasePagerDto queryKCBInfo(QueryKCBDto<KCBExcle> querykcb);
	
	List<KCBExcle> getAllKCB();
}
