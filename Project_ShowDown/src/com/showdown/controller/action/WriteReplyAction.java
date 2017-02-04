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

public class WriteReplyAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";
		
		BoardCommentDto bcdto = new BoardCommentDto();
		HttpSession session = request.getSession();
		MemberDto loginUser =(MemberDto) session.getAttribute("loginUser");
		
		bcdto.setBoardnum(Integer.parseInt(request.getParameter("num")));
		bcdto.setContent(request.getParameter("boardcomment"));
		bcdto.setUsernum(loginUser.getUsernum());
		
		BoardDao bdao = BoardDao.getInstance();
		bdao.InsertBoardComment(bcdto);
		
		new ViewBoardAction().execute(request, response);
	}
}
