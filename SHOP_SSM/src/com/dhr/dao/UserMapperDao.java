package com.dhr.dao;
/**
 * 用户数据层
 * @author Mr DU
 *
 */

import java.util.List;

import com.dhr.domain.User;

public interface UserMapperDao {

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	User getCheckUsername(String username);

	/**
	 * 添加用户
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
	User findUserCode(String code);

	/**
	 * 修改用户状态
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 邮箱登录
	 * 
	 * @param user
	 * @return
	 */
	User loginUserEmail(User user);

	/**
	 * 用户名登录
	 * 
	 * @param user
	 * @return
	 */
	User loginUserName(User user);

	/**
	 * 检查邮箱是否存在
	 * 
	 * @param email
	 * @return
	 */
	User validationEmail(String email);

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 */
	void updateUserPassword(User user);

	/**
	 * 修改用户状态
	 * 
	 * @param uid
	 */
	void updateUserState(String uid);

	// =====================================管理员操作============================================================\\
	List<User> findAllUser();
}
