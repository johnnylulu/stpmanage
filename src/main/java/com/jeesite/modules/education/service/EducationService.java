/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.education.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.education.dao.EducationDao;
import com.jeesite.modules.education.entity.Education;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 学习经历Service
 * @author fjm
 * @version 2018-10-10
 */
@Service
@Transactional(readOnly=true)
public class EducationService extends CrudService<EducationDao, Education> {
	
	@Autowired EducationDao educationDao; 
	
	/**
	 * 获取单条数据
	 * @param education
	 * @return
	 */
	@Override
	public Education get(Education education) {
		return super.get(education);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param education
	 * @return
	 */
	@Override
	public Page<Education> findPage(Page<Education> page, Education education) {
		return super.findPage(page, education);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param education
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Education education) {
		if(education.getId() == null || education.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			education.setIsEnable(true);
			education.setCreateBy(userID);
			education.setCreateTime(new Date());
			educationDao.insertEducation(education);
		} else {
			super.save(education);
		}		
	}
	
	/**
	 * 更新状态
	 * @param education
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Education education) {
		super.updateStatus(education);
	}
	
	/**
	 * 删除数据
	 * @param education
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Education education) {
		super.delete(education);
	}
	
}