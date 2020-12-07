package com.zgc.mtl.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 接口调用
 * 
 * @date 2019-05-29 09:52:33
 * @author yang
 */
public class HttpUtils {
	/**
	 * 请求接口数据，请求参数拼接在url后面
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {// 接口数据
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
	 * 
	 * post请求数据
	 * 
	 * @param url
	 *            请求地址
	 * @param body
	 *            map集合封装请求参数，json化为字符串
	 * @param headers 请求头
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String param, Map<String, String> headers) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		if (headers != null && headers.size() > 0) {
			headers.forEach((k, v) -> httpPost.addHeader(k, v));
		}
		StringEntity stringEntity = new StringEntity(param);
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
	public static String post(String url, List<? extends NameValuePair> list) throws Exception {
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
	
	public static void downloadResource(String url) throws IOException {
		String redirectAddress = getRedirectAddress(url);
		 OkHttpClient client = new OkHttpClient();
		 Request request = new Request.Builder().url(redirectAddress).get() .build();
		 Response response = client.newCall(request).execute();
		 String name = System.currentTimeMillis() + ".mp4";
//		 if (url.indexOf("/") > 0) {
//			 name = url.substring(url.lastIndexOf("/") + 1);
//		 }
		 OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(name));
		 outputStream.write(response.body().bytes());
		 outputStream.flush();
		 outputStream.close();
	}
	
	
	public static String getRedirectAddress(String url) throws MalformedURLException, IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setInstanceFollowRedirects(false);
		conn.setConnectTimeout(5000);
		conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
		String localtion = conn.getHeaderField("location");
		if (StringUtils.isBlank(localtion)) {
			localtion = url;
		}
		return localtion;
	}
}
