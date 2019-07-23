package com.zgc.mtl.common.enu;

/**
 * 返回结果的标志码
 * 
 * @date 2019-07-23 10:54:29
 * @author yang
 */
public enum ResultCode {
	/**
	 * 成功
	 */
	SUCCESS(1), 
	/**
	 * 失败
	 */
	FAILURE(0);
	
	private int code;
	
	ResultCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
