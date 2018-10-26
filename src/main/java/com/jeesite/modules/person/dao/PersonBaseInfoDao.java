/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.person.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.person.entity.PersonBaseInfo;

/**
 * 人员基本信息表DAO接口
 * @author fjm
 * @version 2018-10-08
 */
@MyBatisDao
public interface PersonBaseInfoDao extends CrudDao<PersonBaseInfo> {
	
	public void insertBaseInfo(PersonBaseInfo personBaseInfo);
	
	public long delete(PersonBaseInfo personBaseInfo);
	
	public PersonBaseInfo findById(int id);
	
	public void auditSave(PersonBaseInfo personBaseInfo);
	
}