/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.title.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 专业技术资格Entity
 * @author fjm
 * @version 2018-10-11
 */
@Table(name="title", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="base_info_id", attrName="baseInfoId", label="基础信息表id"),
		@Column(name="name", attrName="name", label="职称名称", queryType=QueryType.LIKE),
		@Column(name="department", attrName="department", label="颁发部门"),
		@Column(name="work_unit", attrName="workUnit", label="工作单位"),
		@Column(name="title_attachment", attrName="titleAtt", label="附件"),
		@Column(name="is_enable", attrName="isEnable", label="是否有效"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_time", attrName="updateTime", label="修改时间"),
	}, orderBy="a.id DESC"
)
public class Title extends DataEntity<Title> {
	
	private static final long serialVersionUID = 1L;
	private int baseInfoId;		// 基础信息表id
	private String name;		// 职称名称
	private String department;		// 颁发部门
	private String workUnit;		// 工作单位
	private String titleAtt;		// 附件
	private String titleAtt__del;		// 附件
	private boolean isEnable;		// 是否有效
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	
	public Title() {
		this(null);
	}

	public Title(String id){
		super(id);
	}
	
	public int getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(int baseInfoId) {
		this.baseInfoId = baseInfoId;
	}
	
	@Length(min=0, max=64, message="职称名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="颁发部门长度不能超过 64 个字符")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=64, message="工作单位长度不能超过 64 个字符")
	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	
	public String getTitleAtt() {
		return titleAtt;
	}

	public void setTitleAtt(String titleAtt) {
		this.titleAtt = titleAtt;
	}

	public String getTitleAtt__del() {
		return titleAtt__del;
	}

	public void setTitleAtt__del(String titleAtt__del) {
		this.titleAtt__del = titleAtt__del;
	}

	public boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}