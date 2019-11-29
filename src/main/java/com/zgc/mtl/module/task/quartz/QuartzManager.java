package com.zgc.mtl.module.task.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zgc.mtl.module.task.quartz.job.HelloJob;
import com.zgc.mtl.module.task.quartz.job.HotSearchJob;

/**
 * 管理schedule，schedule工厂，项目启动即可运行下列的多个任务
 * @date 2019-11-28 15:29:46
 * @author yang
 */
//@Configuration
public class QuartzManager {
//  @Autowired
//  QuartzUtil quartzUtil;

  /**
   * 持久化在数据库的时候用cron表达式的话，jobName是不能重复的，在正式服务器环境下可以使用这种方式，在开发环境下建议使用simpleSchedule
   */
//  @Bean
//  public void Initialization(){
//	  quartzUtil.addJob(HelloJob.class.getName(),HelloJob.class,"0 0/1 * * * ?");
//  }
//  
//  @Bean
//  public void hotSearch(){
//	  quartzUtil.addJob(HotSearchJob.class.getName(),HotSearchJob.class,"0 0/2 * * * ? ");
//  }
}
