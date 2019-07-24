package com.zgc.mtl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.service.HealthyService;

/**
 * 
 * @date 2019-06-18 11:32:16
 * @author yang
 */
@RestController
@RequestMapping("healthy")
public class HealthyController{
	@Autowired
	private HealthyService healthyService;
	@PostMapping("user")
	public Object getMyCondition(@RequestBody Map<String, Object> param) {
		Object all = healthyService.getAll(param);
		return all;
	}
}
