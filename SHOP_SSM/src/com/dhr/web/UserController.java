package com.dhr.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhr.domain.User;
import com.dhr.service.UserService;
import com.dhr.util.ShopResult;

/**
 * 用户模块
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	// 注入服务
	@Autowired
	private UserService userService;

	/**
	 * 跳转到注册页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/toRegister")
	public String toRegister(Model model) {
		return "jsp/userRegister";
	}

	/**
	 * 跳转到登录页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		return "jsp/userLogin";
	}

	/**
	 * 异步检查用户名
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping("/check/{username}")
	@ResponseBody
	public ShopResult checkUser(@PathVariable String username) {
		// 注入服务
		// 调用服务
		User user = userService.getUserName(username);
		if (user == null) {
			return ShopResult.ok("恭喜，该用户可以注册!", null);
		}
		// 返回
		return ShopResult.bad("该用户已被占用!");
	}

	/**
	 * 用户注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public String userRegister(@Valid User user, BindingResult result, Model model, HttpServletRequest request,
			String checkcode) {
		// JSR303验证
		if (result.hasFieldErrors()) {
			// 封装错误信息
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());
			}
			model.addAttribute("errors", errors);
			return "jsp/userRegister";
		}

		// 校验验证码是否正确
		StringBuffer code = (StringBuffer) request.getSession().getAttribute("code");
		if (!checkcode.equalsIgnoreCase(code.toString())) {
			model.addAttribute("msg", "验证码错误!!!");
			return "jsp/userRegister";
		}
		// 销毁验证码session
		request.getSession().invalidate();
		// 调用服务
		userService.insertUser(user);
		model.addAttribute("msg", "注册成功！");
		return "redirect:/user/msg";
	}

	@RequestMapping("/msg")
	public String gotoIndex() {
		return "jsp/msg";
	}

	/**
	 * 用户激活
	 * 
	 * @return
	 */
	@RequestMapping(value = "/activtion", method = RequestMethod.GET)
	public String userActive(String code, Model model) {
		// 1.获取激活码
		User user = userService.getUserActiveCode(code);
		if (user == null) {
			model.addAttribute("msg", "激活失败，请重新注册!");
		}
		// 修改用户状态，清空code
		user.setCode(null);
		user.setState(1);
		userService.updateUser(user);
		model.addAttribute("msg", "激活成功,快去登录吧!");
		return "jsp/msg";
	}

	/**
	 * 用户登录 可以是:用户名+密码/邮箱+密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public String userLogin(User user, String loginType, String code, String isRememberUsername,
			HttpServletRequest request, HttpServletResponse response) {
		// 判断验证码输入是否正确
		StringBuffer c = (StringBuffer) request.getSession().getAttribute("code");
		if (!code.equalsIgnoreCase(c.toString())) {
			request.setAttribute("msg", "验证码输入错误");
			return "jsp/userLogin";
		}
		// 判断是否勾选记住用户名
		try {
			if (isRememberUsername.equals("true")) {
				// 把用户名存到cookie 下次就直接取cookie
				Cookie cookie = new Cookie("save_name", URLEncoder.encode(loginType, "utf-8"));
				cookie.setMaxAge(Integer.MAX_VALUE);
				cookie.setPath(request.getContextPath() + "/");
				response.addCookie(cookie);
			}
		} catch (Exception e) {
		}
		// 业务层实现两种方式登录
		user = userService.userLogin(user, loginType);
		if (user != null) {
			// 判断用户是否激活了
			if (user.getState() == 0) {
				request.setAttribute("msg", "该用户尚未激活!");
				return "jsp/userLogin";
			}
			// 登录成功，将用户信息存到session
			request.getSession().setAttribute("user", user);
			return "redirect:/index";
		}
		request.setAttribute("msg", "用户名或密码错误!");
		return "jsp/userLogin";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/quit")
	public String userQuit(HttpServletRequest request) {
		// 销毁session
		request.getSession().removeAttribute("user");
		return "redirect:/index.html";
	}

	/**
	 * 找回密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/toBackPassword")
	public String tobackPassword() {
		return "jsp/userBackPassword";
	}

	/**
	 * 找回密码
	 * 
	 * @return
	 */
	@RequestMapping("/backPassword")
	public String userBackPassword(User user, Model model) {
		// 获取用户的email和新密码
		// 调用service
		String info = userService.backPassword(user);
		if (info.equals("true")) {
			model.addAttribute("msg", "已经发送了一封新密码生效文件，快去邮箱激活登录吧!");
			return "msg";
		}
		model.addAttribute("msg", "这个邮箱没有注册过本网站!");
		return "jsp/userBackPassword";
	}

	/**
	 * 密码生效
	 * 
	 * @return
	 */
	@RequestMapping(value = "/passwordActivtion", method = RequestMethod.GET)
	public String passwordEffect(String uid, Model model) {
		// 处理生效 ---修改用户登录状态
		userService.updateUserState(uid);
		model.addAttribute("msg", "密码生效成功，快去登录吧!");
		return "jsp/msg";
	}
}
