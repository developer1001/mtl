package com.zgc.mtl.common.enu;

/**
 * 枚举类，工具类
 * @date 2019-07-23 15:51:05
 * @author yang
 */
public enum StrEnum {
	/**
	 * redis 存储的productId的key
	 */
	PRODUCTID("mtl-es-productId");
	private String string;

	StrEnum(String identity){
		string = identity;
	}
	
	public String getStr() {
		return string;
	}
}
