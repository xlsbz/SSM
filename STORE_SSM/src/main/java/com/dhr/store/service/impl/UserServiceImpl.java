package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.User;
import com.dhr.store.domain.UserExample;
import com.dhr.store.domain.UserExample.Criteria;
import com.dhr.store.mapper.UserMapper;
import com.dhr.store.service.UserService;
import com.dhr.store.util.MD5Utils;
import com.dhr.store.util.MailUtils;
import com.dhr.store.util.UUIDUtils;

/**
 * @author Mr DU 用户业务
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * @param user
	 * @return
	 */
	@Override
	public User loginUser(User user) {
		// 注入mapper
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		criteria.andPasswordEqualTo(MD5Utils.md5(user.getPassword()));
		List<User> selectByExample = userMapper.selectByExample(example);
		if (selectByExample != null && selectByExample.size() > 0) {
			return selectByExample.get(0);
		}
		return null;
	}

	/**
	 * @param username
	 * @return
	 */
	@Override
	public boolean getUserByName(String username) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param user
	 */
	@Override
	public void insertUser(User user) {
		// 补全用户属性,发送激活码
		user.setUid(UUIDUtils.getId());
		// 加密
		user.setPassword(MD5Utils.md5(user.getPassword()));
		// 生成激活码
		String code = UUIDUtils.getCode();
		user.setCode(code);
		user.setState(0);
		// 发送邮件
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userMapper.insertSelective(user);
	}

	/**
	 * @param code
	 * @return
	 */
	@Override
	public boolean findUserActive(String code) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			// 激活成功,改变用户状态
			User user = list.get(0);
			user.setState(1);
			user.setCode(null);
			userMapper.updateByPrimaryKey(user);
			return true;
		} else {
			return false;
		}
	}

}
