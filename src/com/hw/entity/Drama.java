package com.hw.entity;

/**
 * 话剧实体类，包括话剧的所有基本信息，供用户评分
 * 
 * @author Smile
 *
 */
public class Drama {

	private int dramaId; // 话剧id
	private String dramaName; // 话剧名称
	private String theaterName; // 剧场名称
	private Double ticketPrice; // 票价
	private String dramaIntro; // 话剧介绍
	
	//用于添加剧目信息的构造
	public Drama(String dramaName, String theaterName, Double ticketPrice, String dramaIntro) {
		super();
		this.dramaName = dramaName;
		this.theaterName = theaterName;
		this.ticketPrice = ticketPrice;
		this.dramaIntro = dramaIntro;
	}

	// 空参构造
	public Drama() {
		super();
	}

	// 全部参数构造
	public Drama(int dramaId, String dramaName, String theaterName, Double ticketPrice, String dramaIntro) {
		super();
		this.dramaId = dramaId;
		this.dramaName = dramaName;
		this.theaterName = theaterName;
		this.ticketPrice = ticketPrice;
		this.dramaIntro = dramaIntro;
	}

	// 封装
	public int getDramaId() {
		return dramaId;
	}

	public void setDramaId(int dramaId) {
		this.dramaId = dramaId;
	}

	public String getDramaName() {
		return dramaName;
	}

	public void setDramaName(String dramaName) {
		this.dramaName = dramaName;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getDramaIntro() {
		return dramaIntro;
	}

	public void setDramaIntro(String dramaIntro) {
		this.dramaIntro = dramaIntro;
	}

	// 重写toString
	@Override
	public String toString() {
		return "Drama [dramaId=" + dramaId + ", dramaName=" + dramaName + ", theaterName=" + theaterName
				+ ", ticketPrice=" + ticketPrice + ", dramaIntro=" + dramaIntro + "]";
	}

}
