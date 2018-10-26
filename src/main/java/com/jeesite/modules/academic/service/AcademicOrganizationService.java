/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.academic.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.academic.dao.AcademicOrganizationDao;
import com.jeesite.modules.academic.entity.AcademicOrganization;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 参加学术团体经历Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class AcademicOrganizationService extends CrudService<AcademicOrganizationDao, AcademicOrganization> {
	@Autowired AcademicOrganizationDao academicOrganizationDao;
	/**
	 * 获取单条数据
	 * @param academicOrganization
	 * @return
	 */
	@Override
	public AcademicOrganization get(AcademicOrganization academicOrganization) {
		return super.get(academicOrganization);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param academicOrganization
	 * @return
	 */
	@Override
	public Page<AcademicOrganization> findPage(Page<AcademicOrganization> page, AcademicOrganization academicOrganization) {
		return super.findPage(page, academicOrganization);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param academicOrganization
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(AcademicOrganization academicOrganization) {
		if(academicOrganization.getId() == null || academicOrganization.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			academicOrganization.setIsEnable(true);
			academicOrganization.setCreateBy(userID);
			academicOrganization.setCreateTime(new Date());
			academicOrganizationDao.insertAcademicOrganization(academicOrganization);
		} else {
			super.save(academicOrganization);
		}
		
	}
	
	/**
	 * 更新状态
	 * @param academicOrganization
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(AcademicOrganization academicOrganization) {
		super.updateStatus(academicOrganization);
	}
	
	/**
	 * 删除数据
	 * @param academicOrganization
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(AcademicOrganization academicOrganization) {
		super.delete(academicOrganization);
	}
	
}