package com.hw.entity.vo;

/**
 * ����ƽ���ֵ�ֵ�������ڵó������ƽ����
 * 
 * @author Smile
 *
 */
public class ScoreInfoVo {

	private int dramaId;
	private double scoreAvg;

	public ScoreInfoVo() {
		super();
	}

	public ScoreInfoVo(int dramaId, double scoreAvg) {
		super();
		this.dramaId = dramaId;
		this.scoreAvg = scoreAvg;
	}

	public int getDramaId() {
		return dramaId;
	}

	public void setDramaId(int dramaId) {
		this.dramaId = dramaId;
	}

	public double getScoreAvg() {
		return scoreAvg;
	}

	public void setScoreAvg(double scoreAvg) {
		this.scoreAvg = scoreAvg;
	}

	@Override
	public String toString() {
		return "ScoreInfoVo [dramaId=" + dramaId + ", scoreAvg=" + scoreAvg + "]";
	}

}
