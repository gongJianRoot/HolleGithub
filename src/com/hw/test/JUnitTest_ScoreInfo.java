package com.hw.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hw.entity.Drama;
import com.hw.entity.ScoreInfo;
import com.hw.entity.vo.ScoreInfoVo;
import com.hw.service.DramaService;
import com.hw.service.ScoreInfoService;

/**
 * ����ҵ��Ԫ����
 * @author Smile
 *
 */
class JUnitTest_ScoreInfo {

	//����ȫ��ApplicationContext����
	ApplicationContext applicationContext;
	@BeforeEach
	void setUp() throws Exception {
		//ΪApplicationContext����ֵ
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * ���Ը��ݾ�ĿID��ѯ��Ŀ��ƽ����������
	 */
	@Test
	void test_GetScoreAvg() {
		ScoreInfoService scoreInfoService = (ScoreInfoService) applicationContext.getBean("scoreInfoService");
		DramaService dramaService = (DramaService) applicationContext.getBean("dramaService");
		List<Drama> dramaList = dramaService.getAllDramas();
		List<Integer> dramaIdList =new ArrayList<>();
		for (Drama drama : dramaList) {
			int id = drama.getDramaId();
			dramaIdList.add(id);
		}
		List<ScoreInfoVo> scoreInfoVoList = scoreInfoService.getScoreAvg(dramaIdList);
		for (ScoreInfoVo scoreInfoVo : scoreInfoVoList) {
			System.out.println(scoreInfoVo);
		}
	}
	
	/**
	 * �����û�����Ӱ������scoreInfo���������
	 */
	@Test
	void test_AddScoreInfo() {
		ScoreInfoService scoreInfoService = (ScoreInfoService) applicationContext.getBean("scoreInfoService");
		ScoreInfo scoreInfo = new ScoreInfo(5,7,6);
		int num = scoreInfoService.addScoreInfo(scoreInfo);
		System.out.println(num);
	}

}
