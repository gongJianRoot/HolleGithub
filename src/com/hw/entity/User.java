package com.hw.entity;

import java.sql.Timestamp;

/**
 * 用户实体类，用户进行话剧评分的评价
 * 
 * @author smile
 *
 */
public class User {

	private int userId; // 用户id
	private String userName; // 用户名
	private String userPwd; // 用户密码
	private int userType; // 用户类型
	private Timestamp registerTime; // 注册时间
	private int userLevel; // 用户等级
	private double integrate; // 用户积分

	// 空参构造
	public User() {
		super();
	}

	// 用户登陆构造
	public User(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}

	// 全部参数构造
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

	// 封装
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

	// 重写toString
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userType=" + userType
				+ ", registerTime=" + registerTime + ", userLevel=" + userLevel + ", integrate=" + integrate + "]";
	}

}
