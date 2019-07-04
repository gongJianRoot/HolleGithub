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
 * 评分业务单元测试
 * @author Smile
 *
 */
class JUnitTest_ScoreInfo {

	//创建全局ApplicationContext对象
	ApplicationContext applicationContext;
	@BeforeEach
	void setUp() throws Exception {
		//为ApplicationContext对象赋值
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	/**
	 * 测试根据剧目ID查询剧目的平均点评分数
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
	 * 测试用户进行影评，向scoreInfo中添加数据
	 */
	@Test
	void test_AddScoreInfo() {
		ScoreInfoService scoreInfoService = (ScoreInfoService) applicationContext.getBean("scoreInfoService");
		ScoreInfo scoreInfo = new ScoreInfo(5,7,6);
		int num = scoreInfoService.addScoreInfo(scoreInfo);
		System.out.println(num);
	}

}
