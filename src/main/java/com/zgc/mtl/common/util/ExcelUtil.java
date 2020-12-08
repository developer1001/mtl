package com.zgc.mtl.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @date 2020-05-09 17:09:17O
 * @author yang
 */
public class ExcelUtil {
	/**
	 * 读取指定文件，将文件内容（Navicat导出表结构sql文件）以指定格式输出到Excel
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void tableFieldFormat(String filePath) throws IOException {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
//		HttpServletRequest request = servletRequestAttributes.getRequest();
		HttpServletResponse response = servletRequestAttributes.getResponse();
		// 1.在内存中创建一个excel文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

		File file = new File(filePath);
		FileReader ir = new FileReader(file);
		BufferedReader br = new BufferedReader(ir);
		String lineStr = null;
		// int index = -1;
		while ((lineStr = br.readLine()) != null) {
			if (lineStr.contains("CREATE TABLE")) {
				// 表名
				String tableName = lineStr.substring(lineStr.indexOf("`") + 1, lineStr.lastIndexOf("`"));
				// index += 1;
				// 2.创建工作簿
				HSSFSheet sheet = hssfWorkbook.createSheet();
				sheet.setDefaultColumnWidth(18);
				// 3.创建头部
				HSSFRow headRow = sheet.createRow(0);
				// 字体设置
				HSSFFont font = hssfWorkbook.createFont();
				font.setFontName("宋体");
				font.setBold(true);
				font.setCharSet(30);
				font.setColor(HSSFFont.COLOR_NORMAL);
				// 样式
				HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
				cellStyle.setFont(font);
				cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
				// 合并单元格
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
				// 3.创建标题行
				HSSFRow titlerRow = sheet.createRow(1);
				titlerRow.createCell(0).setCellValue("字段名称");
				titlerRow.createCell(1).setCellValue("字段类型");
				titlerRow.createCell(2).setCellValue("类型说明");
				titlerRow.createCell(3).setCellValue("是否为空");
				titlerRow.createCell(4).setCellValue("字段含义");
				titlerRow.createCell(5).setCellValue("备注");

				// 循环单表内容
				while ((lineStr = br.readLine()) != null && lineStr.length() > 5) {
					if (lineStr.charAt(2) == '`') {
						// 字段名
						String fieldName = lineStr.substring(3, lineStr.indexOf("`", 3));
						// 字段类型
						int fieldStart = lineStr.indexOf(" ", 3);
						int fieldEnd = lineStr.indexOf(" ", fieldStart + 1);
						String fieldType = lineStr.substring(fieldStart, fieldEnd);
						// 类型说明
						String typeInfo = "";
						// 是否为空
						String empty = "是";
						// if(lineStr.contains("DEFAULT NULL")) {
						// empty = "是";
						// }
						if (lineStr.contains("NOT NULL")) {
							empty = "否";
						}
						// 评论
						String comment = "";
						int commentStart = lineStr.indexOf("COMMENT");
						if (commentStart != -1) {
							commentStart = lineStr.indexOf("'", commentStart + 1);
							int conmmentEnd = lineStr.lastIndexOf("'");
							comment = lineStr.substring(commentStart + 1, conmmentEnd);
						}
						// 备注
						String remark = "";

						int lastRowNum = sheet.getLastRowNum();
						HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
						dataRow.createCell(0).setCellValue(fieldName);
						dataRow.createCell(1).setCellValue(fieldType);
						dataRow.createCell(2).setCellValue(typeInfo);
						dataRow.createCell(3).setCellValue(empty);
						dataRow.createCell(4).setCellValue(comment);
						dataRow.createCell(5).setCellValue(remark);
					}
				}
				// hssfWorkbook.setSheetName(index, tableName);
				headRow.createCell(0).setCellValue(tableName);
				headRow.getCell(0).setCellStyle(cellStyle);
			}
		}

		// 5.创建文件名
		String fileName = "table-field-info.xls";
		// 6.获取输出流对象
		ServletOutputStream outputStream = response.getOutputStream();

		// 8.获取浏览器信息,对文件名进行重新编码
		// fileName = FileUtils.filenameEncoding(fileName, request);

		// 9.设置信息头
		// response.setContentType(ContentType.);
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		// 10.写出文件,关闭流
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
		br.close();
	}

	public static void main(String[] args) {
		//SELECT CONTENT_ID, CONTENT_TITLE,ORIGINAL_URL FROM content_info WHERE CONTENT_TYPE = '0'
		
		/*
		 * UPDATE content_info SET CLICK_COUNT = CAST((1500 * RAND()) AS SIGNED),
			FORWARD_COUNT = CAST((1500 * RAND()) AS SIGNED),
			STATE = '2'
			WHERE CONTENT_ID >= 1357;
		 */
		LinkedHashMap<String, String> sheetMap = new LinkedHashMap<String, String>();
		HashMap<String, String> sheetMap2 = new HashMap<String, String>();
//		HashMap<String, String[]> sheetMap3 = new HashMap<String, String[]>();
		//标签和内容关联插入的sql
		String values = "";
		try {
			XSSFWorkbook work = new XSSFWorkbook(new FileInputStream("pro.xlsx"));// 得到这个excel表格对象
			XSSFSheet sheet = work.getSheetAt(0); // 得到第一个sheet
			int rowNo = sheet.getLastRowNum(); // 得到行数
			for (int i = 1; i < rowNo; i++) {
				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.getCell((short) 3);
				XSSFCell cell1 = row.getCell((short) 6);
				if (cell1 != null && StringUtils.isNotBlank(cell1.getStringCellValue()) && cell1.getStringCellValue().startsWith("http")) {
					String type = cell.getStringCellValue().trim();
					if (StringUtils.isBlank(type)) {
						for (int j = i; j > 0; j--) {
							type = sheet.getRow(j).getCell((short) 3).getStringCellValue().trim();
							if (StringUtils.isNotBlank(type)) {
								break;
							}
						}
					}
					sheetMap.put(cell1.getStringCellValue().trim(), type);
//					System.out.println(cell1.getStringCellValue().trim() + "------" + type);
					//预备sql
					//建立关联
					values += "INSERT INTO content_label_relationship (`CONTENT_ID`, `LABEL_ID`) VALUES (" 
					+ "(SELECT CONTENT_ID FROM content_info WHERE ORIGINAL_URL = '" + cell1.getStringCellValue().trim() + "' LIMIT 0,1)" + 
							", (SELECT COLUMN_ID FROM content_column WHERE COLUMN_NAME = '" + type + "'  AND LABEL_SQL IS NOT NULL));\r\n";
				}
			}
			System.out.println(values);
			XSSFSheet sheet2 = work.getSheetAt(1); 
			rowNo = sheet2.getLastRowNum(); // 得到行数
			for (int i = 1; i < rowNo; i++) {
				XSSFRow row = sheet2.getRow(i);
				XSSFCell cell = row.getCell((short) 0);
				XSSFCell cell1 = row.getCell((short) 1);
				if (cell1 != null && StringUtils.isNotBlank(cell1.getStringCellValue())) {
					sheetMap2.put(cell1.getStringCellValue().trim(), cell.getStringCellValue().trim());
				}
			}
			for (Entry<String, String> e :sheetMap.entrySet()){
				if (sheetMap2.containsKey(e.getKey())) {
					System.out.println("!!!提醒：" + sheetMap2.get(e.getKey())+ "    已经处理过了，本次处理跳过此文章");
				} else {
					if (!e.getKey().startsWith("http")) {
//						System.out.println("非文章，不添加-------------------------");
					} else {
						System.out.println("请求添加文章数据：" + sheetMap.get(e.getKey()) + "--------------" + e.getKey());
						String url = "https://ins-int.yixiubx.com/marketingContent/api/v1.0/article/import";
						HashMap<String, String> param = new HashMap<String, String>();
						param.put("originalUrl", e.getKey());
						HashMap<String, String> headers = new HashMap<String, String>();
						headers.put("appid", "wx9989962e43e4030f");
						headers.put("openid", "ovAFZ6NCHK46MKdQJ-9qzTkzIRVQ");
						long s = System.currentTimeMillis();
						try {
//							HttpUtils.post(url, JSONObject.toJSONString(param), headers);
//							String id = ((Map)(JSONObject.parseObject(json, Map.class).get("object"))).get("contentId").toString();
							System.out.println("耗时：" + (System.currentTimeMillis() - s));
						} catch (Exception e1) {
							e1.printStackTrace();
							System.out.println(e.getKey() + "导入出问题了：");
						}
					}
				}
			}
			work.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
