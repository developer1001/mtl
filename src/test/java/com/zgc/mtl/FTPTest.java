package com.zgc.mtl;

import com.zgc.mtl.common.util.FTPUtils;

public class FTPTest {

	public static void main(String[] args) {
		FTPUtils ftpUtils = new FTPUtils("localhost", 21, "zkr_employ", "Aa123456");
		ftpUtils.readFile("D:\\ftpFiles\\zkr", "99.txt");
	}

}
