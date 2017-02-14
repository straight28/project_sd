package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardCommentDto;
import com.showdown.dto.MemberDto;

public class WriteDeepReply implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int commentnum = Integer.parseInt(request.getParameter("comment"));
		System.out.println("코멘트번호 내용"+request.getParameter("comment"));
		
		/* 게시판글에 대한 대댓글 달기 */
		HttpSession session = request.getSession();
		MemberDto loginUser =(MemberDto) session.getAttribute("loginUser");

		BoardCommentDto bcDTO = new BoardCommentDto();
		BoardDao bDAO = BoardDao.getInstance();
		bcDTO = bDAO.selectOneBoardReplyCommentByCommentNum(commentnum);
		
		bcDTO.setCommentnum(commentnum);
		bcDTO.setBoardnum(Integer.parseInt(request.getParameter("num")));
		bcDTO.setUsernum(loginUser.getUsernum());
		bcDTO.setContent(request.getParameter("boardcomment"));
		System.out.println("리플컨텐츠 내용"+request.getParameter("boardcomment"));
		
		bcDTO.setRef(Integer.parseInt(request.getParameter("ref")));
		System.out.println(request.getParameter("ref"));
		bcDTO.setRe_step(Integer.parseInt(request.getParameter("step")));
		System.out.println(request.getParameter("step"));
		bcDTO.setRe_level(Integer.parseInt(request.getParameter("level")));
		System.out.println(request.getParameter("level"));
		
		bDAO.InsertTwoBoardComment(bcDTO);
		
		new ViewBoardAction().execute(request, response);
	}
}
