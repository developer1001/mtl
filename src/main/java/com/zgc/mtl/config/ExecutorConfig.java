package com.zgc.mtl.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * @date 2019-07-08 17:48:22
 * @author yang
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
	
	@Bean
	public Executor threadPoolCenter() {
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		 //配置核心线程数
		executor.setCorePoolSize(5);
		 //配置最大线程数
		executor.setMaxPoolSize(10);
		//配置队列大小
		executor.setQueueCapacity(30);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("poolCenter-thread-");
		executor.initialize();
		return executor;
	}
}
