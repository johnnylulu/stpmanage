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
import com.jeesite.modules.work.dao.WorkRecordDao;
import com.jeesite.modules.work.entity.WorkRecord;

/**
 * 工作履历Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class WorkRecordService extends CrudService<WorkRecordDao, WorkRecord> {
	
	@Autowired WorkRecordDao workRecordDao;
	
	/**
	 * 获取单条数据
	 * @param workRecord
	 * @return
	 */
	@Override
	public WorkRecord get(WorkRecord workRecord) {
		return super.get(workRecord);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param workRecord
	 * @return
	 */
	@Override
	public Page<WorkRecord> findPage(Page<WorkRecord> page, WorkRecord workRecord) {
		return super.findPage(page, workRecord);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param workRecord
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(WorkRecord workRecord) {
		if(workRecord.getId() == null || workRecord.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			workRecord.setIsEnable(true);
			workRecord.setCreateBy(userID);
			workRecord.setCreateTime(new Date());
			workRecordDao.insertWorkRecord(workRecord);
		} else {
			super.save(workRecord);
		}		
	}
	
	/**
	 * 更新状态
	 * @param workRecord
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(WorkRecord workRecord) {
		super.updateStatus(workRecord);
	}
	
	/**
	 * 删除数据
	 * @param workRecord
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(WorkRecord workRecord) {
		super.delete(workRecord);
	}
	
}