/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.talents.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.talents.dao.TalentsDao;
import com.jeesite.modules.talents.entity.Talents;

/**
 * 国家级、省部级人才Service
 * @author fjm
 * @version 2018-10-08
 */
@Service
@Transactional(readOnly=true)
public class TalentsService extends CrudService<TalentsDao, Talents> {
	
	@Autowired TalentsDao talentsDao;
	
	/**
	 * 获取单条数据
	 * @param talents
	 * @return
	 */
	@Override
	public Talents get(Talents talents) {
		return super.get(talents);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param talents
	 * @return
	 */
	@Override
	public Page<Talents> findPage(Page<Talents> page, Talents talents) {
		return super.findPage(page, talents);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param talents
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Talents talents) {
		if(talents.getId() == null || talents.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			talents.setIsEnable(true);
			talents.setCreateBy(userID);
			talents.setCreateTime(new Date());
			talentsDao.insertTalents(talents);
		} else {
			super.save(talents);
		}
		
	}
	
	/**
	 * 更新状态
	 * @param talents
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Talents talents) {
		super.updateStatus(talents);
	}
	
	/**
	 * 删除数据
	 * @param talents
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Talents talents) {
		super.delete(talents);
	}
	
}