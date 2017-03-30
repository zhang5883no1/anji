package com.zhibo.mainTain.dao;

import java.util.List;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dto.BasePagerDto;
import com.zhibo.mainTain.domain.CustomerBasicInfo;
import com.zhibo.mainTain.dto.QueryCustomerBasicInfoDto;

public interface CustomerBasicInfoDao  extends IBaseDao<CustomerBasicInfo, Long>{

	BasePagerDto queryCustomerBasicInfo(
			QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto);

	List<CustomerBasicInfo> getAllAdminCustomer();

	BasePagerDto queryAdminBasicInfo(
			QueryCustomerBasicInfoDto<CustomerBasicInfo> queryDto);

}
