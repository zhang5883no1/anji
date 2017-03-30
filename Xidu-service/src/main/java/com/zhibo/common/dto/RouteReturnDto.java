package com.zhibo.common.dto;

import com.xidu.framework.common.dto.BaseDto;

public class RouteReturnDto extends BaseDto{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String id;
	    private String routeId;
	    private String routeName;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getRouteId() {
			return routeId;
		}
		public void setRouteId(String routeId) {
			this.routeId = routeId;
		}
		public String getRouteName() {
			return routeName;
		}
		public void setRouteName(String routeName) {
			this.routeName = routeName;
		}
	    

}
