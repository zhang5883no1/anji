package com.zhibo.reporting.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.xidu.framework.common.dao.impl.BaseDaoImpl;
import com.zhibo.reporting.dao.DeviationReportDao;
import com.zhibo.reporting.domain.DeviationReportDomain;

@Repository
public class DeviationReportDaoImpl extends
BaseDaoImpl<DeviationReportDomain, Long> implements DeviationReportDao{

	@Autowired
	public DeviationReportDaoImpl(@Value("com.zhibo.reporting.domain.DeviationReportDomain")Class<DeviationReportDomain> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}


}
