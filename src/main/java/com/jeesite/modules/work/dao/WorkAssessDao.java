/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.work.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.work.entity.WorkAssess;

/**
 * 工作考核记录DAO接口
 * @author fjm
 * @version 2018-10-11
 */
@MyBatisDao
public interface WorkAssessDao extends CrudDao<WorkAssess> {
	public void insertWorkAssess(WorkAssess workAssess);
}