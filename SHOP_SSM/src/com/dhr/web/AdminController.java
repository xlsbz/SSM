package com.dhr.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhr.domain.Admin;
import com.dhr.service.AdminService;

/**
 * 后台系统
 * 
 * @author Mr DU
 *
 */
@Controller
public class AdminController {

	// 注入服务
	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin")
	public String adminIndex() {
		return "admin/index";
	}

	/**
	 * 管理员登录
	 * 
	 * @param admin
	 * @return
	 */
	@RequestMapping("/admin/login")
	public String adminLogin(Admin admin, Model model, HttpServletRequest request) {
		// 1.注入服务
		// 2.调用登录方法
		Admin a = adminService.adminLogin(admin);
		if (a == null) {
			model.addAttribute("msg", "用户名或密码错误!");
			return "admin/index";
		}
		// 3.设置到session
		request.getSession().setAttribute("admin", a);
		// 4.返回
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public String indexAdmin() {
		return "admin/home/home";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/admin/quit")
	public String adminQuit(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/admin";
	}
}
