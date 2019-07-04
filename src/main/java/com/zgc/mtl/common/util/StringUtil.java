package com.zgc.mtl.common.util;

public class StringUtil {
    /**
     * 验证字符串是否有效
     * @param str
     * @return
     */
    public static boolean isValid(String str){

        return (str != null && !str.trim().equals("") &&  !str.trim().equals("null"));
    }
}
