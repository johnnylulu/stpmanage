/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.talents.web;

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
import com.jeesite.modules.talents.entity.Talents;
import com.jeesite.modules.talents.service.TalentsService;

/**
 * 国家级、省部级人才Controller
 * @author fjm
 * @version 2018-10-08
 */
@Controller
@RequestMapping(value = "${adminPath}/talents/talents")
public class TalentsController extends BaseController {

	@Autowired
	private TalentsService talentsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Talents get(String id, boolean isNewRecord) {
		return talentsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Talents talents, Model model) {
		model.addAttribute("talents", talents);
		return "modules/talents/talentsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Talents> listData(Talents talents, HttpServletRequest request, HttpServletResponse response) {
		talents.setIsEnable(true);
		Page<Talents> page = talentsService.findPage(new Page<Talents>(request, response), talents); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Talents talents, Model model) {
		model.addAttribute("talents", talents);
		return "modules/talents/talentsForm";
	}

	/**
	 * 保存国家级、省部级人才
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Talents talents) {
		talentsService.save(talents);
		return renderResult(Global.TRUE, text("保存国家级、省部级人才成功！"), talents);
	}
	
	/**
	 * 删除国家级、省部级人才
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Talents talents) {
		talentsService.delete(talents);
		return renderResult(Global.TRUE, text("删除国家级、省部级人才成功！"));
	}
	
}