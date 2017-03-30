package com.zhibo.reporting.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Airfreight {
	private String items;
	private String pickup_date;
	private String order_no;
	private String supplier_name;
	private String plant_name;
	private String origin;
	private String destination;
	private BigDecimal package_quantity;
	private String gross_weight;
	private BigDecimal chargable_weight;
	private String volume;
	private String hawb;
	private String mawb;
	private String etd;
	private String atd;
	private String eta;
	private String ata;
	private String comment;
	
	
	private String service_level;
	private String booking_date;
	private String incoterm;
	private String flight;
	private String clearance_time;
	
	private String atavccd;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getPlant_name() {
		return plant_name;
	}
	public void setPlant_name(String plant_name) {
		this.plant_name = plant_name;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getGross_weight() {
		return gross_weight;
	}
	public void setGross_weight(String gross_weight) {
		this.gross_weight = gross_weight;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getEtd() {
		return etd;
	}
	public void setEtd(String etd) {
		this.etd = etd;
	}
	public String getAtd() {
		return atd;
	}
	public void setAtd(String atd) {
		this.atd = atd;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPickup_date() {
		return pickup_date;
	}
	public void setPickup_date(String pickup_date) {
		this.pickup_date = pickup_date;
	}
	public BigDecimal getPackage_quantity() {
		return package_quantity;
	}
	public void setPackage_quantity(BigDecimal package_quantity) {
		this.package_quantity = package_quantity;
	}
	public BigDecimal getChargable_weight() {
		return chargable_weight;
	}
	public void setChargable_weight(BigDecimal chargable_weight) {
		this.chargable_weight = chargable_weight;
	}
	public String getHawb() {
		return hawb;
	}
	public void setHawb(String hawb) {
		this.hawb = hawb;
	}
	public String getMawb() {
		return mawb;
	}
	public void setMawb(String mawb) {
		this.mawb = mawb;
	}
	public String getService_level() {
		return service_level;
	}
	public void setService_level(String service_level) {
		this.service_level = service_level;
	}
	public String getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getClearance_time() {
		return clearance_time;
	}
	public void setClearance_time(String clearance_time) {
		this.clearance_time = clearance_time;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getAtavccd() {
		return atavccd;
	}
	public void setAtavccd(String atavccd) {
		this.atavccd = atavccd;
	}
	
	
}
