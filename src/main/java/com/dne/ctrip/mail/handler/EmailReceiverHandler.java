/*
 * @(#)EmailReceiverHandler.java	2018年2月6日下午6:18:51
 * Copyright 2017 All rights reserved.
 */
package com.dne.ctrip.mail.handler;

import cn.hutool.core.util.RandomUtil;
import com.dne.core.util.DateUtils;
import com.dne.core.util.FileUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * 类<strong>EmailReceiverHandler.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2018年2月6日 下午6:18:51
 */
@Component
public class EmailReceiverHandler {

	private final static Logger log = LoggerFactory.getLogger(EmailReceiverHandler.class);

	@Value("${email.serverHost}")
	private String mailServerHost;

	@Value("${email.serverPort}")
	private String mailServerPort;

	@Value("${email.protocal}")
	private String mailProtocal;

	@Value("${email.userName}")
	private String mailUserName;

	@Value("${email.password}")
	private String mailPassword;

	/**
	 * 接收邮件附件
	 * @param appointSubject
	 * @param attachDir
	 */
	public List<String> receiveMailAttachment(String appointSubject, String attachDir) {
		List<String> attachList = Lists.newArrayList();
		Store store = null;
		try {
			Session session = Session.getInstance(getProperties());
			//new MyAuthenticator(mailUserName, mailPassword);
			session.setDebug(true);
			//URLName url = new URLName(protocol, host, port, file, username, password)
			store = session.getStore(mailProtocal);
			store.connect(mailServerHost,mailUserName, mailPassword);
			Folder folder = store.getFolder("INBOX");// 获取收件箱
			folder.open(Folder.READ_WRITE);
			int mailCount = folder.getMessageCount();
			log.info("Receive mail count: " + mailCount);
			if (mailCount > 0) {
				Message[] messages = folder.getMessages();
				for (Message message : messages) {
					MimeMessage msg = (MimeMessage)message;
					String subject = msg.getSubject();// 获得邮件主题
					if (subject.indexOf(appointSubject) != -1) {// 只处理指定主题的邮件
						log.info("Mail subject is: " + subject);// 主题
						log.info("Mail sender is: " + this.getFrom(msg));// 发件人
						log.info("Mail send data: " + DateUtils.dateToStr(msg.getSentDate(), "yyyy-MM-dd HH:mm:ss"));// 发送时间
						log.info("Mail received data: " + DateUtils.dateToStr(msg.getReceivedDate(), "yyyy-MM-dd HH:mm:ss"));// 接收时间
						log.info("Mail is seen: " + this.isSeen(msg));// 是否已读
						boolean isContainerAttachment = this.isContainAttachment(msg);
						log.info("Mail is include attachments: " + isContainerAttachment);// 是否包含附件
						if (isContainerAttachment) {
							this.saveAttachment(attachList, msg, attachDir); // 保存附件
						}
						message.setFlag(Flag.DELETED, true);//删除邮件
					}
				}
			}
			// 关闭 Folder 会真正删除邮件, false 不删除
			folder.close(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != store) {
				try {
					store.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		return attachList;
	}
	
	/**
	 * 接收邮件附件
	 * @param appointSubject
	 * @param attachDir
	 */
	public List<Map<String,Object>> receiveMailAttachmentExt(String appointSubject, String attachDir) {
		List<Map<String,Object>> attachMapList = Lists.newArrayList();
		Store store = null;
		try {
			Session session = Session.getInstance(getProperties());
			//new MyAuthenticator(mailUserName, mailPassword);
			session.setDebug(true);
			//URLName url = new URLName(protocol, host, port, file, username, password)
			store = session.getStore(mailProtocal);
			store.connect(mailServerHost,mailUserName, mailPassword);
			Folder folder = store.getFolder("INBOX");// 获取收件箱
			folder.open(Folder.READ_WRITE);
			int mailCount = folder.getMessageCount();
			log.info("Receive mail count: " + mailCount);
			if (mailCount > 0) {
				Message[] messages = folder.getMessages();
				for (Message message : messages) {
					MimeMessage msg = (MimeMessage)message;
					String subject = msg.getSubject();// 获得邮件主题
					if (subject.indexOf(appointSubject) != -1) {// 只处理指定主题的邮件
						log.info("Mail subject is: " + subject);// 主题
						String sendMan = this.getFrom(msg);
						Date sentDate  = msg.getSentDate();
						Date receivedDate = message.getReceivedDate()==null?new Date():message.getReceivedDate();
						log.info("Mail sender is: " + sendMan);// 发件人
						log.info("Mail send data: " + DateUtils.dateToStr(sentDate, "yyyy-MM-dd HH:mm:ss"));// 发送时间
						log.info("Mail received data: " + DateUtils.dateToStr(receivedDate, "yyyy-MM-dd HH:mm:ss"));// 接收时间
						log.info("Mail is seen: " + this.isSeen(msg));// 是否已读
						boolean isContainerAttachment = this.isContainAttachment(msg);
						log.info("Mail is include attachments: " + isContainerAttachment);// 是否包含附件
						Map<String,Object> mailInfo = Maps.newHashMap();
						mailInfo.put("subject", subject);
						mailInfo.put("sendMan", sendMan);
						mailInfo.put("sentDate", sentDate);
						mailInfo.put("sentDateStr", DateUtils.dateToStr(sentDate, "yyyy-MM-dd HH:mm:ss"));
						mailInfo.put("receivedDate", receivedDate);
						mailInfo.put("receivedDateStr", DateUtils.dateToStr(receivedDate, "yyyy-MM-dd HH:mm:ss"));
						mailInfo.put("isContainerAttachment", isContainerAttachment);
						List<Map<String,String>> attachList = Lists.newArrayList();
						if(isContainerAttachment){
							this.saveAttachmentExt(attachList, msg, attachDir); // 保存附件
						}
						mailInfo.put("attachList", attachList);
						StringBuffer content = new StringBuffer();  
			            getMailTextContent(msg, content); 
			            mailInfo.put("mailContent", content.toString());
						attachMapList.add(mailInfo);
						//message.setFlag(Flag.DELETED, true);//删除邮件
					}
				}
			}
			// 关闭 Folder 会真正删除邮件, false 不删除
			folder.close(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != store) {
				try {
					store.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		return attachMapList;
	}

	/**
	 * 获得邮件会话属性
	 */
	private Properties getProperties() {
		Properties localProperties = new Properties();
		Security.setProperty("ssl.SocketFactory.provider", "com.dne.ctrip.mail.handler.DNESSLSocketFactory");
		localProperties.setProperty("mail.store.protocol", mailProtocal);
		localProperties.put("mail.pop3.ssl.enable", true);
		localProperties.put("mail.pop3.host", mailServerHost);
		localProperties.put("mail.pop3.port", mailServerPort);
	    localProperties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    localProperties.put("mail.pop3.socketFactory.fallback", false);
	    localProperties.put("mail.pop3.auth", true);
		/*
		Security.setProperty("ssl.SocketFactory.provider", "javax.net.ssl.SSLSocketFactory");
		localProperties.setProperty("mail.pop3.host", mailServerHost);
		localProperties.setProperty("mail.pop3.port", mailServerPort);
		localProperties.setProperty("mail.pop3.auth", "true");
		localProperties.setProperty("mail.store.protocol", mailProtocal);
		localProperties.setProperty("mail.pop3.starttls.enable", "true");
		localProperties.put("mail.pop3.ssl.enable", true);
		//localProperties.setProperty("mail.pop3.socketFactory.class", "com.dne.didi.mail.handler.DNESSLSocketFactory");
		localProperties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		localProperties.setProperty("mail.pop3.socketFactory.fallback", "false");
		localProperties.setProperty("mail.pop3.socketFactory.port", mailServerPort);
		*/
		return localProperties;
	}

	/**
	 * 判断此邮件是否已读，如果未读返回返回false,反之返回true
	 * @param msg
	 * @return
	 * @throws MessagingException
	 */
	private boolean isSeen(MimeMessage msg) throws MessagingException {
		return msg.getFlags().contains(Flag.SEEN);
	}

	/**
	 * 判断邮件中是否包含附件
	 * @param msg 邮件内容
	 * @return 邮件中存在附件返回true，不存在返回false
	 * @throws MessagingException
	 * @throws IOException
	 */
	private boolean isContainAttachment(Part part) throws MessagingException, IOException {
		boolean flag = false;
		if (part.isMimeType("multipart/*")) {
			MimeMultipart multipart = (MimeMultipart)part.getContent();
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				String disp = bodyPart.getDisposition();
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
					flag = true;
				} else if (bodyPart.isMimeType("multipart/*")) {
					flag = isContainAttachment(bodyPart);
				} else {
					String contentType = bodyPart.getContentType();
					if (contentType.indexOf("application") != -1) {
						flag = true;
					}
					if (contentType.indexOf("name") != -1) {
						flag = true;
					}
				}
				if (flag)
					break;
			}
		} else if (part.isMimeType("message/rfc822")) {
			flag = isContainAttachment((Part)part.getContent());
		}
		return flag;
	}

	/**
	 * 保存附件
	 * @param part 邮件中多个组合体中的其中一个组合体
	 * @param attachDir 附件保存目录
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 * @throws IOException
	 */
	private void saveAttachment(List<String> attachList, Part part, String attachDir) throws MessagingException, IOException {
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart)part.getContent(); // 复杂体邮件 ,包含多个邮件体
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				// 获得复杂体邮件中其中一个邮件体
				BodyPart bodyPart = multipart.getBodyPart(i);
				// 某一个邮件体也有可能是由多个邮件体组成的复杂体
				String disp = bodyPart.getDisposition();
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
					String attachFile = attachDir + this.decodeText(bodyPart.getFileName());
					attachList.add(attachFile);
					FileUtils.copyFile(bodyPart.getInputStream(), attachFile);
				} else if (bodyPart.isMimeType("multipart/*")) {
					this.saveAttachment(attachList, bodyPart, attachDir);
				} else {
					String contentType = bodyPart.getContentType();
					if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
						String attachFile = attachDir + this.decodeText(bodyPart.getFileName());
						attachList.add(attachFile);
						FileUtils.copyFile(bodyPart.getInputStream(), attachFile);
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			saveAttachment(attachList, (Part)part.getContent(), attachDir);
		}
	}
	
	private void saveAttachmentExt(List<Map<String,String>> attachList, Part part, String attachDir) throws MessagingException, IOException {
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart)part.getContent(); // 复杂体邮件 ,包含多个邮件体
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				// 获得复杂体邮件中其中一个邮件体
				BodyPart bodyPart = multipart.getBodyPart(i);
				// 某一个邮件体也有可能是由多个邮件体组成的复杂体
				String disp = bodyPart.getDisposition();
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
					String attachName = this.decodeText(bodyPart.getFileName());
					String attachFile = attachDir + RandomUtil.getSecureRandom().hashCode()+"_"+attachName;
					Map<String,String> attachMap = Maps.newHashMap();
					attachMap.put("attachPath", attachFile);
					attachMap.put("attachName", attachName);
					attachList.add(attachMap);
					FileUtils.copyFile(bodyPart.getInputStream(), attachFile);
				} else if (bodyPart.isMimeType("multipart/*")) {
					this.saveAttachmentExt(attachList, bodyPart, attachDir);
				} else {
					String contentType = bodyPart.getContentType();
					if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
						String attachName = this.decodeText(bodyPart.getFileName());
						String attachFile = attachDir + RandomUtil.getSecureRandom().hashCode()+"_"+attachName;
						Map<String,String> attachMap = Maps.newHashMap();
						attachMap.put("attachPath", attachFile);
						attachMap.put("attachName", attachName);
						attachList.add(attachMap);
						FileUtils.copyFile(bodyPart.getInputStream(), attachFile);
					}
				}
			}
		} else if (part.isMimeType("message/rfc822")) {
			saveAttachmentExt(attachList, (Part)part.getContent(), attachDir);
		}
	}

	/**
	 * 获得邮件发件人
	 * @param msg 邮件内容
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
		String from = "";
		Address[] froms = msg.getFrom();
		if (froms.length < 1) {
			return from;
		}
		InternetAddress address = (InternetAddress)froms[0];
		String person = this.decodeText(address.getPersonal());
		from = person + "<" + address.getAddress() + ">";
		return from;
	}

	/**
	 * 文本解码
	 * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本
	 * @return 解码后的文本
	 * @throws UnsupportedEncodingException
	 */
	private String decodeText(String encodeText) throws UnsupportedEncodingException {
		if (encodeText == null || "".equals(encodeText)) {
			return "";
		} else {
			return MimeUtility.decodeText(encodeText);
		}
	}
	
	/** 
	 * 获得邮件文本内容 
	 * @param part 邮件体 
	 * @param content 存储邮件文本内容的字符串 
	 * @throws MessagingException 
	 * @throws IOException 
	 */  
	public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {  
		//如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
		boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
		if (part.isMimeType("text/*") && !isContainTextAttach) {  
			content.append(part.getContent().toString());  
		} else if (part.isMimeType("message/rfc822")) {   
			getMailTextContent((Part)part.getContent(),content);  
		} else if (part.isMimeType("multipart/*")) {  
			Multipart multipart = (Multipart) part.getContent();  
			int partCount = multipart.getCount();  
			for (int i = 0; i < partCount; i++) {  
				BodyPart bodyPart = multipart.getBodyPart(i);  
				getMailTextContent(bodyPart,content);  
			}  
		}  
	}  
}
