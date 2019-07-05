package com.zgc.mtl.common.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiver {

//	public static void main(String[] args) throws IOException {
//		ServerSocket serverSocket = new ServerSocket(10087);
//		Socket socket = serverSocket.accept();
//		InputStream in = socket.getInputStream();
//		FileOutputStream fos = new FileOutputStream(new File(
//				new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".file"));
//		BufferedOutputStream bos = new BufferedOutputStream(fos);
//		byte[] arr = new byte[1024];
//		int length = 0;
//		int count = 0;
//		while((length = in.read(arr)) != -1) {
//			bos.write(arr, 0, length);
//			System.out.println("count:" + count + "; length:" + length);
//		}
//		System.out.println("文件收取完毕");
//		bos.close();
//		fos.close();
//		in.close();
////		socket.close();
////		serverSocket.close();
//	}

	public static void main(String[] args){
		ServerSocket serverSocket = null;
		Socket socket = null;
		String filePath = null;
		try {
			serverSocket = new ServerSocket(10087);
			socket = serverSocket.accept();
			InputStream in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			MyFile myFile = (MyFile) ois.readObject();
			filePath = "d:\\111";
			String fileName = myFile.getFileName();
			byte[] fileByteArr = myFile.getFileByteArr();
			FileUtil.toFile(filePath, fileName, fileByteArr);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null) {
					socket.close();
				}
				Desktop.getDesktop().open(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
