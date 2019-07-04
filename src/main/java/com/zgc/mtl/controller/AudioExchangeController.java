package com.zgc.mtl.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.common.util.AudioExchangeRequest2;
import com.zgc.mtl.common.util.SASRsdk2;

@RestController
@RequestMapping("audio")
public class AudioExchangeController{
	// logger
	
	@PostMapping("exchange")
	public Object audioExchange(@RequestBody AudioExchangeRequest2 param) {
		if (StringUtils.isBlank(param.getVoiceFormat()) || StringUtils.isBlank(param.getFileByteArr()) ||
				StringUtils.isBlank(param.getEngSerViceType())) {
			return "";
		}
//		if(param.getFileByteArr() != null ) {
//			System.out.println("录音文件大小为：" + (param.getFileByteArr().length / 1024) + "KB");
//		}
		// 用户需修改为自己的SecretId，SecretKey
		String secretId = "AKID31NbfXbpBLJ4kGJrytc9UfgVAlGltJJ8";
		String secretKey = "kKm26uXCgLtGRWVJvKtGU0LYdWCgOvGP";
		// 识别引擎 8k or 16k 。8k：电话 8k 通用模型；16k：16k 通用模型。只支持单声道音频识别
//		String engSerViceType = "8k";
		String engSerViceType = param.getEngSerViceType();

		// 语音数据来源 0:语音url or 1:语音数据bodydata(data数据大小要小于800k)
		String sourceType = "1";
//		if (!StringUtils.isBlank(param.get("sourceType"))) {
//			sourceType = param.get("sourceType");
//		}
		// 音频格式 wav，mp3
		// String voiceFormat = "wav";
		String voiceFormat = param.getVoiceFormat();

		// 语音数据地址
		String fileURI = "D:\\eclipse2017-workspace\\test\\test.mp3";
		// String fileURI="http://liqiansunvoice-1255628450.cosgz.myqcloud.com/30s.wav";

		String fileByteArr = param.getFileByteArr();
		// 调用setConfig函数设置相关参数
		int res = SASRsdk2.setConfig(secretId, secretKey, engSerViceType, sourceType, voiceFormat, fileURI,fileByteArr);
		if (res < 0) {
			return "";
		}

		// 调用sendVoice函数获得音频识别结果
		String sendVoice = SASRsdk2.sendVoice();
		return sendVoice;
	}
}
