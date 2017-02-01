package com.showdown.dto;

import java.sql.Timestamp;

public class BoardDto {
	private int boardnum;
	private String boardtitle;
	private int usernum; //유저 작성자
	private int adminnum; //운영자 작성자
	private String boardcontent;
	private Timestamp boarddate;
	private String replequantity; //답수
	private String hit; ///조회수
	
	
	
	
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
	public String getReplequantity() {
		return replequantity;
	}
	public void setReplequantity(String replequantity) {
		this.replequantity = replequantity;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	
	
	
	
	
}
