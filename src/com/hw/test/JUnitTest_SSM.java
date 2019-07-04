package com.hw.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试该项目的框架结构-MyBatis
 * 
 * @author Smile www.hwua.com
 *
 */
class JUnitTest_SSM {

	//创建全局ApplicationContext对象
	ApplicationContext applicationContext;
	
	@BeforeEach
	void setUp() throws Exception {
		//为ApplicationContext对象赋值
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * 测试SqlSessionFactory对象
	 */
	@Test
	void test_SqlSessionFactory() {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
		System.out.println(sqlSessionFactory);
	}

}
