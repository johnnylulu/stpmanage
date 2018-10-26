/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.title.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.sysfile.dao.MyFileEntityDao;
import com.jeesite.modules.sysfile.dao.MyFileUploadDao;
import com.jeesite.modules.sysfile.entity.MyFileUpload;
import com.jeesite.modules.sysfile.service.HandleFileUploadService;
import com.jeesite.modules.title.dao.TitleDao;
import com.jeesite.modules.title.entity.Title;

/**
 * 专业技术资格Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class TitleService extends CrudService<TitleDao, Title> {
	
	@Autowired TitleDao titleDao;
	@Autowired MyFileUploadDao myFileUploadDao;
	@Autowired MyFileEntityDao myFileEntityDao;
	@Autowired HandleFileUploadService handleFileUploadService;
	
	/**
	 * 获取单条数据
	 * @param title
	 * @return
	 */
	@Override
	public Title get(Title title) {
		return super.get(title);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param title
	 * @return
	 */
	@Override
	public Page<Title> findPage(Page<Title> page, Title title) {
		return super.findPage(page, title);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param title
	 */

	@Transactional(readOnly=false)
	public void save(HttpServletRequest request, Title title) {
		if(title.getId() == null || title.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			title.setIsEnable(true);
			title.setCreateBy(userID);
			title.setCreateTime(new Date());
			titleDao.insertTitle(title);
		} else {
			super.save(title);
		}
		List<MyFileUpload> uploadList = new ArrayList<>();
		String attachment = title.getTitleAtt();
		if(attachment!=null && attachment.indexOf(",") > -1) {
			String [] atts = attachment.split(",");
			for(int i=0; i<atts.length; i++) {
				MyFileUpload myFileUpload = new MyFileUpload();
				myFileUpload.setId(atts[i]); // js_sys_file_upload表的id
				myFileUpload.setBizKey(title.getId());
				myFileUpload.setBizType("titleAtt");
				uploadList.add(myFileUpload);
			}
		}
		
		// 处理附件	
		String attachment_del = title.getTitleAtt__del();
		handleFileUploadService.handleFileUploadData(request, uploadList, attachment, attachment_del);
	}
	
	/**
	 * 更新状态
	 * @param title
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Title title) {
		super.updateStatus(title);
	}
	
	/**
	 * 删除数据
	 * @param title
	 */
	@Transactional(readOnly=false)
	public void delete(HttpServletRequest request, Title title) {
		super.delete(title);
		handleFileUploadService.deleteAttachment(request, title.getTitleAtt());
	}
	
}