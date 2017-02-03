package com.showdown.dto;

import java.sql.Date;

public class BoardPlusMember {
	private int boardnum;
	private String boardtitle;
	private int usernum; 			//유저 작성자
	private int nickname;
	private int adminnum; 			//운영자 작성자
	private String boardcontent;
	private Date boarddate;
	private int totalcomment; 		//댓글수
	private int hit; 				///조회수
	
	private int board_re_group;  	//답글 그룹
	private int board_re_lev;  		//답글 깊이
	private int board_re_seq;
}
