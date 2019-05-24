package com.dhr.store.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhr.store.domain.User;
import com.dhr.store.service.UserService;
import com.dhr.store.util.CookieUtils;
import com.dhr.store.util.JsonUtils;
import com.dhr.store.util.StoreResult;

/**
 * @author Mr DU 处理用户请求
 */
@Controller
@RequestMapping("/user")
public class UserController {

	// 注入服务
	@Autowired
	private UserService userService;

	/**
	 * 去登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "jsp/login";
	}

	/**
	 * 去注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "jsp/register";
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String userLogin(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.注入服务
		// 判断验证码是否输入正确
		StringBuffer buffer = (StringBuffer) request.getSession().getAttribute("code");
		if (!request.getParameter("code").equalsIgnoreCase(buffer.toString())) {
			request.setAttribute("msg", "验证码输入错误!");
			return "jsp/login";
		}
		// 2.获取用户登录信息
		// 判断自动登录是否成功
		User u = (User) request.getSession().getAttribute("user");
		if (u != null) {
			return "redirect:/index";
		}
		// 3.调用service登录
		user = userService.loginUser(user);
		// 4.成功设置用户信息到session,否则返回提示
		if (user != null) {
			if (user.getState() == 0) {
				request.setAttribute("msg", "用户还未激活,请先激活!");
				return "jsp/login";
			}
			request.getSession().setAttribute("user", user);
		} else {
			request.setAttribute("msg", "用户名或密码错误!");
			return "jsp/login";
		}
		// 判断用户是否勾选自动登录
		try {
			if (request.getParameter("autoLogin").equals("1")) {
				// 把用户账号密码存到cookie
				CookieUtils.setCookie(request, response, "USER_INFO", JsonUtils.objectToJson(user), true);
			}
		} catch (Exception e) {
		}
		try {
			// 判断用户是否勾选记住用户名
			if (request.getParameter("saveName").equals("1")) {
				CookieUtils.setCookie(request, response, "USER_SAVENAME",
						URLEncoder.encode(user.getUsername(), "utf-8"));
			}
		} catch (Exception e) {
		}
		request.getSession().removeAttribute("code");
		return "redirect:/index";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/quit")
	public String quitUser(HttpServletRequest request) {
		// 清除session的user
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			request.getSession().removeAttribute("user");
		}
		return "redirect:/index";
	}

	/**
	 * 异步检查用户名
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/userCheck", method = RequestMethod.GET)
	@ResponseBody
	public StoreResult userCheck(String username) {
		// 获取用户名称调用服务查询有无此用户
		boolean flag = userService.getUserByName(username);
		if (flag) {
			return StoreResult.ok("该用户已被注册,请更换!!!");
		}
		return StoreResult.ok("恭喜,该用户可以使用!");
	}

	/**
	 * 日期转换
	 * 
	 * @param request
	 * @param binder
	 */
	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/**
	 * 用户注册 JSR303校验
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public String userRegister(@Valid User user, BindingResult result, HttpServletRequest request, Model model) {
		// 判断验证码是否输入正确
		StringBuffer buffer = (StringBuffer) request.getSession().getAttribute("code");
		if (!request.getParameter("code").equalsIgnoreCase(buffer.toString())) {
			request.setAttribute("msg", "验证码输入错误!");
			return "jsp/register";
		}
		// 1.获取用户数据
		// 2.校验
		if (result.hasFieldErrors()) {
			System.out.println(result.getFieldErrorCount());
			Map<String, Object> errors = new HashMap<>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			// 把错误设置到域
			model.addAttribute("errors", errors);
			return "jsp/register";
		}
		// 数据正确
		userService.insertUser(user);
		request.getSession().removeAttribute("code");
		model.addAttribute("msg", "用户注册成功,快去邮箱激活吧!");
		return "jsp/msg";
	}

	/**
	 * 用户激活
	 * 
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/userActive")
	public String activeUser(String code, Model model) {
		// 获取激活码
		// 查询用户对比激活码
		boolean flag = userService.findUserActive(code);
		if (flag) {
			model.addAttribute("msg", "用户激活成功!快去登录吧!");
			return "jsp/msg";
		}
		model.addAttribute("msg", "用户激活失败!");
		return "jsp/msg";
	}
}
