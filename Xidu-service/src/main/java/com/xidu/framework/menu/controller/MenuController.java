package com.xidu.framework.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidu.framework.common.controller.BaseController;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.menu.domain.Menu;
import com.xidu.framework.menu.dto.CreateMenuDto;
import com.xidu.framework.menu.dto.QueryMenuDto;
import com.xidu.framework.menu.service.IMenuService;
import com.xidu.framework.usermgnt.constant.WorkbenchConstant;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.baseInfo.constant.BaseInfoConstant;
import com.zhibo.mainTain.service.MainTainService;

@Controller
@RequestMapping(value = "/maintain/menu")
public class MenuController extends BaseController{
	private static Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	private IMenuService menuService;
	@Autowired
	private MainTainService mservice;

	@RequestMapping(value="init" ,method = RequestMethod.GET)
	public String init(Model model,HttpServletRequest request) {
		if(!mservice.validmenu(request)){
			return "/error";
		}
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		QueryMenuDto<Menu> queryMenuDto=new QueryMenuDto<Menu>();
		queryMenuDto.setType(String.valueOf(sessionUser.getUserGroup().getId()));
		List menuList=menuService.queryMenu(queryMenuDto);
		queryMenuDto.setResultList(menuList);
		model.addAttribute("queryMenuDto", queryMenuDto);
		return BaseInfoConstant.MENU_LIST_PAGE;
	}

	@RequestMapping(value="queryMenu" ,method = RequestMethod.POST)
	public String queryMenu(QueryMenuDto<Menu> queryDto,Model model,HttpServletRequest req){
		if(!mservice.validmenu(req)){
			return "/error";
		}
		SessionUserDto sessionUser=(SessionUserDto)req.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		logger.debug("query start");
	    if(req!=null){
	    	 logger.debug("sort porperties:"+req.getParameter("tableResults_s_menuName"));
	    }
	    queryDto.setType(String.valueOf(sessionUser.getUserGroup().getId()));
	    List menuList=menuService.queryMenu(queryDto);
	    queryDto.setResultList(menuList);
		model.addAttribute("queryMenuDto", queryDto);
		return BaseInfoConstant.MENU_LIST_PAGE;
		
	}
	
	@RequestMapping(value="addOrEditMenu" ,method = RequestMethod.POST)
	public String addOrEditMenu(QueryMenuDto<Menu> queryDto,Model model,HttpServletRequest request) {
		if(!mservice.validmenu(request)){
			return "/error";
		}
		logger.debug("edit menu===>>>"+queryDto.getMenuId());
		CreateMenuDto menuDto=new CreateMenuDto();
		//edit menu
		if(Utils.notEmpty(queryDto.getMenuId())){
			menuDto=menuService.getMenuByMenuId(queryDto.getMenuId());
		}
//		model.addAttribute("querySupplierInfoDto", queryDto);
		model.addAttribute("menuDto", menuDto);
		return BaseInfoConstant.MENU_ADD_PAGE;
	}
	
	@RequestMapping(value="saveMenu" ,method = RequestMethod.POST)
	public String saveMenu(@Valid CreateMenuDto menuDto, BindingResult result,Model model,HttpServletRequest request) {
		if(!mservice.validmenu(request)){
			return "/error";
		}
		Long operatorId=null;
		SessionUserDto sessionUser=(SessionUserDto)request.getSession().getAttribute(WorkbenchConstant.SESSION_USER);
		if(sessionUser==null){
			result.rejectValue("notLogin", BaseInfoConstant.NOT_LOGIN);
		}
		operatorId=Long.parseLong(sessionUser.getUserId());
		//save dto
		try {
			if(menuDto.getId()==null){
				menuService.createMenu(menuDto.getMenuCode(), menuDto.getMenuName(), menuDto.getParentMenuCode(), menuDto.getMenuLevel(), menuDto.getIsLeaf(), menuDto.getUrl(), operatorId,sessionUser.getUserGroup());
			}else{
				menuService.updateMenu(menuDto.getMenuCode(), menuDto.getMenuName(), menuDto.getParentMenuCode(), menuDto.getMenuLevel(), menuDto.getIsLeaf(), menuDto.getUrl(), operatorId,sessionUser.getUserGroup());
			}
			
		} catch (AppException e) {
			result.rejectValue("createMenuError", BaseInfoConstant.CREAT_MENU_ERROR);
			e.printStackTrace();
		}
		//query list
		QueryMenuDto<Menu> queryDto=new QueryMenuDto<Menu>();
		return this.queryMenu(queryDto, model,request);
	}
	
	@RequestMapping(value="deleteMenu" ,method = RequestMethod.POST)
	public String deleteMenu(QueryMenuDto<Menu> queryDto,Model model,HttpServletRequest request) throws AppException {
		if(!mservice.validmenu(request)){
			return "/error";
		}
		//delete dto
		menuService.deleteMenuById(Long.parseLong(queryDto.getMenuId()));
		
		//query list
		return this.queryMenu(queryDto, model,request);
	}
	
	
//	@RequestMapping(value="showSupplierDetail" ,method = RequestMethod.GET)
//	public String showSupplierDetail(HttpServletRequest request,Model model) {
//		String supplierId =request.getParameter("supplierId");
//		logger.debug("edit supplierId===>>>"+supplierId);
//		SupplierBasicInfoDto supplierDto=new SupplierBasicInfoDto();
//		//edit supplier
//		if(Utils.notEmpty(supplierId)){
//			supplierDto=baseInfoService.getSupplierInfoById(supplierId);
//		}
//		model.addAttribute("supplierInfoDto", supplierDto);
//		return BaseInfoConstant.SUPPLIER_SHOWDETAIL_PAGE;
//	}
}
