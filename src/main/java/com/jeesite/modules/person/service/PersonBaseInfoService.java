/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.person.service;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.person.dao.PersonBaseInfoDao;
import com.jeesite.modules.person.entity.PersonBaseInfo;
import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.sysfile.dao.MyFileEntityDao;
import com.jeesite.modules.sysfile.dao.MyFileUploadDao;
import com.jeesite.modules.sysfile.entity.MyFileEntity;
import com.jeesite.modules.sysfile.entity.MyFileUpload;

/**
 * 人员基本信息表Service
 * @author fjm
 * @version 2018-10-08
 */
@Service
@Transactional(readOnly=true)
public class PersonBaseInfoService extends CrudService<PersonBaseInfoDao, PersonBaseInfo> {
	
	//private static final Logger logger = LoggerFactory.getLogger(PersonBaseInfoService.class);
	
	@Autowired PersonBaseInfoDao personBaseInfoDao;
	@Autowired MyFileUploadDao myFileUploadDao;
	@Autowired MyFileEntityDao myFileEntityDao;
	
	/**
	 * 获取单条数据
	 * @param personBaseInfo
	 * @return
	 */
	@Override
	public PersonBaseInfo get(PersonBaseInfo personBaseInfo) {
		personBaseInfo.setSid(Integer.parseInt(personBaseInfo.getId()));
		return super.get(personBaseInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param personBaseInfo
	 * @return
	 */
	@Override
	public Page<PersonBaseInfo> findPage(Page<PersonBaseInfo> page, PersonBaseInfo personBaseInfo) {
		return super.findPage(page, personBaseInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param personBaseInfo
	 */

	@Transactional(readOnly=false)
	public void save(HttpServletRequest request, PersonBaseInfo personBaseInfo) {
		if(personBaseInfo.getId() == null || personBaseInfo.getId().trim().equals("") || personBaseInfo.getId().equals("0") ) {
			// 因为ID是自增的，所以无法用super.save;
			User user =  UserUtils.getUser();
			String userID = user.getId();						
			Employee employee = (Employee)user.getRefObj();
			if(employee!=null) {
				String officeCode = employee.getOffice().getOfficeCode();
				personBaseInfo.setOfficeCode(officeCode);
			}
			personBaseInfo.setIsEnable(true);
			personBaseInfo.setAuditStatus("1"); // 1表示录入
			personBaseInfo.setCreateBy(userID);
			personBaseInfo.setCreateTime(new Date());
			personBaseInfoDao.insertBaseInfo(personBaseInfo);
		} else {
			// 修改订单
			super.save(personBaseInfo);
		}
		MyFileUpload myFileUpload = new MyFileUpload();
		myFileUpload.setId(personBaseInfo.getPhoto());
		myFileUpload.setBizKey(personBaseInfo.getId());
		myFileUpload.setBizType("photo");
		// 如果上传了照片，则保存成功后需要修改文件数据库
		if(personBaseInfo.getPhoto() != null && !personBaseInfo.getPhoto().equals("")) {
			myFileUploadDao.updateBizInfo(myFileUpload);
		}
		// 编辑表单时，如果删除了照片，需要删除文件表（js_sys_file_upload,js_sys_file_entity）中的数据，同时删除硬盘中保存的文件
		if((personBaseInfo.getPhoto()==null || personBaseInfo.getPhoto().equals("")) 
			&&(personBaseInfo.getPhoto__del()!=null && !personBaseInfo.getPhoto__del().equals(""))) {
			deletePhotoData(request,personBaseInfo);
		} 
		// 如果删除了照片，并且又新增了另一张照片
		else if((personBaseInfo.getPhoto()!=null && !personBaseInfo.getPhoto().equals("")) 
			&&(personBaseInfo.getPhoto__del()!=null && !personBaseInfo.getPhoto__del().equals(""))) {
			deleteOldPhotoData(personBaseInfo);
		}
	}
	
	@Transactional(readOnly=false)
	public void auditSave(PersonBaseInfo personBaseInfo) {
		personBaseInfoDao.auditSave(personBaseInfo);
	}
	
	/**
	 * 更新状态
	 * @param personBaseInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PersonBaseInfo personBaseInfo) {
		super.updateStatus(personBaseInfo);
	}
	
	/**
	 * 删除数据
	 * @param personBaseInfo
	 */
	@Transactional(readOnly=false)
	public void delete(HttpServletRequest request,PersonBaseInfo personBaseInfo) {
		personBaseInfoDao.delete(personBaseInfo);
		PersonBaseInfo personBaseInfo2 = personBaseInfoDao.findById(personBaseInfo.getSid());
		personBaseInfo.setPhoto__del(personBaseInfo2.getPhoto());
		deletePhotoData(request,personBaseInfo);
	}
	
	/**
	 * 删除照片附件数据，包括DB和file
	 */
	public void deletePhotoData(HttpServletRequest request,PersonBaseInfo personBaseInfo) {
		if(personBaseInfo.getPhoto__del() != null && !personBaseInfo.getPhoto__del().equals("")) {
			String rootPath = request.getSession().getServletContext().getRealPath("/"); 
			MyFileUpload myFileUpload2 = myFileUploadDao.getById(personBaseInfo.getPhoto__del());
			myFileUploadDao.deleteFileUploadById(personBaseInfo.getPhoto__del());			
			// 根据id得到file_id,根据file_id删除FileEntity
			if(myFileUpload2 != null && myFileUpload2.getFileId() != null) {
				MyFileEntity entity = myFileEntityDao.getByFileId(myFileUpload2.getFileId());
				if(entity != null) {
					myFileEntityDao.deleteFileEntityByFileId(myFileUpload2.getFileId());			
					File file = new File(rootPath+"//userfiles//fileupload//"+entity.getFilePath()+entity.getFileId()+"."+entity.getFileExtension());
					String filePath = file.getAbsolutePath();
					//logger.info(filePath);
					System.out.println("*********************"+filePath);
					if(file.exists()) {
						file.delete();
					}
				}				
			}
		}
	}
	
	/**
	 * 只需要删除js_sys_file_upload旧的数据，js_sys_file_entity数据不动，硬盘的文件不动
	 * @param personBaseInfo
	 */
	public void deleteOldPhotoData(PersonBaseInfo personBaseInfo){
		if(personBaseInfo.getPhoto__del() != null && !personBaseInfo.getPhoto__del().equals("")) {
			MyFileUpload myFileUpload2 = myFileUploadDao.getById(personBaseInfo.getPhoto__del());
			if(myFileUpload2 != null && myFileUpload2.getFileId() != null) {
				myFileUploadDao.deleteFileUploadById(personBaseInfo.getPhoto__del());
			}			
		}
	}
}