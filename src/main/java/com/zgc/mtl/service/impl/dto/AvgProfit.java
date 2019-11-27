package com.zgc.mtl.service.impl.dto;
/**
 * 平均收益
 * @date 2019-11-02 14:58:25
 * @author yang
 */
public class AvgProfit {
	private String orderId;
	private String name;
	private String dayAvg;
	private String tenThousandDayAvg;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDayAvg() {
		return dayAvg;
	}
	public void setDayAvg(String dayAvg) {
		this.dayAvg = dayAvg;
	}
	public String getTenThousandDayAvg() {
		return tenThousandDayAvg;
	}
	public void setTenThousandDayAvg(String tenThousandDayAvg) {
		this.tenThousandDayAvg = tenThousandDayAvg;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
