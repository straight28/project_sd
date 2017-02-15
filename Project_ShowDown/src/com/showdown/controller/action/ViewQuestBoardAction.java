package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;
import com.showdown.dto.QuestionBoardDto;

public class ViewQuestBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/questionBoard/questionBoardView.jsp";
		int questboardnum = Integer.parseInt(request.getParameter("num"));
		
		QuestionBoardDao qbdao = QuestionBoardDao.getInstance();
		HttpSession session = request.getSession();
		/* 조회수 상승 */
		qbdao.questionBoardUpdateHit(questboardnum, session);
		 /* 게시글 하나의 내용을 가져옴 */
		QuestionBoardDto qbdto = qbdao.selectOneQuestionBoardByQuestionBoardNum(questboardnum); 
		
		request.setAttribute("qeustboard", qbdto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
