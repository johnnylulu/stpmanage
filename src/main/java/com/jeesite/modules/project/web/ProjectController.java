/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.project.web;

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
import com.jeesite.modules.project.entity.Project;
import com.jeesite.modules.project.service.ProjectService;

/**
 * 参与的课题Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/project/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Project get(String id, boolean isNewRecord) {
		return projectService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/project/projectList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Project> listData(Project project, HttpServletRequest request, HttpServletResponse response) {
		project.setIsEnable(true);
		Page<Project> page = projectService.findPage(new Page<Project>(request, response), project); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Project project, Model model) {
		model.addAttribute("project", project);
		return "modules/project/projectForm";
	}

	/**
	 * 保存参与的课题
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Project project) {
		projectService.save(project);
		return renderResult(Global.TRUE, text("保存参与的课题成功！"),project);
	}
	
	/**
	 * 删除参与的课题
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Project project) {
		projectService.delete(project);
		return renderResult(Global.TRUE, text("删除参与的课题成功！"));
	}
	
}