package com.zgc.mtl.model;
/**
 * mail,邮件，spring-boot-starter-mail
 * @date 2019-07-30 09:30:10
 * @author yang
 */

import org.springframework.core.io.FileSystemResource;

public class Mail {
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 内容
	 */
	private String text;
	/**
	 * 邮件接受者
	 */
	private String[] receivers;
	/**
	 * 附件
	 */
	private FileSystemResource file;
	/**
	 * 附件在磁盘上的位置(测试用，非实体类所需字段)
	 */
	private String diskPath;
	/**
	 * 附加名称
	 */
	private String attachmentFilename;
	/**
	 * 内容id
	 */
	private String contentId;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public FileSystemResource getFile() {
		return file;
	}
	public void setFile(FileSystemResource file) {
		this.file = file;
	}
	public String getAttachmentFilename() {
		return attachmentFilename;
	}
	public void setAttachmentFilename(String attachmentFilename) {
		this.attachmentFilename = attachmentFilename;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String[] getReceivers() {
		return receivers;
	}
	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}
	public String getDiskPath() {
		return diskPath;
	}
	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}
}
