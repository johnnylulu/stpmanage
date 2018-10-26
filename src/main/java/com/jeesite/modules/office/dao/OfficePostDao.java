/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.office.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.office.entity.OfficePost;

/**
 * 机构岗位配置表DAO接口
 * @author fjm
 * @version 2018-10-24
 */
@MyBatisDao
public interface OfficePostDao extends CrudDao<OfficePost> {
	public void insertOfficePost(OfficePost officePost);
}