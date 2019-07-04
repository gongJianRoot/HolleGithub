package com.hw.service.impl;

import java.util.List;

import com.hw.entity.ScoreInfo;
import com.hw.entity.vo.ScoreInfoVo;
import com.hw.mapper.ScoreInfoMapper;
import com.hw.service.ScoreInfoService;

/**
 * ����service���ҵ���ʵ����
 * 
 * @author Smile
 *
 */
public class ScoreInfoServiceImpl implements ScoreInfoService {

	// ����ע��
	ScoreInfoMapper scoreInfoMapper;

	public ScoreInfoMapper getScoreInfoMapper() {
		return scoreInfoMapper;
	}

	public void setScoreInfoMapper(ScoreInfoMapper scoreInfoMapper) {
		this.scoreInfoMapper = scoreInfoMapper;
	}

	/**
	 * ���ݾ�ĿID��ѯ��Ŀ��ƽ�����������Ľӿ�ҵ���ʵ��
	 */
	@Override
	public List<ScoreInfoVo> getScoreAvg(List dramaList) {
		return scoreInfoMapper.getScoreAvg(dramaList);
	}

	/**
	 * �û�����Ӱ������scoreInfo��������ݵĽӿ�ҵ���ʵ��
	 */
	@Override
	public int addScoreInfo(ScoreInfo scoreInfo) {
		return scoreInfoMapper.addScoreInfo(scoreInfo);
	}

}
