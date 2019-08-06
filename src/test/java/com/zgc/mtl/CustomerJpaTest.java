package com.zgc.mtl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.zgc.mtl.common.util.AliMsgUtil;
import com.zgc.mtl.dao.jpa.CustomersRepository;
import com.zgc.mtl.model.Customer;

/**
 * spring jpa测试
 * @date 2019-08-05 09:47:51
 * @author yang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerJpaTest {
	@Autowired
	private CustomersRepository customerRepository;
	
	@Test
	public void insert() {
		Customer c = new  Customer();
		c.setName("奥通");
		c.setPhone("400-5623-5844");
		c.setCreatedBy("admin");
		c.setCreatedDate(new Date());
		c.setUpdatedBy("admin");
		c.setUpdatedDate(new Date());
		customerRepository.save(c);
	}
	
	@Test
	public void findAll() {
		List<Customer> findAll = customerRepository.findAll();
		System.out.println(findAll.size());
	}
	
	@Test
	public void update() {
		Customer c = new  Customer();
		c.setId("402819856c5fa457016c5fa48b690000");
		c.setName("奥通");
		customerRepository.save(c);
	}
	
	@Test
	public void delete() {
		customerRepository.deleteById("402819856c5fa457016c5fa48b690000");
	}
	
	@Test
	public void batchInsert() {
		ArrayList<Customer> arrayList = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			Customer c = new  Customer();
			c.setName(AliMsgUtil.randomNum(3) + "");
			c.setPhone(AliMsgUtil.randomNum(15) + "");
			c.setCreatedBy("admin");
			c.setCreatedDate(new Date());
			c.setUpdatedBy("admin");
			c.setUpdatedDate(new Date());
			arrayList.add(c);
		}
		customerRepository.saveAll(arrayList);
	}
	
	@Test
	public void findOne() {
		customerRepository.findById("402819856c5fa457016c5fa48b690000").orElseGet(Customer:: new);
	}
	
	
	@Test
	public void pageQuery() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "name", "phone");
		Customer customer = new Customer();
		customer.setSex("F");
		Example<Customer> example = Example.of(customer);
		Page<Customer> findAll = customerRepository.findAll(example,page);
		System.out.println(JSONObject.toJSONString(findAll));
	}
	
	@Test
	public void findTop3ByName() {
		String name = "129";
		List<Customer> findByNameLike = customerRepository.findTop3ByName(name);
		System.out.println(JSONObject.toJSONString(findByNameLike));
	}
	
	@Test
	public void findByPhone() {
		String phone = "126681451368906";
		List<Customer> findByPhone = customerRepository.phoneNumber(phone);
		System.out.println(JSONObject.toJSONString(findByPhone));
	}
	
	@Test
	public void xiggBie() {
		String sex = "F";
		List<Customer> res = customerRepository.xingBie(sex);
		System.out.println(JSONObject.toJSONString(res));
	}
	
	@Test
	public void uptPho() {
		String id = "402819856c5fd537016c5fd569d20000";
		String phone = String.valueOf(AliMsgUtil.randomNum(11));
		customerRepository.uptPho(phone, id);
	}
}
