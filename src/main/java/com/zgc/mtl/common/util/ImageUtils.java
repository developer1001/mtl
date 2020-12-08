package com.zgc.mtl.common.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zgc.mtl.common.enu.ImageEnum;

/**
 * 图片工具类
 * @date 2020-09-09 15:03:42
 * @author yang
 */
public class ImageUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);
	
	public static String imgToBase64(ImageEnum imgType, String imgUrl) {
		long currentTimeMillis = System.currentTimeMillis();
		byte[] byteArr = getByteArr(imgUrl);
		System.out.println("文件转字节数组：" + (System.currentTimeMillis() - currentTimeMillis));
		byte[] encode = Base64.getEncoder().encode(byteArr);
		String result = "data:image/" + imgType.getName() +";base64," + new String(encode);
		return result;
	}
	
	/**
	 * 以字节数组形式获取远程地址文件
	 * @param FileUrl
	 * @return
	 */
	public static byte[] getByteArr(String FileUrl) {
		BufferedInputStream bis = null;
		byte[] buffer = null;
		try {
			long currentTimeMillis = System.currentTimeMillis();
			URL uri = new URL(FileUrl);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setConnectTimeout(3000);
			System.out.println("openConnection：" + (System.currentTimeMillis() - currentTimeMillis));
			bis = new BufferedInputStream(conn.getInputStream());
			buffer = new byte[bis.available()];
			bis.read(buffer);
		} catch (FileNotFoundException e) {
			LOGGER.error("文件转base64未找到文件", e);
		} catch (IOException e) {
			LOGGER.error("文件转base64出现异常", e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					LOGGER.error("文件转base64关闭流失败", e);
				}
			}
		}
		return buffer;
	}
	
}
