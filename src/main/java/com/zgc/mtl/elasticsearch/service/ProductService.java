package com.zgc.mtl.elasticsearch.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.zgc.mtl.elasticsearch.request.BulkProduct;
import com.zgc.mtl.model.Product;

/**
 * 产品
 * @date 2019-07-10 17:43:51
 * @author yang
 */
public interface ProductService {
	/**
	 * 创建索引
	 * @param index
	 * @throws IOException
	 */
	void createIndex(String index) throws IOException;
	
	/**
	 * 判断库中此产品记录是否存在
	 * @param index 索引
	 * @param type 类型
	 * @param itemId id
	 * @return
	 * @throws IOException
	 */
	public boolean hasRecord(String index, String type, String itemId) throws IOException;
	
	/**
	 * 增加记录
	 * @param param
	 * @return
	 * @throws IOException
	 */
	Object insert(BulkProduct param) throws IOException;
	
	/**
	 * 获取记录信息
	 * @param index
	 * @param type
	 * @param id
	 * @throws IOException
	 */
	Product get(String index, String type, String id) throws IOException;
	
	/**
	 * 更新记录信息
	 * @param param
	 * @return
	 * @throws IOException
	 */
	Object update(BulkProduct param) throws IOException;
	
	/**
	 * 条件模糊搜索,返回多个匹配结果
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public List<Product> searchList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 删除记录
	 * @param index
	 * @param type
	 * @param id
	 * @throws IOException
	 */
	Object delete(String index, String type, String id) throws IOException;
	
	/**
	 * 批量插入
	 * @param param
	 * @return
	 * @throws IOException
	 */
	Object bulkInsert(BulkProduct param) throws IOException;
	
	/**
	 * 批量更新
	 * @param param
	 * @return
	 * @throws IOException
	 */
	Object bulkUpdate(BulkProduct param) throws IOException;
	
	/**
	 * 批量删除
	 * @param param
	 * @return
	 * @throws IOException
	 */
	Object bulkDelete(BulkProduct param) throws IOException;
	/**
	 * 数量
	 * @return
	 */
    long count();
    /**
     * 存储
     * @param product
     * @return
     */
    Product save(Product product);
    /**
     * 删除
     * @param product
     */
    void delete(Product product);
    /**
     * 查找所有
     * @return
     */
    Iterable<Product> getAll();
    /**
     * 名称模糊查询
     * @param name
     * @return
     */
    List<Product> getByName(String name);
    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param kw
     * @return
     */
    Page<Product> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
