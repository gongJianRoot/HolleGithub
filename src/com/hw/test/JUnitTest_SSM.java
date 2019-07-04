package com.hw.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ���Ը���Ŀ�Ŀ�ܽṹ-MyBatis
 * 
 * @author Smile www.hwua.com
 *
 */
class JUnitTest_SSM {

	//����ȫ��ApplicationContext����
	ApplicationContext applicationContext;
	
	@BeforeEach
	void setUp() throws Exception {
		//ΪApplicationContext����ֵ
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * ����SqlSessionFactory����
	 */
	@Test
	void test_SqlSessionFactory() {
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
		System.out.println(sqlSessionFactory);
	}

}
