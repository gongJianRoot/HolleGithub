package com.hw.service.impl;

import java.util.List;
import java.util.Map;

import com.hw.entity.User;
import com.hw.mapper.UserMapper;
import com.hw.service.UserService;

/**
 * �û�service���ҵ���ʵ����
 * 
 * @author Smile
 *
 */
public class UserServiceImpl implements UserService {

	// ����ע��UserMapper��Bean
	UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * �����û�������������û���½ҵ���ʵ��
	 */
	@Override
	public User loginByNameAndPwd(User user) {
		return userMapper.loginByNameAndPwd(user);
	}

	/**
	 * �����û�������������û�ע��ҵ���ʵ��
	 */
	@Override
	public int registerByNameAndPwd(Map map) {
		int num = userMapper.registerByNameAndPwd(map);
		return num;
	}

	/**
	 * �����û���Ψһ�Խ����û�ע����֤ҵ���ʵ��
	 */
	@Override
	public User registerByName(String userName) {
		return userMapper.registerByName(userName);
	}

	/**
	 * ���������û���Ϣ�ķ���ʵ��
	 */
	@Override
	public List<Map<String, Object>> selectAllUserInfo() {
		List<Map<String, Object>> userlist = userMapper.selectAllUserInfo();
		for (int i = 0; i < userlist.size(); i++) {
			System.out.println(userlist.get(i).get("userName"));
			if("����".equals(userlist.get(i).get("userName"))) {
				System.out.println("������������");
			}
		}
		return userlist;
	}

}
