package com.zgc.mtl.common.util;

import com.zgc.mtl.common.enu.ResultStatusCode;

public class Result {
	/**
	 * 返回的code
	 */
	private int code;
	/**
	 * 返回的信息
	 */
    private String msg;
	/**
	 * 返回的数据
	 */
    private Object data;

	public Result(int code, String msg, Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Result(ResultStatusCode resultStatusCode, Object data){
		this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
	}

    public Result(int code, String msg){
    	this(code, msg, null);
	}

	public Result(ResultStatusCode resultStatusCode){
    	this(resultStatusCode, null);
	}
    
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
