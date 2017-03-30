package com.zhibo.reporting.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DomesticTransport {
	private String items;
	private String supplier_code;
	private String supplier_name;
	private String ctiy;
	private String part_no;
	private String part_name;
	private BigDecimal part_quantity;
	private BigDecimal actual_quantity;
	private BigDecimal variance;
	private String delivery_date;
	private String pickup_date;
	private String eta;
	private String ata;
	private String transport_time;
	private String comments;
	public String getSupplier_code() {
		return supplier_code;
	}
	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getCtiy() {
		return ctiy;
	}
	public void setCtiy(String ctiy) {
		this.ctiy = ctiy;
	}
	public String getPart_no() {
		return part_no;
	}
	public void setPart_no(String part_no) {
		this.part_no = part_no;
	}
	public String getPart_name() {
		return part_name;
	}
	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}
	public BigDecimal getPart_quantity() {
		return part_quantity;
	}
	public void setPart_quantity(BigDecimal part_quantity) {
		this.part_quantity = part_quantity;
	}
	public BigDecimal getActual_quantity() {
		return actual_quantity;
	}
	public void setActual_quantity(BigDecimal actual_quantity) {
		this.actual_quantity = actual_quantity;
	}
	public BigDecimal getVariance() {
		return variance;
	}
	public void setVariance(BigDecimal variance) {
		this.variance = variance;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getPickup_date() {
		return pickup_date;
	}
	public void setPickup_date(String pickup_date) {
		this.pickup_date = pickup_date;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getAta() {
		return ata;
	}
	public void setAta(String ata) {
		this.ata = ata;
	}
	public String getTransport_time() {
		return transport_time;
	}
	public void setTransport_time(String transport_time) {
		this.transport_time = transport_time;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}

}
