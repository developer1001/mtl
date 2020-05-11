package com.zgc.mtl.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FTP工具类
 * 
 * @date 2020-04-10 15:15:44
 * @author yang
 */
public class FTPUtils {
	private String host;
	private int port;
	private String userName;
	private String password;
	
	public FTPUtils() {
	}
	
	public FTPUtils(String host, int port, String userName, String password) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}
	
	public FTPClient getFTPClient() {
		FTPClient ftpClient = null;
		// 创建一个ftp客户端
		ftpClient = new FTPClient();
		try {
			// 连接FTP服务器
			ftpClient.connect(host, port);
			// 登陆FTP服务器
			ftpClient.login(userName, password);
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				// log.info("FTP连接失败，用户登录信息有误或权限不足");
				ftpClient.disconnect();
				// throw new bu
			}
		} catch (IOException e) {
			e.printStackTrace();
			// throw new bu("FTP连接失败，请检查网络等配置");
		}
		System.out.println("FTP连接成功。");
		return ftpClient;
	}
	
	public void readFile(String ftpPath, String fileName) {
		FTPClient ftpClient = null;
		try {
			ftpClient = getFTPClient();
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);
			File localFile = new File(File.separatorChar + fileName);
			FileInputStream fis = new FileInputStream(localFile);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			isr.close();
			fis.close();
			ftpClient.logout();
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void downloadFile(String ftpPath, String filePath, String fileName) {
		FTPClient ftpClient = null;
			try {
				ftpClient = getFTPClient();
				ftpClient.setControlEncoding("UTF-8"); // 中文支持
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				ftpClient.changeWorkingDirectory(ftpPath);
				File localFile = new File(filePath + File.separatorChar + fileName);
				OutputStream os = new FileOutputStream(localFile);
				ftpClient.retrieveFile(fileName, os);
				os.close();
				ftpClient.logout();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void uploadFile(String ftpPath, String fileName, InputStream input) {
		FTPClient ftp = null;
		try {
			ftp = getFTPClient();
			ftp.makeDirectory(ftpPath);
			ftp.changeWorkingDirectory(ftpPath);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			ftp.storeFile(fileName, input);
			input.close();
			ftp.logout();
			System.out.println("upload succes!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
