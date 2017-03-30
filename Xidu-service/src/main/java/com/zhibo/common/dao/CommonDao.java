package com.zhibo.common.dao;

import com.zhibo.common.dto.QueryCommonDto;

public interface CommonDao  {
	/**
	 * 
	 * @param queryDto
	 * @return
	 */
	QueryCommonDto getResultListByCondition(QueryCommonDto queryDto);

	/**
	 * query by sql
	 * @param queryDto
	 * @return
	 * @throws ClassNotFoundException 
	 */
	QueryCommonDto getResultListBySQL(QueryCommonDto queryDto) throws Exception;

	String getSizeByQuery(String sql);

	String getEntityBySql(String sql);
	
}
