package com.showdwon.DTO;


/* 회원 정보 dto */
public class MemberDTO {
	
	private String userid;    ///아이디
	private String userpass;  /// 비밀번호
	private String nickname;  /// 별명(닉네임)
	private String email;  /// 이메일
	private int admin;  /// 0 이면 일반회원 1이면 관리자
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
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
