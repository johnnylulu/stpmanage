/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.patent.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.patent.dao.PatentDao;
import com.jeesite.modules.patent.entity.Patent;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 所获专利Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class PatentService extends CrudService<PatentDao, Patent> {
	
	@Autowired PatentDao patentDao;
	
	/**
	 * 获取单条数据
	 * @param patent
	 * @return
	 */
	@Override
	public Patent get(Patent patent) {
		return super.get(patent);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param patent
	 * @return
	 */
	@Override
	public Page<Patent> findPage(Page<Patent> page, Patent patent) {
		return super.findPage(page, patent);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param patent
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Patent patent) {
		if(patent.getId() == null || patent.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			patent.setIsEnable(true);
			patent.setCreateBy(userID);
			patent.setCreateTime(new Date());
			patentDao.insertPatent(patent);
		} else {
			super.save(patent);
		}		
	}
	
	/**
	 * 更新状态
	 * @param patent
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Patent patent) {
		super.updateStatus(patent);
	}
	
	/**
	 * 删除数据
	 * @param patent
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Patent patent) {
		super.delete(patent);
	}
	
}