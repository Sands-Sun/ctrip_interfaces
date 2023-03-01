/*
 * @(#)RoleService.java	2016年11月26日下午6:08:26
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.ctrip.mail.service;

import com.dne.ctrip.entity.BatchCompleteMail;
import com.dne.ctrip.entity.CommonMailPolicy;
import com.dne.ctrip.mail.dao.CommonMailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 类<strong>RoleService.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年11月26日 下午6:08:26
 */
@Service
public class CommonMailService {

	@Autowired
	private CommonMailDao commonMailDao;

	public CommonMailPolicy getCommonMailPolicyByMailType(String processType, String mailType) {
		return this.commonMailDao.getCommonMailPolicyByMailType(processType, mailType);
	}

	public int insertComonMail(BatchCompleteMail completeMail) {
		return this.commonMailDao.insertComonMail(completeMail);
	}
}
