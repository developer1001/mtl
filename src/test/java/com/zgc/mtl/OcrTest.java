package com.zgc.mtl;

import org.junit.Test;

import com.zgc.mtl.common.util.OCRUtil;

public class OcrTest {
	@Test
	public void ocr() throws Exception {
		String fileUrl = "‪C:\\bd_logo1.png";
		OCRUtil.ocr(fileUrl);
	}
}
