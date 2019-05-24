package com.dhr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.AdminMapperDao;
import com.dhr.domain.Admin;
import com.dhr.service.AdminService;

/**
 * 管理员业务
 * 
 * @author Mr DU
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapperDao adminMapper;

	/**
	 * 管理员登录
	 */
	@Override
	public Admin adminLogin(Admin admin) {
		// 注入mapper
		// 调用方法
		return adminMapper.adminLogin(admin);
	}

}
