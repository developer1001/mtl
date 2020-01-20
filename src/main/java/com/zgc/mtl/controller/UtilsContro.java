package com.zgc.mtl.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.base.model.Json;
/**
 * 工具类调用
 * @date 2020-01-19 12:21:26
 * @author yang
 */
@RestController
@RequestMapping("/utils")
public class UtilsContro {
//	private Logger logger = LoggerFactory.getLogger(SysController.class);
	
	@RequestMapping("enDecode")
	public Json enDecode(@RequestBody Map<String, String> param) {
		String type = param.get("type");
		String content = param.get("content");
		if(type.equals("encode")) {
			String encodeToString= Base64.getEncoder().encodeToString(content.getBytes());
			return new Json(true, encodeToString);
		}
		else if(type.equals("decode")) {
			byte[] decode = Base64.getDecoder().decode(content);
			System.out.println(new String(decode));
			return new Json(true, new String(decode).replaceAll("\n", "<br>"));
		}
		return new Json(false,"error");
	}
}
