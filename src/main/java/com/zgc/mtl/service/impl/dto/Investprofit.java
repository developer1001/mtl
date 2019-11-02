package com.zgc.mtl.service.impl.dto;

public class Investprofit {
	private String orderId;
	private String productName;
	private String profitDay;
	private Integer profit;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProfitDay() {
		return profitDay;
	}
	public void setProfitDay(String profitDay) {
		this.profitDay = profitDay;
	}
	public Integer getProfit() {
		return profit;
	}
	public void setProfit(Integer profit) {
		this.profit = profit;
	}
}
