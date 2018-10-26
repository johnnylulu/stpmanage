/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.project.dao.ProjectDao;
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 参与的课题Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class ProjectService extends CrudService<ProjectDao, Project> {
	@Autowired ProjectDao projectDao;
	/**
	 * 获取单条数据
	 * @param project
	 * @return
	 */
	@Override
	public Project get(Project project) {
		return super.get(project);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param project
	 * @return
	 */
	@Override
	public Page<Project> findPage(Page<Project> page, Project project) {
		return super.findPage(page, project);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param project
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Project project) {
		if(project.getId() == null || project.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			project.setIsEnable(true);
			project.setCreateBy(userID);
			project.setCreateTime(new Date());
			projectDao.insertProject(project);
		} else {
			super.save(project);
		}		
	}
	
	/**
	 * 更新状态
	 * @param project
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Project project) {
		super.updateStatus(project);
	}
	
	/**
	 * 删除数据
	 * @param project
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Project project) {
		super.delete(project);
	}
	
}