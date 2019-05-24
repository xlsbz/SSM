package com.dhr.store.service;

import java.util.List;

import com.dhr.store.domain.User;

/**
 * @author Mr DU
 *
 */
public interface AdminUserService {

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> findAllUser();

}
