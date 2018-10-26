/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.honor.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.honor.entity.Honor;

/**
 * 奖励和荣誉DAO接口
 * @author fjm
 * @version 2018-10-11
 */
@MyBatisDao
public interface HonorDao extends CrudDao<Honor> {
	public void insertHonor(Honor honor);
}