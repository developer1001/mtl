package com.zgc.mtl.module.task.quartz;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zgc.mtl.base.model.Json;

/**
 * quartz工具类
 * 
 * @date 2019-08-28 17:37:28
 * @author yang
 */

@Component
public class QuartzUtil {
	/**
	 * 注入任务调度器
	 */
	@Autowired
	private Scheduler sched;
	private static String JOB_GROUP_NAME = "ATAO_JOBGROUP"; // 任务组
	private static String TRIGGER_GROUP_NAME = "ATAO_TRIGGERGROUP"; // 触发器组

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 *
	 * @param jobName
	 *            任务名
	 * @param cls
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 */
	public void addJob(String jobName, Class<? extends Job> cls, String time) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build(); // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
			CronTrigger trigger = TriggerBuilder.newTrigger() // 创建一个新的TriggerBuilder来规范一个触发器
					.withIdentity(jobName, TRIGGER_GROUP_NAME) // 给触发器起一个名字和组名
					.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 （带参数）
	 *
	 * @param jobName
	 *            任务名
	 * @param cls
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 */
	public void addJob(String jobName, Class<? extends Job> cls, String time, Map<String, Object> parameter) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build(); // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
			jobDetail.getJobDataMap().put("parameterList", parameter); // 传参数
			CronTrigger trigger = TriggerBuilder.newTrigger() // 创建一个新的TriggerBuilder来规范一个触发器
					.withIdentity(jobName, TRIGGER_GROUP_NAME) // 给触发器起一个名字和组名
					.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加一个定时任务
	 *
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 */
	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			Class<? extends Job> jobClass, String time) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();// 任务名，任务组，任务执行类
			CronTrigger trigger = TriggerBuilder // 触发器
					.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加一个定时任务 （带参数）
	 *
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 */
	public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			Class<? extends Job> jobClass, String time, Map<String, Object> parameter) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();// 任务名，任务组，任务执行类
			jobDetail.getJobDataMap().put("parameterList", parameter); // 传参数
			CronTrigger trigger = TriggerBuilder // 触发器
					.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
			sched.scheduleJob(jobDetail, trigger);
			if (!sched.isShutdown()) {
				sched.start(); // 启动
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 *
	 * @param jobName
	 *            任务名
	 * @param time
	 *            新的时间设置
	 */
	public void modifyJobTime(String jobName, String time) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME); // 通过触发器名和组名获取TriggerKey
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey); // 通过TriggerKey获取CronTrigger
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME); // 通过任务名和组名获取JobKey
				JobDetail jobDetail = sched.getJobDetail(jobKey);
				Class<? extends Job> objJobClass = jobDetail.getJobClass();
				removeJob(jobName);
				addJob(jobName, objJobClass, time);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改一个任务的触发时间
	 *
	 * @param triggerName
	 *            任务名称
	 * @param triggerGroupName
	 *            传过来的任务名称
	 * @param time
	 *            更新后的时间规则
	 */
	public void modifyJobTime(String triggerName, String triggerGroupName, String time) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName); // 通过触发器名和组名获取TriggerKey
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey); // 通过TriggerKey获取CronTrigger
			if (trigger == null)
				return;
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(trigger.getCronExpression());
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				trigger = (CronTrigger) trigger.getTriggerBuilder() // 重新构建trigger
						.withIdentity(triggerKey).withSchedule(scheduleBuilder)
						.withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
				sched.rescheduleJob(triggerKey, trigger); // 按新的trigger重新设置job执行
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 暂停一个任务（使用默认的任务组名，触发器名，触发器组名）
	 * 
	 * @param jobName
	 */
	public Json pauseJob(String jobName) {
		String triggerState = null;
		try {
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME); // 通过任务名和组名获取JobKey
			sched.pauseJob(jobKey);
			triggerState = getTriggerState(jobName, TRIGGER_GROUP_NAME);
			return new Json(true, "暂停任务", triggerState);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(false, "暂停任务", triggerState);
		}
	}

	/**
	 * @Description:暂停一个任务
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 */
	public Json pauseJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		String triggerState = null;
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		try {
			sched.pauseJob(jobKey);
			triggerState = getTriggerState(triggerName, triggerGroupName);
			return new Json(true, "暂停任务", triggerState);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(false, "暂停任务", triggerState);
		}
	}

	/**
	 * @Description:恢复一个任务(使用默认组名)
	 * @param jobName
	 */
	public Json resumeJob(String jobName) {
		String triggerState = null;
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		try {
			sched.resumeJob(jobKey);
			triggerState = getTriggerState(jobName, TRIGGER_GROUP_NAME);
			return new Json(true, "恢复任务", triggerState);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(false, "恢复任务", triggerState);
		}
	}

	/**
	 * @Description:恢复一个任务
	 * @param jobName
	 * @param jobGroupName
	 */
	public Json resumeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		String triggerState = null;
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		try {
			sched.resumeJob(jobKey);
			triggerState = getTriggerState(triggerName, triggerGroupName);
			return new Json(true, "恢复任务", triggerState);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(true, "恢复任务", triggerState);
		}
	}

	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 *
	 * @param jobName
	 *            任务名称
	 */
	public void removeJob(String jobName) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME); // 通过触发器名和组名获取TriggerKey
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME); // 通过任务名和组名获取JobKey
			sched.pauseTrigger(triggerKey); // 停止触发器
			sched.unscheduleJob(triggerKey);// 移除触发器
			sched.deleteJob(jobKey); // 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 移除一个任务
	 *
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 */
	public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName); // 通过触发器名和组名获取TriggerKey
			JobKey jobKey = JobKey.jobKey(jobName, jobGroupName); // 通过任务名和组名获取JobKey
			sched.pauseTrigger(triggerKey); // 停止触发器
			sched.unscheduleJob(triggerKey);// 移除触发器
			sched.deleteJob(jobKey); // 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 启动所有定时任务
	 */
	public Json startAllJobs() {
		try {
			if (sched.isShutdown()) {
				sched.start();
			}
			int size = sched.getCurrentlyExecutingJobs().size();
			return new Json(true, "所有任务已启动", "当前运行任务数：" + size);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(false, "启动所有任务出现异常");
		}
	}

	/**
	 * 关闭所有定时任务
	 */
	public Json shutdownAllJobs() {
		try {
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
			int size = sched.getCurrentlyExecutingJobs().size();
			return new Json(true, "所有任务已关闭", "当前运行任务数：" + size);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(true, "关闭所有任务过程中出现异常");
		}
	}

	/**
	 * 暂停所有定时任务
	 */
	public Json pauseAllJobs() {
		try {
			sched.pauseAll();
			int size = sched.getCurrentlyExecutingJobs().size();
			return new Json(true, "所有任务已暂停", "当前运行任务数：" + size);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(true, "暂停所有任务过程中出现异常");
		}
	}

	/**
	 * 苏醒/恢复所有定时任务
	 */
	public Json resumeAllJobs() {
		try {
			sched.resumeAll();
			int size = sched.getCurrentlyExecutingJobs().size();
			return new Json(true, "所有任务已恢复运行", "当前运行任务数：" + size);
		} catch (Exception e) {
			e.printStackTrace();
			return new Json(true, "恢复所有任务过程中出现异常");
		}
	}

	/**
	 * @Description: 获取任务状态 NONE: 不存在 NORMAL: 正常 PAUSED: 暂停 COMPLETE:完成 ERROR : 错误
	 *               BLOCKED : 阻塞
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @return
	 */
	public String getTriggerState(String triggerName, String triggerGroupName) {
		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
		String name = null;
		try {
			TriggerState triggerState = sched.getTriggerState(triggerKey);
			name = triggerState.name();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
