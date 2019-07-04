package com.hw.service.impl;

import java.util.List;
import java.util.Map;

import com.hw.entity.User;
import com.hw.mapper.UserMapper;
import com.hw.service.UserService;

/**
 * 用户service层的业务的实现类
 * 
 * @author Smile
 *
 */
public class UserServiceImpl implements UserService {

	// 依赖注入UserMapper的Bean
	UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 根据用户名和密码进行用户登陆业务的实现
	 */
	@Override
	public User loginByNameAndPwd(User user) {
		return userMapper.loginByNameAndPwd(user);
	}

	/**
	 * 根据用户名和密码进行用户注册业务的实现
	 */
	@Override
	public int registerByNameAndPwd(Map map) {
		int num = userMapper.registerByNameAndPwd(map);
		return num;
	}

	/**
	 * 根据用户名唯一性进行用户注册验证业务的实现
	 */
	@Override
	public User registerByName(String userName) {
		return userMapper.registerByName(userName);
	}

	/**
	 * 查新所有用户信息的方法实现
	 */
	@Override
	public List<Map<String, Object>> selectAllUserInfo() {
		List<Map<String, Object>> userlist = userMapper.selectAllUserInfo();
		for (int i = 0; i < userlist.size(); i++) {
			System.out.println(userlist.get(i).get("userName"));
			if("张三".equals(userlist.get(i).get("userName"))) {
				System.out.println("我是张三啊！");
			}
		}
		return userlist;
	}

}
