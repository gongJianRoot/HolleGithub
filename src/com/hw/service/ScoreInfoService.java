package com.hw.service;

import java.util.List;

import com.hw.entity.ScoreInfo;
import com.hw.entity.vo.ScoreInfoVo;

/**
 * ���ֵ�service��ҵ��ӿ���
 * @author Smile
 *
 */
public interface ScoreInfoService {

	/**
	 * ���ݾ�ĿID��ѯ��Ŀ��ƽ�����������Ľӿ�
	 * 
	 * @param dramaList
	 * @return List<ScoreInfo>
	 */
	public List<ScoreInfoVo> getScoreAvg(List dramaList);
	
	/**
	 * �û�����Ӱ������scoreInfo��������ݵĽӿ�
	 * @param scoreInfo
	 * @return int
	 */
	public int addScoreInfo(ScoreInfo scoreInfo);
}
