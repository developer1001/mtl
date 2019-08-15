package com.zgc.mtl.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 接口调用
 * 
 * @date 2019-05-29 09:52:33
 * @author yang
 */
public class PostUtil {
	/**
	 * 请求接口数据，请求参数拼接在url后面
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String doRequire(String url) throws Exception {// 接口数据
		StringBuilder result = new StringBuilder();
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) (conn);
		httpConnection.setRequestProperty("Accept", "application/json");
//		httpConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		httpConnection.setRequestMethod("GET");
		httpConnection.connect();
		// 发送POST请求必须设置如下两行
		// conn.setDoOutput(true);
		// conn.setDoInput(true);
		BufferedReader input = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "utf-8"));
		String line;
		while ((line = input.readLine()) != null) {
			result.append(line);
		}
		input.close();
		return result.toString();
	}

	/**
	 * post请求数据
	 * 
	 * @param url
	 *            请求地址
	 * @param body
	 *            map集合封装请求参数，json化为字符串
	 * @return
	 * @throws Exception
	 */
	public static String sendJsonHttpPost(String url, String body) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		StringEntity stringEntity = new StringEntity(body);
		stringEntity.setContentEncoding("UTF-8");
		httpPost.setEntity(stringEntity);
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8");
		response.close();
		httpClient.close();
		return responseContent;
	}
	
	/**
	 * 
	 * @param url请求地址
	 * @param list 请求参数
	 * @return
	 * @throws Exception
	 */
	public static String sendHttpPost(String url, List<? extends NameValuePair> list) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list);
		httpPost.setEntity(urlEncodedFormEntity);
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8");
		response.close();
		httpClient.close();
		return responseContent;
	}
}
