package com.zgc.mtl.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 百度印刷文字识别OCR
 * 配置文件未配置在yml中。
 * 识别文字前，首先获取access_token，鉴权认证。
 * @date 2019-08-14 14:04:42
 * @author yang
 */
public class OCRUtil {
	private static final Logger logger = LoggerFactory.getLogger(OCRUtil.class);
	
	private static String grant_type = "client_credentials";
	private String AppId = "17013086";
	private static String ApiKey = "oNIf1WlIuhj9MLm6qxjIrFpD";
	private static String secretKey = "L7qQ4uiVOuiy6qBG2Gm7cd9IXhGG3HAL";
	private static String accessTokenServer = "https://aip.baidubce.com/oauth/2.0/token?";
	private static String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
	
	/**
	 * OCR鉴权认证
	 * @return
	 * @throws Exception
	 */
	public static String getAccessToken() throws Exception {
		//首先鉴权认证获得access-token
		String accessTokenUrl = accessTokenServer + "grant_type=" + grant_type +"&client_id="
				+ ApiKey + "&client_secret=" + secretKey;
		String token = PostUtil.doRequire(accessTokenUrl);
		JSONObject parseObject = JSONObject.parseObject(token);
		String accessToken = parseObject.get("access_token").toString();
		logger.info("ocr获得Access token：{}", accessToken);
		return accessToken; 
	}
	
	/**
	 * OCR识别文字
	 * @return
	 * @throws Exception
	 */
	public static String ocr(String fileUrl) throws Exception {
		String accessToken = getAccessToken();
		url = url + "?" + "access_token=" + accessToken;
		//请求体的参数
		FileInputStream fis = new FileInputStream(new File(fileUrl));
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] byteArr = new byte[bis.available()];
		bis.read(byteArr);
		String encodeToString= Base64.getEncoder().encodeToString(byteArr);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		BasicNameValuePair nameValue = new BasicNameValuePair("image", encodeToString);
		list.add(nameValue);
		String ocrJson = PostUtil.sendHttpPost(url, list);
		logger.info("ocr识别结果：{}", ocrJson);
		bis.close();
		fis.close();
		return ocrJson;
	}
	
	public static void main(String[] args) throws Exception {
		String fileUrl = "qqqq.jpg";
		ocr(fileUrl);
	}
}
