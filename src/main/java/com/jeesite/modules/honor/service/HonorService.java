/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.honor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.honor.dao.HonorDao;
import com.jeesite.modules.honor.entity.Honor;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.sysfile.dao.MyFileEntityDao;
import com.jeesite.modules.sysfile.dao.MyFileUploadDao;
import com.jeesite.modules.sysfile.entity.MyFileUpload;
import com.jeesite.modules.sysfile.service.HandleFileUploadService;

/**
 * 奖励和荣誉Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class HonorService extends CrudService<HonorDao, Honor> {
	
	@Autowired HonorDao honorDao;
	@Autowired MyFileUploadDao myFileUploadDao;
	@Autowired MyFileEntityDao myFileEntityDao;
	@Autowired HandleFileUploadService handleFileUploadService;
	
	/**
	 * 获取单条数据
	 * @param honor
	 * @return
	 */
	@Override
	public Honor get(Honor honor) {
		return super.get(honor);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param honor
	 * @return
	 */
	@Override
	public Page<Honor> findPage(Page<Honor> page, Honor honor) {
		return super.findPage(page, honor);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param honor
	 */

	@Transactional(readOnly=false)
	public void save(HttpServletRequest request, Honor honor) {
		if(honor.getId() == null || honor.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			honor.setIsEnable(true);
			honor.setCreateBy(userID);
			honor.setCreateTime(new Date());
			honorDao.insertHonor(honor);
		} else {
			super.save(honor);
		}	
		List<MyFileUpload> uploadList = new ArrayList<>();
		String attachment = honor.getHonorAtt();
		if(attachment!=null && attachment.indexOf(",") > -1) {
			String [] atts = attachment.split(",");
			for(int i=0; i<atts.length; i++) {
				MyFileUpload myFileUpload = new MyFileUpload();
				myFileUpload.setId(atts[i]); // js_sys_file_upload表的id
				myFileUpload.setBizKey(honor.getId());
				myFileUpload.setBizType("honorAtt");
				uploadList.add(myFileUpload);
			}
		}
		
		// 处理附件
		String attachment_del = honor.getHonorAtt__del();
		handleFileUploadService.handleFileUploadData(request, uploadList, attachment, attachment_del);
	}
	
	/**
	 * 更新状态
	 * @param honor
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Honor honor) {
		super.updateStatus(honor);
	}
	
	/**
	 * 删除数据
	 * @param honor
	 */
	@Transactional(readOnly=false)
	public void delete(HttpServletRequest request, Honor honor) {
		super.delete(honor);
		handleFileUploadService.deleteAttachment(request, honor.getHonorAtt());
	}
	
}