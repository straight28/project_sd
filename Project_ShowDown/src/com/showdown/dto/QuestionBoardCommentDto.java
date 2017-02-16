package com.showdown.dto;

import java.sql.Timestamp;

public class QuestionBoardCommentDto {
	private int questcommentnum;
	private int questboardnum;
	private int usernum;
	private String nickname;
	private String content;
	private Timestamp regdate;
	
	private int ref;
	private int re_step;
	private int re_level;
	
	
	
	
	public int getQuestcommentnum() {
		return questcommentnum;
	}
	public void setQuestcommentnum(int questcommentnum) {
		this.questcommentnum = questcommentnum;
	}
	public int getQuestboardnum() {
		return questboardnum;
	}
	public void setQuestboardnum(int questboardnum) {
		this.questboardnum = questboardnum;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
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
	
	
}
