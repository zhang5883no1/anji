package com.zhibo.reporting.service;

import java.text.ParseException;
import java.util.List;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.service.IBaseService;
import com.xidu.framework.fileloading.dto.BaseLoadingFileProcessDto;
import com.xidu.framework.usermgnt.dto.SessionUserDto;
import com.zhibo.reporting.domain.DeviationReportDomain;
import com.zhibo.reporting.dto.Airfreight;
import com.zhibo.reporting.dto.DeviationFileLoadingProcess;
import com.zhibo.reporting.dto.DeviationInfoDto;
import com.zhibo.reporting.dto.DomesticTransport;
import com.zhibo.reporting.dto.QueryGlobalDto;

public interface ReportingService  extends IBaseService {

//	
	public void upLoadDeviationReport(final DeviationFileLoadingProcess deviationUploadDto,DeviationInfoDto<?> queryDto,SessionUserDto sessionUserDto);
//	
//	public void downLoadDeviationReport(final DeviationFileLoadingProcess deviationDownloadDto,DeviationInfoDto<?> queryDto,SessionUserDto sessionUserDto);
//
//	BasePagerDto searchShipmentReport(QueryGlobalDto queryDto)throws ParseException;
	
}
