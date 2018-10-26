/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.treatise.web;

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
import com.jeesite.modules.treatise.entity.Treatise;
import com.jeesite.modules.treatise.service.TreatiseService;

/**
 * 论著Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/treatise/treatise")
public class TreatiseController extends BaseController {

	@Autowired
	private TreatiseService treatiseService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Treatise get(String id, boolean isNewRecord) {
		return treatiseService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Treatise treatise, Model model) {
		model.addAttribute("treatise", treatise);
		return "modules/treatise/treatiseList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Treatise> listData(Treatise treatise, HttpServletRequest request, HttpServletResponse response) {
		treatise.setIsEnable(true);
		Page<Treatise> page = treatiseService.findPage(new Page<Treatise>(request, response), treatise); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Treatise treatise, Model model) {
		model.addAttribute("treatise", treatise);
		return "modules/treatise/treatiseForm";
	}

	/**
	 * 保存论著
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Treatise treatise) {
		treatiseService.save(treatise);
		return renderResult(Global.TRUE, text("保存论著成功！"),treatise);
	}
	
	/**
	 * 删除论著
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Treatise treatise) {
		treatiseService.delete(treatise);
		return renderResult(Global.TRUE, text("删除论著成功！"));
	}
	
}