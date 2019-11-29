package com.zgc.mtl.module.task.quartz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.base.model.Json;

/**
 * 
 * @date 2019-11-28 17:04:16
 * @author yang
 */
@RestController
@RequestMapping("/job")
public class QuartzJobController {
	
	@Autowired
	private StringRedisTemplate redis;
	@Autowired
	QuartzUtil quartzUtil;
	
	@RequestMapping("hotSearch")
	public Object hotSearch() {
		String redisKey = "hotSearchJson";
		List<String> range = redis.opsForList().range(redisKey, 0, -1);
		return range;
	}
	
	@PostMapping("modifyJobTime")
	public void modifyJobTime(@RequestBody Map<String,String> param) {
		quartzUtil.modifyJobTime(param.get("jobName"), param.get("time"));
	}
	
	@RequestMapping("pauseJob")
	public Json pauseJob(String jobName) {
		Json pauseJob = quartzUtil.pauseJob(jobName);
		return pauseJob;
	}
	
	@RequestMapping("resumeJob")
	public Json resumeJob(String jobName) {
		Json resumeJob = quartzUtil.resumeJob(jobName);
		return resumeJob;
	}
	
	@RequestMapping("pauseAllJobs")
	public Json pauseAllJobs() {
		Json pauseAllJobs = quartzUtil.pauseAllJobs();
		return pauseAllJobs;
	}
	
	@RequestMapping("resumeAllJobs")
	public Json resumeAllJobs(String jobName,String time) {
		Json resumeAllJobs = quartzUtil.resumeAllJobs();
		return resumeAllJobs;
	}
}
