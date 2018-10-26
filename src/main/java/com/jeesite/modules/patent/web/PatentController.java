/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.patent.web;

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
import com.jeesite.modules.patent.entity.Patent;
import com.jeesite.modules.patent.service.PatentService;

/**
 * 所获专利Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/patent/patent")
public class PatentController extends BaseController {

	@Autowired
	private PatentService patentService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Patent get(String id, boolean isNewRecord) {
		return patentService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Patent patent, Model model) {
		model.addAttribute("patent", patent);
		return "modules/patent/patentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Patent> listData(Patent patent, HttpServletRequest request, HttpServletResponse response) {
		patent.setIsEnable(true);
		Page<Patent> page = patentService.findPage(new Page<Patent>(request, response), patent); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Patent patent, Model model) {
		model.addAttribute("patent", patent);
		return "modules/patent/patentForm";
	}

	/**
	 * 保存所获专利
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Patent patent) {
		patentService.save(patent);
		return renderResult(Global.TRUE, text("保存所获专利成功！"),patent);
	}
	
	/**
	 * 删除所获专利
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Patent patent) {
		patentService.delete(patent);
		return renderResult(Global.TRUE, text("删除所获专利成功！"));
	}
	
}