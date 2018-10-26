/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sysfile.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sysfile.entity.MyFileUpload;

/**
 * 文件上传表DAO接口
 * @author fjm
 * @version 2018-10-14
 */
@MyBatisDao
public interface MyFileUploadDao extends CrudDao<MyFileUpload> {
	
	public MyFileUpload getById(String id);
	
	public void updateBizInfo(MyFileUpload myFileUpload);
	
	public void deleteFileUploadById(String id);
	
	public void deleteFileEntity(MyFileUpload myFileUpload);
	
}