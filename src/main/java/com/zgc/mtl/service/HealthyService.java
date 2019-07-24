package com.zgc.mtl.service;

import java.util.Map;

public interface HealthyService {
	/**
	 * 获得个人的健康情况
	 * @param param
	 * @return
	 */
	Object getAll(Map<String, Object> param);
}
