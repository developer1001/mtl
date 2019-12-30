package com.zgc.mtl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.common.util.PostUtil;

@RestController
@RequestMapping("/system")
public class SysController {
	private Logger logger = LoggerFactory.getLogger(SysController.class);
	@Value("${sucureData.deployAddr}")
	private String deployAddr;
	
	@RequestMapping("/deploy")
	@Async("threadPoolCenter")
	public String DeployNow() {
		String message = "准备部署，60秒等待期后，应用将关闭";
		logger.info(message);
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			String doRequire = PostUtil.doRequire(deployAddr);
			logger.info("重部署系统反馈结果：{}", doRequire);
		} catch (Exception e) {
			logger.error("重部署出错", e);
		}
		logger.info("重部署指令发送完毕，应用稍后将重启");
		return "重部署指令发送完毕，应用稍后将重启，这可能需要3分钟左右";
	}
}
