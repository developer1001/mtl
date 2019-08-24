package com.zgc.mtl.module.elasticsearch.response;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 产品 DTO,elasticsearch测试用,
 * 与Product区别是：多了一个高亮显示的字段(针对name)，
 * 用于模糊搜索返回时，提供高亮效果
 * @date 2019-07-17 15:40:13
 * @author yang
 */
public class ProductDto{
	/**
	 * 产品编号 
	 */
	private String productId;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 分类
	 */
	private String category;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 * 上市日期
	 */
	private Date launchDate;
	/**
	 * 搜索字段高亮显示
	 */
	private String highLightName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public String getHighLightName() {
		return highLightName;
	}
	public void setHighLightName(String highLightName) {
		this.highLightName = highLightName;
	}
	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", name=" + name + ", category=" + category + ", brand=" + brand
				+ ", price=" + price + ", stock=" + stock + ", launchDate=" + launchDate + ", highLightName="
				+ highLightName + "]";
	}
	
}
