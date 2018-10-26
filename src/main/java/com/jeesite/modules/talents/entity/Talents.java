/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.talents.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 国家级、省部级人才Entity
 * @author fjm
 * @version 2018-10-08
 */
@Table(name="talents", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="base_info_id", attrName="baseInfoId", label="基础信息表id"),
		@Column(name="beselected_time", attrName="beselectedTime", label="入选时间"),
		@Column(name="project_name", attrName="projectName", label="人才工程项目名称", queryType=QueryType.LIKE),
		@Column(name="level", attrName="level", label="等级"),
		@Column(name="award_unit", attrName="awardUnit", label="授予单位"),
		@Column(name="is_enable", attrName="isEnable", label="是否有效"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_time", attrName="updateTime", label="修改时间"),
	}, orderBy="a.id DESC"
)
public class Talents extends DataEntity<Talents> {
	
	private static final long serialVersionUID = 1L;
	private int baseInfoId;
	private Date beselectedTime;		// 入选时间
	private String projectName;		// 人才工程项目名称
	private String level;		// 等级
	private String awardUnit;		// 授予单位
	private boolean isEnable;		// 是否有效
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	
	public Talents() {
		this(null);
	}

	public Talents(String id){
		super(id);
	}
	
	public int getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(int baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBeselectedTime() {
		return beselectedTime;
	}

	public void setBeselectedTime(Date beselectedTime) {
		this.beselectedTime = beselectedTime;
	}
	
	@Length(min=0, max=64, message="人才工程项目名称长度不能超过 64 个字符")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=0, max=32, message="等级长度不能超过 32 个字符")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Length(min=0, max=64, message="授予单位长度不能超过 64 个字符")
	public String getAwardUnit() {
		return awardUnit;
	}

	public void setAwardUnit(String awardUnit) {
		this.awardUnit = awardUnit;
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