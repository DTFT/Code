package com.test;
//����һ��UserBean  <--->users�� ӳ��
//����һ��ʵ��  <---->users�����һ����¼��Ӧ
//����
public class UserBean {
	
	private int userId;
	private String userName;
	private String passWd;
	private String email;
	private int grade;

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
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
