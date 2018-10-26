/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.qualification.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.qualification.dao.QualificationDao;
import com.jeesite.modules.qualification.entity.Qualification;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.sysfile.dao.MyFileEntityDao;
import com.jeesite.modules.sysfile.dao.MyFileUploadDao;
import com.jeesite.modules.sysfile.entity.MyFileUpload;
import com.jeesite.modules.sysfile.service.HandleFileUploadService;


/**
 * 专业技术资格Service
 * @author fjm
 * @version 2018-10-11
 */
@Service
@Transactional(readOnly=true)
public class QualificationService extends CrudService<QualificationDao, Qualification> {
	
	@Autowired QualificationDao qualificationDao; 
	@Autowired MyFileUploadDao myFileUploadDao;
	@Autowired MyFileEntityDao myFileEntityDao;
	@Autowired HandleFileUploadService handleFileUploadService;
	
	/**
	 * 获取单条数据
	 * @param qualification
	 * @return
	 */
	@Override
	public Qualification get(Qualification qualification) {
		return super.get(qualification);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param qualification
	 * @return
	 */
	@Override
	public Page<Qualification> findPage(Page<Qualification> page, Qualification qualification) {
		return super.findPage(page, qualification);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param qualification
	 */
	@Transactional(readOnly=false)
	public void save(HttpServletRequest request, Qualification qualification) {
		if(qualification.getId() == null || qualification.getId().trim().equals("")) {
			// 因为ID是自增的，所以无法用super.save;
			String userID = UserUtils.getUser().getId();
			qualification.setIsEnable(true);
			qualification.setCreateBy(userID);
			qualification.setCreateTime(new Date());
			qualificationDao.insertQualification(qualification);
		} else {
			super.save(qualification);
		}
		List<MyFileUpload> uploadList = new ArrayList<>();
		String attachment = qualification.getQualificationAtt();
		if(attachment!=null && attachment.indexOf(",") > -1) {
			String [] atts = attachment.split(",");
			for(int i=0; i<atts.length; i++) {
				MyFileUpload myFileUpload = new MyFileUpload();
				myFileUpload.setId(atts[i]); // js_sys_file_upload表的id
				myFileUpload.setBizKey(qualification.getId());
				myFileUpload.setBizType("qualificationAtt");
				uploadList.add(myFileUpload);
			}
		}
		// 处理附件
		String attachment_del = qualification.getQualificationAtt__del();
		handleFileUploadService.handleFileUploadData(request, uploadList, attachment, attachment_del);	
		
	}
	
	/**
	 * 更新状态
	 * @param qualification
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Qualification qualification) {
		super.updateStatus(qualification);
	}
	
	/**
	 * 删除数据
	 * @param qualification
	 */	
	@Transactional(readOnly=false)
	public void delete(HttpServletRequest request, Qualification qualification) {
		super.delete(qualification);
		handleFileUploadService.deleteAttachment(request, qualification.getQualificationAtt());
	}
	
}