/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.work.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.work.dao.WorkAssessDao;
import com.jeesite.modules.work.entity.WorkAssess;

/**
 * 工作考核记录Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class WorkAssessService extends CrudService<WorkAssessDao, WorkAssess> {
	@Autowired WorkAssessDao workAssessDao;
	/**
	 * 获取单条数据
	 * @param workAssess
	 * @return
	 */
	@Override
	public WorkAssess get(WorkAssess workAssess) {
		return super.get(workAssess);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param workAssess
	 * @return
	 */
	@Override
	public Page<WorkAssess> findPage(Page<WorkAssess> page, WorkAssess workAssess) {
		return super.findPage(page, workAssess);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param workAssess
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WorkAssess workAssess) {
		if(workAssess.getId() == null || workAssess.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			workAssess.setIsEnable(true);
			workAssess.setCreateBy(userID);
			workAssess.setCreateTime(new Date());
			workAssessDao.insertWorkAssess(workAssess);
		} else {
			super.save(workAssess);
		}		
	}
	
	/**
	 * 更新状态
	 * @param workAssess
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WorkAssess workAssess) {
		super.updateStatus(workAssess);
	}
	
	/**
	 * 删除数据
	 * @param workAssess
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WorkAssess workAssess) {
		super.delete(workAssess);
	}
	
}