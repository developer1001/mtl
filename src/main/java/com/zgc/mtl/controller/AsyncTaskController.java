package com.zgc.mtl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.service.AsyncTaskService;

/**
 * spring 的 ThreadPoolTaskExecutor使用示例
 * @date 2019-07-08 17:24:11
 * @author yang
 */
@RestController
public class AsyncTaskController {
	@Autowired
	AsyncTaskService asyncTaskService;
	
	@RequestMapping("/coffee")
	public String coffee(String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("调用咖啡机,    time=" + sdf.format(new Date()));
		asyncTaskService.coffee(name);
		System.out.println("咖啡机调用完毕,time=" + sdf.format(new Date()));
		return "success";
	}
	
	@RequestMapping("/tea")
	public String rtea(String name) {
		long s = System.currentTimeMillis();
		asyncTaskService.tea(name);
		asyncTaskService.send();
		System.out.println("冰茶制作, 总time:" + (System.currentTimeMillis() -s));
		return "success";
	}
}
