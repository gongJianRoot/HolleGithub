package com.hw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hw.entity.ScoreInfo;
import com.hw.service.ScoreInfoService;

/**
 * ������Ϣ�Ŀ�����
 * 
 * @author Smile
 *
 */
@Controller
public class ScoreInfoController {

	// ����ע��
	ScoreInfoService scoreInfoService;

	public ScoreInfoService getScoreInfoService() {
		return scoreInfoService;
	}

	public void setScoreInfoService(ScoreInfoService scoreInfoService) {
		this.scoreInfoService = scoreInfoService;
	}
	
	@RequestMapping("/addScoreInfo")
	public String addScoreInfo(ScoreInfo scoreInfo) {
		ScoreInfo scoreInfo2 = new ScoreInfo(scoreInfo.getUserId(),scoreInfo.getDramaId(),scoreInfo.getScore());
		int num = scoreInfoService.addScoreInfo(scoreInfo2);
		if(num==1) {
			return "redirect:getAllDramas_User.action";
		}
		return null;
		
	}
}
