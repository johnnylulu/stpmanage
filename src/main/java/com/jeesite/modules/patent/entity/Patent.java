/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.patent.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 所获专利Entity
 * @author fjm
 * @version 2018-10-11
 */
@Table(name="patent", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="base_info_id", attrName="baseInfoId", label="基础信息表id"),
		@Column(name="name", attrName="name", label="论著名称", queryType=QueryType.LIKE),
		@Column(name="type", attrName="type", label="专利类型"),
		@Column(name="rank", attrName="rank", label="排名"),
		@Column(name="register_no", attrName="registerNo", label="登记号"),
		@Column(name="register_date", attrName="registerDate", label="登记年月"),
		@Column(name="conversion", attrName="conversion", label="转化情况"),
		@Column(name="is_enable", attrName="isEnable", label="是否有效"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_time", attrName="updateTime", label="修改时间"),
	}, orderBy="a.id DESC"
)
public class Patent extends DataEntity<Patent> {
	
	private static final long serialVersionUID = 1L;
	private int baseInfoId;		// 基础信息表id
	private String name;		// 论著名称
	private String type;		// 专利类型
	private Long rank;		// 排名
	private String registerNo;		// 登记号
	private Date registerDate;		// 登记年月
	private String conversion;		// 转化情况
	private boolean isEnable;		// 是否有效
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	
	public Patent() {
		this(null);
	}

	public Patent(String id){
		super(id);
	}
	
	public int getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(int baseInfoId) {
		this.baseInfoId = baseInfoId;
	}
	
	@Length(min=0, max=64, message="论著名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="专利类型长度不能超过 32 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	@Length(min=0, max=32, message="登记号长度不能超过 32 个字符")
	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	@Length(min=0, max=1024, message="转化情况长度不能超过 1024 个字符")
	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
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