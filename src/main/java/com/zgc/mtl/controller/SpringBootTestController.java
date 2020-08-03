package com.zgc.mtl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.zgc.mtl.common.enu.Week;
import com.zgc.mtl.model.Person;
import com.zgc.mtl.service.ISysUserService;
import com.zgc.mtl.service.ITestService;

@RestController
public class SpringBootTestController {
	@Autowired
	ITestService testService;
	@Autowired
	ISysUserService sysUserService;
	
	@RequestMapping("bootTest")
	public String bootTest() {
		return "start up";
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
	
}
