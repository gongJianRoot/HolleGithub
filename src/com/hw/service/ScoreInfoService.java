package com.hw.service;

import java.util.List;

import com.hw.entity.ScoreInfo;
import com.hw.entity.vo.ScoreInfoVo;

/**
 * 评分的service层业务接口类
 * @author Smile
 *
 */
public interface ScoreInfoService {

	/**
	 * 根据剧目ID查询剧目的平均点评分数的接口
	 * 
	 * @param dramaList
	 * @return List<ScoreInfo>
	 */
	public List<ScoreInfoVo> getScoreAvg(List dramaList);
	
	/**
	 * 用户进行影评，向scoreInfo中添加数据的接口
	 * @param scoreInfo
	 * @return int
	 */
	public int addScoreInfo(ScoreInfo scoreInfo);
}
