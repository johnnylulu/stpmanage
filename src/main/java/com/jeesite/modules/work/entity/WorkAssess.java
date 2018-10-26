/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.work.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 工作考核记录Entity
 * @author fjm
 * @version 2018-10-11
 */
@Table(name="work_assess", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="base_info_id", attrName="baseInfoId", label="基础信息表id"),
		@Column(name="assess_date", attrName="assessDate", label="考核时间"),
		@Column(name="assess_level", attrName="assessLevel", label="考核等级"),
		@Column(name="remark", attrName="remark", label="考核评语"),
		@Column(name="work_unit", attrName="workUnit", label="考核单位"),
		@Column(name="is_enable", attrName="isEnable", label="是否有效"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_time", attrName="updateTime", label="修改时间"),
	}, orderBy="a.id DESC"
)
public class WorkAssess extends DataEntity<WorkAssess> {
	
	private static final long serialVersionUID = 1L;
	private int baseInfoId;		// 基础信息表id
	private Date assessDate;		// 考核时间
	private String assessLevel;		// 考核等级
	private String remark;		// 考核评语
	private String workUnit;		// 考核单位
	private boolean isEnable;		// 是否有效
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	
	public WorkAssess() {
		this(null);
	}

	public WorkAssess(String id){
		super(id);
	}
	
	public int getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(int baseInfoId) {
		this.baseInfoId = baseInfoId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(Date assessDate) {
		this.assessDate = assessDate;
	}
	
	@Length(min=0, max=16, message="考核等级长度不能超过 16 个字符")
	public String getAssessLevel() {
		return assessLevel;
	}

	public void setAssessLevel(String assessLevel) {
		this.assessLevel = assessLevel;
	}
	
	@Length(min=0, max=1024, message="考核评语长度不能超过 1024 个字符")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=64, message="考核单位长度不能超过 64 个字符")
	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
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