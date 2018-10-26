/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.education.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 学习经历Entity
 * @author fjm
 * @version 2018-10-10
 */
@Table(name="education", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="base_info_id", attrName="baseInfoId", label="基础信息表id"),
		@Column(name="start_date", attrName="startDate", label="start_date"),
		@Column(name="end_date", attrName="endDate", label="end_date"),
		@Column(name="school", attrName="school", label="school"),
		@Column(name="education", attrName="education", label="学历"),
		@Column(name="degree", attrName="degree", label="学位"),
		@Column(name="education_system", attrName="educationSystem", label="学制"),
		@Column(name="specialty", attrName="specialty", label="专业"),
		@Column(name="is_enable", attrName="isEnable", label="是否有效"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_time", attrName="updateTime", label="修改时间"),
	}, orderBy="a.id DESC"
)
public class Education extends DataEntity<Education> {
	
	private static final long serialVersionUID = 1L;
	private int baseInfoId;		// 基础信息表id
	private Date startDate;		// start_date
	private Date endDate;		// end_date
	private String school;		// school
	private String education;		// 学历
	private String degree;		// 学位
	private String educationSystem;		// 学制
	private String specialty;		// 专业
	private boolean isEnable;		// 是否有效
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	
	public Education() {
		this(null);
	}

	public Education(String id){
		super(id);
	}
	
	public int getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(int baseInfoId) {
		this.baseInfoId = baseInfoId;
	}
	@JsonFormat(pattern = "yyyy-MM")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonFormat(pattern = "yyyy-MM")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=64, message="school长度不能超过 64 个字符")
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	@Length(min=0, max=16, message="学历长度不能超过 16 个字符")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=16, message="学位长度不能超过 16 个字符")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Length(min=0, max=32, message="学制长度不能超过 32 个字符")
	public String getEducationSystem() {
		return educationSystem;
	}

	public void setEducationSystem(String educationSystem) {
		this.educationSystem = educationSystem;
	}
	
	@Length(min=0, max=32, message="专业长度不能超过 32 个字符")
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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