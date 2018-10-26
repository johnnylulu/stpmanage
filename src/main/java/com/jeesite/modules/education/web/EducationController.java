/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.education.web;

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
import com.jeesite.modules.education.entity.Education;
import com.jeesite.modules.education.service.EducationService;

/**
 * 学习经历Controller
 * @author fjm
 * @version 2018-10-10
 */
@Controller
@RequestMapping(value = "${adminPath}/education/education")
public class EducationController extends BaseController {

	@Autowired
	private EducationService educationService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Education get(String id, boolean isNewRecord) {
		return educationService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Education education, Model model) {
		model.addAttribute("education", education);
		return "modules/education/educationList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Education> listData(Education education, HttpServletRequest request, HttpServletResponse response) {
		education.setIsEnable(true);
		Page<Education> page = educationService.findPage(new Page<Education>(request, response), education); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Education education, Model model) {
		model.addAttribute("education", education);
		return "modules/education/educationForm";
	}

	/**
	 * 保存学习经历
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Education education) {
		educationService.save(education);
		return renderResult(Global.TRUE, text("保存学习经历成功！"),education);
	}
	
	/**
	 * 删除学习经历
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Education education) {
		educationService.delete(education);
		return renderResult(Global.TRUE, text("删除学习经历成功！"));
	}
	
}