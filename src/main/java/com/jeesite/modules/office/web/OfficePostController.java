/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.office.web;

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
import com.jeesite.modules.office.entity.OfficePost;
import com.jeesite.modules.office.service.OfficePostService;

/**
 * 机构岗位配置表Controller
 * @author fjm
 * @version 2018-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/office/officePost")
public class OfficePostController extends BaseController {

	@Autowired
	private OfficePostService officePostService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OfficePost get(String id, boolean isNewRecord) {
		return officePostService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(OfficePost officePost, Model model) {
		model.addAttribute("officePost", officePost);
		return "modules/office/officePostList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OfficePost> listData(OfficePost officePost, HttpServletRequest request, HttpServletResponse response) {
		Page<OfficePost> page = officePostService.findPage(new Page<OfficePost>(request, response), officePost); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(OfficePost officePost, Model model) {
		model.addAttribute("officePost", officePost);
		return "modules/office/officePostForm";
	}

	/**
	 * 保存机构岗位配置表
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OfficePost officePost) {
		officePostService.save(officePost);
		return renderResult(Global.TRUE, text("保存机构岗位配置表成功！"));
	}
	
	/**
	 * 删除机构岗位配置表
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OfficePost officePost) {
		officePostService.delete(officePost);
		return renderResult(Global.TRUE, text("删除机构岗位配置表成功！"));
	}
	
}