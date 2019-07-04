package com.hw.service.impl;

import java.util.List;

import com.hw.entity.ScoreInfo;
import com.hw.entity.vo.ScoreInfoVo;
import com.hw.mapper.ScoreInfoMapper;
import com.hw.service.ScoreInfoService;

/**
 * 评分service层的业务的实现类
 * 
 * @author Smile
 *
 */
public class ScoreInfoServiceImpl implements ScoreInfoService {

	// 依赖注入
	ScoreInfoMapper scoreInfoMapper;

	public ScoreInfoMapper getScoreInfoMapper() {
		return scoreInfoMapper;
	}

	public void setScoreInfoMapper(ScoreInfoMapper scoreInfoMapper) {
		this.scoreInfoMapper = scoreInfoMapper;
	}

	/**
	 * 根据剧目ID查询剧目的平均点评分数的接口业务的实现
	 */
	@Override
	public List<ScoreInfoVo> getScoreAvg(List dramaList) {
		return scoreInfoMapper.getScoreAvg(dramaList);
	}

	/**
	 * 用户进行影评，向scoreInfo中添加数据的接口业务的实现
	 */
	@Override
	public int addScoreInfo(ScoreInfo scoreInfo) {
		return scoreInfoMapper.addScoreInfo(scoreInfo);
	}

}
