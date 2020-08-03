package com.zgc.mtl.common.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

public class RequestUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);
	private static ArrayList<String> excludeHeadList = new ArrayList<String>();
	static {
		excludeHeadList.add("accept");
		excludeHeadList.add("accept-encoding");
		excludeHeadList.add("accept-language");
		excludeHeadList.add("cache-control");
		excludeHeadList.add("content-type");
		excludeHeadList.add("content-length");
		excludeHeadList.add("connection");
		excludeHeadList.add("cookie");
		excludeHeadList.add("host");
		excludeHeadList.add("origin");
		excludeHeadList.add("postman-token");
		excludeHeadList.add("referer");
		excludeHeadList.add("sec-fetch-dest");
		excludeHeadList.add("sec-fetch-mode");
		excludeHeadList.add("sec-fetch-site");
		excludeHeadList.add("sec-fetch-user");
		excludeHeadList.add("sec-ch-ua");
		excludeHeadList.add("upgrade-insecure-requests");
		excludeHeadList.add("user-agent");
	}

	/**
	 * 获取request的请求头参数和普通请求参数， 并打印到日志
	 * @return 请求头参数和普通请求参数Map集合
	 */
	public static Map<String, Object> getParamMap() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (requestAttributes == null) {
			return resultMap;
		}
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		Enumeration<String> headerNames = request.getHeaderNames();
		String headerName = null;
		HashMap<String, String> headParams = new HashMap<>();
		while (headerNames.hasMoreElements()) {
			headerName = headerNames.nextElement().toLowerCase();
			if (excludeHeadList.contains(headerName)) {
				continue;
			}
			headParams.put(headerName, request.getHeader(headerName));
		}
		LOGGER.info("接口请求头参数有：{}", JSONObject.toJSONString(headParams));
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (headParams.size() == 0 && parameterMap.size() == 0) {
			LOGGER.info("无请求参数传递...");
			return resultMap;
		}
		resultMap.putAll(headParams);
		if (parameterMap.size() > 0) {
			parameterMap.forEach((k, v) -> resultMap.put(k, v.length > 1 ? v : v[0]));
		}
		LOGGER.info("接口请求参数：{}", JSONObject.toJSONString(resultMap));
		return resultMap;
	}
}
