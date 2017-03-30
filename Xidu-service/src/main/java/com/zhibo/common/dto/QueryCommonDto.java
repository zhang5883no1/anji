/**
 * 
 */
package com.zhibo.common.dto;

import java.util.Map;

import com.xidu.framework.common.dto.BasePagerDto;

/**
 * @author WEICWANG
 *
 */
public class QueryCommonDto extends BasePagerDto{
	private String domainName;
	private Map queryProperties;
	private String returnDto;
	
	private String sql;
	private String properties;
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public Map getQueryProperties() {
		return queryProperties;
	}
	public void setQueryProperties(Map queryProperties) {
		this.queryProperties = queryProperties;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getReturnDto() {
		return returnDto;
	}
	public void setReturnDto(String returnDto) {
		this.returnDto = returnDto;
	}
	
	
	

}
