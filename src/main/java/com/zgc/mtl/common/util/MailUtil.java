package com.zgc.mtl.common.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.zgc.mtl.model.Mail;

/**
 * springboot邮件工具类
 * @date 2019-07-30 09:49:49
 * @author yang
 */
@Component
public class MailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
    private String sender; // 读取配置文件中的参数
	
	/**
	 * 发送简易邮件
	 * @param mail
	 */
	public void sendSimpleMail(Mail mail) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setTo(mail.getReceivers());
		simpleMailMessage.setSubject(mail.getSubject());
		simpleMailMessage.setText(mail.getText());
		javaMailSender.send(simpleMailMessage);
	}
	
	/**
	 * 发送普通邮件
	 * @param mail
	 * @throws MessagingException 
	 */
	public void sendMail(Mail mail) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage);	
		helper.setFrom(sender);
		helper.setTo(mail.getReceivers());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getText());
		javaMailSender.send(mimeMessage);
	}
	
	/**
	 * 发送html邮件
	 * @param mail
	 * @throws MessagingException 
	 */
	public void sendHtmlMail(Mail mail) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage);
		helper.setFrom(sender);
		helper.setTo(mail.getReceivers());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getText(),true);
		javaMailSender.send(mimeMessage);
	}
	
	/**
	 * 发送附件邮件
	 * @param mail 邮件主体
	 * @param diskPath 文件在本地磁盘的路径，本参数-diskPath其实只为利于测试,
	 * @throws MessagingException 
	 */
	public void sendAttachmentMail(Mail mail) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper  = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(sender);
		helper.setTo(mail.getReceivers());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getText(),true);
		FileSystemResource fsr = null;
		if(null != mail.getDiskPath()) {
			 fsr = new FileSystemResource(new File(mail.getDiskPath()));
		}
		else {
			fsr = mail.getFile();
		}
		// 增加附件名称和附件
		 helper.addAttachment(mail.getAttachmentFilename(), fsr);
		javaMailSender.send(mimeMessage);
	}
}
