/*
 * @(#)MyAuthenticator.java	2015-8-20下午4:23:51
 * Copyright 2013 DNE All rights reserved.
 */
package com.dne.ctrip.mail.handler;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * 类<strong>MyAuthenticator.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2015-8-20 下午4:23:51
 */
public class MyAuthenticator extends Authenticator {
	private String username = "";

	private String password = "";

	public MyAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
