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
import com.jeesite.modules.sysfile.entity.MyFileUpload;
import com.jeesite.modules.sysfile.service.MyFileUploadService;

/**
 * 文件上传表Controller
 * @author fjm
 * @version 2018-10-14
 */
@Controller
@RequestMapping(value = "${adminPath}/sysfile/fileUpload")
public class MyFileUploadController extends BaseController {

	@Autowired
	private MyFileUploadService myFileUploadService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public MyFileUpload get(String id, boolean isNewRecord) {
		return myFileUploadService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sysfile:fileUpload:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyFileUpload myFileUpload, Model model) {
		model.addAttribute("fileUpload", myFileUpload);
		return "modules/sysfile/fileUploadList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sysfile:fileUpload:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<MyFileUpload> listData(MyFileUpload myFileUpload, HttpServletRequest request, HttpServletResponse response) {
		Page<MyFileUpload> page = myFileUploadService.findPage(new Page<MyFileUpload>(request, response), myFileUpload); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sysfile:fileUpload:view")
	@RequestMapping(value = "form")
	public String form(MyFileUpload myFileUpload, Model model) {
		model.addAttribute("fileUpload", myFileUpload);
		return "modules/sysfile/fileUploadForm";
	}

	/**
	 * 保存文件上传表
	 */
	@RequiresPermissions("sysfile:fileUpload:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated MyFileUpload myFileUpload) {
		myFileUploadService.save(myFileUpload);
		return renderResult(Global.TRUE, text("保存文件上传表成功！"));
	}
	
	/**
	 * 删除文件上传表
	 */
	@RequiresPermissions("sysfile:fileUpload:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(MyFileUpload myFileUpload) {
		myFileUploadService.delete(myFileUpload);
		return renderResult(Global.TRUE, text("删除文件上传表成功！"));
	}
	
}