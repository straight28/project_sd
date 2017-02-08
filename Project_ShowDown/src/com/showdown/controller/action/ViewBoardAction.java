package com.showdown.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardCommentDto;
import com.showdown.dto.BoardDto;
import com.showdown.dto.MemberDto;

public class ViewBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/board/boardView.jsp";
		int boardnum = Integer.parseInt(request.getParameter("num"));
		
		BoardDao bDAO = BoardDao.getInstance();
		/* 조회수를 올림 */
		HttpSession session = request.getSession();
		bDAO.updateHit(boardnum,session);
		/* 게시글 하나의 내용을 가져옴 */
		BoardDto BDTO = bDAO.selectOneBoardByBoardNum(boardnum);
		request.setAttribute("oneboard", BDTO);
		/* 댓글 목록을 가져옴 */
		List<BoardCommentDto> DCList = bDAO.commentList(boardnum);
		request.setAttribute("commentList", DCList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
