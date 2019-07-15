package com.zgc.mtl.elasticsearch.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zgc.mtl.common.util.RedisTool;
import com.zgc.mtl.elasticsearch.dao.ProductRepository;
import com.zgc.mtl.elasticsearch.request.BulkProduct;
import com.zgc.mtl.elasticsearch.service.ProductService;
import com.zgc.mtl.model.Product;
/**
 * 产品service，elasticsearch
 * @date 2019-07-10 18:08:46
 * @author yang
 */
@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RestHighLevelClient client;
	@Autowired
	private RedisTool redisTool;
	/**
	 * 创建索引
	 * @param index
	 * @throws IOException
	 */
	public void createIndex(String index) throws IOException {
		// 判断是否存在索引
		if (!existsIndex(index)) {
			CreateIndexRequest request = new CreateIndexRequest(index);
			CreateIndexResponse createIndexResponse = client.indices().create(request,RequestOptions.DEFAULT);
			System.out.println("创建索引: " + JSON.toJSONString(createIndexResponse));
		}
	}
	
	/**
	 * 判断索引是否存在
	 * @param index
	 * @return
	 * @throws IOException
	 */
	public boolean existsIndex(String index) throws IOException {
		GetIndexRequest request = new GetIndexRequest();
		request.indices(index);
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println("存在索引？: " + exists);
		return exists;
	}
	
	/**
	 * 判断库中此产品记录是否存在
	 * @param index
	 * @param type
	 * @param product
	 * @return
	 * @throws IOException
	 */
	public boolean hasProduct(String index, String type, Product product) throws IOException {
		GetRequest getRequest = new GetRequest(index, type, product.getProductId());
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		getRequest.storedFields("_none_");
		boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
		System.out.println("本产品已存在？: " + exists);
		return exists;
	}
	
	/**
	 * 增加记录
	 * @param index
	 * @param type
	 * @param product
	 * @throws IOException
	 */
	public Object add(String index, String type, Product product) throws IOException {
		IndexRequest indexRequest = new IndexRequest(index, type, product.getProductId());
		indexRequest.source(JSONObject.toJSONString(product), XContentType.JSON);
		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
		String result = indexResponse.getResult().toString();
		return result;
	}
	
	/**
	 * 获取记录信息
	 * @param index
	 * @param type
	 * @param id
	 * @return 
	 * @throws IOException
	 */
	public Product get(String index, String type, String id) throws IOException {
		GetRequest getRequest = new GetRequest(index, type, id);
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		String sourceAsString = getResponse.getSourceAsString();
		Product parseObject = JSONObject.parseObject(sourceAsString, Product.class);
		return parseObject;
	}
 
	/**
	 * 模糊搜索
	 * @param index
	 * @param type
	 * @param name
	 * @throws IOException
	 */
	public List<Product> searchByName(String index, String type, String name) throws IOException {
		BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
		boolBuilder.must(QueryBuilders.matchQuery("name", name)); // 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
		// boolBuilder.must(QueryBuilders.matchQuery("id", tests.getId().toString()));
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(boolBuilder);
		sourceBuilder.from(0);
		sourceBuilder.size(100); // 获取记录数，默认10
		sourceBuilder.fetchSource(new String[] {"name", "productId", "category", "brand", "price", "stock" },
				new String[] {}); // 第一个是获取字段，第二个是过滤的字段，默认获取全部
		SearchRequest searchRequest = new SearchRequest(index);
		searchRequest.types(type);
		searchRequest.source(sourceBuilder);
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = response.getHits();
		SearchHit[] searchHits = hits.getHits();
		List<Product> list = new ArrayList<>();
		if(searchHits != null) {
			for (SearchHit hit : searchHits) {
				String sourceAsString = hit.getSourceAsString();
				Product parseObject = JSONObject.parseObject(sourceAsString, Product.class);
				list.add(parseObject);
			}
		}
		return list;
	}
	
	/**
	 * 更新记录信息
	 * @param index
	 * @param type
	 * @param product
	 * @return 
	 * @throws IOException
	 */
	public Object update(String index, String type, Product product) throws IOException {
		product.setName(product.getName() + "updated");
		UpdateRequest request = new UpdateRequest(index, type, product.getProductId());
		request.doc(JSON.toJSONString(product), XContentType.JSON);
		UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
		Result result = updateResponse.getResult();
		return result;
	}
 
	/**
	 * 删除记录
	 * @param index
	 * @param type
	 * @param id
	 * @return 
	 * @throws IOException
	 */
	public Object delete(String index, String type, String id) throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
		DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
		Result result = response.getResult();
		return result;
	}
	
	/**
	 * 批量增加操作
	 * @throws IOException
	 */
	public Object bulkInsert(BulkProduct param) throws IOException {
		// 批量增加
		BulkRequest bulkAddRequest = new BulkRequest();
		List<Product> products = param.getProducts();
		if(products == null) {
			return "数据不全";
		}
		for(Product p : products) {
			p.setProductId(redisTool.generateSeq("mtl-es-productId","productId",  20));
		}
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			IndexRequest indexRequest = new IndexRequest(param.getIndex(), param.getType(), product.getProductId());
			indexRequest.source(JSONObject.toJSONString(product), XContentType.JSON);
			bulkAddRequest.add(indexRequest);
		}
		client.bulk(bulkAddRequest, RequestOptions.DEFAULT);
		return null;
	}
	
	/**
	 * 批量更新操作
	 * @throws IOException
	 */
	public Object bulkUpdate(BulkProduct param) throws IOException {
		// 批量更新
		BulkRequest bulkUpdateRequest = new BulkRequest();
		List<Product> products = param.getProducts();
		if(products == null) {
			return "数据不全";
		}
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			UpdateRequest updateRequest = new UpdateRequest(param.getIndex(), param.getType(), product.getProductId());
			updateRequest.doc(JSONObject.toJSONString(product), XContentType.JSON);
			bulkUpdateRequest.add(updateRequest);
		}
		client.bulk(bulkUpdateRequest, RequestOptions.DEFAULT);
		return null;
	}
	
	/**
	 * 批量删除操作
	 * @throws IOException
	 */
	public Object bulkDelete(BulkProduct param) throws IOException {
		// 批量删除
		BulkRequest bulkDeleteRequest = new BulkRequest();
		List<Product> products = param.getProducts();
		if(products == null) {
			return "数据不全";
		}
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			DeleteRequest deleteRequest = new DeleteRequest(param.getIndex(), param.getType(), product.getProductId());
			bulkDeleteRequest.add(deleteRequest);
		}
		client.bulk(bulkDeleteRequest, RequestOptions.DEFAULT);
		return null;
	}
	
	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public Product save(Product product) {
		Product save = productRepository.save(product);
		return save;
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	@Override
	public Iterable<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getByName(String name) {
		List<Product> list = new ArrayList<>();
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name);
		Iterable<Product> iterable = productRepository.search(matchQueryBuilder);
		iterable.forEach(item->list.add(item));
		logger.info("es查询getByName，结果集：{}", JSONObject.toJSONString(list));
		return list;
	}

	@Override
	public Page<Product> pageQuery(Integer pageNo, Integer pageSize, String kw) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name", kw))
				.withPageable(PageRequest.of(pageNo, pageSize)).build();
		return productRepository.search(searchQuery);
	}

}
