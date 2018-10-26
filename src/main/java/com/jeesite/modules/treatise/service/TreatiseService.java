/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.treatise.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.treatise.dao.TreatiseDao;
import com.jeesite.modules.treatise.entity.Treatise;

/**
 * 论著Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class TreatiseService extends CrudService<TreatiseDao, Treatise> {
	
	@Autowired TreatiseDao treatiseDao;
	
	/**
	 * 获取单条数据
	 * @param treatise
	 * @return
	 */
	@Override
	public Treatise get(Treatise treatise) {
		return super.get(treatise);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param treatise
	 * @return
	 */
	@Override
	public Page<Treatise> findPage(Page<Treatise> page, Treatise treatise) {
		return super.findPage(page, treatise);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param treatise
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Treatise treatise) {
		if(treatise.getId() == null || treatise.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			treatise.setIsEnable(true);
			treatise.setCreateBy(userID);
			treatise.setCreateTime(new Date());
			treatiseDao.insertTreatise(treatise);
		} else {
			super.save(treatise);
		}
		
	}
	
	/**
	 * 更新状态
	 * @param treatise
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Treatise treatise) {
		super.updateStatus(treatise);
	}
	
	/**
	 * 删除数据
	 * @param treatise
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Treatise treatise) {
		super.delete(treatise);
	}
	
}