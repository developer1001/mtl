package com.zgc.mtl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.common.util.AliMsgUtil;

/**
 * aliSms短消息通知(aliyun账户需充值购买sms套餐，未上线项目只可以发验证码)
 * @date 2019-08-01 16:17:58
 * @author yang
 */
@RestController
@RequestMapping("aliSms")
public class AliSmsController {
	@Autowired
	private AliMsgUtil aliMsgUtil;
	/**
	 * 给单个手机号发送验证码消息
	 * @return
	 */
	@RequestMapping("/sendSms")
	public String sendSms(Map<String, String> param) {
		param.put("phoneNumbers", "xxx,xxx");
		String sendSms = aliMsgUtil.sendSms(param);
		return sendSms;
	}
	
	/**
	 * 批量发验证码消息
	 * @return
	 */
	@RequestMapping("/sendBatchSms")
	public String sendBatchSms(Map<String, String> param) {
		param.put("phoneNumbers", "xxx,xxx");
		String sendSms = aliMsgUtil.sendBatchSms(param);
		return sendSms;
	}
}
