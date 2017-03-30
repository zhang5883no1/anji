/**
 * 
 */
package com.zhibo.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.impl.BaseServiceImpl;
import com.zhibo.common.dao.CommonDao;
import com.zhibo.common.dto.QueryCommonDto;
import com.zhibo.common.service.CommonService;

/**
 * @author WEICWANG
 *
 */
@Transactional(rollbackFor = AppException.class)
@Service
public class CommonServiceImpl extends BaseServiceImpl implements CommonService{
	@Autowired
	private CommonDao commonDao;
	@Override
	public QueryCommonDto getResultListByCondition(QueryCommonDto queryDto) throws Exception {
		// TODO Auto-generated method stub
		if(queryDto.getDomainName()==null){
			return commonDao.getResultListBySQL(queryDto);
		}else{
			return commonDao.getResultListByCondition(queryDto);
		}
	}
	@Override
	public String validBysql(String sql) {
		// TODO Auto-generated method stub
		return commonDao.getSizeByQuery(sql);
	}
	@Override
	public String getEntityBySql(String sql) {
		// TODO Auto-generated method stub
		return commonDao.getEntityBySql(sql);
	}
	
}
