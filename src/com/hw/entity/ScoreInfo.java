package com.hw.entity;

/**
 * 分数实体类，代表着话剧的分均分
 * 
 * @author Microsoft
 *
 */
public class ScoreInfo {

	private int scoreId;
	private int userId;
	private int dramaId;
	private double score;

	public ScoreInfo(int userId, int dramaId, double score) {
		super();
		this.userId = userId;
		this.dramaId = dramaId;
		this.score = score;
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDramaId() {
		return dramaId;
	}

	public void setDramaId(int dramaId) {
		this.dramaId = dramaId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ScoreInfo(int scoreId, int userId, int dramaId, double score, double scoreAvg) {
		super();
		this.scoreId = scoreId;
		this.userId = userId;
		this.dramaId = dramaId;
		this.score = score;
	}

	public ScoreInfo() {
		super();
	}

	@Override
	public String toString() {
		return "ScoreInfo [scoreId=" + scoreId + ", userId=" + userId + ", dramaId=" + dramaId + ", score=" + score
				+ "]";
	}
	
}
