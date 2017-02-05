package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;

public class DeleteReplyInBoard implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment = request.getParameter("commentnum");
		int commentnum = Integer.parseInt(comment);
		BoardDao bDAO = BoardDao.getInstance();
		
		bDAO.DeleteBoardReply(commentnum);
		
		String num = request.getParameter("num");
		
		
		
		new ViewBoardAction().execute(request, response);
	}
}
