package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;
import com.showdown.dto.QuestionBoardDto;

public class ModifyQuestionBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "DO?command=questionboard";
		
		QuestionBoardDto qbdto = new QuestionBoardDto();
		
		qbdto.setQuestboardnum(Integer.parseInt(request.getParameter("questboardnum")));
		String questboardcontent = request.getParameter("questboardcontent");
		
		/* 수정시마다 br이 늘어나는 것을 방지 */
		questboardcontent = questboardcontent.replaceAll("<br>", "");
		qbdto.setQuestboardcontent(questboardcontent);
		qbdto.setUsernum(Integer.parseInt(request.getParameter("usernum")));
		
		QuestionBoardDao qbdao = QuestionBoardDao.getInstance();
		qbdao.ModifyQuestionBoards(qbdto);
		
		response.sendRedirect(url);
	}
}
