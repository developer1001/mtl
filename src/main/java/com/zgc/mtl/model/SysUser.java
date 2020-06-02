package com.zgc.mtl.model;
/**
 * 
 *description: 系统用户
 *Author:laoyangtou
 *2018年7月31日 下午3:06:21
 */
public class SysUser {
	//主键id
	private Integer userId;
	//登录名
	private String loginName;
	//用户名
	private String userName;
	//密码
	private String password;
	//是否激活启用：1是，0否
	private Integer isActive;
	
	public Integer getUserId() {
		return userId;
	}
	public void setId(Integer id) {
		this.userId = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
