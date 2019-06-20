package com.zgc.mtl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zgc.mtl.base.model.Json;

/**
 * 
 * @date 2019-06-18 11:32:16
 * @author yang
 */
@RequestMapping("healthy")
public class HealthyController{
	
	@RequestMapping("bgm")
	@ResponseBody
	public Json bgm() {
		
		return null;
	}
}
