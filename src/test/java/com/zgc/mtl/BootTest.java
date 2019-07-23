package com.zgc.mtl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zgc.mtl.common.enu.ResultCode;
import com.zgc.mtl.common.enu.Sex;
import com.zgc.mtl.service.impl.TestServiceImpl;
//下面两行注解可以启动boot
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BootTest {

	@Test
	public void bootTest() {
		new TestServiceImpl().printString();
	}
	
	@Test
	public void enu() {
		System.out.println(ResultCode.SUCCESS.toString());
		int a = 0;
		System.out.println(Sex.MALE.getSex() == a);
	}
}
