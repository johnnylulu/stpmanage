/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.title.web;

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
import com.jeesite.modules.title.entity.Title;
import com.jeesite.modules.title.service.TitleService;

/**
 * 职称Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/title/title")
public class TitleController extends BaseController {

	@Autowired
	private TitleService titleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Title get(String id, boolean isNewRecord) {
		return titleService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Title title, Model model) {
		model.addAttribute("title", title);
		return "modules/title/titleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Title> listData(Title title, HttpServletRequest request, HttpServletResponse response) {
		title.setIsEnable(true);
		Page<Title> page = titleService.findPage(new Page<Title>(request, response), title); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Title title, Model model) {
		model.addAttribute("title", title);
		return "modules/title/titleForm";
	}

	/**
	 * 保存职称
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(HttpServletRequest request, @Validated Title title) {
		titleService.save(request,title);
		return renderResult(Global.TRUE, text("保存职称成功！"),title);
	}
	
	/**
	 * 删除专业技术资格
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HttpServletRequest request, Title title) {
		titleService.delete(request, title);
		return renderResult(Global.TRUE, text("删除职称成功！"));
	}
	
}