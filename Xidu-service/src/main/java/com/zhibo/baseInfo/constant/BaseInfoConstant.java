/**
 * 
 */
package com.zhibo.baseInfo.constant;

/**
 * @author WEICWANG
 * 
 */
public interface BaseInfoConstant {
	/*common*/
	public static final String EC_I="ec_i";
	
	/* supplier list */
	public static final String SUPPLIER_LIST_PAGE = "baseInfo/supplierInfo/supplierList";

	/* supplier add or update */
	public static final String SUPPLIER_ADD_OR_EDIT_PAGE = "baseInfo/supplierInfo/addSupplierBaseInfo";
	/* show detail */
	public static final String SUPPLIER_SHOWDETAIL_PAGE = "baseInfo/supplierInfo/showSupplierBaseInfoDetail";
	/* package add or edit */
	public static final String PACKAGE_ADD_EDIT = "baseInfo/packageBasicInfo/add";
	/* package list */
	public static final String PACKAGE_LIST_PAGE = "baseInfo/packageBasicInfo/packageList";
	/* port list */
	public static final String PORT_LIST_PAGE = "baseInfo/portInfo/portList";
	/* port add or edit */
	public static final String PORT_ADD_EDIT = "baseInfo/portInfo/add";
	/* port list */
	public static final String YARD_LIST_PAGE = "baseInfo/yardInfo/yardList";
	/* port add or edit */
	public static final String YARD_ADD_EDIT = "baseInfo/yardInfo/addYard";
	// menu
	public static final String MENU_LIST_PAGE = "mainTain/menu/menuList";
	public static final String MENU_ADD_PAGE = "mainTain/menu/addMenu";
	// error
	public static final String NOT_LOGIN = "user.not.login";
	public static final String CREAT_MENU_ERROR = "error.menu.create";
	public static final String  PART_LIST_SUPPLIERZ_NOT_EXIST="error";
	public static final String PART_LIST_PAGE = "baseInfo/partinfo/partList";
	public static final String PART_ADD_OR_EDIT_PAGE = "baseInfo/partinfo/addPartBaseInfo";

	public static final String PART_SHOWDETAIL_PAGE = "baseInfo/partinfo/showPartBaseInfoDetail";

	public static final String PART_CUSTOMCLEARANCE_PAGE = "baseInfo/CustomClearanceInfo/CustomClearanceList";

	public static final String CUSTOMCLEARANCE_ADD_OR_EDIT_PAGE = "baseInfo/CustomClearanceInfo/addCoustomClearanceInfo";

	public static final String CUSTOMCLEANARNCE_SHOWDETAIL_PAGE = "baseInfo/CustomClearanceInfo/showCoustomClearanceInfoDetail";

	public static final String WAREHOUSE_LIST_PAGE = "baseInfo/WarehouseInfo/warehouseList";

	public static final String WAREHOUSE_ADD_OR_EDIT_PAGE = "baseInfo/WarehouseInfo/addWarehouseInfo";

	public static final String WAREHOUSE_SHOWDETAIL_PAGE = "baseInfo/WarehouseInfo/showWarehouseInfoDetail";
	public static final String OMS_CTN_INFO_PAGE="baseInfo/CtnInfo/ctnInfoList";
	public static final String CTNINFO_ADD_PAGE="baseInfo/CtnInfo/AddCtnInfo";
	public static final String CTNINFO_EDIT_PAGE="baseInfo/CtnInfo/editCtnInfo";
	public static final String QUERY_CTN_INFO_DTO = "queryDto";
	/*dest plant pages add by xiewei */
	static final class DestPlantInfo{
		public static final String DESTPLANT_LIST_PAGE="baseInfo/destPlantInfo/destPlantList";
		public static final String DESTPLANT_ADD_OR_EDIT_PAGE = "baseInfo/destPlantInfo/addDestPlantInfo";
		public static final String DESTPLANT_SHOWDETAIL_PAGE = "baseInfo/destPlantInfo/showDestPlantInfo";
		public static final String ORDER_SHOWDESTDETAIL_PAGE = "baseInfo/destPlantInfo/showDestDetail";
		
	}
}
