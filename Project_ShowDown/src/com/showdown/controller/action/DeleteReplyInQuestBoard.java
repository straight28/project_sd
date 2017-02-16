package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;

public class DeleteReplyInQuestBoard implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String comment = request.getParameter("commentnum");
		int questcommentnum = Integer.parseInt(comment);
		QuestionBoardDao qbdao = QuestionBoardDao.getInstance();
		
		qbdao.DeleteQuestionBoardReply(questcommentnum);

		new ViewQuestBoardAction().execute(request, response);
	}
}
