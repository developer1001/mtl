package com.zgc.mtl.elasticsearch.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
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
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
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
import com.zgc.mtl.common.enu.StrEnum;
import com.zgc.mtl.common.util.RedisTool;
import com.zgc.mtl.elasticsearch.dao.ProductRepository;
import com.zgc.mtl.elasticsearch.request.BulkProduct;
import com.zgc.mtl.elasticsearch.response.ProductDto;
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
			logger.info("创建索引:{},创建结果：{}", index, JSONObject.toJSONString(createIndexResponse));
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
		logger.info("索引：{}存在？{}",index, exists);
		return exists;
	}
	
	/**
	 * 判断库中此产品记录是否存在
	 * @param index
	 * @param type
	 * @param itemId
	 * @return
	 * @throws IOException
	 */
	public boolean hasRecord(String index, String type, String itemId) throws IOException {
		GetRequest getRequest = new GetRequest(index, type, itemId);
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		getRequest.storedFields("_none_");
		boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
		logger.info("id为{}的文档，已存在？: {}", exists);
		return exists;
	}
	
	public Object insert(BulkProduct param) throws IOException {
		if(param.getProducts() == null) {
			return "未提供文档数据，无法保存新文档";
		}
		String index = param.getIndex();
		String type = param.getType();
		//没有索引先创建索引
		createIndex(index);
		Product product = param.getProducts().get(0);
		product.setProductId(redisTool.generateSeq(StrEnum.PRODUCTID,"productId",  20));
		product.setLaunchDate(new Date());
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
 
	public List<ProductDto> searchList(Map<String, Object> param) throws Exception {
		String index = (String)param.get("index");
		String type = (String)param.get("type");
		int from =  param.get("pageNo") == null ? 0 : ((Integer.parseInt(param.get("pageNo").toString())-1) * 10);
		int size = param.get("pageSize") == null ? 10 :  Integer.parseInt(param.get("pageSize").toString());
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		//查询条件
		BoolQueryBuilder boolBuilder = this.productBoolQuery(param);
		sourceBuilder.query(boolBuilder);
		//排序条件
		List<SortBuilder> orderList = this.productSort(param);
		if(orderList.size() > 0) {
			for(SortBuilder sortBuilder : orderList) {
				sourceBuilder.sort(sortBuilder);
			}
		}
		//是否高亮
		boolean isHighLight = param.get("highLightName") != null;
		if(isHighLight) {
			HighlightBuilder highlightBuilder = new HighlightBuilder().field("name").requireFieldMatch(true);
			Map<String, String> highLightMap  = (Map<String, String>) param.get("highLightName");
			highlightBuilder.preTags(highLightMap.get("prefix"));
			highlightBuilder.postTags(highLightMap.get("suffix"));
			sourceBuilder.highlighter(highlightBuilder);
		}
		sourceBuilder.from(from);
		sourceBuilder.size(size); // 获取记录数，默认10
//		sourceBuilder.fetchSource(new String[] {"name", "productId", "category", "brand", "price", "stock", "launchDate"},
//				new String[] {}); // 第一个是获取字段，第二个是过滤的字段，默认获取全部
		SearchRequest searchRequest = new SearchRequest(index);
		searchRequest.types(type);
		searchRequest.source(sourceBuilder);
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHits hits = response.getHits();
		SearchHit[] searchHits = hits.getHits();
		List<ProductDto> list = new ArrayList<>();
		if(searchHits != null) {
			for (SearchHit hit : searchHits) {
				String sourceAsString = hit.getSourceAsString();
				ProductDto parseObject = JSONObject.parseObject(sourceAsString, ProductDto.class);
				if(isHighLight) {
					Map<String, HighlightField> highlightFields = hit.getHighlightFields();
					HighlightField highlightNameField = highlightFields.get("name");
					if(highlightNameField != null) {
						Text[] fragments = highlightNameField.fragments();
						String highlightName = "";
						for(Text text : fragments) {
							highlightName += text;
						}
						parseObject.setHighLightName(highlightName);
					}
				}
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
	public Object update(BulkProduct param) throws IOException {
		if(param.getProducts() == null) {
			return "需更新的文档数据未提供";
		}
		Product product = param.getProducts().get(0);
		String index = (String)param.getIndex();
		String type = (String)param.getType();
		Product search = this.get(index, type, product.getProductId());
		if(search == null) {
			return "文档已不存在，请勿更新";
		}
//		product.setLaunchDate(new Date());
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
		List<Product> products = param.getProducts();
		if(products == null) {
			return "数据不全";
		}
		for(Product p : products) {
			p.setProductId(redisTool.generateSeq(StrEnum.PRODUCTID,"productId",  20));
			p.setLaunchDate(new Date());
		}
		//没有索引先创建索引
		createIndex(param.getIndex());
		// 批量增加
		BulkRequest bulkAddRequest = new BulkRequest();
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
//			product.setLaunchDate(new Date());
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

	/**
	 * 字段匹配
	 * @param param
	 * @return
	 * @throws Exception
	 */
	private BoolQueryBuilder productBoolQuery(Map<String, Object> param) throws Exception {
		BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
		//blurry下是一个map集合，支持模糊搜索，搜索名称等时，可以模糊匹配出多个相似的名称
		if(param.get("blurry") != null) {
			Map<String,Object> conditions = (Map<String, Object>) param.get("blurry");
			Set<String> keySet = conditions.keySet();
			if(keySet.size() > 0) {
				for(String key : keySet) {
					boolBuilder.must(QueryBuilders.matchQuery(key, conditions.get(key))); 
				}
			}
		}
		//exact下是一个map集合，支持精确搜索，100%全内容匹配，只能精确匹配出一个
		if(param.get("exact") != null) {
			Map<String,Object> conditions = (Map<String, Object>) param.get("exact");
			Set<String> keySet = conditions.keySet();
			if(keySet.size() > 0) {
				for(String key : keySet) {
					boolBuilder.must(QueryBuilders.termQuery(key, conditions.get(key))); 
				}
			}
		}
		//范围条件，range下是一个map集合
		if(param.get("range") != null) {
			Map<String,Object> range = (Map<String, Object>) param.get("range");
			Set<String> rangeKeys = range.keySet();
			if(rangeKeys.size() > 0) {
				for(String key : rangeKeys) {
					if(key.equals("date")) {
						//时间范围的设定
						Map<String,String> date = (Map<String, String>) range.get("date");
						if(date != null) {
							String dateFrom = date.get("from");
							String dateTo = date.get("to");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							RangeQueryBuilder rangequerybuilder = QueryBuilders
									.rangeQuery("launchDate")
									.from(sdf.parse(dateFrom).getTime()).to(sdf.parse(dateTo).getTime());
							boolBuilder.must(rangequerybuilder); 
						}
					}
					else {
						Map<String, Object> stock = (Map<String, Object>) range.get(key);
						if(stock != null) {
							Object stockFrom = stock.get("from");
							Object stockTo = stock.get("to");
							RangeQueryBuilder rangequerybuilder1 = QueryBuilders.rangeQuery(key)
									.from(stockFrom).to(stockTo);
							boolBuilder.must(rangequerybuilder1); 
						}
					}
				}
			}
		}
		return boolBuilder;
	}
	
	/**
	 * 字段排序
	 * @param param
	 * @return
	 */
	private List<SortBuilder> productSort(Map<String, Object> param) {
		//排序条件，order下是一个map集合
		List<SortBuilder> sortList = new ArrayList<>();
		Map<String,Object> orders = (Map<String, Object>) param.get("order");
		if(orders != null) {
			Set<String> keySet = orders.keySet();
			if(keySet.size() > 0) {
				//_score匹配度放在第一位，等级最高，默认倒叙排列
				ScoreSortBuilder scoreSort = SortBuilders.scoreSort();
				sortList.add(scoreSort);
				//对其他的字段再排序
				for(String key : keySet) {
					FieldSortBuilder sortBuilder = null;
					if(orders.get(key).equals("DESC")) {
						sortBuilder = SortBuilders.fieldSort(key).order(SortOrder.DESC);
					}
					else {
						sortBuilder = SortBuilders.fieldSort(key).order(SortOrder.ASC);
					}
					sortList.add(sortBuilder);
				}
			}
		}
		return sortList;
	}
}
