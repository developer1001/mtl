package com.zgc.mtl.common.util;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileSender { 	
//	public static void main(String[] args) throws Exception { 		
//		Socket socket = new Socket("localhost", 10087); 
//		File file = new File("D:\\eclipse2017-workspace\\test\\src\\tool\\Test.java");
//		String fileName = file.getName();
//		FileInputStream fis = new FileInputStream(file);
//		OutputStream out = socket.getOutputStream(); 		
//		BufferedOutputStream bos = new BufferedOutputStream(out); 		
//		byte[] arr = new byte[1024]; 		
//		int length = 0; 		
//		int count = 0;
//		while((length = fis.read(arr)) != -1) { 			
//			bos.write(arr, 0, length); 		
//			count++;
//			System.out.println("length:" + length + "count:" + count);
//			} 		
//		System.out.println("发送完了，推送：" + count + "次");
//		fis.close(); 		
//		bos.close(); 		
//		out.close(); 		
////		socket.shutdownOutput();	
////		socket.close(); 	
//		} 
	
	public static void main(String[] args){ 	
		Socket socket = null; 
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			String path = "D:\\video\\test.mp4";
			File file = new File(path);
			String fileName = file.getName();
			long fileSize = file.length();
			byte[] fileByteArr = FileUtil.toByteArr(path);
			MyFile myFile = new MyFile(fileName,fileSize,fileByteArr);
			socket = new Socket("127.0.0.1", 10087); 
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			oos.writeObject(myFile);
			System.out.println("文件发送完毕");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		} 
	}

