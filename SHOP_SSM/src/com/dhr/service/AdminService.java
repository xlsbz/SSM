package com.dhr.service;

import com.dhr.domain.Admin;

public interface AdminService {

	/**
	 * 管理员登录
	 * 
	 * @param admin
	 * @return
	 */
	Admin adminLogin(Admin admin);

}
