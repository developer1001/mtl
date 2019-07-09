package com.zgc.mtl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.model.Person;
import com.zgc.mtl.service.ISysUserService;
import com.zgc.mtl.service.ITestService;

@RestController
public class SpringBootTestController {
	private static final Logger log = LoggerFactory.getLogger(SpringBootTestController.class);
	@Autowired
	ITestService testService;
	@Autowired
	ISysUserService sysUserService;
	
	@RequestMapping("bootTest")
	public String bootTest() {
//		System.out.println("spring boot start up");
		log.info("info-{}-{}","bootTest","测试");
		return "start up";
	}
	
//方法体中的参数要在前面加注释，@PathVariable，代表url中的参数
	@RequestMapping("getParam/{id}")
	public void getParam(@PathVariable("id") String id) {
		System.out.println("参数是："+id);
	}
	
	@RequestMapping("methodParam")
	public void methodParam(String age) {
		System.out.println("年龄是："+age);
	}
	
	/**
	 * 数据库测试
	 */
	@RequestMapping("getAllPersons")
	public List<Person> getAllPersons() {
		List<Person> persons = testService.getPersons();
		return persons;
	}
	
	@RequestMapping("multiTask")
	public Object multiTask() {
		Map<String, Object> map = new HashMap<>();
		map.put("map1", "map1");
		sysUserService.deleteById(11);
		new Thread(new MultiTaskTest()).start();
		return map;
	}
	
	private class MultiTaskTest  implements Runnable {
		@Override
		public void run(){
			try {
				task();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void task() throws InterruptedException {
			long s = System.currentTimeMillis();
			System.out.println("开始"+System.currentTimeMillis());
			Thread.sleep(10000);
			sysUserService.add(null);
			System.out.println("结束,耗时："+(System.currentTimeMillis() - s) + "ms");

		}

	}
	
}
