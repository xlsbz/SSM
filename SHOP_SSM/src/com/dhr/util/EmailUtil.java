package com.dhr.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送激活邮件
 * 
 * @author Mr DU to:发送给谁 content:邮件内容
 */
public class EmailUtil {
	public static void sendEmail(String to, String content) {
		// 1.导入jar包
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");// 本地邮件服务器
		props.setProperty("mail.smtp.auth", "true");

		// 2.创建session对象
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			// 设置用户名密码
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("server@mrdu.com", "123");
			}

		});

		try {
			// 3.创建mimemessgae对象
			MimeMessage message = new MimeMessage(session);
			// 设置发送主题，内容，发件人，收件人，发送方式等
			message.setSubject("乐淘商城官方邮件");
			message.setContent(content, "text/html;charset=utf-8");
			message.setSender(new InternetAddress("server@mrdu.com"));
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			// 4.发送
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
