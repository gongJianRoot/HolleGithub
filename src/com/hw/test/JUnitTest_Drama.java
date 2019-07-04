package com.hw.test;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hw.entity.Drama;
import com.hw.service.DramaService;

/**
 * ����ҵ��Ԫ����
 * 
 * @author Smile
 *
 */
class JUnitTest_Drama {

	// ����ȫ��ApplicationContext����
	ApplicationContext applicationContext;

	@BeforeEach
	void setUp() throws Exception {
		// ΪApplicationContext����ֵ
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * ������������ѯ���о�Ŀ��Ϣ������Ա���û�����
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
	 * �������һ����Ŀ��Ϣҵ��,��Թ���Ա
	 */
	@Test
	void test_AddOneDrama() {
		DramaService dramaService = (DramaService) applicationContext.getBean("dramaService");
		Drama drama = new Drama("����ҹ׷�ס�", "���Ҵ��Ժ", 1230.00, "������һ�������ǣ��ݼ�ը�ѣ������ڴ�");
		int num = dramaService.addOneDrama(drama);
		System.out.println(num);
	}

	/**
	 * ����ɾ��һ����Ŀ��Ϣҵ��,��Թ���Ա
	 */
	@Test
	void test_DeleteOneDrama() {
		DramaService dramaService = (DramaService) applicationContext.getBean("dramaService");
		int num = dramaService.deleteOneDrama(12);
		System.out.println(num);
	}

}
