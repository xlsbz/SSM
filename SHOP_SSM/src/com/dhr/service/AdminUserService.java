package com.dhr.service;

import java.util.List;

import com.dhr.domain.User;

public interface AdminUserService {

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> findAllUser();

}
