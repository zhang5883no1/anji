package com.xidu.framework.excel.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BatchProcessStatusEnum {
	INIT("1","初始化"),ONHAND("2","待处理")
	,BEING_PROCESSED("5","处理中"),SUCCESSS("6","成功"),FAIL("7","失败"),CANCEL("8","作废");
		private String status;
		private String description;
		public static final Map<String,String> enumMap=new HashMap<String,String>(){
			private static final long serialVersionUID = -5630944099770357620L;
			{
				for(BatchProcessStatusEnum enu : BatchProcessStatusEnum.values()){
					put(enu.getStatus(), enu.getDescription());
				}
			}		
		};
		public static final List<CodeMapping> statusList=new ArrayList<CodeMapping>(5);
		static {
			for(BatchProcessStatusEnum enu : BatchProcessStatusEnum.values()){
				if(enu.status.equals(BatchProcessStatusEnum.INIT.status)
						|| enu.status.equals(BatchProcessStatusEnum.CANCEL.status)
						|| enu.status.equals(BatchProcessStatusEnum.BEING_PROCESSED.status)){
					continue;
				}
				CodeMapping codeMapping=new CodeMapping();
				codeMapping.setStatus(enu.status);
				codeMapping.setDescription(enu.description);
				statusList.add(codeMapping);
			}
		}
		public static Map<String,String> getEnumMap(){
			return enumMap;
		}
		
		public static String getDescByStatus(String status){
			return enumMap.get(status);
		}
		
		public static String getStatusByDesc(String desc){
			return enumMap.get(desc);
		}
		
		private BatchProcessStatusEnum(String status, String description) {
			this.status = status;
			this.description = description;
		}
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
}
