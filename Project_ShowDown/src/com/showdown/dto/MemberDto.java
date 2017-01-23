package com.showdown.dto;

import java.sql.Timestamp;

/* 회원 정보 dto */
public class MemberDto {
	
	
	private int usernum; 		/// 유저번호
	private String userid;    	///	아이디
	private String userpass;  	/// 비밀번호
	private String nickname;  	/// 별명(닉네임)
	private String email;  		/// 이메일
	private int managecode; 	/// 0 이면 일반회원 1이면 관리자
	private Timestamp indate;	/// 날짜
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getManagecode() {
		return managecode;
	}
	public void setManagecode(int managecode) {
		this.managecode = managecode;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
}
