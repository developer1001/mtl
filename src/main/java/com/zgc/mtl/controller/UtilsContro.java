package com.zgc.mtl.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.base.model.Json;
import com.zgc.mtl.common.util.ExcelUtil;
/**
 * 工具类调用
 * @date 2020-01-19 12:21:26
 * @author y
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
			return new Json(true, new String(decode).replaceAll("\n", "<br>"));
		}
		return new Json(false,"error");
	}
	
	@RequestMapping("tableFieldInfo/toExcel")
	public void enDecode(String filePath) throws IOException {
		ExcelUtil.tableFieldFormat("aaa.sql");
	}
}
