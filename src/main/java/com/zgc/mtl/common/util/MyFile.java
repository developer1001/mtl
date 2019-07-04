package com.zgc.mtl.common.util;

import java.io.Serializable;

public class MyFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9003393231829226396L;
	private String fileName;
	private long fileSize;
	private byte[] fileByteArr;
	public MyFile() {
	}
	public MyFile(String fileName, long fileSize, byte[] fileByteArr) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileByteArr = fileByteArr;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public byte[] getFileByteArr() {
		return fileByteArr;
	}
	public void setFileByteArr(byte[] fileByteArr) {
		this.fileByteArr = fileByteArr;
	}
}
