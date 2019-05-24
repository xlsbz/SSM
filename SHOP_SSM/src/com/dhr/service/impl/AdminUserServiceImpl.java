package com.dhr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.UserMapperDao;
import com.dhr.domain.User;
import com.dhr.service.AdminUserService;

/**
 * 管理用户业务
 * 
 * @author Mr DU
 *
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

	// 注入mapper
	@Autowired
	private UserMapperDao userMapper;

	@Override
	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}

}
