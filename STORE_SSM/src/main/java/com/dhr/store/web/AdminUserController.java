package com.dhr.store.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhr.store.domain.User;
import com.dhr.store.service.AdminUserService;

/**
 * @author Mr DU 用户管理
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

	// 注入服务
	@Autowired
	private AdminUserService adminService;

	/**
	 * 显示所有用户
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showUser")
	public String showUser(Model model) {
		List<User> users = adminService.findAllUser();
		if (users != null && users.size() > 0) {
			model.addAttribute("user", users);
		}
		return "admin/user/list";
	}
}
