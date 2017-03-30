package com.zhibo.mainTain.dto;

import com.xidu.framework.common.dto.BasePagerDto;

public class QueryBillDto<T> extends BasePagerDto<T>{
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
