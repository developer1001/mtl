package com.zgc.mtl.common.task.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("这是job任务：去打印当前的时间：" + 
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}
