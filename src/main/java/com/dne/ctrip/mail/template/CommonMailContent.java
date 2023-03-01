package com.dne.ctrip.mail.template;

import com.dne.core.basic.entity.BaseEntity;
import com.dne.core.util.Global;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.lang.reflect.Field;

public class CommonMailContent {
	private static final Logger log = LoggerFactory.getLogger(CommonMailContent.class);

	private String mail_from;

	private String mail_sender;

	private String status;

	private String tpath;

	private String template;

	private VelocityContext context;

	private VelocityEngine ve;

	public CommonMailContent() { 
		ve = new VelocityEngine();
		context = new VelocityContext();
		initVelocityEngine();
		initDoctorMailContent();
	}

	private void initDoctorMailContent() {
		this.mail_sender = CommonSendMailUtils.MAIL_SENDER_CTRIP_CONST;
		this.mail_from = "1";
	}

	private void initVelocityEngine() {
		try {
			ve.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, Global.getConfig("mail.template.path"));
			ve.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
			ve.init();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Init velocity engine error: ", e);
		}
	}

	private String getCommonTemplate(String tpath, String template) {
		String body = "";
		try (StringWriter writer = new StringWriter()) {
			if (ve == null) {
				throw new Exception("Init velocity engine error!");
			}
			ve.mergeTemplate(tpath + template, "utf-8", context, writer);
			body = writer.toString();
			return body;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	public String getMailBody(BaseEntity bizBean, int type) {
		setContextMap(bizBean);
		return getCommonTemplate(this.getTpath(), this.getTemplate());
	}
	 
	public String getMailBody() {
		return getCommonTemplate(this.getTpath(), this.getTemplate());
	}

	public void setContextMap(BaseEntity bizBean) {
		if (null != bizBean) {
			Field[] fields = bizBean.getClass().getDeclaredFields();
			if (fields != null) {
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					try {
						context.put(field.getName(), field.get(bizBean));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public String getMail_cc() {
		return null;
	}

	public String getMail_from() {
		return mail_from;
	}

	public String getMail_sender() {
		return mail_sender;
	}

	public String getMailTo(String mailToStr) {
		return "";
	}

	public String getStatus() {
		return status;
	}

	public void setContext(VelocityContext context) {
		this.context = context;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return template;
	}

	public String getTpath() {
		return tpath;
	}

	public void setTpath(String tpath) {
		this.tpath = tpath;
	}

	public String getMailAttachmentUrl() {
		return "";
	}
}
