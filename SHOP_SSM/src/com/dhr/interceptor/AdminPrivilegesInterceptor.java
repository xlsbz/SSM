package com.dhr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhr.domain.Admin;

/**
 * 后台权限拦截器
 * 
 * @author Mr DU
 *
 */
public class AdminPrivilegesInterceptor implements HandlerInterceptor {

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
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null) {
			return true;
		}
		request.getSession().setAttribute("msg", "请先登录!!!");
		response.sendRedirect(request.getContextPath() + "/admin/login");
		return false;
	}

}
