package com.zgc.mtl.service;

import com.zgc.mtl.model.Log;

public interface LogService {
	/**
	 * 新增日志
	 * @param log
	 * @return
	 */
	Object insert(Log log);
	/**
	 * 更新日志
	 * @param log
	 * @return
	 */
	Object update(Log log);
	/**
	 * 查找日志
	 * @param logId
	 * @return
	 */
	Object get(String logId);
	/**
	 * 删除日志
	 * @param logId
	 * @return
	 */
	Object remove(String logId);
}
