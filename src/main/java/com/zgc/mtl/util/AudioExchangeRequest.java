package com.zgc.mtl.util;

public class AudioExchangeRequest {
	/**
	 * 音频类型，WAV或MP3
	 */
	private String voiceFormat;
	/**
	 * 识别引擎 8k or 16k 
	 */
	private String engSerViceType;
	/**
	 * 文件被转成的字节数组
	 */
	private byte[] fileByteArr;
	
	public String getVoiceFormat() {
		return voiceFormat;
	}
	public void setVoiceFormat(String voiceFormat) {
		this.voiceFormat = voiceFormat;
	}
	public String getEngSerViceType() {
		return engSerViceType;
	}
	public void setEngSerViceType(String engSerViceType) {
		this.engSerViceType = engSerViceType;
	}
	public byte[] getFileByteArr() {
		return fileByteArr;
	}
	public void setFileByteArr(byte[] fileByteArr) {
		this.fileByteArr = fileByteArr;
	}
	
}
