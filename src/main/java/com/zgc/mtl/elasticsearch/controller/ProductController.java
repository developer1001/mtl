package com.zgc.mtl.elasticsearch.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.elasticsearch.request.BulkProduct;
import com.zgc.mtl.elasticsearch.service.ProductService;
import com.zgc.mtl.model.Product;

/**
 * es操作产品controller类
 * @date 2019-07-12 16:58:05
 * @author yang
 */
@RestController
@RequestMapping("product")
public class ProductController{
	@Autowired
	private ProductService productService;
	
	/**
	 * 新增一个记录到es中
	 * @throws IOException
	 */
	@RequestMapping("insert")
	public Object add(BulkProduct param) throws IOException {
		String index = "db-product";
		String type = "product";
		//没有索引先创建索引
		productService.createIndex(index);
		Product product = new Product();
		product.setBrand("卫龙");
		product.setCategory("面制食品");
		product.setName("亲嘴烧");
		product.setProductId(UUID.randomUUID().toString());
		product.setPrice(new BigDecimal(2));
		product.setStock(12652);
		Object add = productService.add(index, type, product);
		return add;
	}
	
	/**
	 * 检验某产品是否存在
	 * @throws IOException
	 */
	@RequestMapping("hasRecord")
	public boolean hasRecord() throws IOException {
		String index = "db-product";
		String type = "product";
		Product product = new Product();
		product.setBrand("卫龙");
		product.setCategory("面制食品");
		product.setName("亲嘴烧");
		product.setProductId("565232558161");
		product.setPrice(new BigDecimal(2));
		product.setStock(12652);
		boolean hasProduct = productService.hasProduct(index, type, product);
		return hasProduct;
	}
	
	/**
	 * 查询
	 * @throws IOException
	 */
	@RequestMapping("getDoc")
	public Product selectByPrimarykey(String id) throws IOException {
		String index = "db-product";
		String type = "product";
		Product product = productService.get(index, type, id);
		return product;
	}
	
	/**
	 * 根据名字模糊搜索
	 * @param name
	 * @throws IOException
	 */
	@RequestMapping("getDocList")
	public List<Product> searchByName(Map<String, Object> param) throws IOException {
		String index = (String)param.get("index");
		String type = (String)param.get("type");
		String name = (String)param.get("name");
		List<Product> list = productService.searchByName(index, type, name);
		return list;
	}
	
	/**
	 * 更新
	 * @throws IOException
	 */
	@RequestMapping("update")
	public Object update() throws IOException {
		String productId = "565232558161";
		Product search = selectByPrimarykey(productId);
		if(search == null) {
			return "文档已不存在，请勿更新";
		}
		String index = "db-product";
		String type = "product";
		Product product = new Product();
		product.setBrand("卫龙");
		product.setCategory("面制食品");
		product.setName("亲嘴烧");
		product.setProductId(productId);
		product.setPrice(new BigDecimal(2));
		product.setStock(12652);
		Object result = productService.update(index, type, product);
		return result;
	}
	
	/**
	 * 删除
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("delete")
	public Object delete(@RequestBody Map<String, String> param) throws IOException {
		Object result = productService.delete(param.get("index"), param.get("type"), param.get("productId"));
		return result;
	}
	
	@RequestMapping("bulkInsert")
	public Object bulkInsert(@RequestBody BulkProduct param) throws IOException {
		Object result = productService.bulkInsert(param);
		return result;
	}
	
	@RequestMapping("bulkUpdate")
	public Object bulkUpdate(@RequestBody BulkProduct param) throws IOException {
		Object result = productService.bulkUpdate(param);
		return result;
	}
	
	@RequestMapping("bulkDelete")
	public Object bulkDelete(@RequestBody BulkProduct param) throws IOException {
		Object result = productService.bulkDelete(param);
		return result;
	}
}
