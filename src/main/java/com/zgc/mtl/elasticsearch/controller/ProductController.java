package com.zgc.mtl.elasticsearch.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.elasticsearch.request.BulkProduct;
import com.zgc.mtl.elasticsearch.response.ProductDto;
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
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("insert")
	public Object insert(@RequestBody BulkProduct param) throws IOException {
		Object add = productService.insert(param);
		return add;
	}
	
	/**
	 * 检验文档是否存在
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("hasRecord")
	public boolean hasRecord(@RequestBody Map<String, Object> param) throws IOException {
		String index = (String)param.get("index");
		String type = (String)param.get("type");
		String id = (String)param.get("itemId");
		boolean hasProduct = productService.hasRecord(index, type, id);
		return hasProduct;
	}
	
	/**
	 * 根据id查询某个文档是否存在
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("search")
	public Product selectByPrimarykey(@RequestBody Map<String, Object> param) throws IOException {
		String index = (String)param.get("index");
		String type = (String)param.get("type");
		String id = (String)param.get("itemId");
		Product product = productService.get(index, type, id);
		return product;
	}
	
	/**
	 * 条件模糊搜索,返回多个匹配结果
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("searchList")
	public List<ProductDto> searchList(@RequestBody Map<String, Object> param) throws Exception {
		List<ProductDto> list = productService.searchList(param);
		return list;
	}
	
	/**
	 * 更新文档
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("update")
	public Object update(@RequestBody BulkProduct param) throws IOException {
		Object result = productService.update(param);
		return result;
	}
	
	/**
	 * 删除一个文档
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("delete")
	public Object delete(@RequestBody Map<String, String> param) throws IOException {
		Object result = productService.delete(param.get("index"), param.get("type"), param.get("itemId"));
		return result;
	}
	
	/**
	 * 批量插入
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("bulkInsert")
	public Object bulkInsert(@RequestBody BulkProduct param) throws IOException {
		Object result = productService.bulkInsert(param);
		return result;
	}
	
	/**
	 * 批量更新
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("bulkUpdate")
	public Object bulkUpdate(@RequestBody BulkProduct param) throws IOException {
		Object result = productService.bulkUpdate(param);
		return result;
	}
	
	/**
	 * 批量删除
	 * @param param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("bulkDelete")
	public Object bulkDelete(@RequestBody BulkProduct param) throws IOException {
		Object result = productService.bulkDelete(param);
		return result;
	}
}
