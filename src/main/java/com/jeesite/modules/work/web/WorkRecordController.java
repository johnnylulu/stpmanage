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
import com.jeesite.modules.work.entity.WorkRecord;
import com.jeesite.modules.work.service.WorkRecordService;

/**
 * 工作履历Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/work/workRecord")
public class WorkRecordController extends BaseController {

	@Autowired
	private WorkRecordService workRecordService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public WorkRecord get(String id, boolean isNewRecord) {
		return workRecordService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(WorkRecord workRecord, Model model) {
		model.addAttribute("workRecord", workRecord);
		return "modules/work/workRecordList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<WorkRecord> listData(WorkRecord workRecord, HttpServletRequest request, HttpServletResponse response) {
		workRecord.setIsEnable(true);
		Page<WorkRecord> page = workRecordService.findPage(new Page<WorkRecord>(request, response), workRecord); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(WorkRecord workRecord, Model model) {
		model.addAttribute("workRecord", workRecord);
		return "modules/work/workRecordForm";
	}

	/**
	 * 保存工作履历
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated WorkRecord workRecord) {
		workRecordService.save(workRecord);
		return renderResult(Global.TRUE, text("保存工作履历成功！"),workRecord);
	}
	
	/**
	 * 删除工作履历
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(WorkRecord workRecord) {
		workRecordService.delete(workRecord);
		return renderResult(Global.TRUE, text("删除工作履历成功！"));
	}
	
}