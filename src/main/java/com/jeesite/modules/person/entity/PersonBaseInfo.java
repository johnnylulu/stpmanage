/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.person.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 人员基本信息表Entity
 * @author fjm
 * @version 2018-10-08
 */
@Table(name="person_base_info", alias="a", columns={
		@Column(name="id", attrName="sid", label="主键ID", isPK=true),		
		@Column(name="name", attrName="name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="office_code", attrName="officeCode", label="所属机构代码"),
		@Column(name="audit_status", attrName="auditStatus", label="表单状态"),
		@Column(name="ever_name", attrName="everName", label="曾用名", queryType=QueryType.LIKE),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="birth_date", attrName="birthDate", label="出生时间"),
		@Column(name="native_place", attrName="nativePlace", label="籍贯"),
		@Column(name="address", attrName="address", label="住址"),
		@Column(name="nationality", attrName="nationality", label="国籍"),
		@Column(name="nation", attrName="nation", label="民族"),
		@Column(name="politics_status", attrName="politicsStatus", label="政治面貌"),
		@Column(name="ident_type", attrName="identType", label="证件类别"),
		@Column(name="ident_number", attrName="identNumber", label="证件号码"),
		@Column(name="startwork_time", attrName="startworkTime", label="参加工作时间"),
		@Column(name="cur_work_unit", attrName="curWorkUnit", label="现工作单位"),
		@Column(name="position", attrName="position", label="岗位"),
		@Column(name="title", attrName="title", label="职称", queryType=QueryType.LIKE),
		@Column(name="education", attrName="education", label="学历"),
		@Column(name="degree", attrName="degree", label="学位"),
		@Column(name="specialty", attrName="specialty", label="专业"),
		@Column(name="photo", attrName="photo", label="照片"),
		@Column(name="telephone", attrName="telephone", label="固定电话"),
		@Column(name="mobile", attrName="mobile", label="手机号码"),
		@Column(name="email", attrName="email", label="电子邮箱"),
		@Column(name="is_enable", attrName="isEnable", label="是否有效"),
		@Column(name="create_by", attrName="createBy", label="创建人"),
		@Column(name="create_time", attrName="createTime", label="创建时间"),
		@Column(name="update_by", attrName="updateBy", label="修改人"),
		@Column(name="update_time", attrName="updateTime", label="修改时间")
	}, orderBy="a.id DESC"
)
public class PersonBaseInfo extends DataEntity<PersonBaseInfo> {
	
	private static final long serialVersionUID = 1L;
	private int sid;
	private String name;		// 姓名
	private String officeCode;
	private String auditStatus; // 表单状态：1-录入，2-已核验，3-确认
	private List<String> officeCodeList;
	private String everName;		// 曾用名
	private String sex;		// 性别
	private Date birthDate;		// 出生时间
	private String nativePlace;		// 籍贯
	private String address;		// 住址
	private String nationality;		// 国籍
	private String nation;		// 民族
	private String politicsStatus;		// 政治面貌
	private Long identType;		// 证件类别
	private String identNumber;		// 证件号码
	private Date startworkTime;		// 参加工作时间
	private String curWorkUnit;		// 现工作单位
	private String position;		// 岗位
	private String title;		// 职称
	private String education;		// 学历
	private String degree;		// 学位
	private String specialty;		// 专业
	private String photo;		// 照片
	private String photo__del;
	private String telephone;		// 固定电话
	private String mobile;		// 手机号码
	private String email;		// 电子邮箱
	private boolean isEnable;		// 是否有效
	private String createBy;
	private Date createTime;		// 创建时间
	private String updateBy;
	private Date updateTime;		// 修改时间
	
	public PersonBaseInfo() {
		this(null);
	}

	public PersonBaseInfo(String id){
		super(id);
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@NotBlank(message="姓名不能为空")
	@Length(min=0, max=32, message="姓名长度不能超过 32 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public List<String> getOfficeCodeList() {
		return officeCodeList;
	}

	public void setOfficeCodeList(List<String> officeCodeList) {
		this.officeCodeList = officeCodeList;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Length(min=0, max=32, message="曾用名长度不能超过 32 个字符")
	public String getEverName() {
		return everName;
	}

	public void setEverName(String everName) {
		this.everName = everName;
	}
	
	@Length(min=0, max=1, message="性别长度不能超过 1 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Length(min=0, max=32, message="籍贯长度不能超过 32 个字符")
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Length(min=0, max=256, message="住址长度不能超过 256 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=32, message="国籍长度不能超过 32 个字符")
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Length(min=0, max=32, message="民族长度不能超过 32 个字符")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=32, message="政治面貌长度不能超过 32 个字符")
	public String getPoliticsStatus() {
		return politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	
	public Long getIdentType() {
		return identType;
	}

	public void setIdentType(Long identType) {
		this.identType = identType;
	}
	
	@Length(min=0, max=32, message="证件号码长度不能超过 32 个字符")
	public String getIdentNumber() {
		return identNumber;
	}

	public void setIdentNumber(String identNumber) {
		this.identNumber = identNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getStartworkTime() {
		return startworkTime;
	}

	public void setStartworkTime(Date startworkTime) {
		this.startworkTime = startworkTime;
	}
	
	@Length(min=0, max=64, message="现工作单位长度不能超过 64 个字符")
	public String getCurWorkUnit() {
		return curWorkUnit;
	}

	public void setCurWorkUnit(String curWorkUnit) {
		this.curWorkUnit = curWorkUnit;
	}
	
	@Length(min=0, max=32, message="岗位长度不能超过 32 个字符")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=0, max=32, message="职称长度不能超过 32 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	@Length(min=0, max=32, message="专业长度不能超过 32 个字符")
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPhoto__del() {
		return photo__del;
	}

	public void setPhoto__del(String photo__del) {
		this.photo__del = photo__del;
	}

	@Length(min=0, max=32, message="固定电话长度不能超过 32 个字符")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=32, message="手机号码长度不能超过 32 个字符")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=64, message="电子邮箱长度不能超过 64 个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	
}