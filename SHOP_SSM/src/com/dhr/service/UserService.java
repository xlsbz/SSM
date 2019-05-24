package com.dhr.service;

import com.dhr.domain.User;

/**
 * 用户业务
 * 
 * @author Mr DU
 *
 */
public interface UserService {

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	User getUserName(String username);

	/**
	 * 注册用户
	 * 
	 * @param user
	 */
	void insertUser(User user);

	/**
	 * 根据激活码查询用户
	 * 
	 * @param code
	 * @return
	 */
	User getUserActiveCode(String code);

	/**
	 * 修改用户
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param loginType
	 * @return
	 */
	User userLogin(User user, String loginType);

	/**
	 * 找回密码
	 * 
	 * @param user
	 */
	String backPassword(User user);

	/**
	 * 修改状态
	 * 
	 * @param uid
	 * @param i
	 */
	void updateUserState(String uid);
}
