package com.zgc.mtl.common.task.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronJobTest {
	public static void main(String[] args) throws SchedulerException {
		JobDetail detail = JobBuilder.newJob(MyJob.class).withIdentity("CronJob").build();
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger").
				withSchedule(CronScheduleBuilder.cronSchedule("0 30/5 * * * ? *")).build();
		StdSchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		scheduler.scheduleJob(detail, cronTrigger);
		scheduler.start();
	}
}
