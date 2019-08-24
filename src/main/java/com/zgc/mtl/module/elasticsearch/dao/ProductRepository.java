package com.zgc.mtl.module.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.zgc.mtl.model.Product;
/**
 * es 产品
 * @date 2019-07-10 17:40:42
 * @author yang
 */
@Component
public interface ProductRepository extends ElasticsearchRepository<Product, String>{

}
