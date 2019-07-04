package com.hw.entity.vo;

import java.util.List;

/**
 * 剧目的评分详细的echarts图的VO类
 * 
 * @author Smile
 *
 */
public class Echarts_DramaScoreVo {

	private List dramaName;
	private List dramaScore;

	public List getDramaName() {
		return dramaName;
	}

	public void setDramaName(List dramaName) {
		this.dramaName = dramaName;
	}

	public List getDramaScore() {
		return dramaScore;
	}

	public void setDramaScore(List dramaScore) {
		this.dramaScore = dramaScore;
	}

	public Echarts_DramaScoreVo() {
		super();
	}
	
	@Override
	public String toString() {
		return "Echarts_DramaScoreVo [dramaName=" + dramaName + ", dramaScore=" + dramaScore + "]";
	}

}