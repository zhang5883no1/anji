package com.zhibo.reporting.dto;

import com.xidu.framework.excel.dto.BaseImportProcessDto;

public class QueryGlobalDto<T> extends BaseImportProcessDto<T> {


	private Long id;// ID
	
	private String transportation_mode;
	
	private String ship_date_from;
	
	private String ship_date_to;
	
	private String container_number;
	
	private String invoice_number;
	
	private String part_number;
	
	private String supplier_code;
	
	private String bl;
	
	private String awb;
	
	private String status;
	
	private String domestic;
	
	private String international;
	
	private String deviation;

	private String isclosed;
	
	private String destPlantCode;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransportation_mode() {
		return transportation_mode;
	}

	public void setTransportation_mode(String transportation_mode) {
		this.transportation_mode = transportation_mode;
	}

	public String getContainer_number() {
		return container_number;
	}

	public void setContainer_number(String container_number) {
		this.container_number = container_number;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public String getPart_number() {
		return part_number;
	}

	public void setPart_number(String part_number) {
		this.part_number = part_number;
	}

	public String getSupplier_code() {
		return supplier_code;
	}

	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getAwb() {
		return awb;
	}

	public void setAwb(String awb) {
		this.awb = awb;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDomestic() {
		return domestic;
	}

	public void setDomestic(String domestic) {
		this.domestic = domestic;
	}

	public String getInternational() {
		return international;
	}

	public void setInternational(String international) {
		this.international = international;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public String getShip_date_from() {
		return ship_date_from;
	}

	public void setShip_date_from(String ship_date_from) {
		this.ship_date_from = ship_date_from;
	}

	public String getShip_date_to() {
		return ship_date_to;
	}

	public void setShip_date_to(String ship_date_to) {
		this.ship_date_to = ship_date_to;
	}

	public String getIsclosed() {
		return isclosed;
	}

	public void setIsclosed(String isclosed) {
		this.isclosed = isclosed;
	}

	public String getDestPlantCode() {
		return destPlantCode;
	}

	public void setDestPlantCode(String destPlantCode) {
		this.destPlantCode = destPlantCode;
	}
	
	
}
