package com.showdown.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;
import com.showdown.dto.QuestionBoardCommentDto;
import com.showdown.dto.QuestionBoardDto;

public class ViewQuestBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/questionBoard/questionBoardView.jsp";
		System.out.println("이거 받는 숫자는2 ??? "+request.getParameter("num"));
		
		int questboardnum = Integer.parseInt(request.getParameter("num"));
		
		QuestionBoardDao qbdao = QuestionBoardDao.getInstance();
		/* 조회수 상승 */
		HttpSession session = request.getSession();
		qbdao.questionBoardUpdateHit(questboardnum, session);
		/* 게시글 하나의 내용을 가져옴 */
		QuestionBoardDto qbdto = qbdao.selectOneQuestionBoardByQuestionBoardNum(questboardnum); 
		request.setAttribute("questboard", qbdto);
		/* 댓글 목록을 가져옴 */
		List<QuestionBoardCommentDto> QBCcommentList = qbdao.commentList(questboardnum);
		request.setAttribute("commentList", QBCcommentList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
