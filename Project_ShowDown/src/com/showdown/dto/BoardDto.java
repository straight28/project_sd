package com.showdown.dto;

import java.sql.Timestamp;

public class BoardDto {
	private int boardnum;
	private String boardtitle;
	private int usernum; 			//유저 작성자
	private int adminnum; 			//운영자 작성자
	private String boardcontent;
	private Timestamp boarddate;
	private int totalcomment; 		//댓글수
	private int hit; 				///조회수
	private String nickname;		///유저 닉네임
	private String shortdate;       ///잘린 날짜
	
	private int ref;
	private int re_step;
	private int re_level;
	
	
	
	public String getShortdate() {
		return shortdate;
	}
	public void setShortdate(String shortdate) {
		this.shortdate = shortdate;
	}
	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public int getAdminnum() {
		return adminnum;
	}
	public void setAdminnum(int adminnum) {
		this.adminnum = adminnum;
	}
	public String getBoardcontent() {
		return boardcontent;
	}
	public void setBoardcontent(String boardcontent) {
		this.boardcontent = boardcontent;
	}
	public Timestamp getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(Timestamp boarddate) {
		this.boarddate = boarddate;
	}
	public int getTotalcomment() {
		return totalcomment;
	}
	public void setTotalcomment(int totalcomment) {
		this.totalcomment = totalcomment;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
}
