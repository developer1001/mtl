package com.zgc.mtl.controller.requestParam;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 请求明细
 * @date 2019-10-30 10:25:15
 * @author yang
 */
public class InvestProfitDetail {
	/**
	 * 订单号
	 */
	private String[] orderId;
	/**
	 * 最近多少天
	 */
	private Integer recentDays;
	/**
	 * 开始日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date from;
	/**
	 * 终止日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date to;
	public String[] getOrderId() {
		return orderId;
	}
	public void setOrderId(String[] orderId) {
		this.orderId = orderId;
	}
	public Integer getRecentDays() {
		return recentDays;
	}
	public void setRecentDays(Integer recentDays) {
		this.recentDays = recentDays;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
}
