/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.honor.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 奖励和荣誉Entity
 * @author fjm
 * @version 2018-10-11
 */
@Table(name="honor", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="base_info_id", attrName="baseInfoId", label="基础信息表id"),
		@Column(name="name", attrName="name", label="论著名称", queryType=QueryType.LIKE),
		@Column(name="department", attrName="department", label="颁发部门"),
		@Column(name="rank", attrName="rank", label="排名"),
		@Column(name="obtain_date", attrName="obtainDate", label="获奖年月"),
		@Column(name="level", attrName="level", label="等级"),
		@Column(name="honor_attachment", attrName="honorAtt", label="附件"),
		@Column(name="is_enable", attrName="isEnable", label="是否有效"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_time", attrName="updateTime", label="修改时间"),
	}, orderBy="a.id DESC"
)
public class Honor extends DataEntity<Honor> {
	
	private static final long serialVersionUID = 1L;
	private int baseInfoId;		// 基础信息表id
	private String name;		// 论著名称
	private String department;		// 颁发部门
	private Long rank;		// 排名
	private Date obtainDate;		// 获奖年月
	private String level;		// 等级
	private String honorAtt;		// 附件
	private String honorAtt__del;
	private boolean isEnable;		// 是否有效
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	
	public Honor() {
		this(null);
	}

	public Honor(String id){
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
	
	@Length(min=0, max=64, message="颁发部门长度不能超过 64 个字符")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	@JsonFormat(pattern = "yyyy-MM")
	public Date getObtainDate() {
		return obtainDate;
	}

	public void setObtainDate(Date obtainDate) {
		this.obtainDate = obtainDate;
	}
	
	@Length(min=0, max=32, message="等级长度不能超过 32 个字符")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getHonorAtt() {
		return honorAtt;
	}

	public void setHonorAtt(String honorAtt) {
		this.honorAtt = honorAtt;
	}

	public String getHonorAtt__del() {
		return honorAtt__del;
	}

	public void setHonorAtt__del(String honorAtt__del) {
		this.honorAtt__del = honorAtt__del;
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