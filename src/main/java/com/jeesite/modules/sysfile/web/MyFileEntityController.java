/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.sysfile.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.jeesite.modules.sysfile.entity.MyFileEntity;
import com.jeesite.modules.sysfile.service.MyFileEntityService;

/**
 * 文件实体表Controller
 * @author fjm
 * @version 2018-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sysfile/myFileEntity")
public class MyFileEntityController extends BaseController {

	@Autowired
	private MyFileEntityService myFileEntityService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public MyFileEntity get(String fileId, boolean isNewRecord) {
		return myFileEntityService.get(fileId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sysfile:myFileEntity:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyFileEntity myFileEntity, Model model) {
		model.addAttribute("myFileEntity", myFileEntity);
		return "modules/sysfile/myFileEntityList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sysfile:myFileEntity:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<MyFileEntity> listData(MyFileEntity myFileEntity, HttpServletRequest request, HttpServletResponse response) {
		Page<MyFileEntity> page = myFileEntityService.findPage(new Page<MyFileEntity>(request, response), myFileEntity); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sysfile:myFileEntity:view")
	@RequestMapping(value = "form")
	public String form(MyFileEntity myFileEntity, Model model) {
		model.addAttribute("myFileEntity", myFileEntity);
		return "modules/sysfile/myFileEntityForm";
	}

	/**
	 * 保存文件实体表
	 */
	@RequiresPermissions("sysfile:myFileEntity:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated MyFileEntity myFileEntity) {
		myFileEntityService.save(myFileEntity);
		return renderResult(Global.TRUE, text("保存文件实体表成功！"));
	}
	
	/**
	 * 删除文件实体表
	 */
	@RequiresPermissions("sysfile:myFileEntity:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(MyFileEntity myFileEntity) {
		myFileEntityService.delete(myFileEntity);
		return renderResult(Global.TRUE, text("删除文件实体表成功！"));
	}
	
}