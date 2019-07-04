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
 * 话剧信息的控制器
 * 
 * @author Smile
 *
 */
@Controller
public class DramaController {

	// 依赖注入
	DramaService dramaService;

	public DramaService getDramaService() {
		return dramaService;
	}

	public void setDramaService(DramaService dramaService) {
		this.dramaService = dramaService;
	}

	// 依赖注入
	ScoreInfoService scoreInfoService;

	public ScoreInfoService getScoreInfoService() {
		return scoreInfoService;
	}

	public void setScoreInfoService(ScoreInfoService scoreInfoService) {
		this.scoreInfoService = scoreInfoService;
	}

	/**
	 * 无条件查询所有话剧信息的控制方法-管理员
	 * 
	 * @return String
	 */
	@RequestMapping("/getAllDramas")
	public String getAllDramas(HttpSession session) {
		List<Drama> dramaList = dramaService.getAllDramas();
		// 将查询到的所有剧目信息存放到session里面
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
	 * 无条件查询所有话剧信息的控制方法-普通用户
	 * 
	 * @return String
	 */
	@RequestMapping("/getAllDramas_User")
	public String getAllDramas_User(HttpSession session) {
		List<Drama> dramaList = dramaService.getAllDramas();
		// 将查询到的所有剧目信息存放到session里面
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
	 * 得到剧目评分详细echarts图的控制
	 * 
	 * @return Echarts_DramaScoreVo
	 */
	@RequestMapping("/getDramaScore_Echarts")
	public @ResponseBody Echarts_DramaScoreVo getDramaScore_Echarts() {
		// 创建剧目评分VO对象
		Echarts_DramaScoreVo dramaScoreVo = new Echarts_DramaScoreVo();
		// 得到剧目名称的集合--用于做图表的行
		List<Drama> dramaList = dramaService.getAllDramas();
		List<String> dramaNameList = new ArrayList<>();
		for (Drama drama : dramaList) {
			dramaNameList.add(drama.getDramaName());
		}
		dramaScoreVo.setDramaName(dramaNameList);
		// 得到剧目评分的集合--用于做图表的列
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
	 * 添加一条剧目信息的控制
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
	 * 删除一条剧目信息的控制
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
