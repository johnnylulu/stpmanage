/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.academic.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.academic.entity.AcademicOrganization;

/**
 * 参加学术团体经历DAO接口
 * @author fjm
 * @version 2018-10-11
 */
@MyBatisDao
public interface AcademicOrganizationDao extends CrudDao<AcademicOrganization> {
	public void insertAcademicOrganization(AcademicOrganization academicOrganization);
}