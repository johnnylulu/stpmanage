/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.TreeService;
import com.jeesite.modules.sys.dao.OfficeDao;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.utils.EmpUtils;
import com.jeesite.modules.sysfile.entity.MyFileUpload;
import com.jeesite.modules.sysfile.service.HandleFileUploadService;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2016-4-23
 */
@Service
@Transactional(readOnly=true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	@Autowired private DataScopeService dataScopeService;
	@Autowired OfficeDao officeDao;
	@Autowired HandleFileUploadService handleFileUploadService;
	
	/**
	 * 获取单条数据
	 */
	@Override
	public Office get(Office office) {
		return super.get(office);
	}
	
	/**
	 * 添加数据权限过滤条件
	 */
	@Override
	public void addDataScopeFilter(Office office, String ctrlPermi) {
		office.getSqlMap().getDataScope().addFilter("dsf", "Office", "a.office_code", ctrlPermi);
	}

	/**
	 * 查询组织机构列表
	 */
	@Override
	public List<Office> findList(Office office) {
		return super.findList(office);
	}

	/**
	 * 保存数据（插入或更新）
	 */
	@Transactional(readOnly=false)
	public void save(HttpServletRequest request, Office office) {
		if (office.getIsNewRecord()){
			genIdAndValid(office, office.getViewCode());
			// 当前新数据授权，如果用户有上级数据权限，则当前数据也有相应的数据权限
			dataScopeService.insertIfParentExists(office, "Office");
		}
		super.save(office);
		// 清理部门相关缓存
		clearOfficeCache();
		
		List<MyFileUpload> uploadList = new ArrayList<>();
		String attachment = office.getOfficeAttachment();
		if(attachment!=null && !attachment.equals("")) {
			String [] atts = attachment.split(",");
			for(int i=0; i<atts.length; i++) {
				MyFileUpload myFileUpload = new MyFileUpload();
				myFileUpload.setId(atts[i]); // js_sys_file_upload表的id
				myFileUpload.setBizKey(office.getOfficeCode());
				myFileUpload.setBizType("officeAttachment");
				uploadList.add(myFileUpload);
			}
		}		
		// 处理附件	
		String attachment_del = office.getOfficeAttachment__del();
		handleFileUploadService.handleFileUploadData(request, uploadList, attachment, attachment_del);
	}

	/**
	 * 更新部门状态
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Office office) {
		super.updateStatus(office);
		// 清理部门相关缓存
		clearOfficeCache();
	}
	
	/**
	 * 部门加锁-解锁
	 */
	@Transactional(readOnly=false)
	public void lockOffice(Office office) {
		officeDao.lockOffice(office);
	}

	/**
	 * 删除数据
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Office office) {
		super.delete(office);
		// 清理部门相关缓存
		clearOfficeCache();
	}
	
	/**
	 * 清理部门相关缓存
	 */
	private void clearOfficeCache(){
//		EmpUtils.removeCache(EmpUtils.CACHE_OFFICE_LIST);
		EmpUtils.removeCache(EmpUtils.CACHE_OFFICE_ALL_LIST);
	}

}
