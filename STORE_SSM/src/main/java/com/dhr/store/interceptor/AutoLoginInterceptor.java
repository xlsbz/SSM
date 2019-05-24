package com.dhr.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhr.store.domain.User;
import com.dhr.store.service.UserService;
import com.dhr.store.util.CookieUtils;
import com.dhr.store.util.JsonUtils;

/**
 * @author Mr DU 拦截器实现自动登录
 */
public class AutoLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		XmlWebApplicationContext context = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(request.getServletContext());
		userService = context.getBean(UserService.class);
		// 进入目标方法之前检测cookie里面是否有自动登录的账号密码
		String autoLogin = CookieUtils.getCookieValue(request, "USER_INFO", true);
		if ((autoLogin != null) && (!autoLogin.equals(""))) {
			// 实现自动登陆
			User user = JsonUtils.jsonToPojo(autoLogin, User.class);
			if (user != null) {
				// 调用service服务登录程序
				user = userService.loginUser(user);
				// user正确,设置到session
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/index");
			}
		}
		return true;
	}

	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
