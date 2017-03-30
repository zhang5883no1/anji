package com.zhibo.mainTain.dto;

public class BillDto {

	private Long id;
	private String openTime;
	private String type;//现价买入/到价买入/到价卖出/限价买入/限价卖出
	private int position;//仓位 %
	private String product;//商品
	private int openPrice;//开仓价
	private int stopLossPrice;//止损价
	private int stopGainPrice;//止盈价
	private String closingTime;//平仓时间
	private int closingPrice;//平仓价
	private int gainPercent;//获利点数
	private String detail;//备注
	private String teacherName;//分析师
	private int status;//
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(int openPrice) {
		this.openPrice = openPrice;
	}
	public int getStopLossPrice() {
		return stopLossPrice;
	}
	public void setStopLossPrice(int stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
	}
	public int getStopGainPrice() {
		return stopGainPrice;
	}
	public void setStopGainPrice(int stopGainPrice) {
		this.stopGainPrice = stopGainPrice;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public int getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(int closingPrice) {
		this.closingPrice = closingPrice;
	}
	public int getGainPercent() {
		return gainPercent;
	}
	public void setGainPercent(int gainPercent) {
		this.gainPercent = gainPercent;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
