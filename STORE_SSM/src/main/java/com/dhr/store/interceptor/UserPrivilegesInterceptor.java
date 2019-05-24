package com.dhr.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhr.store.domain.User;

/**
 * @author Mr DU 前台用户权限拦截
 */
public class UserPrivilegesInterceptor implements HandlerInterceptor {

	/**
	 * 拦截用户
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.getSession().removeAttribute("title");
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.getSession().setAttribute("title", "请先登录在下单!!!");
			response.sendRedirect(request.getContextPath() + "/user/toLogin");
			return false;
		}
		// 把user传给被拦截所需要的user
		request.setAttribute("user", user);
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
