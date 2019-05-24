package com.dhr.store.service;

import com.dhr.store.domain.Admin;

/**
 * @author Mr DU
 *
 */
public interface AdminService {

	/**
	 * 管理员登录
	 * 
	 * @param admin
	 * @return
	 */
	boolean loginAdmin(Admin admin);

}
