package com.zgc.mtl.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.zgc.mtl.common.util.RedisTool;
/**
 * 产品 ,elasticsearch测试用
 * @date 2019-07-10 17:13:10
 * @author yang
 */
@Document(indexName="product", type="product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8683197073221330402L;
	@Id
	private String productId;
	private String name;
	private String category;
	private String brand;
	private BigDecimal price;
	private Integer stock;
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
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", category=" + category + ", brand=" + brand
				+ ", price=" + price + ", stock=" + stock + "]";
	}
	
}
