package com.zgc.mtl.module.task.quartz.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgc.mtl.common.util.PostUtil;
/**
 * 热搜
 * @date 2019-11-28 16:10:06
 * @author yang
 */
@Component
public class HotSearchJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(HotSearchJob.class);
	@Autowired
	private StringRedisTemplate redis;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String redisKey = "hotSearchJson";
		String url = "https://s.weibo.com/ajax/jsonp/gettopsug";
		String doRequire = null;
		try {
			doRequire = PostUtil.doRequire(url);
			String subStr = doRequire.substring(doRequire.indexOf("(") + 1 , 
					doRequire.lastIndexOf(")}catch"));
			JSONObject parseObject = JSONObject.parseObject(subStr);
			String dataStr = parseObject.getString("data");
			JSONObject dataJson = JSONObject.parseObject(dataStr);
			String list = dataJson.getString("list");
			JSONArray parseArray = JSONObject.parseArray(list);
			int size = parseArray.size();
			redis.delete(redisKey);
			ArrayList<String> arrayList = new ArrayList<String>();
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			for(int i = 0; i < size; i++) {
				JSONObject jsonObject = parseArray.getJSONObject(i);
//				String realpos = jsonObject.getString("realpos");
				String word = jsonObject.getString("word");
//				String onboardTime = jsonObject.getString("onboard_time");
				arrayList.add(word);
			}
			redis.opsForList().rightPushAll(redisKey, arrayList);
			redis.opsForList().rightPush(redisKey, date);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解析热点数据出错，源数据{}", JSONObject.toJSONString(doRequire));
			throw new RuntimeException(e);
		}
	}

}