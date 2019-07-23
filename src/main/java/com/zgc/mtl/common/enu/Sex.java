package com.zgc.mtl.common.enu;
/**
 * 性别
 * @date 2019-07-23 14:26:25
 * @author yang
 */
public enum Sex {
	/**
	 * 男
	 */
	MALE(0),
	/**
	 * 女
	 */
	FEMALE(1);
	
	private final int sex;
	Sex(int sex){
		this.sex = sex;
	}
	
	public int getSex(){
		return sex;
	}
}
