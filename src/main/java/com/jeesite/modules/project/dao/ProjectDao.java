/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.project.entity.Project;

/**
 * 参与的课题DAO接口
 * @author fjm
 * @version 2018-10-11
 */
@MyBatisDao
public interface ProjectDao extends CrudDao<Project> {
	public void insertProject(Project project);
}