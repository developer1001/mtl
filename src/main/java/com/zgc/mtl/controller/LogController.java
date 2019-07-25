package com.zgc.mtl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.model.Log;
import com.zgc.mtl.service.LogService;

/**
 * log类，MongoDB测试用
 * @date 2019-07-25 15:09:04
 * @author yang
 */
@RestController
@RequestMapping("log")
public class LogController {
	@Autowired
	private LogService logService;
	
	@PostMapping("insert")
	public Object insert(@RequestBody Log log) {
		Object insert = logService.insert(log);
		return insert;
	}
	
	@PostMapping("update")
	public Object update(@RequestBody Log log) {
		Object update = logService.update(log);
		return update;
	}
	
	@PostMapping("get")
	public Object get(String logId) {
		Object get = logService.get(logId);
		return get;
	}
	
	@PostMapping("delete")
	public Object delete(String logId) {
		Object delete = logService.remove(logId);
		return delete;
	}
}
