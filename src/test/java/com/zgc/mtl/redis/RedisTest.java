package com.zgc.mtl.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zgc.mtl.common.util.RedisTool;
import com.zgc.mtl.model.Person;

/**
 * redis测试
 * @date 2019-07-10 14:57:16
 * @author yang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisTool redisTool;
	
	@Test
	public void getPerson() {
		Person obj = redisTool.getObj("9527", Person.class);
		System.out.println(obj.getId()+ "," + obj.getAge() + "," + obj.getName());
		assert(obj.getName().equals("黄师傅"));
	}
	
	@Test
	public void getPerson2() {
		Person obj = redisTool.getObj("9527", Person.class);
		System.out.println(obj.getId()+ "," + obj.getAge() + "," + obj.getName());
		assert(obj.getName().equals("黄师"));
	}
	
	@Test
	public void getPerson3() {
		Person obj = redisTool.getObj("9527", Person.class);
		System.out.println(obj.getId()+ "," + obj.getAge() + "," + obj.getName());
		assert(obj.getAge().equals("2"));
	}
}
