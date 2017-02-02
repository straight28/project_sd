package com.showdown.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class BoardDto {
	private int boardnum;
	private String boardtitle;
	private int usernum; 			//유저 작성자
	private int adminnum; 			//운영자 작성자
	private String boardcontent;
	private Date boarddate;
	private int totalcomment; 		//댓글수
	private int hit; 				///조회수
	
	private int board_re_group;  	//답글 그룹
	private int board_re_lev;  		//답글 깊이
	private int board_re_seq;		//답글 순서
	
	
	
	
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
	public Date getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(Date boarddate) {
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
	public int getBoard_re_group() {
		return board_re_group;
	}
	public void setBoard_re_group(int board_re_group) {
		this.board_re_group = board_re_group;
	}
	public int getBoard_re_lev() {
		return board_re_lev;
	}
	public void setBoard_re_lev(int board_re_lev) {
		this.board_re_lev = board_re_lev;
	}
	public int getBoard_re_seq() {
		return board_re_seq;
	}
	public void setBoard_re_seq(int board_re_seq) {
		this.board_re_seq = board_re_seq;
	}
	
	
	
}
