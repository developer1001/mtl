package com.zgc.mtl.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @date 2020-05-09 17:09:17O
 * @author yang
 */
public class ExcelUtil {
	/**
	 * 读取指定文件，将文件内容（Navicat导出表结构sql文件）以指定格式输出到Excel
	 * @param filePath
	 * @throws IOException
	 */
	public static void tableFieldFormat(String filePath) throws IOException {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	    HttpServletRequest request = servletRequestAttributes.getRequest();
	    HttpServletResponse response = servletRequestAttributes.getResponse();
		//1.在内存中创建一个excel文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		
		File file = new File(filePath);
		FileReader ir = new FileReader(file);
		BufferedReader br = new BufferedReader(ir);
		String lineStr = null;
		int index = -1;
		while((lineStr  = br.readLine()) != null) {
			if(lineStr.contains("CREATE TABLE")) {
				//表名
				String tableName = lineStr.substring(lineStr.indexOf("`") + 1, lineStr.lastIndexOf("`"));
				index += 1;
				 //2.创建工作簿
		        HSSFSheet sheet = hssfWorkbook.createSheet();
		        sheet.setDefaultColumnWidth(18);
		        //3.创建头部
		        HSSFRow headRow = sheet.createRow(0);
		        //3.创建标题行
		        HSSFRow titlerRow = sheet.createRow(1);
		        titlerRow.createCell(0).setCellValue("字段名称");
//		        titlerRow.
		        titlerRow.createCell(1).setCellValue("字段类型");
		        titlerRow.createCell(2).setCellValue("类型说明");
		        titlerRow.createCell(3).setCellValue("是否为空");
		        titlerRow.createCell(4).setCellValue("字段含义");
		        titlerRow.createCell(5).setCellValue("备注");
		        
				//循环单表内容
				while((lineStr  = br.readLine()) != null && lineStr.length() > 5) {
					if(lineStr.charAt(2) == '`') {
						//字段名
						String fieldName = lineStr.substring(3, lineStr.indexOf("`", 3));
						//字段类型
						int fieldStart = lineStr.indexOf(" ", 3);
						int fieldEnd = lineStr.indexOf(" ", fieldStart + 1);
						String fieldType = lineStr.substring(fieldStart, fieldEnd);
						//类型说明
						String typeInfo = "";
						//是否为空
						String empty = "是";
//						if(lineStr.contains("DEFAULT NULL")) {
//							empty = "是";
//						}
						if(lineStr.contains("NOT NULL")) {
							empty = "否";
						}
						//评论
						String comment = "";
						int commentStart = lineStr.indexOf("COMMENT");
						if(commentStart != -1) {
							commentStart = lineStr.indexOf("'", commentStart + 1);
							int conmmentEnd = lineStr.lastIndexOf("'");
							comment = lineStr.substring(commentStart + 1, conmmentEnd);
						}
						//备注
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
//				int nextInt = new Random().nextInt();
//				hssfWorkbook.setSheetName(index, tableName + "_" + nextInt);
				System.out.println(tableName);
				 headRow.createCell(0).setCellValue(tableName);
				
			}
		}
		
//        //4.遍历数据,创建数据行
//        for (Area area : list) {
//            //获取最后一行的行号
//            int lastRowNum = sheet.getLastRowNum();
//            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
//            dataRow.createCell(0).setCellValue(area.getProvince());
//            dataRow.createCell(1).setCellValue(area.getCity());
//            dataRow.createCell(2).setCellValue(area.getDistrict());
//            dataRow.createCell(3).setCellValue(area.getPostcode());
//            dataRow.createCell(4).setCellValue(area.getShortcode());
//            dataRow.createCell(5).setCellValue(area.getCitycode());
//        }
        //5.创建文件名
        String fileName = "table-field-info.xls";
        //6.获取输出流对象
        ServletOutputStream outputStream = response.getOutputStream();

        //8.获取浏览器信息,对文件名进行重新编码
//        fileName = FileUtils.filenameEncoding(fileName, request);

        //9.设置信息头
//        response.setContentType(ContentType.);
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        //10.写出文件,关闭流
        hssfWorkbook.write(outputStream);
        hssfWorkbook.close();
        br.close();
    }
}
