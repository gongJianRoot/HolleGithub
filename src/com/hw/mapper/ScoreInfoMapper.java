package com.hw.mapper;

import java.util.List;

import com.hw.entity.ScoreInfo;
import com.hw.entity.vo.ScoreInfoVo;

/**
 * ����mapper��ӿڣ���Ҫ�Ƕ����ݿ���������Ϣ�Ĳ�����
 * 
 * @author Smile
 *
 */
public interface ScoreInfoMapper {

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
