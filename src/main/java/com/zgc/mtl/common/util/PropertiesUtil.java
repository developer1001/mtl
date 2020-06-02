package com.zgc.mtl.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bytebuddy.asm.Advice.This;

/**
 * 解析properties文件
 * @date 2020-06-02 15:23:41
 * @author yang
 */
public class PropertiesUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Map<String, String> map = new HashedMap<>();
	public static String get(String key) {
		return map.get(key);
	}
	
	static {
		LOGGER.info("开始初始化系统properties文件内容...");
		Properties properties = new Properties();
		String path = This.class.getClassLoader().getResource("config/properties").getPath();
		File dir = new File(path);
		File[] listFiles = dir.listFiles();
		if(listFiles != null && listFiles.length > 0) {
			for(int i = 0; i < listFiles.length; i++) {
				properties.clear();
				File file = listFiles[i];
				InputStreamReader inputStreamReader = null;
				try {
					inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
					properties.load(inputStreamReader);
					Set<Entry<Object, Object>> entrySet = properties.entrySet();
					if(entrySet != null && entrySet.size() > 0) {
						for(Iterator<Entry<Object, Object>> it = entrySet.iterator(); it.hasNext();) {
							Entry<Object, Object> next = it.next();
							String key = (String) next.getKey();
							String value = (String) next.getValue();
							if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
								map.put(key, value);
							}
						}
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if(inputStreamReader != null) {
							inputStreamReader.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		LOGGER.info("系统properties文件内容初始化完毕！");
	}
}
