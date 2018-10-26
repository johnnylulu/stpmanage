/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sysfile.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 文件实体表Entity
 * @author fjm
 * @version 2018-10-14
 */
@Table(name="${_prefix}sys_file_entity", alias="a", columns={
		@Column(name="file_id", attrName="fileId", label="文件编号", isPK=true),
		@Column(name="file_md5", attrName="fileMd5", label="文件MD5"),
		@Column(name="file_path", attrName="filePath", label="文件相对路径"),
		@Column(name="file_content_type", attrName="fileContentType", label="文件内容类型"),
		@Column(name="file_extension", attrName="fileExtension", label="文件后缀扩展名"),
		@Column(name="file_size", attrName="fileSize", label="文件大小", comment="文件大小(单位B)"),
	}, orderBy="a.file_id DESC"
)
public class MyFileEntity extends DataEntity<MyFileEntity> {
	
	private static final long serialVersionUID = 1L;
	private String fileId;		// 文件编号
	private String fileMd5;		// 文件MD5
	private String filePath;		// 文件相对路径
	private String fileContentType;		// 文件内容类型
	private String fileExtension;		// 文件后缀扩展名
	private Long fileSize;		// 文件大小(单位B)
	
	public MyFileEntity() {
		this(null);
	}

	public MyFileEntity(String id){
		super(id);
	}
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	@NotBlank(message="文件MD5不能为空")
	@Length(min=0, max=64, message="文件MD5长度不能超过 64 个字符")
	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	
	@NotBlank(message="文件相对路径不能为空")
	@Length(min=0, max=1000, message="文件相对路径长度不能超过 1000 个字符")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@NotBlank(message="文件内容类型不能为空")
	@Length(min=0, max=200, message="文件内容类型长度不能超过 200 个字符")
	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	@NotBlank(message="文件后缀扩展名不能为空")
	@Length(min=0, max=100, message="文件后缀扩展名长度不能超过 100 个字符")
	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	@NotNull(message="文件大小不能为空")
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
}