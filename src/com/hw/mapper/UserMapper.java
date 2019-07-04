package com.hw.mapper;

import java.util.List;
import java.util.Map;

import com.hw.entity.User;

/**
 * 用户mapper层接口，主要定义关于用户的登陆与注册等方法
 * 
 * @author Smile
 */
public interface UserMapper {

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
	 * @return User
	 */
	public User registerByName(String userName);
	
	/**
	 * 查新所有用户信息
	 * @return Map<String,Object>
	 */
	public List<Map<String,Object>> selectAllUserInfo();
}
