package com.zgc.mtl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.zgc.mtl.module.task.job.SimpleJob;

@Component
public class StartUpConfig implements ApplicationRunner{
	@Autowired
	private SimpleJob simpleJob;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		simpleJob.test();
		
	}
	
}
