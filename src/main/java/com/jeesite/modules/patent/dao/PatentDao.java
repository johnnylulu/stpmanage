/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.patent.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.patent.entity.Patent;

/**
 * 所获专利DAO接口
 * @author fjm
 * @version 2018-10-11
 */
@MyBatisDao
public interface PatentDao extends CrudDao<Patent> {
	public void insertPatent(Patent patent);
}