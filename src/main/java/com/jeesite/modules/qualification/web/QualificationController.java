/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.qualification.web;

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
import com.jeesite.modules.qualification.entity.Qualification;
import com.jeesite.modules.qualification.service.QualificationService;

/**
 * 专业技术资格Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/qualification/qualification")
public class QualificationController extends BaseController {

	@Autowired
	private QualificationService qualificationService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Qualification get(String id, boolean isNewRecord) {
		return qualificationService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Qualification qualification, Model model) {
		model.addAttribute("qualification", qualification);
		return "modules/qualification/qualificationList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Qualification> listData(Qualification qualification, HttpServletRequest request, HttpServletResponse response) {
		qualification.setIsEnable(true);
		Page<Qualification> page = qualificationService.findPage(new Page<Qualification>(request, response), qualification); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Qualification qualification, Model model) {
		model.addAttribute("qualification", qualification);
		return "modules/qualification/qualificationForm";
	}

	/**
	 * 保存专业技术资格
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(HttpServletRequest request, @Validated Qualification qualification) {
		qualificationService.save(request, qualification);
		return renderResult(Global.TRUE, text("保存专业技术资格成功！"),qualification);
	}
	
	/**
	 * 删除专业技术资格
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HttpServletRequest request, Qualification qualification) {
		qualificationService.delete(request, qualification);
		return renderResult(Global.TRUE, text("删除专业技术资格成功！"));
	}
	
}