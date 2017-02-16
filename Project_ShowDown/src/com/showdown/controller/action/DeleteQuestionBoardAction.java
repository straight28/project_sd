package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;

public class DeleteQuestionBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int questBoardNum = Integer.parseInt(request.getParameter("num"));
		
		QuestionBoardDao qbdto = QuestionBoardDao.getInstance();
		
		qbdto.DeleteQuestionBoards(questBoardNum);
		
		new QuestionBoardAction().execute(request, response);
	}
}
