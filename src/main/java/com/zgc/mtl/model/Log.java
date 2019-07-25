package com.zgc.mtl.model;

import java.util.Date;

/**
 * mongdb记录日志，测试用
 * @date 2019-07-25 14:41:51
 * @author yang
 */
public class Log {
	/**
	 * id
	 */
	private String logId;
	/**
	 * 日志类型
	 */
	private Integer logType;
	/**
	 * 日志内容
	 */
	private String content;
	/**
	 * 日志创建日期
	 */
	private Date createDate;
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
