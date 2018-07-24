package com.zgc.mtl.service.impl;

import com.zgc.mtl.service.ITestService;

public class TestServiceImpl implements ITestService {

	@Override
	public void printString() {
		// TODO Auto-generated method stub
		System.out.println("service测试方法，无数据库连接的执行");
	}

}
