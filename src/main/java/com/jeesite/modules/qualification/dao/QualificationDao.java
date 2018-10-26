/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.qualification.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.qualification.entity.Qualification;

/**
 * 专业技术资格DAO接口
 * @author fjm
 * @version 2018-10-11
 */
@MyBatisDao
public interface QualificationDao extends CrudDao<Qualification> {
	public void insertQualification(Qualification qualification);
}