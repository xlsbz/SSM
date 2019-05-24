package com.dhr.store.service;

import com.dhr.store.domain.User;

/**
 * @author Mr DU
 *
 */
public interface UserService {

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	User loginUser(User user);

	/**
	 * 用户检测
	 * 
	 * @param username
	 * @return
	 */
	boolean getUserByName(String username);

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	void insertUser(User user);

	/**
	 * 激活用户
	 * 
	 * @param code
	 * @return
	 */
	boolean findUserActive(String code);

}
