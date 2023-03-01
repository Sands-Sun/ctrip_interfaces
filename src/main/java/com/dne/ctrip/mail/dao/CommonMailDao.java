/*
 * @(#)RoleDao.java	2016年11月26日下午6:07:50
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.ctrip.mail.dao;

import com.dne.core.basic.dao.BaseDao;
import com.dne.ctrip.entity.BatchCompleteMail;
import com.dne.ctrip.entity.CommonMailPolicy;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 类<strong>RoleDao.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年11月26日 下午6:07:50
 */
public interface CommonMailDao extends BaseDao {

	public CommonMailPolicy getCommonMailPolicyByMailType(@Param("processType") String processType, @Param("mailType") String mailType);

	public int insertComonMail(BatchCompleteMail completeMail);
}
