package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardDto;

public class WriteBoardReplyFormAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "board/boardReplyWrite.jsp";
		int boardnum = Integer.parseInt(request.getParameter("num"));
		System.out.println(boardnum);
		
		BoardDao bdao = BoardDao.getInstance();
		BoardDto bdto = bdao.selectOneBoardByBoardNum(boardnum);
		request.setAttribute("oneboard", bdto);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
