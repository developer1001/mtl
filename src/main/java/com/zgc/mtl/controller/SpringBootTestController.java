package com.zgc.mtl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.zgc.mtl.common.enu.Week;
import com.zgc.mtl.common.util.RedisTool;
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
	@Autowired
	private RedisTool redisTool;
	
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
//		String generateSeq = redisTool.generateSeq("mtl-seq", "mtl", 10);
//		log.info("当前mtl项目序列号是：{}",generateSeq);
//		System.out.println(generateSeq);
		Person p = new Person();
		p.setId(9527);
		p.setAge("22");
		p.setName("黄师傅");
		redisTool.saveObj("9527", p);
		Person obj = redisTool.getObj("9527", Person.class);
		System.out.println(obj.getId()+ "," + obj.getAge() + "," + obj.getName());
	}
	
	/**
	 * pageHelper使用示例，可以分页加排序
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("selectUser")
	public Object selectUser(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize, "id desc");
		List<Person> persons = testService.getPersons();
		return persons;
	}
	
	/**
	 * 数据库测试
	 */
	@RequestMapping("getAllPersons")
	public List<Person> getAllPersons() {
		List<Person> persons = testService.getPersons();
		return persons;
	}
	
	@RequestMapping("week")
	public String getWeek(Week day) {
		String day2 = Week.getDay(day);
		return day2;
	}
	
	/**
	 * 热部署测试接口
	 * @param id
	 * @return
	 */
	@RequestMapping("devTools")
	public Object print(int id) {
		Person user = testService.selectById(id);
		return user;
	}
}
