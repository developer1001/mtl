package com.zgc.mtl.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
/**
 * 产品 ,elasticsearch测试用
 * @date 2019-07-10 17:13:10
 * @author yang
 */
@Document(indexName="db-product", type="product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8683197073221330402L;
	/**
	 * 产品编号 
	 */
	@Id
//	@Field(type = FieldType.Keyword)
	private String productId;
	/**
	 * 产品名称
	 */
//	@Field(store = true, analyzer = "ik_max_word", type = FieldType.Text, searchAnalyzer = "ik_max_word")
	private String name;
	/**
	 * 分类
	 */
//	@Field(type = FieldType.Keyword)
	private String category;
	/**
	 * 品牌
	 */
//	@Field(type = FieldType.Keyword,index = false)
	private String brand;
	/**
	 * 价格
	 */
//	@Field(type = FieldType.Float)
	private BigDecimal price;
	/**
	 * 库存
	 */
//	@Field(type = FieldType.Integer)
	private Integer stock;
	/**
	 * 上市日期
	 */
//	@Field(type = FieldType.Date)
	private Date launchDate;
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
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", category=" + category + ", brand=" + brand
				+ ", price=" + price + ", stock=" + stock + ", launchDate=" + launchDate + "]";
	}
	
}
