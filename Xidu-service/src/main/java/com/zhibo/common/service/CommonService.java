package com.zhibo.common.service;

import com.xidu.framework.common.service.IBaseService;
import com.zhibo.common.dto.QueryCommonDto;

public interface CommonService extends IBaseService {
	/**
	 * 
	 * @param queryDto
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public QueryCommonDto getResultListByCondition(QueryCommonDto queryDto) throws Exception;

	public String validBysql(String sql);

	public String getEntityBySql(String sql);
}
