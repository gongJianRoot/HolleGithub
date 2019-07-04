package com.hw.entity;

/**
 * ����ʵ���࣬������������л�����Ϣ�����û�����
 * 
 * @author Smile
 *
 */
public class Drama {

	private int dramaId; // ����id
	private String dramaName; // ��������
	private String theaterName; // �糡����
	private Double ticketPrice; // Ʊ��
	private String dramaIntro; // �������
	
	//������Ӿ�Ŀ��Ϣ�Ĺ���
	public Drama(String dramaName, String theaterName, Double ticketPrice, String dramaIntro) {
		super();
		this.dramaName = dramaName;
		this.theaterName = theaterName;
		this.ticketPrice = ticketPrice;
		this.dramaIntro = dramaIntro;
	}

	// �ղι���
	public Drama() {
		super();
	}

	// ȫ����������
	public Drama(int dramaId, String dramaName, String theaterName, Double ticketPrice, String dramaIntro) {
		super();
		this.dramaId = dramaId;
		this.dramaName = dramaName;
		this.theaterName = theaterName;
		this.ticketPrice = ticketPrice;
		this.dramaIntro = dramaIntro;
	}

	// ��װ
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

	// ��дtoString
	@Override
	public String toString() {
		return "Drama [dramaId=" + dramaId + ", dramaName=" + dramaName + ", theaterName=" + theaterName
				+ ", ticketPrice=" + ticketPrice + ", dramaIntro=" + dramaIntro + "]";
	}

}
