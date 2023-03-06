package com.dne.ctrip.mail.template;

import com.dne.core.basic.entity.BaseEntity;
import com.dne.core.util.Global;
import com.dne.core.util.SpringContextHolder;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.entity.BatchCompleteMail;
import com.dne.ctrip.entity.CommonMailPolicy;
import com.dne.ctrip.mail.service.CommonMailService;
import com.dne.ctrip.mail.vo.BaseMailVo;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 公共的邮件发送实现类
 */
public class CommonMailSender {

	private static final Logger log = LoggerFactory.getLogger(CommonMailSender.class);

	protected VelocityContext context = null;



	private CommonMailContent mailContent;

	private String mailType;

	private String processType;

	protected String mail_from;

	protected String mail_to = "";

	protected String mail_cc = "";

	protected String mail_subject;

	private String mailSubjectEnd;

	protected String mail_body;

	protected String mail_status;

	protected String mail_sender;

	protected String status;

	private String mailToForRole;

	private String mail_attachment;

	private CommonMailPolicy commonMailPolicy;

	private static CommonMailService commonMailService = SpringContextHolder.getBean("commonMailService");

	protected final static String configName = "mail.conf";

	public CommonMailSender() {

	}

	private static boolean isDebug;

	private static String debugMailReceive= "";

	static {
		isDebug = Boolean.parseBoolean(Global.getConfig("sync.debug"));
		debugMailReceive = String.valueOf(Global.getConfig("sync.debug.email")) ;
	}

	public CommonMailSender(String processType, String mailType) {
		this.mailContent = new CommonMailContent();
		this.mailType = mailType;
		this.processType = processType;
		try {
			commonMailPolicy = commonMailService.getCommonMailPolicyByMailType(this.processType, this.mailType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMailBody(BaseMailVo bizBean, int type) {
		this.mail_body = mailContent.getMailBody(bizBean, type);
		return mail_body;
	}

	public String getMailTo() {
		if (StringUtils.isEmpty(this.mail_to)) {
			this.mail_to = mailContent.getMailTo(this.mailToForRole);
		} else {
			String mailforrole = mailContent.getMailTo(this.mailToForRole);
			if (!StringUtils.isEmpty(mailforrole)) {
				this.mail_to = this.mail_to + "," + mailforrole;
			}
		}
		return mail_to;
	}

	public CommonMailContent getMailContent() {
		return mailContent;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getMail_body() {
		return mail_body;
	}

	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}

	public String getMailToForRole() {
		return mailToForRole;
	}

	public void setMailToForRole(String mailToForRole) {
		this.mailToForRole = mailToForRole;
	}

	public String getMail_from() {
		return mail_from;
	}

	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}

	public String getMail_to() {
		return mail_to;
	}

	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

	public String getMail_cc() {
		return mail_cc;
	}

	public void setMail_cc(String mail_cc) {
		this.mail_cc = mail_cc;
	}

	public String getMail_subject() {
		return mail_subject;
	}

	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}

	public String getMail_status() {
		return mail_status;
	}

	public void setMail_status(String mail_status) {
		this.mail_status = mail_status;
	}

	public String getMail_sender() {
		return mail_sender;
	}

	public void setMail_sender(String mail_sender) {
		this.mail_sender = mail_sender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMail_attachment() {
		return mail_attachment;
	}

	public void setMail_attachment(String mail_attachment) {
		this.mail_attachment = mail_attachment;
	}

	public String getMailAttachmentUrl() {
		return mailContent.getMailAttachmentUrl();
	}

	public VelocityContext getContext() {
		return context;
	}

	public void setContext(VelocityContext context) {
		this.context = context;
	}

	public void setMailContent(CommonMailContent mailContent) {
		this.mailContent = mailContent;
	}

	public String getMailSubjectEnd() {
		return mailSubjectEnd;
	}

	public void setMailSubjectEnd(String mailSubjectEnd) {
		this.mailSubjectEnd = mailSubjectEnd;
	}

	public boolean sendMail(BaseMailVo baseMailVo) {
		boolean flag = true;
		if (null != commonMailPolicy) {
			try {
				context = new VelocityContext();
				mailContent.setContext(context);
				this.mail_subject = commonMailPolicy.getSubject();
				mailContent.setTemplate(commonMailPolicy.getTemplate());
				mailContent.setTpath(commonMailPolicy.getTpath());
				this.mail_from = mailContent.getMail_from();

				this.mail_sender = mailContent.getMail_sender();
				this.status = mailContent.getStatus();
				this.mailToForRole = commonMailPolicy.getMailTo();
				this.mail_body = mailContent.getMailBody(baseMailVo, 0);
				this.getMailTo();
				flag = flag && this.sendMail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}


	public boolean sendMail(String mailSenderConst,String mailSubject, String mailTo,
							String mailBody, String mailAttachment) {
		this.mail_from = "1";
		this.mail_subject = mailSubject;
		this.mail_to = mailTo;
		this.mail_body = mailBody;
		this.mail_attachment = mailAttachment;
		this.mail_sender = mailSenderConst;
		return this.sendMail();
	}

	public boolean sendMailToUser(String mailType,String mailTo) {
		context = new VelocityContext();
		mailContent.setContext(context);
		mailContent.setTemplate(commonMailPolicy.getTemplate());
		mailContent.setTpath(commonMailPolicy.getTpath());
		this.mail_from = "1";
		this.mail_subject = commonMailPolicy.getSubject();
		this.mail_sender = CommonSendMailUtils.MAIL_SENDER_CTRIP_CONST;
		this.mail_to = mailTo;
		this.mail_body = mailContent.getMailBody();
		return this.sendMail();
	}

	public boolean sendMail() {
		String sendMailTo = isDebug ? debugMailReceive : this.mail_to;
		String sendMailCC = isDebug ? "" : this.mail_cc;
		log.info("isDebug: {}, sendUser: {}, CCUser: {}, if is debug do not send really user.",
				isDebug,sendMailTo, sendMailCC);
		log.info("mail_cc: " + sendMailCC);
		log.info("mail_from: " + this.mail_from);
		log.info("mail_sender: " + this.mail_sender);
		log.info("mail_subject: " + this.mail_subject);
		log.info("mail_body: " + this.mail_body);
		log.info("mail_to: " + sendMailTo);

		BatchCompleteMail mail = new BatchCompleteMail();
		mail.setMailSender(this.mail_sender);
		mail.setMailFrom(this.mail_from);
		mail.setMailTo(sendMailTo);
		mail.setMailCc(sendMailCC);
		mail.setMailSubject(this.mail_subject);
		mail.setMailBody(this.mail_body);
		mail.setMailAttachment(this.mail_attachment);
		mail.setCreateDate(new Date());
		mail.setIsSent("N");
		mail.setWrongtimes(0);
		int insermailint = commonMailService.insertComonMail(mail);
		log.info("Send mail insert db result: " + insermailint);
		return true;
	}
}
