package com.zgc.mtl.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

public class RequestUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
	//超出长度的参数，不再打印到日志
	private static final int PARAM_LENGTH_LIMIT = 3000;
	private static ArrayList<String> excludeHeadList = new ArrayList<String>();
	static {
		excludeHeadList.add("cache-control");
		excludeHeadList.add("content-type");
		excludeHeadList.add("origin");
		excludeHeadList.add("postman-token");
		excludeHeadList.add("content-length");
		excludeHeadList.add("host");
		excludeHeadList.add("user-agent");
		excludeHeadList.add("accept");
		excludeHeadList.add("accept-encoding");
		excludeHeadList.add("accept-language");
		excludeHeadList.add("connection");
		excludeHeadList.add("cookie");
		excludeHeadList.add("sec-fetch-dest");
		excludeHeadList.add("sec-fetch-mode");
		excludeHeadList.add("sec-fetch-site");
		excludeHeadList.add("sec-fetch-user");
		excludeHeadList.add("sec-ch-ua");
		excludeHeadList.add("upgrade-insecure-requests");
		excludeHeadList.add("remoteip");
		excludeHeadList.add("referer");
		excludeHeadList.add("x-forwarded-proto");
		excludeHeadList.add("x-forwarded-port");
		excludeHeadList.add("x-forwarded-for");
	}

	/**
	 * 获取request的请求头参数和普通请求参数， 并打印到日志
	 * 
	 * @return 请求头参数和普通请求参数Map集合
	 */
	public static Map<String, Object> getParamMap() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (requestAttributes == null) {
			return resultMap;
		}
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		//打印参数信息
		HashMap<String, Object> logMap = new HashMap<String, Object>();
		// 1.处理请求头参数
		Enumeration<String> headerNames = request.getHeaderNames();
		String headerName = null;
		HashMap<String, String> headParams = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			headerName = headerNames.nextElement();
			if (excludeHeadList.contains(headerName)) {
				continue;
			}
			headParams.put(headerName, request.getHeader(headerName));
		}
		LOGGER.info("接口请求头参数有：{}", JSONObject.toJSONString(headParams));
		logMap.putAll(headParams);
		resultMap.putAll(headParams);
		
		// 2.处理requestBody参数
		Map<String, Object> requestBodyData = getRequestBodyData(request);
		if (requestBodyData != null) {
			resultMap.putAll(requestBodyData);
			requestBodyData.forEach((k, v) -> {
				if (JSONObject.toJSONString(v).length() >= PARAM_LENGTH_LIMIT) {
					logMap.put(k, "longText! will not be shown...");
				} else {
					logMap.put(k, v);
				}
			});
			LOGGER.info("接口请求体(+请求头)参数有：{}", JSONObject.toJSONString(logMap));
		}
		
		// 3.处理普通参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (headParams.size() == 0 && parameterMap.size() == 0) {
			LOGGER.info("接口无其他请求参数传递...");
			return resultMap;
		}
		if (parameterMap.size() > 0) {
			parameterMap.forEach((k, v) -> resultMap.put(k, v.length > 1 ? v : v[0]));
			parameterMap.forEach((k, v) -> {
				boolean hasLongText = false;
				for (String str : v) {
					if (str.length() >= PARAM_LENGTH_LIMIT) {
						hasLongText = true;
						break;
					}
				}
				if (hasLongText) {
					logMap.put(k, "longText! will not be shown...");
				} else {
					logMap.put(k, v.length > 1 ? v : v[0]);
				}
			});
		}
		LOGGER.info("接口总的请求参数有：{}", JSONObject.toJSONString(logMap));
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getRequestBodyData(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ex) {
			LOGGER.error("读取request请求参数的流数据出错", ex);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					LOGGER.error("关闭读取request请求的流数据出错", ex);
				}
			}
		}
		Map<String, Object> result = null;
		String params = stringBuilder.toString();
		if (StringUtils.isNotBlank(params)) {
			result = JSONObject.parseObject(params, Map.class);
		}
		return result;
	}
}
