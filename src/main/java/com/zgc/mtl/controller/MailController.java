package com.zgc.mtl.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgc.mtl.common.util.MailUtil;
import com.zgc.mtl.common.util.QYEmailUtil;
import com.zgc.mtl.model.Mail;

/**
 * 发送邮件
 * @date 2019-07-30 10:55:23
 * @author yang
 */
@RestController
@RequestMapping("mail")
public class MailController {
	@Autowired
	private MailUtil mailUtil;
	@RequestMapping(value = "/simple")
	public void sendSimpleMail(Mail mail) {
		mailUtil.sendSimpleMail(mail);
	}
	
	@PostMapping(value = "/general", produces = "application/json;charset=utf-8")
	public void sendGeneralMail(@RequestBody Mail mail) throws MessagingException {
		mailUtil.sendMail(mail);;
	}
	
	@PostMapping(value = "/html", produces = "application/json;charset=utf-8")
	public void sendHtmlMail(@RequestBody Mail mail) throws MessagingException {
		mailUtil.sendHtmlMail(mail);
	}
	
	@PostMapping(value = "/attachment", produces = "application/json;charset=utf-8")
	public void sendAttachmentMail(@RequestBody Mail mail) throws MessagingException {
		mailUtil.sendAttachmentMail(mail);
	}
	
	@RequestMapping(value = "/qiyeEmail")
	public void qiyeEmail(Mail mail) {
		new QYEmailUtil().send(mail);
	}
}
