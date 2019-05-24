package com.dhr.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.UserMapperDao;
import com.dhr.domain.User;
import com.dhr.service.UserService;
import com.dhr.util.EmailUtil;
import com.dhr.util.UUIDUtils;

@Service
public class UserServiceImpl implements UserService {

	// 注入服务
	@Autowired
	private UserMapperDao userMapper;

	/**
	 * 根据用户名查询用户
	 */
	@Override
	public User getUserName(String username) {
		// 注入服务
		// 调用方法
		User user = userMapper.getCheckUsername(username);
		return user;
	}

	@Override
	/**
	 * 注册用户
	 */
	public void insertUser(User user) {
		// 生成激活码
		String code = UUIDUtils.timeNumber();
		// 给用户发送激活邮件
		String content = "<a href=\"http://172.19.7.43:8081/SHOP_SSM/user/activtion?code=" + code
				+ "\">这是来自官方的激活邮件，点击这条链接完成激活!</a>";
		EmailUtil.sendEmail(user.getEmail(), content);
		// 补全user状态
		user.setState(0);
		user.setCode(code);
		userMapper.insertUser(user);
	}

	/**
	 * 根据激活码查询用户
	 */
	@Override
	public User getUserActiveCode(String code) {
		User user = userMapper.findUserCode(code);
		if (user != null) {
			return user;
		}
		return null;
	}

	/**
	 * 修改用户状态
	 */
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	/**
	 * 用户登录 邮箱 用户名
	 */
	@Override
	public User userLogin(User user, String loginType) {
		// 1.如果username里面包含@那么是邮箱登录
		User u = null;
		// loginType是用户名或者密码
		if (loginType.endsWith(".com")) {
			user.setEmail(loginType);
			u = userMapper.loginUserEmail(user);
		} else {
			// 2.没有@则是用户名登录
			user.setUsername(loginType);
			u = userMapper.loginUserName(user);
		}
		if (u != null) {
			return u;
		}
		return null;
	}

	/**
	 * 找回密码
	 */
	@Override
	public String backPassword(User user) {
		// 对用户的邮件进行验证，存在这个邮箱就发送密码生效邮件到邮箱 邮箱不存在直接返回提示
		String newPassword = user.getPassword();
		user = userMapper.validationEmail(user.getEmail());
		if (user != null) {
			// 修改用户密码和状态
			// 修改用户状态不可登录
			user.setState(0);
			user.setPassword(newPassword);
			userMapper.updateUserPassword(user);
			if (StringUtils.isNotBlank(user.getEmail())) {
				// 不为空发送密码生效邮件
				String content = "<a href=\"http://172.19.7.43:8081/SHOP_SSM/user/passwordActivtion?uid="
						+ user.getUid() + "\">这是来自官方的密码生效邮件，点击这条链接完成生效!</a>";
				EmailUtil.sendEmail(user.getEmail(), content);
			}
		} else {
			// 返回错误信息
			return "false";
		}

		return "true";
	}

	@Override
	public void updateUserState(String uid) {
		userMapper.updateUserState(uid);
	}

}
