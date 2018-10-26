/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sysfile.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sysfile.entity.MyFileEntity;

/**
 * 文件实体表DAO接口
 * @author fjm
 * @version 2018-10-14
 */
@MyBatisDao
public interface MyFileEntityDao extends CrudDao<MyFileEntity> {
	public void deleteFileEntityByFileId(String fileId);
	
	public MyFileEntity getByFileId(String fileId);
}