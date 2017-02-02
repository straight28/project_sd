package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardDto;

public class ViewBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";
		String boardnum = request.getParameter("num");
		
		BoardDao bDAO = BoardDao.getInstance();
		bDAO.updateHit(boardnum);
		
		BoardDto dDTO = bDAO.selectOneBoardByBoardNum(boardnum);
		request.setAttribute("oneboard", dDTO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
