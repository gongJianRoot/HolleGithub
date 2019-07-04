package com.hw.entity;

import java.sql.Timestamp;

/**
 * �û�ʵ���࣬�û����л������ֵ�����
 * 
 * @author smile
 *
 */
public class User {

	private int userId; // �û�id
	private String userName; // �û���
	private String userPwd; // �û�����
	private int userType; // �û�����
	private Timestamp registerTime; // ע��ʱ��
	private int userLevel; // �û��ȼ�
	private double integrate; // �û�����

	// �ղι���
	public User() {
		super();
	}

	// �û���½����
	public User(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}

	// ȫ����������
	public User(int userId, String userName, String userPwd, int userType, Timestamp registerTime, int userLevel,
			double integrate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
		this.registerTime = registerTime;
		this.userLevel = userLevel;
		this.integrate = integrate;
	}

	// ��װ
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public double getIntegrate() {
		return integrate;
	}

	public void setIntegrate(double integrate) {
		this.integrate = integrate;
	}

	// ��дtoString
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userType=" + userType
				+ ", registerTime=" + registerTime + ", userLevel=" + userLevel + ", integrate=" + integrate + "]";
	}

}
