package com.dhr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhr.domain.User;

/**
 * 前台权限拦截器
 * 
 * @author Mr DU
 *
 */
public class UserPrivilegesInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	/**
	 * 进入目标方法之前
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 1.获取用户session
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.getSession().setAttribute("msg", "请先登录!!!");
			response.sendRedirect(request.getContextPath() + "/user/toLogin");
			return false;
		}
		return true;
	}

}
