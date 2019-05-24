package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.User;
import com.dhr.store.mapper.UserMapper;
import com.dhr.store.service.AdminUserService;

/**
 * @author Mr DU
 *
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

	// 注入服务
	@Autowired
	private UserMapper userMapper;

	/**
	 * @return
	 */
	@Override
	public List<User> findAllUser() {
		List<User> list = userMapper.selectByExample(null);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
