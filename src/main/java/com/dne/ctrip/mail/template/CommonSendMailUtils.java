/*
 * @(#)CommonSendMailUtils.java	2018年1月24日下午5:42:59
 * Copyright 2017 All rights reserved.
 */
package com.dne.ctrip.mail.template;

import com.dne.core.basic.entity.BaseEntity;
import com.dne.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类<strong>CommonSendMailUtils.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2018年1月24日 下午5:42:59
 */
public class CommonSendMailUtils {

	private static final Logger log = LoggerFactory.getLogger(CommonSendMailUtils.class);

	public static final String MAIL_SENDER_CTRIP_CONST = "ctrip_";
	public static final String MAIL_PROCESS_CTRIP_TYPE = "ctrip";

	public static void sendMail(String processType,BaseEntity bizBean, String mailTo, String mailType, String attachment) {
		log.info("Email send start: mailTo={}, mailType={}", mailTo, mailType);
		boolean sendMailStatus = false;
		try {
			CommonMailSender mailSender = new CommonMailSender(processType, mailType);
			mailSender.setMail_to(mailTo);
			if (StringUtils.isNotEmpty(attachment)) {
				mailSender.setMail_attachment(attachment);
			}
			sendMailStatus = mailSender.sendMail(bizBean);
		} catch (Exception e) {
			e.printStackTrace();
			sendMailStatus = false;
		}
		if (!sendMailStatus) {
			log.info("Email send error!");
		}
	}

	public static void sendMailToUser(String processType,String mailType, String mailTo) {
		log.info("Email send start: mailTo={}, mailType={}", mailTo, mailType);
		boolean sendMailStatus = false;
		try {
			CommonMailSender mailSender = new CommonMailSender(processType, mailType);
			sendMailStatus = mailSender.sendMailToUser(mailType,mailTo);
		} catch (Exception e) {
			e.printStackTrace();
			sendMailStatus = false;
		}
		if (!sendMailStatus) { 
			log.info("Email send error!");
		}
	}
	

	public static void sendMail(String mailSenderConst,String mailSubject, String mailTo, String mailBody, String mailAttachment) {
		log.info("Email send start: mailTo={}, mailSubject={}", mailTo, mailSubject);
		boolean sendMailStatus = false;
		try {
			CommonMailSender mailSender = new CommonMailSender();
			sendMailStatus = mailSender.sendMail(mailSenderConst,mailSubject, mailTo, mailBody, mailAttachment);
		} catch (Exception e) {
			e.printStackTrace();
			sendMailStatus = false;
		}
		if (!sendMailStatus) {
			log.info("Email send error!");
		}
	}

}
