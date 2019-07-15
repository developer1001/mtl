package com.zgc.mtl.elasticsearch.request;

import java.util.List;

import com.zgc.mtl.model.Product;

/**
 * 批量产品对象
 * @date 2019-07-12 20:51:48
 * @author yang
 */
public class BulkProduct {
	private String index;
	private String type;
	private List<Product> products;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "BulkAddProduct [index=" + index + ", type=" + type + ", products=" + products + "]";
	}
	
}
