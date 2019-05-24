package com.dhr.store.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhr.store.domain.Admin;
import com.dhr.store.service.AdminService;

/**
 * @author Mr DU 管理员
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

	@Autowired
	private AdminService adminService;

	/**
	 * 管理员
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex() {
		return "admin/index";
	}

	@RequestMapping("/home")
	public String showHome() {
		return "admin/home";
	}

	/**
	 * 管理员登录
	 * 
	 * @param admin
	 * @return
	 */
	@RequestMapping("/adminLogin")
	public String adminLogin(Admin admin, HttpServletRequest request, Model model) {
		// 注入service
		boolean a = adminService.loginAdmin(admin);
		if (a) {
			request.getSession().setAttribute("admin", admin);
			return "redirect:/admin/home";
		} else {
			model.addAttribute("msg", "管理员名或密码错误!");
		}

		return "admin/index";
	}
}
