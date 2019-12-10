package com.zgc.mtl.common.util;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;
import com.zgc.mtl.model.Mail;

/**
 * 企业邮箱
 * @date 2019-11-27 11:27:06
 * @author yang
 */
public class QYEmailUtil {
	//发送者
	private String from = "@sinosoft.com.cn";
	//授权码
	private String pass = "we6Qy8vfgB4ebpei";
	//邮件署名
	private String senderName = "管理员";
	private class MyAuthenricator extends Authenticator {
	      public MyAuthenricator() {
	      }
 
	      @Override
	      public PasswordAuthentication getPasswordAuthentication() {
	          return new PasswordAuthentication(from, pass);
	      }
	  }
	  
	  /**
	   * 发送邮件
	   * @param mail
	   */
	  public  void send(Mail mail){
	  	Properties prop = new Properties();
	      //协议
	      prop.setProperty("mail.transport.protocol", "smtp");
	      //服务器
	      prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
	      //端口
	      prop.setProperty("mail.smtp.port", "465");
	      //使用smtp身份验证
	      prop.setProperty("mail.smtp.auth", "true");
	      //使用SSL，企业邮箱必需！
	      //开启安全协议
	      MailSSLSocketFactory sf = null;
	      try {
	          sf = new MailSSLSocketFactory();
	          sf.setTrustAllHosts(true);
	      } catch (GeneralSecurityException e) {
	          e.printStackTrace();
	      }
	      prop.put("mail.smtp.ssl.enable", "true");
	      prop.put("mail.smtp.ssl.socketFactory", sf);
	      Session session = Session.getInstance(prop, new MyAuthenricator());
	      session.setDebug(false);
	      MimeMessage mimeMessage = new MimeMessage(session);
	      try {
	    	  String[] arr = mail.getReceivers();
	    	  List<InternetAddress> receiv = new ArrayList<>();
	    	  if(arr != null) {
	    		  for(String receiver : arr) {
	    			  receiv.add(new InternetAddress(receiver));
	    		  }
	    	  }
	    	  InternetAddress[] receivers = new InternetAddress[arr.length];
	          mimeMessage.setFrom(new InternetAddress(from, senderName));
	          mimeMessage.addRecipients(Message.RecipientType.TO, receiv.toArray(receivers));
	          mimeMessage.setSubject(mail.getSubject());
	          mimeMessage.setSentDate(new Date());
	          Multipart multipart = new MimeMultipart();
	          BodyPart part1 = new MimeBodyPart();
              part1.setContent(mail.getText(), "text/html;charset=utf-8");//设置邮件文本内容  
              multipart.addBodyPart(part1); 
//              if(filePath != null) {
//            	  MimeBodyPart attachment = new MimeBodyPart();
//            	  attachment.attachFile(filePath);
//            	  multipart.addBodyPart(attachment);
//              }
              mimeMessage.setContent(multipart); 
	          mimeMessage.saveChanges();
	          Transport.send(mimeMessage);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	  }
}