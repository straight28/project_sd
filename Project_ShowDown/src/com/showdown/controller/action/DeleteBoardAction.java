package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;

public class DeleteBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String num = request.getParameter("num");
		int boardnum = Integer.parseInt(num);
		BoardDao bDAO = BoardDao.getInstance();
		
		bDAO.DeleteBoards(boardnum);
		new UserBoardAction().execute(request, response);
	}
}
