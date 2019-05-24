package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.Admin;
import com.dhr.store.domain.AdminExample;
import com.dhr.store.domain.AdminExample.Criteria;
import com.dhr.store.mapper.AdminMapper;
import com.dhr.store.service.AdminService;

/**
 * @author Mr DU 管理员
 */
@Service
public class AdminServiceImpl implements AdminService {

	// 注入mapper
	@Autowired
	private AdminMapper adminMapper;

	/**
	 * 
	 * @param admin
	 * @return
	 */
	@Override
	public boolean loginAdmin(Admin admin) {
		AdminExample adminExample = new AdminExample();
		Criteria criteria = adminExample.createCriteria();
		criteria.andNameEqualTo(admin.getName());

		List<Admin> list = adminMapper.selectByExample(adminExample);
		if (list != null && list.size() > 0) {
			Admin admin2 = list.get(0);
			if (!(admin2.getPassword().equals(admin.getPassword()))) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

}
