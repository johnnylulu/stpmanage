/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.office.dao.OfficePostDao;
import com.jeesite.modules.office.entity.OfficePost;

/**
 * 机构岗位配置表Service
 * @author fjm
 * @version 2018-10-24
 */
@Service
@Transactional(readOnly=true)
public class OfficePostService extends CrudService<OfficePostDao, OfficePost> {
	
	@Autowired
	OfficePostDao officePostDao;
	
	/**
	 * 获取单条数据
	 * @param officePost
	 * @return
	 */
	@Override
	public OfficePost get(OfficePost officePost) {
		return super.get(officePost);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param officePost
	 * @return
	 */
	@Override
	public Page<OfficePost> findPage(Page<OfficePost> page, OfficePost officePost) {
		return super.findPage(page, officePost);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param officePost
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OfficePost officePost) {
		if(officePost.getId()==null || "".equals(officePost.getId().trim())) {
			officePostDao.insertOfficePost(officePost);
		} else {
			super.save(officePost);
		}		
	}
	
	/**
	 * 更新状态
	 * @param officePost
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OfficePost officePost) {
		super.updateStatus(officePost);
	}
	
	/**
	 * 删除数据
	 * @param officePost
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OfficePost officePost) {
		super.delete(officePost);
	}
	
}