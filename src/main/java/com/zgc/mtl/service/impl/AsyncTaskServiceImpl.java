package com.zgc.mtl.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.zgc.mtl.service.AsyncTaskService;

/**
 * 异步执行，线程池使用示例之service方法
 * @date 2019-07-08 17:13:46
 * @author yang
 */
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService {

	@Async("threadPoolCenter")
	@Override
	public void coffee(String name) {
		System.out.println(name + "：您好，您的订单已收到，请稍后！(" + Thread.currentThread().getName() + ")");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + "：您的咖啡制作完毕，请取用！(" + Thread.currentThread().getName() + ")");
	}

	@Override
	public void tea(String name) {
		System.out.println("准备制作冰茶");
		long s = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("冰茶制作完毕");
		System.out.println("冰茶制作, time:" + (System.currentTimeMillis() -s));
	}

	@Async
	@Override
	public void send() {
		long s = System.currentTimeMillis();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("send, time:" + (System.currentTimeMillis() -s));
	}
	

}
