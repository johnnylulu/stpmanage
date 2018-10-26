/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.office.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 机构岗位配置表Entity
 * @author fjm
 * @version 2018-10-24
 */
@Table(name="office_post", alias="a", columns={
		@Column(name="id", attrName="id", label="主键ID", isPK=true),
		@Column(name="office_code", attrName="officeCode", label="机构代码"),
		@Column(name="name", attrName="name", label="岗位名称", queryType=QueryType.LIKE),
		@Column(name="standard_num", attrName="standardNum", label="标准配置人数"),
		@Column(name="real_num", attrName="realNum", label="实际人数"),
		@Column(name="remark", attrName="remark", label="备注"),
	}, orderBy="a.id DESC"
)
public class OfficePost extends DataEntity<OfficePost> {
	
	private static final long serialVersionUID = 1L;
	private String officeCode;		// 机构代码
	private String name;		// 岗位名称
	private Long standardNum;		// 标准配置人数
	private Long realNum;		// 实际人数
	private String remark;		// 备注
	
	public OfficePost() {
		this(null);
	}

	public OfficePost(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="岗位名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	@NotNull(message="机构代码不能为空")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getStandardNum() {
		return standardNum;
	}

	public void setStandardNum(Long standardNum) {
		this.standardNum = standardNum;
	}
	
	public Long getRealNum() {
		return realNum;
	}

	public void setRealNum(Long realNum) {
		this.realNum = realNum;
	}
	
	@Length(min=0, max=1024, message="备注长度不能超过 1024 个字符")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}