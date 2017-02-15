package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;
import com.showdown.dto.QuestionBoardDto;

public class WriteQuestionBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "DO?command=questionboard";
		QuestionBoardDto QBDTO = new QuestionBoardDto();
		
		QBDTO.setQuestboardtitle(request.getParameter("questboardtitle"));
		QBDTO.setQuestboardcontent(request.getParameter("questboardcontent"));
		QBDTO.setUsernum(Integer.parseInt(request.getParameter("usernum")));
		
		QuestionBoardDao QBDao = QuestionBoardDao.getInstance();
		
		QBDao.InsertQuestionBoards(QBDTO);
		
		response.sendRedirect(url);
		
		
	}
}
