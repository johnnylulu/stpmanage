/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sysfile.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 文件上传表Entity
 * @author fjm
 * @version 2018-10-14
 */
@Table(name="${_prefix}sys_file_upload", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="file_id", attrName="fileId", label="文件编号"),
		@Column(name="file_name", attrName="fileName", label="文件名称", queryType=QueryType.LIKE),
		@Column(name="file_type", attrName="fileType", label="文件分类", comment="文件分类（image、media、file）"),
		@Column(name="biz_key", attrName="bizKey", label="业务主键"),
		@Column(name="biz_type", attrName="bizType", label="业务类型"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class MyFileUpload extends DataEntity<MyFileUpload> {
	
	private static final long serialVersionUID = 1L;
	private String fileId;		// 文件编号
	private String fileName;		// 文件名称
	private String fileType;		// 文件分类（image、media、file）
	private String bizKey;		// 业务主键
	private String bizType;		// 业务类型
	
	public MyFileUpload() {
		this(null);
	}

	public MyFileUpload(String id){
		super(id);
	}
	
	@NotBlank(message="文件编号不能为空")
	@Length(min=0, max=64, message="文件编号长度不能超过 64 个字符")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	@NotBlank(message="文件名称不能为空")
	@Length(min=0, max=500, message="文件名称长度不能超过 500 个字符")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@NotBlank(message="文件分类不能为空")
	@Length(min=0, max=20, message="文件分类长度不能超过 20 个字符")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Length(min=0, max=64, message="业务主键长度不能超过 64 个字符")
	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}
	
	@Length(min=0, max=64, message="业务类型长度不能超过 64 个字符")
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
}