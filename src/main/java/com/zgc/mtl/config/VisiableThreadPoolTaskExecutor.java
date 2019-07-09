package com.zgc.mtl.config;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池扩展类
 * @date 2019-07-08 18:32:31
 * @author yang
 */
public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void printMsg(String method) {
		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
		if(threadPoolExecutor == null) {
			return;
		}
		System.out.println("ThreadNamePrefix:" + this.getThreadNamePrefix() +
				" method:" + method +
				" taskCount:" + threadPoolExecutor.getTaskCount() +
				" completedTaskCount:" + threadPoolExecutor.getCompletedTaskCount() + 
				" activeCount:" +  threadPoolExecutor.getActiveCount() +
				" queueSize:" + threadPoolExecutor.getQueue().size());
	}
	
	@Override
	public <T> Future<T> submit(Callable<T> arg0) {
		printMsg("submit(Callable<T> arg0)");
		return super.submit(arg0);
	}

}
