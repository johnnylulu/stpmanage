/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.education.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.education.entity.Education;

/**
 * 学习经历DAO接口
 * @author fjm
 * @version 2018-10-10
 */
@MyBatisDao
public interface EducationDao extends CrudDao<Education> {
	public void insertEducation(Education education);
}