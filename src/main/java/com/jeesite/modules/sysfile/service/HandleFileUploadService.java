package com.jeesite.modules.sysfile.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.modules.sysfile.dao.MyFileEntityDao;
import com.jeesite.modules.sysfile.dao.MyFileUploadDao;
import com.jeesite.modules.sysfile.entity.MyFileEntity;
import com.jeesite.modules.sysfile.entity.MyFileUpload;

@Service
@Transactional(readOnly=false)
public class HandleFileUploadService {
	
	@Autowired MyFileUploadDao myFileUploadDao;
	@Autowired MyFileEntityDao myFileEntityDao;
	
	public void handleFileUploadData(HttpServletRequest request, List<MyFileUpload> uploadList, String attachment, String attachment_del) {
		// 如果上传了附件，则保存成功后需要修改文件数据库
		if(attachment != null && !attachment.equals("")) {
			for(int i=0; i<uploadList.size(); i++) {
				myFileUploadDao.updateBizInfo(uploadList.get(i));
			}			
		}
		// 编辑表单时，如果删除了附件，需要删除文件表（js_sys_file_upload,js_sys_file_entity）中的数据，同时删除硬盘中保存的文件
		if((attachment==null || attachment.equals("")) 
			&&(attachment_del!=null && !attachment_del.equals(""))) {
			String [] delUploadIds = attachment_del.split(",");
			for(int i=0; i<delUploadIds.length; i++) {
				deleteUploadData(request, delUploadIds[i]);
			}			
		} 
		// 如果删除了附件，并且又新增了其它附件
		else if((attachment!=null && !attachment.equals("")) 
			&&(attachment_del!=null && !attachment_del.equals(""))) {
			String [] attachaments = attachment.split(",");
			String [] attachament_dels = attachment_del.split(",");
			HashMap<String, MyFileUpload> fileUploadMap = new HashMap<>();
			for(int k=0; k<attachaments.length; k++) {
				MyFileUpload upload = myFileUploadDao.getById(attachaments[k]);
				if(upload != null) {
					// key:upload id, value: MyFileUpload
					fileUploadMap.put(attachaments[k], upload);				
				}
			}
			for(int i=0;i<attachaments.length;i++) {
				for(int j=0; j<attachament_dels.length; j++) {
					deleteOldUploadData(request, fileUploadMap, attachaments[i], attachament_dels[j]);
				}
			}
			
		}
	}
	
	/**
	 * 删除附件数据，包括DB和file
	 * delAttId-删除附件的id
	 */
	public void deleteUploadData(HttpServletRequest request,String delAttId) {
		if(delAttId != null && !delAttId.equals("")) {
			String rootPath = request.getSession().getServletContext().getRealPath("/"); 
			MyFileUpload myFileUpload2 = myFileUploadDao.getById(delAttId);
			// (1) 删除upload
			myFileUploadDao.deleteFileUploadById(delAttId);			
			// 根据id得到file_id,根据file_id删除FileEntity
			if(myFileUpload2 != null && myFileUpload2.getFileId() != null) {
				MyFileEntity entity = myFileEntityDao.getByFileId(myFileUpload2.getFileId());
				if(entity != null) {
					// (2)删除entity
					myFileEntityDao.deleteFileEntityByFileId(myFileUpload2.getFileId());			
					File file = new File(rootPath+"//userfiles//fileupload//"+entity.getFilePath()+entity.getFileId()+"."+entity.getFileExtension());
					String filePath = file.getAbsolutePath();
					//logger.info(filePath);
					System.out.println("*********************"+filePath);
					if(file.exists()) {
						// (3) 删除文件
						file.delete();
					}
				}				
			}
		}
	}
	
	/**
	 * 只需要删除js_sys_file_upload旧的数据，js_sys_file_entity数据不动，硬盘的文件不动
	 * @param 
	 */
	public void deleteOldUploadData(HttpServletRequest request,HashMap<String, MyFileUpload> fileUploadMap, String attId, String delAttId){
		if(delAttId!= null && !delAttId.equals("")) {
			// 用于判断删除的文件fileid是否在新增的文件里面，如果在，则不要删除（用户操作：先上传了某个文件，然后删除，然后又上传该文件，等于是没有删除）
			// 这种情况只需要删除js_sys_file_upload里面的数据，无需删除js_sys_file_entity和文件
			StringBuffer newFileIds = new StringBuffer();
			 for(Entry<String, MyFileUpload> entry: fileUploadMap.entrySet()){
		        newFileIds.append(entry.getValue().getFileId());
		     }		
			
			MyFileUpload myFileUpload_new = fileUploadMap.get(attId);
			MyFileUpload myFileUpload_old = myFileUploadDao.getById(delAttId);
			if(myFileUpload_old != null && myFileUpload_old.getFileId() != null) {
				myFileUploadDao.deleteFileUploadById(delAttId);
			}
			// 如果fileid不相同，则说明更换了附件,并且删除的fileid不在新增的fileids中，需要删除旧的附件
			if(myFileUpload_old!=null && !myFileUpload_new.getFileId().equals(myFileUpload_old.getFileId()) 
				&& newFileIds.indexOf(myFileUpload_old.getFileId())<0) {
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				MyFileEntity entity = myFileEntityDao.getByFileId(myFileUpload_old.getFileId());
				if(entity != null) {
					myFileEntityDao.deleteFileEntityByFileId(myFileUpload_old.getFileId());
					File file = new File(rootPath+"//userfiles//fileupload//"+entity.getFilePath()+entity.getFileId()+"."+entity.getFileExtension());
					String filePath = file.getAbsolutePath();
					//logger.info(filePath);
					System.out.println("****************"+filePath);
					if(file.exists()) {
						file.delete();
					}
				}
			}
		}
	}
	
	// 删除记录时删除附件数据
	public void deleteAttachment(HttpServletRequest request, String attachments) {
		if(attachments!=null && !attachments.trim().equals("")) {
			String [] attArray = attachments.split(",");
			for(int i=0; i<attArray.length; i++) {
				deleteUploadData(request, attArray[i]);
			}
		}
	}
}
