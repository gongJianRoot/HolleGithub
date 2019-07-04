package com.hw.test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hw.entity.User;
import com.hw.service.UserService;

/**
 * �û�ҵ��Ԫ����
 * @author Smile
 *
 */
class JUnitTest_User {

	//����ȫ��ApplicationContext����
	ApplicationContext applicationContext;
	@BeforeEach
	void setUp() throws Exception {
		//ΪApplicationContext����ֵ
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * �����û���½
	 */
	@Test
	void test_UserLogin() {
		UserService userService = (UserService) applicationContext.getBean("userService"); 
		User user = new User("roots","root");
		User oneUser = userService.loginByNameAndPwd(user);
		if(oneUser.getUserType()==0) {
			System.out.println("��¼�ɹ�����ӭ"+oneUser.getUserName()+"\t"+oneUser.getUserType());
		}else {
			System.err.println("��¼ʧ�ܣ�����");
		}
	}
	
	/**
	 * �����û�ע��
	 */
	@Test
	void test_UserRegister() {
		UserService userService = (UserService) applicationContext.getBean("userService");
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", "����");
		map.put("userPwd", "123456");
		map.put("registerTime", timeStamp);
		int num = userService.registerByNameAndPwd(map);
		System.out.println(num);
	}

	/**
	 * �����û�ע����֤�û���Ψһ��
	 */
	@Test
	void test_UserRegisterByName() {
		UserService userService = (UserService) applicationContext.getBean("userService");
		User user = userService.registerByName("root");
		System.out.println(user);
	}
}
