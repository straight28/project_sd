package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;
import com.showdown.dto.QuestionBoardDto;

public class ModifyQuestionBoardFormAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="questionBoard/questionBoardModify.jsp";
		
		int questboardnum = Integer.parseInt(request.getParameter("num"));
		
		QuestionBoardDao qbdao = QuestionBoardDao.getInstance();
		QuestionBoardDto qbdto = qbdao.selectOneQuestionBoardByQuestionBoardNum(questboardnum);
		
		request.setAttribute("questionboard", qbdto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
			
	}
}
