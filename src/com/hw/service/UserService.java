package com.hw.service;

import java.util.List;
import java.util.Map;

import com.hw.entity.User;

/**
 * 用户的service层业务接口类
 * @author Smile
 *
 */
public interface UserService {

	/**
	 * 根据用户名和密码进行用户登陆的接口方法
	 * @param user
	 * @return User对象
	 */
	public User loginByNameAndPwd(User user);
	
	/**
	 * 根据用户名和密码进行用户注册的接口方法
	 * @param map
	 * @return int
	 */
	public int registerByNameAndPwd(Map map);
	
	/**
	 * 根据用户名唯一性进行用户注册验证的接口方法
	 * @param userName
	 * @return int
	 */
	public User registerByName(String userName);
	
	/**
	 * 查新所有用户信息
	 * @return Map<String,Object>
	 */
	public List<Map<String,Object>> selectAllUserInfo();
}
