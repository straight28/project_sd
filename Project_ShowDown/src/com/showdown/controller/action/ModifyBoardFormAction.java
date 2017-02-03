package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardDto;

public class ModifyBoardFormAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/boardModify.jsp";
		String boardnum = request.getParameter("num");
		
		BoardDao bDAO = BoardDao.getInstance();
		bDAO.updateHit(boardnum);
		
		BoardDto bDTO = bDAO.selectOneBoardByBoardNum(boardnum);
		request.setAttribute("oneboard", bDTO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
	
}
