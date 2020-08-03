package com.zgc.mtl.service;

/**
 * 异步执行示例，spring框架threadPoolTaskExecutor
 * @date 2019-07-08 16:56:15
 * @author yang
 */
public interface AsyncTaskService {
	/**
	 * 制作咖啡
	 * @param name
	 */
	void coffee(String name);

	void tea(String name);
	/**
	 * 派送
	 */
	void send();
}
