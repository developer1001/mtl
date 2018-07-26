package com.zgc.mtl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zgc.mtl.dao.FirstDao;
import com.zgc.mtl.model.Person;
import com.zgc.mtl.service.ITestService;

@Service
public class TestServiceImpl implements ITestService {

	@Autowired
	FirstDao testDao;
	@Override
	public void printString() {
		// TODO Auto-generated method stub
		System.out.println("service测试方法，无数据库连接的执行");
	}

	@Override
	public List<Person> getPersons() {
		// TODO Auto-generated method stub
		return testDao.getAllPersons();
	}

}
