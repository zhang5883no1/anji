package com.zhibo.reporting.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.util.ApplicationContextUtil;
import com.xidu.framework.common.util.PropertiesUtil;
import com.xidu.framework.common.util.Utils;
import com.xidu.framework.fileloading.constant.FileLoadingConstant;
import com.xidu.framework.fileloading.service.FileLoadingComponent;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.reporting.dao.DeviationReportDao;
import com.zhibo.reporting.domain.DeviationReportDomain;
import com.zhibo.reporting.dto.DeviationFileLoadingProcess;
import com.zhibo.reporting.dto.DeviationInfoDto;
import com.zhibo.reporting.service.ReportingService;

@Transactional(rollbackFor = AppException.class)
@Service
public class ReportingServiceImpl implements ReportingService{
	
	@Autowired
	DeviationReportDao deviationReportDao;
	
	@Override
	public void upLoadDeviationReport(DeviationFileLoadingProcess deviationUploadDto,DeviationInfoDto<?> queryDto,SessionUserDto sessionUser) {
		doValidate(deviationUploadDto,queryDto,sessionUser);
		if(Utils.notEmpty(deviationUploadDto.getErrorMsg())){
			return;
		}
		initUploadDeviationLogInfo(deviationUploadDto, queryDto, sessionUser);
		FileLoadingComponent bfcc = (FileLoadingComponent) ApplicationContextUtil.getBean("deviationReport");
		bfcc.doUploading(deviationUploadDto);
		String allFileName=deviationUploadDto.getDeviationInfoDto().getFileName().replace(PropertiesUtil.getPropertyValueByKey(FileLoadingConstant.DEVIATION_REPORT_LOCATION_KEY), "");
		System.out.println(allFileName);
		saveUploadDeviation(new DeviationReportDomain(deviationUploadDto.getDeviationInfoDto().getFileName(),deviationUploadDto.getMultipartFile().getOriginalFilename()));
	}
	
	private void saveUploadDeviation(DeviationReportDomain domain) {
		// TODO Auto-generated method stub
		deviationReportDao.save(domain);
	}

	private void doValidate(DeviationFileLoadingProcess deviationUploadDto,
			DeviationInfoDto<?> queryDto, SessionUserDto sessionUser){
		AbstractMessageSource messageSource = (AbstractMessageSource) ApplicationContextUtil
				.getBean("messageSource");
		StringBuilder errorMsg = new StringBuilder();
		System.out.println(deviationUploadDto.getMultipartFile().getSize() );
		if(deviationUploadDto.getMultipartFile().getSize() == 0){
			errorMsg.append(messageSource.getMessage("file.notEmpty", null, Locale.ENGLISH));
		}
		else{
			//validate deviation report file name 
//			String originalFileName = deviationUploadDto.getMultipartFile().getOriginalFilename();
//			int index = originalFileName.lastIndexOf("_");
//			int subfix = originalFileName.lastIndexOf(".");
//			if(-1 == index || -1 == subfix){
//				errorMsg.append(messageSource.getMessage("error.file.deviation.report.fileName",null,Locale.ENGLISH));
//			}else{
//				String needValidateMonthStr =  originalFileName.substring(index + 1,subfix);
//				try {
//					Integer month = new Integer(needValidateMonthStr);
//					if(month < 1 || month > 12){
//						errorMsg.append(messageSource.getMessage("error.file.deviation.report.monthNum",null, Locale.ENGLISH));
//					}
//				} catch (Exception e) {
//					errorMsg.append(messageSource.getMessage("error.file.deviation.report.monthFormat",null, Locale.ENGLISH));
//				}
//			}
		}
		//validate file store path
		String realPath = PropertiesUtil.getPropertyValueByKey(FileLoadingConstant.DEVIATION_REPORT_LOCATION_KEY);
		File dirFile = new File(realPath);  
		if (!dirFile.exists()) { 
			errorMsg.append(" ");
			errorMsg.append(messageSource.getMessage("error.file.deviation.report.path", null,Locale.ENGLISH));
		}  
		deviationUploadDto.setErrorMsg(errorMsg.toString());
	}

	private void initUploadDeviationLogInfo(
			DeviationFileLoadingProcess deviationUploadDto,
			DeviationInfoDto<?> queryDto, SessionUserDto sessionUser) {
		deviationUploadDto.setOperationUser(sessionUser.getUserId());
		deviationUploadDto.setSessionUser(sessionUser);
		String realPath = PropertiesUtil.getPropertyValueByKey(FileLoadingConstant.DEVIATION_REPORT_LOCATION_KEY);
		StringBuilder fileName = new StringBuilder(realPath);
		deviationUploadDto.setOperationType(FileLoadingConstant.UPLOAD_OPERATION);
		deviationUploadDto.setDeviationInfoDto(queryDto);
		deviationUploadDto.setOperationDate(new Date());
		deviationUploadDto.setModuleCode("MO5");
		deviationUploadDto.setFunctionCode("MO5_04");
		deviationUploadDto.setOperationDesc("Uploading Deviation Report ANJI");
		deviationUploadDto.setStatus(FileLoadingConstant.DEVIATION_REPORT_UPLOAD_SUCCESS);
		 Calendar ca = Calendar.getInstance();
		 int year = ca.get(Calendar.YEAR);
		deviationUploadDto.setYear(new Integer(year).toString());
		fileName.append(File.separator).append(new Integer(year).toString());
		doFileMonth(deviationUploadDto);
		queryDto.setCreateBy(new Long(sessionUser.getUserId()));
		queryDto.setLastUpdateBy(new Long(sessionUser.getUserId()));
		queryDto.setMonth(new Long(deviationUploadDto.getMonth()));
		fileName.append(File.separator).append(deviationUploadDto.getMonth());
		deviationUploadDto.setDeviationInfoDto(queryDto);
		deviationUploadDto.setFileLocation(fileName.toString());
		String originFileName = deviationUploadDto.getMultipartFile().getOriginalFilename();
		queryDto.setFileName(fileName.toString() +File.separator+ originFileName);
	}
	
	private void doFileMonth(DeviationFileLoadingProcess deviationUploadDto){
//		String fileName = deviationUploadDto.getMultipartFile().getOriginalFilename();
//		int index = fileName.lastIndexOf("_");
//		int subfix = fileName.lastIndexOf(".");
		deviationUploadDto.setMonth(new Date().getMonth()+"");
		
	}
//
//	@Override
//	public void downLoadDeviationReport(DeviationFileLoadingProcess deviationDownloadDto,DeviationInfoDto<?> queryDto,
//			SessionUserDto sessionUserDto) {
//		initDownloadDeviationLogInfo(deviationDownloadDto,queryDto,sessionUserDto);
//		FileLoadingComponent bfcc = (FileLoadingComponent) ApplicationContextUtil.getBean("deviationReport");
//		bfcc.doDownloading(deviationDownloadDto);
//	}
//	
//	
//	private void initDownloadDeviationLogInfo(
//			DeviationFileLoadingProcess deviationUploadDto,
//			DeviationInfoDto<?> queryDto, SessionUserDto sessionUser) {
//		CustomerBasicInfo cust=customerDao.findCustomerByCustomerCode(deviationUploadDto.getSessionUser().getUserCode());
//		if(cust!=null){
//			queryDto.setCustomerId(cust.getId());
//		}
//		DeviationInfoDomain entity=deviationDao.findByQuery(queryDto);
//		if(entity!=null){
//			deviationUploadDto.setFileLocation(entity.getFileName());
//			queryDto.setFileName(entity.getFileName());
//			deviationUploadDto.setStatus(FileLoadingConstant.DEVIATION_REPORT_UPLOAD_SUCCESS);
//		}else{
//			deviationUploadDto.setFileLocation("");
//			queryDto.setFileName("");
//			deviationUploadDto.setStatus(FileLoadingConstant.DEVIATION_REPORT_UPLOAD_FAILED);
//		}
//		deviationUploadDto.setOperationUser(sessionUser.getUserId());
//		deviationUploadDto.setSessionUser(sessionUser);
//		deviationUploadDto.setOperationType(FileLoadingConstant.DOWNLOAD_OPERATION);
//		deviationUploadDto.setDeviationInfoDto(queryDto);
//		deviationUploadDto.setOperationDate(new Date());
//		deviationUploadDto.setModuleCode("MO5");
//		deviationUploadDto.setFunctionCode("MO5_04");
//		deviationUploadDto.setOperationDesc("Download Deviation Report ANJI");
//		deviationUploadDto.setYear(String.valueOf(queryDto.getYear()));
//		queryDto.setCreateBy(new Long(sessionUser.getUserId()));
//		queryDto.setLastUpdateBy(new Long(sessionUser.getUserId()));
//		deviationUploadDto.setDeviationInfoDto(queryDto);
//	}
//
//	@Override
//	public BasePagerDto searchShipmentReport(QueryGlobalDto queryDto) throws ParseException {
//		// TODO Auto-generated method stub
//		BasePagerDto dto=shipDao.searchShipmentReportInfo(queryDto);
//		if("Domestic".equals(queryDto.getTransportation_mode())){
//			List<DomesticTransport> list=dto.getResultList();
//			dto.setResultList(setDomestic(list));
//		}else if("Air".equals(queryDto.getTransportation_mode())){
//			List<Airfreight> list=dto.getResultList();
//			dto.setResultList(setAirfreight(list));
//		}else if("Ocean".equals(queryDto.getTransportation_mode())){
//			List<Seafreight> list=dto.getResultList();
//			dto.setResultList(setSeafreight(list));
//		}else if("LTL+BARGE".equals(queryDto.getTransportation_mode())){
//			List<FtlBarge> list=dto.getResultList();
//			dto.setResultList(setFtlBarge(list));
//		}
////		dto.setResultList(list);
//		return dto;
//	}
//	
//	
//	private List<DomesticTransport> setDomestic(List<DomesticTransport> list){
//		List<DomesticTransport> resultlist=new ArrayList<DomesticTransport>();
//		for(int i=0;i<list.size();i++){
//			DomesticTransport entity=new DomesticTransport();
//			try {
//				BeanUtils.copyProperties(list.get(i), entity);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if(Utils.notEmpty(entity.getAta())){
//				TTripInstance tripinstance=tripinstanceDao.findById(Long.valueOf(entity.getAta()));
//				entity.setAta("");
//				if(tripinstance!=null){
//					List<TRouteInstance> routeinstancelist=routeinstanceDao.findByTripInstanceId(tripinstance.getId());
//					if(routeinstancelist!=null&&routeinstancelist.size()>0){
//						for(int j=0;j<routeinstancelist.size();j++){
//							QueryMilestoneDto<TMilestoneRecord> pagedto=new QueryMilestoneDto<TMilestoneRecord>();
//							Pager pager=new Pager();
//							pager.setCurrentPage(1);
//							pager.setPageSize(65536);
//							pagedto.setRouteInstanceId(String.valueOf(routeinstancelist.get(j).getId()));
//							List<TMilestoneRecord> milestonelist=milestoneDao.findByRouteId(pagedto).getResultList();
//							if(milestonelist!=null&&milestonelist.size()>0){
//								TMilestoneRecord milesone=milestonelist.get(milestonelist.size()-1);
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery")){
//									entity.setDelivery_date(milesone.getEtd());
//									entity.setPickup_date(milesone.getAtd());
//									entity.setAta(milesone.getAta());
//									entity.setEta(milesone.getEta());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery to CFS")){
//									entity.setDelivery_date(milesone.getEtd());
//									entity.setPickup_date(milesone.getAtd());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery to Dest plant")){
//									entity.setAta(milesone.getAta());
//									entity.setEta(milesone.getEta());
//								}
//							}
//						}
//					}
//				}
//			}else{
//				entity.setAta("");
//			}
//			resultlist.add(entity);
//		}
//			
//		return resultlist;
//	}
//	
//	private List<Airfreight> setAirfreight(List<Airfreight> list) throws ParseException{
//		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat ssf=new SimpleDateFormat("yyyy-MM-dd");
//		List<Airfreight> resultlist=new ArrayList<Airfreight>();
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Airfreight entity=new Airfreight();
//				try {
//					BeanUtils.copyProperties(list.get(i), entity);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				if(Utils.notEmpty(entity.getAta())){
//					System.out.println("-----------------------"+entity.getAta()+"is ata");
//					TTripInstance tripinstance=tripinstanceDao.findById(Long.valueOf(entity.getAta()));
//					System.out.println("is find tripinstance end--------------");
//					entity.setAta("");
//					if(tripinstance!=null){
//						List<TRouteInstance> routeinstancelist=routeinstanceDao.findByTripInstanceId(tripinstance.getId());
//						System.out.println("find routeinstance end------------");
//						if(routeinstancelist!=null&&routeinstancelist.size()>0){
//							for(int j=0;j<routeinstancelist.size();j++){
//								QueryMilestoneDto<TMilestoneRecord> pagedto=new QueryMilestoneDto<TMilestoneRecord>();
//								Pager pager=new Pager();
//								pager.setCurrentPage(1);
//								pager.setPageSize(65536);
//								pagedto.setRouteInstanceId(String.valueOf(routeinstancelist.get(j).getId()));
//								List<TMilestoneRecord> milestonelist=milestoneDao.findByRouteId(pagedto).getResultList();
//								if(milestonelist!=null&&milestonelist.size()>0){
//									TMilestoneRecord milesone=milestonelist.get(milestonelist.size()-1);
//									if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Customs release")){
//										if(milesone.getAta()!=null&&milesone.getAtd()!=null&&!"".equals(milesone.getAta())&&!"".equals(milesone.getAtd())){
////											entity.setClearance_time(sf.format(new Date(sf.parse(milesone.getAta()).getTime() * 24 * 60 * 60 * 1000-sf.parse(milesone.getAtd()).getTime() * 24 * 60 * 60 * 1000)));
//											Long time1=sf.parse(milesone.getAta()).getTime() ;
//											Long time2=sf.parse(milesone.getAtd()).getTime();
//											Long time=(time1-time2)/(1000*60*60*24);
//											entity.setClearance_time(String.valueOf(time));
//										}
//									}
//									if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Arrival at APOD")){
//										if(Utils.notEmpty(milesone.getEtd())){
//											entity.setEtd(ssf.format(ssf.parse(milesone.getEtd())));
//										}
//										if(Utils.notEmpty(milesone.getAtd())){
//											entity.setAtd(ssf.format(ssf.parse(milesone.getAtd())));
//										}
//										if(Utils.notEmpty(milesone.getAta())){
//											entity.setAta(ssf.format(ssf.parse(milesone.getAta())));
//										}
//										if(Utils.notEmpty(milesone.getEta())){
//											entity.setEta(ssf.format(ssf.parse(milesone.getEta())));
//										}
//									}
//									if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery to Airport")){
//										entity.setBooking_date(milesone.getEtd());
//										if(Utils.notEmpty(milesone.getAtd())){
//											entity.setPickup_date(sf.format(sf.parse(milesone.getAtd())));
//										}
//									}
//									if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Air Delivery to Dest plant")){
//										if(Utils.notEmpty(milesone.getAta())){
//											entity.setAtavccd(ssf.format(ssf.parse(milesone.getAta())));
//										}
//										
//									}
//								}
//							}
//						}
//					}
//				}
//				resultlist.add(entity);
//			}
//		}
//		return resultlist;
//	}
//	
//	private List<Seafreight> setSeafreight(List<Seafreight> list){
//		List<Seafreight> resultlist=new ArrayList<Seafreight>();
//		for(int i=0;i<list.size();i++){
//			Seafreight entity=new Seafreight();
//			try {
//				BeanUtils.copyProperties(list.get(i), entity);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
////			entity.setContainer_20GP("");
////			entity.setContainer_40GP("");
////			entity.setContainer_40HG("");
////			entity.setPackage_NO("");
//			if(Utils.notEmpty(entity.getAta())){
//				TTripInstance tripinstance=tripinstanceDao.findById(Long.valueOf(entity.getAta()));
//				entity.setAta("");
//				if(tripinstance!=null){
//					List<TRouteInstance> routeinstancelist=routeinstanceDao.findByTripInstanceId(tripinstance.getId());
//					if(routeinstancelist!=null&&routeinstancelist.size()>0){
//						for(int j=0;j<routeinstancelist.size();j++){
//							QueryMilestoneDto<TMilestoneRecord> pagedto=new QueryMilestoneDto<TMilestoneRecord>();
//							Pager pager=new Pager();
//							pager.setCurrentPage(1);
//							pager.setPageSize(65536);
//							RouteInfo route=routeDao.findById(Long.valueOf(routeinstancelist.get(j).getRouteId()));
//							SupplierBasicInfo supplier=supplierDao.findById(route.getOriginId());
//							DestPlantInfo destInfo=destDao.findById(route.getDestId());
//							
//							pagedto.setRouteInstanceId(String.valueOf(routeinstancelist.get(j).getId()));
//							List<TMilestoneRecord> milestonelist=milestoneDao.findByRouteId(pagedto).getResultList();
//							if(milestonelist!=null&&milestonelist.size()>0){
//								TMilestoneRecord milesone=milestonelist.get(milestonelist.size()-1);
////								if(destInfo.getPlantNameEn().equals("SHANGHAI")){
////									entity.setaTA(milesone.getAta());
////									entity.setaTD(milesone.getAtd());
////									entity.seteTA(milesone.getEta());
////									entity.seteTD(milesone.getEtd());
////								}
////								if(supplier.getSupplierNameEn().equals("SHANGHAI")){
////									entity.setDespatch_SH_Port(milesone.getAtd());
////								}
////								if(supplier.getSupplierNameEn().equals("WUHAN")){
////									entity.setArrival_WH(milesone.getAtd());
////								}
////								if(destInfo.getPlantNameEn().equals("LUZHOU")){
////									entity.setArrival_LZ(milesone.getAta());
////								}
////								if(supplier.getSupplierNameEn().equals("CHENGDU")){
////									entity.setArrive_VCCD(milesone.getAta());
////									entity.setDespatch_LZ(milesone.getAtd());
////								}
//								
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery to POL")){
//									entity.setPickuptime(milesone.getAtd());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Oversea Customs release")){
//									entity.setCustoms(milesone.getEtd());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Arrival at POD")){
//									entity.setEtd(milesone.getEtd());
//									entity.setAtd(milesone.getAtd());
//									entity.setEta(milesone.getEta());
//									entity.setAta(milesone.getAta());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Customs release")){
//									entity.setCustoms_clearance_Finish_Date(milesone.getAtd());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Arrival at Transit Port")){
//									entity.setDespatch_SH_Port(milesone.getAtd());
//									entity.setArrival_WH(milesone.getAta());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Arrival at barge POD")){
//									entity.setArrival_LZ(milesone.getAta());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery to Dest plant")){
//									entity.setDespatch_LZ(milesone.getAtd());
//									entity.setArrive_VCCD(milesone.getAta());
//								}
//
//							}
//						}
//					}
//				}
//			}else{
//				entity.setAta("");
//			}
//			resultlist.add(entity);
//		}
//		return resultlist;
//	}
//
//	private List<FtlBarge> setFtlBarge(List<FtlBarge> list){
//		List<FtlBarge> resultlist=new ArrayList<FtlBarge>();
//		for(int i=0;i<list.size();i++){
//			FtlBarge entity=new FtlBarge();
//			try {
//				BeanUtils.copyProperties(list.get(i), entity);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if(Utils.notEmpty(entity.getAta())){
//				TTripInstance tripinstance=tripinstanceDao.findById(Long.valueOf(entity.getAta()));
//				entity.setAta("");
//				if(tripinstance!=null){
//					List<TRouteInstance> routeinstancelist=routeinstanceDao.findByTripInstanceId(tripinstance.getId());
//					if(routeinstancelist!=null&&routeinstancelist.size()>0){
//						for(int j=0;j<routeinstancelist.size();j++){
//							QueryMilestoneDto<TMilestoneRecord> pagedto=new QueryMilestoneDto<TMilestoneRecord>();
//							Pager pager=new Pager();
//							pager.setCurrentPage(1);
//							pager.setPageSize(65536);
//							RouteInfo route=routeDao.findById(Long.valueOf(routeinstancelist.get(j).getRouteId()));
//							SupplierBasicInfo supplier=supplierDao.findById(route.getOriginId());
//							DestPlantInfo destInfo=destDao.findById(route.getDestId());
//							
//							pagedto.setRouteInstanceId(String.valueOf(routeinstancelist.get(j).getId()));
//							List<TMilestoneRecord> milestonelist=milestoneDao.findByRouteId(pagedto).getResultList();
//							if(milestonelist!=null&&milestonelist.size()>0){
//								TMilestoneRecord milesone=milestonelist.get(milestonelist.size()-1);
////								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Arrival at POD")){
////									entity.setEtd(milesone.getEtd());
////									entity.setAtd(milesone.getAtd());
//////									entity.setEta(milesone.getEta());
//////									entity.setAta(milesone.getAta());
////								}
////								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Customs release")){
////									entity.setCustoms_clearance_Finish_Date(milesone.getAtd());
////								}
////								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Arrival at Transit Port")){
////									entity.setDespatch_SH_Port(milesone.getAtd());
////									entity.setArrival_WH(milesone.getAta());
////								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Arrival at barge POD")){
//									entity.setArrival_LZ(milesone.getAta());
//									entity.setEtd(milesone.getEtd());
//									entity.setAtd(milesone.getAtd());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery to Dest plant")){
//									entity.setDespatch_LZ(milesone.getAtd());
//									entity.setArrive_VCCD(milesone.getAta());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery")){
//									entity.setDelivery_date(milesone.getEtd());
//									entity.setPickup_date(milesone.getAtd());
//								}
//								if(routeinstancelist.get(j).getRouteInstanceId().substring(0,routeinstancelist.get(j).getRouteInstanceId().indexOf(".")).equals("Delivery to CFS")){
//									entity.setDelivery_date(milesone.getEtd());
//									entity.setPickup_date(milesone.getAtd());
//								}
//								
//							}
//						}
//					}
//				}
//			}else{
//				entity.setAta("");
//			}
//			resultlist.add(entity);
//		}
//		return resultlist;
//	}

	
}
