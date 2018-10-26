/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.work.web;

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
import com.jeesite.modules.work.entity.WorkAssess;
import com.jeesite.modules.work.service.WorkAssessService;

/**
 * 工作考核记录Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/work/workAssess")
public class WorkAssessController extends BaseController {

	@Autowired
	private WorkAssessService workAssessService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WorkAssess get(String id, boolean isNewRecord) {
		return workAssessService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(WorkAssess workAssess, Model model) {
		model.addAttribute("workAssess", workAssess);
		return "modules/work/workAssessList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WorkAssess> listData(WorkAssess workAssess, HttpServletRequest request, HttpServletResponse response) {
		workAssess.setIsEnable(true);
		Page<WorkAssess> page = workAssessService.findPage(new Page<WorkAssess>(request, response), workAssess); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(WorkAssess workAssess, Model model) {
		model.addAttribute("workAssess", workAssess);
		return "modules/work/workAssessForm";
	}

	/**
	 * 保存工作考核记录
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WorkAssess workAssess) {
		workAssessService.save(workAssess);
		return renderResult(Global.TRUE, text("保存工作考核记录成功！"),workAssess);
	}
	
	/**
	 * 删除工作考核记录
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WorkAssess workAssess) {
		workAssessService.delete(workAssess);
		return renderResult(Global.TRUE, text("删除工作考核记录成功！"));
	}
	
}