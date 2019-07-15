package com.zgc.mtl.elasticsearch;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * es相关方法的测试
 * @date 2019-07-11 11:18:09
 * @author yang
 */

import com.zgc.mtl.elasticsearch.service.ProductService;
import com.zgc.mtl.model.Product;
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
	@Autowired
	private ProductService productService;
	
	@Before
	public void es() {
	}
	@Test
	public void count() {
		long count = productService.count();
		assert(count == 1);
	}
	
	@Test
	public void add() {
		Product product = new Product();
		product.setBrand("卫龙");
		product.setCategory("面制食品");
		product.setName("亲嘴烧");
		product.setProductId("565232558161");
		product.setPrice(new BigDecimal(2));
		product.setStock(12652);
		Product save = productService.save(product);
	}
}
