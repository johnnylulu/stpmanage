/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.honor.web;

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
import com.jeesite.modules.honor.entity.Honor;
import com.jeesite.modules.honor.service.HonorService;

/**
 * 奖励和荣誉Controller
 * @author fjm
 * @version 2018-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/honor/honor")
public class HonorController extends BaseController {

	@Autowired
	private HonorService honorService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Honor get(String id, boolean isNewRecord) {
		return honorService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Honor honor, Model model) {
		model.addAttribute("honor", honor);
		return "modules/honor/honorList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Honor> listData(Honor honor, HttpServletRequest request, HttpServletResponse response) {
		honor.setIsEnable(true);
		Page<Honor> page = honorService.findPage(new Page<Honor>(request, response), honor); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequestMapping(value = "form")
	public String form(Honor honor, Model model) {
		model.addAttribute("honor", honor);
		return "modules/honor/honorForm";
	}

	/**
	 * 保存奖励和荣誉
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(HttpServletRequest request, @Validated Honor honor) {
		honorService.save(request,honor);
		return renderResult(Global.TRUE, text("保存奖励和荣誉成功！"),honor);
	}
	
	/**
	 * 删除奖励和荣誉
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HttpServletRequest request, Honor honor) {
		honorService.delete(request, honor);
		return renderResult(Global.TRUE, text("删除奖励和荣誉成功！"));
	}
	
}