/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.person.web;

import java.util.ArrayList;
import java.util.List;

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
import com.jeesite.modules.person.entity.PersonBaseInfo;
import com.jeesite.modules.person.service.PersonBaseInfoService;
import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.entity.Role;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.entity.UserDataScope;
import com.jeesite.modules.sys.service.UserService;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 人员基本信息表Controller
 * @author fjm
 * @version 2018-10-08
 */
@Controller
@RequestMapping(value = "${adminPath}/person/personBaseInfo")
public class PersonBaseInfoController extends BaseController {

	@Autowired
	private PersonBaseInfoService personBaseInfoService;
	@Autowired
	private UserService userService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PersonBaseInfo get(String id, boolean isNewRecord) {
		return personBaseInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("person:personBaseInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PersonBaseInfo personBaseInfo, Model model) {
		model.addAttribute("personBaseInfo", personBaseInfo);
		return "modules/person/personBaseInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("person:personBaseInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PersonBaseInfo> listData(PersonBaseInfo personBaseInfo, HttpServletRequest request, HttpServletResponse response) {
		User user =  UserUtils.getUser();
		// 如果不是管理员，则只能查自己所属机构的数据
		if(!user.isSuperAdmin() && !user.isAdmin()) {
			// 查询用户的数据范围
			UserDataScope userDataScope = new UserDataScope();
			userDataScope.setUserCode(user.getUserCode());
			userDataScope.setCtrlPermi(UserDataScope.CTRL_PERMI_HAVE);
			List<UserDataScope> userDataScopeList = userService.findDataScopeList(userDataScope);
			List<String> officeList = new ArrayList<>();
			if(userDataScopeList != null) {
				for(int i=0; i<userDataScopeList.size(); i++) {
					UserDataScope uds = userDataScopeList.get(i);
					if("Office".equals(uds.getCtrlType())) {
						officeList.add(uds.getCtrlData());
					}
				}
			}
			// 当前用户所属的机构
			Employee employee = (Employee)user.getRefObj();
			String officeCode = employee.getOffice().getOfficeCode();
			if(!officeList.contains(officeCode)) {
				officeList.add(officeCode);
			}
			personBaseInfo.setOfficeCodeList(officeList);
		}		
		personBaseInfo.setIsEnable(true);
		Page<PersonBaseInfo> page = personBaseInfoService.findPage(new Page<PersonBaseInfo>(request, response), personBaseInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("person:personBaseInfo:view")
	@RequestMapping(value = "form")
	public String form(PersonBaseInfo personBaseInfo, Model model) {
		model.addAttribute("personBaseInfo", personBaseInfo);
		return "modules/person/personBaseInfoForm";
	}
	
	/**
	 * 查看详细情况
	 */
	@RequiresPermissions("person:personBaseInfo:view")
	@RequestMapping(value = "detail")
	public String detail(PersonBaseInfo personBaseInfo, Model model) {
		model.addAttribute("personBaseInfo", personBaseInfo);
		return "modules/person/personBaseInfoDetail";
	}
	
	/**
	 * 审核表单跳转
	 */
	@RequiresPermissions("person:personBaseInfo:view")
	@RequestMapping(value = "auditForm")
	public String auditForm(PersonBaseInfo personBaseInfo, Model model) {
		model.addAttribute("personBaseInfo", personBaseInfo);
		User user = UserUtils.getUser();
		model.addAttribute("user", user);
		return "modules/person/personBaseInfoAudit";
	}
	
	/**
	 * 审核表单
	 */
	@RequiresPermissions("person:personBaseInfo:view")
	@RequestMapping(value = "auditSave")
	@ResponseBody
	public String auditSave(HttpServletRequest request, PersonBaseInfo personBaseInfo, Model model) {
		String userOpt = request.getParameter("userOpt");
		String auditStatus = personBaseInfo.getAuditStatus();
		User user = UserUtils.getUser();
		// 重置表单
		if(userOpt!=null && "reset".equals(userOpt) && auditStatus != null && "1".equals(auditStatus)) {
			if(user.isSuperAdmin() || user.isAdmin()) {
				personBaseInfo.setAuditStatus(auditStatus);
				personBaseInfoService.auditSave(personBaseInfo);
				return renderResult(Global.TRUE, text("重置成功！"), personBaseInfo);
			}else {
				return renderResult(Global.FALSE, text("只有管理员可以重置表单！"), personBaseInfo);	
			}
		}		
		List<Role> roleList = user.getRoleList();
		boolean auth = false; // 审核表单权限
		if(user.isSuperAdmin()) {
			auth = true;
		}
		for(int i=0; i<roleList.size(); i++) {
			String roleCode = roleList.get(i).getRoleCode();
			// 如果当前状态是1-录入，并且用户有核验的角色
			if("1".equals(auditStatus) && "audit_1".equals(roleCode)) {
				auth = true;
				break;
			} else if("2".equals(auditStatus) && "audit_2".equals(roleCode)) {
				// 如果当前状态是2-已核验，并且用户有确认的角色
				auth = true;
				break;
			}  
		}
		model.addAttribute("personBaseInfo", personBaseInfo);
		if(personBaseInfo.getId()==null || auditStatus==null || (!auditStatus.equals("1")&&!auditStatus.equals("2"))) {
			return renderResult(Global.FALSE, text("审核失败表单状态错误！"), personBaseInfo);
		} else if(!auth){
			return renderResult(Global.FALSE, text("您没有当前操作的权限！"), personBaseInfo);			
		} else {			
			personBaseInfo.setAuditStatus(String.valueOf((Integer.parseInt(auditStatus)+1)));
			personBaseInfoService.auditSave(personBaseInfo);
			return renderResult(Global.TRUE, text("审核成功！"), personBaseInfo);
		}		
	}

	/**
	 * 保存人员基本信息表
	 */
	@RequiresPermissions("person:personBaseInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PersonBaseInfo personBaseInfo, Model model, HttpServletRequest request) {
		//String photo_del = request.getParameter("photo_del");
		personBaseInfoService.save(request,personBaseInfo);
		//String id = personBaseInfo.getId();
		model.addAttribute("personBaseInfo", personBaseInfo);		
		return renderResult(Global.TRUE, text("保存人员基本信息表成功！"), personBaseInfo);
	}
	
	/**
	 * 删除人员基本信息表
	 */
	@RequiresPermissions("person:personBaseInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HttpServletRequest request,PersonBaseInfo personBaseInfo) {
		personBaseInfoService.delete(request,personBaseInfo);
		return renderResult(Global.TRUE, text("删除人员基本信息表成功！"));
	}
	
}