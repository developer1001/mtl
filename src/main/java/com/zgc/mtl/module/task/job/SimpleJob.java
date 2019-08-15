package com.zgc.mtl.module.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zgc.mtl.common.util.PostUtil;

@Component
public class SimpleJob {
	private Logger logger = LoggerFactory.getLogger(SimpleJob.class);
	@Scheduled(cron = "0 0/30 * * * ? ")
	public void getWeather() throws Exception {
		String url = "http://www.weather.com.cn/data/sk/101010100.html";
		String result = PostUtil.doRequire(url);
		logger.info("北京天气：{}", result);
	}
}
