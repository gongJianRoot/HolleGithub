package com.hw.service;

import java.util.List;
import java.util.Map;

import com.hw.entity.User;

/**
 * �û���service��ҵ��ӿ���
 * @author Smile
 *
 */
public interface UserService {

	/**
	 * �����û�������������û���½�Ľӿڷ���
	 * @param user
	 * @return User����
	 */
	public User loginByNameAndPwd(User user);
	
	/**
	 * �����û�������������û�ע��Ľӿڷ���
	 * @param map
	 * @return int
	 */
	public int registerByNameAndPwd(Map map);
	
	/**
	 * �����û���Ψһ�Խ����û�ע����֤�Ľӿڷ���
	 * @param userName
	 * @return int
	 */
	public User registerByName(String userName);
	
	/**
	 * ���������û���Ϣ
	 * @return Map<String,Object>
	 */
	public List<Map<String,Object>> selectAllUserInfo();
}
