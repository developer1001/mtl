package com.zgc.mtl.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	/**
	 * 文件转字节数组
	 * @param path 本地文件的路径
	 * @return
	 */
	public static byte[] toByteArr(String path) {
		long start = System.currentTimeMillis();
		try {
			File file = new File(path);
			FileInputStream in = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(in);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
//			BufferedOutputStream bos = new BufferedOutputStream(baos);
			byte[] buff = new byte[1024];
			int len = 0;
			while((len = bis.read(buff)) != -1) {
				baos.write(buff, 0, len);
			}
			byte[] data = baos.toByteArray();
//			if(bos != null) {
//				bos.close();
//			}
			if(baos != null) {
				baos.close();
			}
			if(bis != null) {
				bis.close();
			}
			if(in != null) {
				in.close();
			}
			System.out.println("文件转字节数组，耗时：" + (System.currentTimeMillis() - start) + 
					"ms");
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 文件字节数组转文件
	 * @param filePath 存储文件目标路径
	 * @param fileName 存储文件目标名字
	 * @param sourceFile 源文件字节数组
	 */
	public static void toFile(String filePath, String fileName, byte[] sourceFile){
		long start = System.currentTimeMillis();
		if(sourceFile.length == 0) {
			System.out.println("源文件数组为空，转化为文件预计将会失败");
		}
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(filePath, File.separator + fileName);
		if(file.exists() && !file.isDirectory()) {
			File[] fileList = dir.listFiles();
			List<String> nameList = new ArrayList<>();
			if(fileList != null) {
				for(File f : fileList) {
					nameList.add(f.getName());
				}
			}
			int max = 1;
			for(String name : nameList) {
				String nameStr = name.substring(0,name.lastIndexOf("."));
				if(nameStr.contains("(") && nameStr.contains(")")) {
					int a = nameStr.lastIndexOf("(");
					int b = nameStr.lastIndexOf(")");
					if(a > 0 && b > 0 && (b - a) >= 2) {
						String numStr = nameStr.substring(a + 1,b);
						try {
							int num  = Integer.parseInt(numStr);
							if(num >= max) {
								max = num + 1;
							}
						} catch (NumberFormatException e) {
						}
					}
				}
			}
			String newFileName = fileName.substring(0,fileName.lastIndexOf(".")) + 
					"(" + max + ")" + fileName.substring(fileName.lastIndexOf("."));
			file = new File(filePath, File.separator + newFileName);
		}
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(sourceFile);
			bos.close();
			System.out.println("字节数组转文件，耗时：" + (System.currentTimeMillis() - start)
					 + "ms");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}