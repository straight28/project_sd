package com.showdown.dto;

import java.sql.Timestamp;

public class QuestionBoardDto {

	private int questboardnum;
	private String questboardtitle;
	private int usernum;				///작성자
	private String questboardcontent;		///내용
	private Timestamp questdate;
	private int totalcomment;			///댓글수
	private int hit;
	private int ref;
	private int re_step;
	private int re_level;
	private String nickname;			///유저 닉네임
	private String shortdate;			///잘린 날짜
	
	
	
	
	
	public int getQuestboardnum() {
		return questboardnum;
	}
	public void setQuestboardnum(int questboardnum) {
		this.questboardnum = questboardnum;
	}
	public String getQuestboardtitle() {
		return questboardtitle;
	}
	public void setQuestboardtitle(String questboardtitle) {
		this.questboardtitle = questboardtitle;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public String getQuestboardcontent() {
		return questboardcontent;
	}
	public void setQuestboardcontent(String questboardcontent) {
		this.questboardcontent = questboardcontent;
	}
	public Timestamp getQuestdate() {
		return questdate;
	}
	public void setQuestdate(Timestamp questdate) {
		this.questdate = questdate;
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
	public String getShortdate() {
		return shortdate;
	}
	public void setShortdate(String shortdate) {
		this.shortdate = shortdate;
	}
	
	
	
	
	
	
}
