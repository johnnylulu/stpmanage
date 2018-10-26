/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.academic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.academic.entity.AcademicOrganization;
import com.jeesite.modules.academic.service.AcademicOrganizationService;

/**
 * 参加学术团体经历Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/academic/academicOrganization")
public class AcademicOrganizationController extends BaseController {

	@Autowired
	private AcademicOrganizationService academicOrganizationService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public AcademicOrganization get(String id, boolean isNewRecord) {
		return academicOrganizationService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(AcademicOrganization academicOrganization, Model model) {
		model.addAttribute("academicOrganization", academicOrganization);
		return "modules/academic/academicOrganizationList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<AcademicOrganization> listData(AcademicOrganization academicOrganization, HttpServletRequest request, HttpServletResponse response) {
		academicOrganization.setIsEnable(true);
		Page<AcademicOrganization> page = academicOrganizationService.findPage(new Page<AcademicOrganization>(request, response), academicOrganization); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(AcademicOrganization academicOrganization, Model model) {
		model.addAttribute("academicOrganization", academicOrganization);
		return "modules/academic/academicOrganizationForm";
	}

	/**
	 * 保存参加学术团体经历
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated AcademicOrganization academicOrganization) {
		academicOrganizationService.save(academicOrganization);
		return renderResult(Global.TRUE, text("保存参加学术团体经历成功！"),academicOrganization);
	}
	
	/**
	 * 删除参加学术团体经历
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(AcademicOrganization academicOrganization) {
		academicOrganizationService.delete(academicOrganization);
		return renderResult(Global.TRUE, text("删除参加学术团体经历成功！"));
	}
	
}