package com.zgc.mtl.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zgc.mtl.common.enu.StrEnum;
/**
 * redis工具类
 * @date 2019-07-09 16:56:44
 * @author yang
 */
@Component
public class RedisTool {
	@Autowired
	private  StringRedisTemplate stringRedisTemplate;
	/**
	 * 生成指定位数的纯数字字符串,位数不够前面补0；超出位数，保留所有位数
	 * @param enu 字符串枚举类，从其中取值
	 * @param length 序列的长度
	 * @return 数字自增为1的纯数字字符串
	 */
	public  String generateSeq(StrEnum enu, int length) {
		if(length <= 0) {
			return "";
		}
		String key = enu.getStr();
		Long num = stringRedisTemplate.opsForValue().increment(key, 1);
		String numStr = num + "";
		if(numStr.length() >= length) {
			return numStr;
		}
		else if(numStr.length() < length) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length; i++) {
				sb.append("0");
			}
			sb.append(numStr);
			return sb.toString().substring(numStr.length());
		}
		return "";
	}
	
	/**
	 * 生成指定位数并指定前缀的字符串，若位数不够，中间补0，超出位数，保留所有位数
	 * @param enu 字符串枚举类，从其中取值
	 * @param prefix 字符串前缀
	 * @param length 序列的长度
	 * @return 指定前缀的字符串
	 */
	public  String generateSeq(StrEnum enu, String prefix, int length) {
		String key = enu.getStr();
		if(length <= 0) {
			return "";
		}
		Long num = stringRedisTemplate.opsForValue().increment(key, 1);
		String numStr = num + "";
		//当前redis自增数字加上前缀的长度
		int len= numStr.length() + prefix.length();
		if(len >= length) {
			return prefix + numStr;
		}
		else if(len < length) {
			StringBuilder sb = new StringBuilder();
			sb.append(prefix);
			for (int i = 0; i < (length - len); i++) {
				sb.append("0");
			}
			sb.append(numStr);
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 保存一个对象
	 * @param key 键
	 * @param obj 对象实体
	 */
	public  void saveObj(String key, Object obj) {
		String json = JSONObject.toJSONString(obj);
		stringRedisTemplate.opsForValue().set(key, json);
	}
	
	/**
	 * 取出一个对象
	 * @param key 键
	 * @param clazz 对象所属类
	 * @return
	 */
	public  <T> T getObj(String key,Class<T> clazz) {
		String string = stringRedisTemplate.opsForValue().get(key);
		T t = JSONObject.parseObject(string, clazz);
		return t;
	}
}
