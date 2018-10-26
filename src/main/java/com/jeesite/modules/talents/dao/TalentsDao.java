/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.talents.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.talents.entity.Talents;

/**
 * 国家级、省部级人才DAO接口
 * @author fjm
 * @version 2018-10-08
 */
@MyBatisDao
public interface TalentsDao extends CrudDao<Talents> {
	
	public void insertTalents(Talents talents);
	
}