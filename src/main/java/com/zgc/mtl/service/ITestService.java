package com.zgc.mtl.service;

import java.util.List;

import com.zgc.mtl.model.Person;

public interface ITestService {
	//打印一个字符串
	void printString ();
	/**
	 * 数据库测试
	 * @return
	 */
	List<Person> getPersons(); 
}
