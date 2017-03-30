package com.zhibo.mainTain.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dto.BasePagerDto;
import com.zhibo.mainTain.domain.QQFenPei;
import com.zhibo.mainTain.dto.QueryQQBasicInfoDto;

public interface QQFenPeiDao extends IBaseDao<QQFenPei, Long>{
	BasePagerDto queryqqbasicinfo(QueryQQBasicInfoDto<QQFenPei> querydto);
	List<QQFenPei> getAllQQfenpei();
}
