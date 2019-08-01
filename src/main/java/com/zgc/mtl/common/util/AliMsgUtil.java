package com.zgc.mtl.common.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 阿里云sms短消息工具类(需要付费买阿里云短信套餐)
 * @date 2019-08-01 15:59:24
 * @author yang
 */
@Component
public class AliMsgUtil {
	@Value("${aliyun.sms.accessKeyId}")
	private String accessKeyId;
	
	@Value("${aliyun.sms.accessSecret}")
	private String accessSecret;
	
	@Value("${aliyun.sms.signName}")
	private String signName;
	
	@Value("${aliyun.sms.templateCode}")
	private String templateCode;
	
	private static String doMain = "dysmsapi.aliyuncs.com";

	private static String version = "2017-05-25";

	private static String regionId = "default";
	
	/**
	 * 批量发送消息
	 * @param param
	 * @return 
	 */
	public String sendBatchSms(Map<String, String> param) {
		DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain(doMain);
		request.setVersion(version );
		request.setAction("SendBatchSms");
		request.putQueryParameter("RegionId", regionId );
		request.putQueryParameter("PhoneNumberJson", param.get("phoneNumbers"));
		request.putQueryParameter("SignNameJson", signName);
		request.putQueryParameter("TemplateCode", templateCode);
		request.putQueryParameter("TemplateParam", "{\"code\":\""+ randomNum(6) + "\"}" );
		try {
			CommonResponse response = client.getCommonResponse(request);
			String data = response.getData();
			return data;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送单个消息
	 * @param param
	 */
	public String sendSms(Map<String, String> param) {
		DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain(doMain);
		request.setVersion(version);
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", regionId);
		request.putQueryParameter("PhoneNumbers", param.get("phoneNumbers"));
		request.putQueryParameter("SignName", signName);
		request.putQueryParameter("TemplateCode", templateCode);
		request.putQueryParameter("TemplateParam", "{\"code\":\""+ randomNum(6) + "\"}" );
		try {
			CommonResponse response = client.getCommonResponse(request);
			String data = response.getData();
			return data;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询指定日期指定号码的发送情况
	 * @param param
	 * @return 
	 */
	public String querySendDetails(Map<String, String> param) {
		DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain(doMain);
		request.setVersion(version);
		request.setAction("QuerySendDetails");
		request.putQueryParameter("RegionId", regionId);
		request.putQueryParameter("PhoneNumber", param.get("phoneNumbers"));
		request.putQueryParameter("SendDate", param.get("sendDate"));
		request.putQueryParameter("PageSize", param.get("pageSize"));
		request.putQueryParameter("CurrentPage", param.get("currentPage"));
		try {
			CommonResponse response = client.getCommonResponse(request);
			String data = response.getData();
			return data;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 产生指定位数随机数字
	 * @param length
	 * @return
	 */
	public static long randomNum(int length) {
		long num = (long) ((Math.random() + 1) * 1000000000000000000L);
		return Long.parseLong(String.valueOf(num).substring(0, length));
	}
}
