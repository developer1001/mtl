package com.zgc.mtl.common.task.job;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobTest{
	public static void main(String[] args) throws SchedulerException {
//		JobDetail detail = JobBuilder.newJob(MyJob.class).withIdentity("myjob").build();
//		SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("mytrigger").
//				startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().
//						repeatForever().withIntervalInSeconds(2)).build();
//		StdSchedulerFactory factory = new StdSchedulerFactory();
//		Scheduler scheduler = factory.getScheduler();
//		scheduler.scheduleJob(detail, simpleTrigger);
//		scheduler.start();
	}
}