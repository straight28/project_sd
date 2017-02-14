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
		
		/* 
		
		 글을 등록 하거나 수정한 후에는 forward 로 보내면 안됨. redirect 로 보내는 것을 권장.
		forward 로 보내면 F5를 누르면 기존에 등록된 데이터가 반복되어서 등록되거나 수정됨 
		이 부분과 관련하여 해결법 찾아보기
		
		*/

		
		
	}
}
