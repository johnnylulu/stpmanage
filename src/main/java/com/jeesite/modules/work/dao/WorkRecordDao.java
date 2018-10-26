/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.work.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.work.entity.WorkRecord;

/**
 * 工作履历DAO接口
 * @author fjm
 * @version 2018-10-11
 */
@MyBatisDao
public interface WorkRecordDao extends CrudDao<WorkRecord> {
	public void insertWorkRecord(WorkRecord workRecord);
}