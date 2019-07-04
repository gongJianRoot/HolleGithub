package com.hw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hw.entity.Drama;
import com.hw.entity.vo.Echarts_DramaScoreVo;
import com.hw.entity.vo.ScoreInfoVo;
import com.hw.service.DramaService;
import com.hw.service.ScoreInfoService;

/**
 * ������Ϣ�Ŀ�����
 * 
 * @author Smile
 *
 */
@Controller
public class DramaController {

	// ����ע��
	DramaService dramaService;

	public DramaService getDramaService() {
		return dramaService;
	}

	public void setDramaService(DramaService dramaService) {
		this.dramaService = dramaService;
	}

	// ����ע��
	ScoreInfoService scoreInfoService;

	public ScoreInfoService getScoreInfoService() {
		return scoreInfoService;
	}

	public void setScoreInfoService(ScoreInfoService scoreInfoService) {
		this.scoreInfoService = scoreInfoService;
	}

	/**
	 * ��������ѯ���л�����Ϣ�Ŀ��Ʒ���-����Ա
	 * 
	 * @return String
	 */
	@RequestMapping("/getAllDramas")
	public String getAllDramas(HttpSession session) {
		List<Drama> dramaList = dramaService.getAllDramas();
		// ����ѯ�������о�Ŀ��Ϣ��ŵ�session����
		session.setAttribute("dramaList", dramaList);
		List<Integer> dramaIdList = new ArrayList<>();
		for (Drama drama : dramaList) {
			int id = drama.getDramaId();
			dramaIdList.add(id);
		}
		List<ScoreInfoVo> scoreInfoVoList = scoreInfoService.getScoreAvg(dramaIdList);
		session.setAttribute("scoreInfoVoList", scoreInfoVoList);
		return "redirect:showDramaInfo.jsp";
	}

	/**
	 * ��������ѯ���л�����Ϣ�Ŀ��Ʒ���-��ͨ�û�
	 * 
	 * @return String
	 */
	@RequestMapping("/getAllDramas_User")
	public String getAllDramas_User(HttpSession session) {
		List<Drama> dramaList = dramaService.getAllDramas();
		// ����ѯ�������о�Ŀ��Ϣ��ŵ�session����
		session.setAttribute("dramaList", dramaList);
		List<Integer> dramaIdList = new ArrayList<>();
		for (Drama drama : dramaList) {
			int id = drama.getDramaId();
			dramaIdList.add(id);
		}
		List<ScoreInfoVo> scoreInfoVoList = scoreInfoService.getScoreAvg(dramaIdList);
		session.setAttribute("scoreInfoVoList", scoreInfoVoList);
		return "redirect:showDramaInfo_User.jsp";
	}

	/**
	 * �õ���Ŀ������ϸechartsͼ�Ŀ���
	 * 
	 * @return Echarts_DramaScoreVo
	 */
	@RequestMapping("/getDramaScore_Echarts")
	public @ResponseBody Echarts_DramaScoreVo getDramaScore_Echarts() {
		// ������Ŀ����VO����
		Echarts_DramaScoreVo dramaScoreVo = new Echarts_DramaScoreVo();
		// �õ���Ŀ���Ƶļ���--������ͼ�����
		List<Drama> dramaList = dramaService.getAllDramas();
		List<String> dramaNameList = new ArrayList<>();
		for (Drama drama : dramaList) {
			dramaNameList.add(drama.getDramaName());
		}
		dramaScoreVo.setDramaName(dramaNameList);
		// �õ���Ŀ���ֵļ���--������ͼ�����
		List<Integer> dramaIdList = new ArrayList<>();
		for (Drama drama : dramaList) {
			int id = drama.getDramaId();
			dramaIdList.add(id);
		}
		List<ScoreInfoVo> scoreInfoVoList = scoreInfoService.getScoreAvg(dramaIdList);
		List<Double> dramaScoreList = new ArrayList<>();
		for (ScoreInfoVo scoreInfoVo : scoreInfoVoList) {
			dramaScoreList.add(scoreInfoVo.getScoreAvg());
		}
		dramaScoreVo.setDramaScore(dramaScoreList);
		return dramaScoreVo;
	}

	/**
	 * ���һ����Ŀ��Ϣ�Ŀ���
	 * 
	 * @param drama
	 * @return String
	 */
	@RequestMapping("/addOneDrama")
	public String addOneDrama(Drama drama) {
		Drama oneDrama = new Drama(drama.getDramaName(), drama.getTheaterName(), drama.getTicketPrice(),
				drama.getDramaIntro());
		int num = dramaService.addOneDrama(oneDrama);
		if (num == 1) {
			return "redirect:getAllDramas.action";
		}
		return null;
	}
	
	/**
	 * ɾ��һ����Ŀ��Ϣ�Ŀ���
	 * 
	 * @param dramaId
	 * @return String
	 */
	@RequestMapping("/deleteOneDrama")
	public String deleteOneDrama(int dramaId) {
		int num = dramaService.deleteOneDrama(dramaId);
		if (num == 1) {
			return "redirect:getAllDramas.action";
		}
		return null;
	}
}
