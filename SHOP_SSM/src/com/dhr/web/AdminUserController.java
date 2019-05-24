package com.dhr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhr.domain.User;
import com.dhr.service.AdminUserService;

/**
 * 管理用户
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserSerive;

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@RequestMapping("/findAllUser")
	public String findAllUser(Model model) {
		// 1.注入服务
		// 2.调用服务查询
		List<User> users = adminUserSerive.findAllUser();
		// 3.设置到域
		model.addAttribute("users", users);
		// 4.返回
		return "admin/user/list";
	}
}
