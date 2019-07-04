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
 * 用户业务单元测试
 * @author Smile
 *
 */
class JUnitTest_User {

	//创建全局ApplicationContext对象
	ApplicationContext applicationContext;
	@BeforeEach
	void setUp() throws Exception {
		//为ApplicationContext对象赋值
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * 测试用户登陆
	 */
	@Test
	void test_UserLogin() {
		UserService userService = (UserService) applicationContext.getBean("userService"); 
		User user = new User("roots","root");
		User oneUser = userService.loginByNameAndPwd(user);
		if(oneUser.getUserType()==0) {
			System.out.println("登录成功，欢迎"+oneUser.getUserName()+"\t"+oneUser.getUserType());
		}else {
			System.err.println("登录失败！！！");
		}
	}
	
	/**
	 * 测试用户注册
	 */
	@Test
	void test_UserRegister() {
		UserService userService = (UserService) applicationContext.getBean("userService");
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", "张三");
		map.put("userPwd", "123456");
		map.put("registerTime", timeStamp);
		int num = userService.registerByNameAndPwd(map);
		System.out.println(num);
	}

	/**
	 * 测试用户注册验证用户名唯一性
	 */
	@Test
	void test_UserRegisterByName() {
		UserService userService = (UserService) applicationContext.getBean("userService");
		User user = userService.registerByName("root");
		System.out.println(user);
	}
}
