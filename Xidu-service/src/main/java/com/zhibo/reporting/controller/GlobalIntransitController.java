package com.zhibo.reporting.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xidu.framework.Constants;
import com.zhibo.reporting.dto.Airfreight;
import com.zhibo.reporting.dto.DomesticTransport;
import com.zhibo.reporting.dto.FtlBarge;
import com.zhibo.reporting.dto.QueryGlobalDto;
import com.zhibo.reporting.dto.Seafreight;
import com.zhibo.reporting.service.ReportingService;

public class GlobalIntransitController {

//	private static Logger logger = Logger.getLogger(GlobalIntransitController.class);
//	
//	@Autowired
//	ReportingService service;
//	
//	
//	@RequestMapping(value = "init", method = RequestMethod.GET)
//	public String init(Model model,HttpServletRequest request){
//		model.addAttribute("queryDto", new QueryGlobalDto());
//		return "reporting/globalintransit/globalintransit";
//	}
//	
//	@RequestMapping(value="queryInfo")
//	public String queryInfo(QueryGlobalDto queryDto, Model model,HttpServletRequest request) throws ParseException{
//		service.searchShipmentReport(queryDto);
//		model.addAttribute("queryDto",queryDto);
//		return "reporting/globalintransit/globalintransit";
//	}
//	
//	@RequestMapping(value = "exportExcel")
//	public ModelAndView exportActualShipQtyGapAsExcel(QueryGlobalDto queryDto)throws ParseException{
//		ModelAndView modelAndView = new ModelAndView();
//	    queryDto.getPager().setPageSize(65525);
//		if("Domestic".equals(queryDto.getTransportation_mode())){
//	      List<DomesticTransport> callOffGapList = service.searchShipmentReport(queryDto).getResultList();
//	      modelAndView.addObject(Constants.EXPORT_XLS_DATA_LIST, callOffGapList);
//	      modelAndView.addObject(Constants.EXPORT_XLS_MAPPING_FILE, "globalIntransitExportTemplate.xml");
//	      modelAndView.addObject(Constants.EXPORT_XLS_TEMPLATE_NAME, "callOfGlobalExportTemplate");
//	      modelAndView.setViewName("exportGlobalintransit");
//		}else if("Air".equals(queryDto.getTransportation_mode())){
//		  List<Airfreight> callOffGapList=service.searchShipmentReport(queryDto).getResultList();
//		  modelAndView.addObject(Constants.EXPORT_XLS_DATA_LIST, callOffGapList);
//		  modelAndView.addObject(Constants.EXPORT_XLS_MAPPING_FILE, "airfreightExportTemplate.xml");
//		  modelAndView.addObject(Constants.EXPORT_XLS_TEMPLATE_NAME, "callOfAirExportTemplate");
//		  modelAndView.setViewName("exportAirfreight");			
//		}else if("Ocean".equals(queryDto.getTransportation_mode())&&!"CEP".equals(queryDto.getDestPlantCode())){
//			List<Seafreight> callOffGapList=service.searchShipmentReport(queryDto).getResultList();
//			modelAndView.addObject(Constants.EXPORT_XLS_DATA_LIST, callOffGapList);
//			modelAndView.addObject(Constants.EXPORT_XLS_MAPPING_FILE, "seafreightExportTemplate.xml");
//			modelAndView.addObject(Constants.EXPORT_XLS_TEMPLATE_NAME, "callOfSeaExportTemplate");
//			modelAndView.addObject("isOcean","SEA");
//			modelAndView.setViewName("exportSeafreight");			
//		}else if("Ocean".equals(queryDto.getTransportation_mode())&&"CEP".equals(queryDto.getDestPlantCode())){
//			List<Seafreight> callOffGapList=service.searchShipmentReport(queryDto).getResultList();
//			modelAndView.addObject(Constants.EXPORT_XLS_DATA_LIST, callOffGapList);
//			modelAndView.addObject(Constants.EXPORT_XLS_MAPPING_FILE, "seafreightCEPExportTemplate.xml");
//			modelAndView.addObject(Constants.EXPORT_XLS_TEMPLATE_NAME, "callOfSeaCEPExportTemplate");
//			modelAndView.addObject("isOcean","CEP");
//			modelAndView.setViewName("exportSeafreight");			
//		}else if("FTL+BARGE".equals(queryDto.getTransportation_mode())){
//			List<FtlBarge> callOffGapList=service.searchShipmentReport(queryDto).getResultList();
//			modelAndView.addObject(Constants.EXPORT_XLS_DATA_LIST, callOffGapList);
//			modelAndView.addObject(Constants.EXPORT_XLS_MAPPING_FILE, "ftlbargeExportTemplate.xml");
//			modelAndView.addObject(Constants.EXPORT_XLS_TEMPLATE_NAME, "callOfftlbargeExportTemplate");
//			modelAndView.setViewName("exportFtlBarge");		
//		}
//	    
//	    
//      return modelAndView;
//	}
	
}
