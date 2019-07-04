package com.hw.mapper;

import java.util.List;

import com.hw.entity.ScoreInfo;
import com.hw.entity.vo.ScoreInfoVo;

/**
 * 评分mapper层接口，主要是对数据库中评分信息的操作等
 * 
 * @author Smile
 *
 */
public interface ScoreInfoMapper {

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
