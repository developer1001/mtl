package com.zgc.mtl.service.impl.dto;
/**
 * 投资的订单信息
 * @date 2019-11-09 12:42:16
 * @author yang
 */
public class InvestOrders {
	private String orderId;
	private String shortName;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	@Override
	public String toString() {
		return "InvestOrders [orderId=" + orderId + ", shortName=" + shortName + "]";
	}
	
}
