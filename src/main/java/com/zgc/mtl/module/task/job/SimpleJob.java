package com.zgc.mtl.module.task.job;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zgc.mtl.common.util.MailUtil;
import com.zgc.mtl.model.Mail;

@Component
public class SimpleJob {
	private static final Logger logger = LoggerFactory.getLogger(SimpleJob.class);
	@Value("${sucureData.dbbackup.filePath}")
	private String filePath;
	@Value("${sucureData.dbbackup.mail.to}")
	private String receivers;
	@Autowired
	private MailUtil mailUtil;
	
	//发备份数据邮件
	@Scheduled(cron = "0 0 9 1/5 * ?")
	public void backupDbJob() throws Exception {
		//原始文件名
		String attachmentName = backupDb(filePath);
		Mail mail = new Mail();
		mail.setReceivers(receivers.split(","));
		mail.setSubject("db backup file");
		mail.setText("请接收最新的备份文件。");
		mail.setAttachmentFilename(attachmentName);
		mail.setDiskPath(filePath + attachmentName);
		mailUtil.sendAttachmentMail(mail);
		logger.info("数据备份邮件已推送");
	}
	
	/**
	 * 监测有无最新文件生成
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 9 * * ?")
	public void backupCheking() throws Exception {
		//原始文件名
		String attachmentName = backupDb(filePath);
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		String date = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
		if(attachmentName.contains(date)) {
			logger.info("今日的备份文件已生成");
			return;
		}
		String message = "今日的数据库备份文件没有生成，最近的一次备份(" + attachmentName
				+ ")以附件形式包含在本邮件中，请及时检查服务器相关服务是否正常。";
		Mail mail = new Mail();
		mail.setReceivers(receivers.split(","));
		mail.setSubject("今日数据库备份文件未检测到");
		mail.setText(message);
		mail.setAttachmentFilename(attachmentName);
		mail.setDiskPath(filePath + attachmentName);
		mailUtil.sendAttachmentMail(mail);
		logger.error(message);
	}
	
	public String backupDb(String fileName) {
		if(StringUtils.isBlank(fileName)) {
			logger.info("文件所在磁盘路径为空！将采用默认路径");
			fileName = filePath;
		}
		File dir = new File(fileName);
		if (dir.exists()) {
			// 列出该目录下所有文件和文件夹
			File[] files = dir.listFiles();
			// 按照文件最后修改日期倒序排序
			Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File file1, File file2) {
					return (int) (file2.lastModified() - file1.lastModified());
				}
			});
			fileName = files[0].getName();
		}
		return fileName;
	}
}
