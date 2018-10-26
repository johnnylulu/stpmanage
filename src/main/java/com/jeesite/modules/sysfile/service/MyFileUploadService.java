/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sysfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sysfile.dao.MyFileUploadDao;
import com.jeesite.modules.sysfile.entity.MyFileUpload;

/**
 * 文件上传表Service
 * @author fjm
 * @version 2018-10-14
 */
@Service
@Transactional(readOnly=true)
public class MyFileUploadService extends CrudService<MyFileUploadDao, MyFileUpload> {
	@Autowired MyFileUploadDao myFileUploadDao;
	/**
	 * 获取单条数据
	 * @param myFileUpload
	 * @return
	 */
	@Override
	public MyFileUpload get(MyFileUpload myFileUpload) {
		return super.get(myFileUpload);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param myFileUpload
	 * @return
	 */
	@Override
	public Page<MyFileUpload> findPage(Page<MyFileUpload> page, MyFileUpload myFileUpload) {
		return super.findPage(page, myFileUpload);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param myFileUpload
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(MyFileUpload myFileUpload) {
		super.save(myFileUpload);
	}
	
	/**
	 * 更新状态
	 * @param myFileUpload
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(MyFileUpload myFileUpload) {
		super.updateStatus(myFileUpload);
	}
	
	/**
	 * 更新业务字段
	 * @param myFileUpload
	 */
	@Transactional(readOnly=false)
	public void updateBizInfo(MyFileUpload myFileUpload) {
		myFileUploadDao.updateBizInfo(myFileUpload);
	}
	
	/**
	 * 删除数据
	 * @param myFileUpload
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(MyFileUpload myFileUpload) {
		super.delete(myFileUpload);
	}
	
}