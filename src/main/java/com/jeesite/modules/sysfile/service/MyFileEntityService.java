/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sysfile.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sysfile.dao.MyFileEntityDao;
import com.jeesite.modules.sysfile.entity.MyFileEntity;

/**
 * 文件实体表Service
 * @author fjm
 * @version 2018-10-14
 */
@Service
@Transactional(readOnly=true)
public class MyFileEntityService extends CrudService<MyFileEntityDao, MyFileEntity> {
	
	/**
	 * 获取单条数据
	 * @param myFileEntity
	 * @return
	 */
	@Override
	public MyFileEntity get(MyFileEntity myFileEntity) {
		return super.get(myFileEntity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param myFileEntity
	 * @return
	 */
	@Override
	public Page<MyFileEntity> findPage(Page<MyFileEntity> page, MyFileEntity myFileEntity) {
		return super.findPage(page, myFileEntity);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param myFileEntity
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(MyFileEntity myFileEntity) {
		super.save(myFileEntity);
	}
	
	/**
	 * 更新状态
	 * @param myFileEntity
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(MyFileEntity myFileEntity) {
		super.updateStatus(myFileEntity);
	}
	
	/**
	 * 删除数据
	 * @param myFileEntity
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(MyFileEntity myFileEntity) {
		super.delete(myFileEntity);
	}
	
}