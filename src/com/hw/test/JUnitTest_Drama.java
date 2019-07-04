package com.hw.test;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hw.entity.Drama;
import com.hw.service.DramaService;

/**
 * 话剧业务单元测试
 * 
 * @author Smile
 *
 */
class JUnitTest_Drama {

	// 创建全局ApplicationContext对象
	ApplicationContext applicationContext;

	@BeforeEach
	void setUp() throws Exception {
		// 为ApplicationContext对象赋值
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * 测试无条件查询所有剧目信息，管理员和用户共享
	 */
	@Test
	void test_GetAllDramas() {
		DramaService dramaService = (DramaService) applicationContext.getBean("dramaService");
		List<Drama> dramaList = dramaService.getAllDramas();
		for (Drama drama : dramaList) {
			System.out.println(drama);
		}
	}

	/**
	 * 测试添加一条剧目信息业务,针对管理员
	 */
	@Test
	void test_AddOneDrama() {
		DramaService dramaService = (DramaService) applicationContext.getBean("dramaService");
		Drama drama = new Drama("《白夜追凶》", "国家大剧院", 1230.00, "潘粤明一人饰两角，演技炸裂，敬请期待");
		int num = dramaService.addOneDrama(drama);
		System.out.println(num);
	}

	/**
	 * 测试删除一条剧目信息业务,针对管理员
	 */
	@Test
	void test_DeleteOneDrama() {
		DramaService dramaService = (DramaService) applicationContext.getBean("dramaService");
		int num = dramaService.deleteOneDrama(12);
		System.out.println(num);
	}

}
