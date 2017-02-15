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
import com.showdown.dao.pageDao;
import com.showdown.dto.MemberDto;
import com.showdown.dto.QuestionBoardDto;

public class QuestionBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="questionBoard/questionBoardList.jsp";
		
		HttpSession session = request.getSession();
		MemberDto loginUser = (MemberDto)session.getAttribute("loginUser");
		QuestionBoardDao QBDao = QuestionBoardDao.getInstance();
		
		if(loginUser == null){
			url = "DO?command=member_Login_Form";
		}
		///DB에서 모든 게시글 수 계산
		int count = QBDao.countQuestionBoard();
		System.out.println("질문 게시판의 총 게시물 수는 ? "+count);
		
		//페이지 번호 설정 
		int curPage = 1; // 현재 페이지를 1로 설정
		if(request.getParameter("curPage") != null){
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		//페이지 계산 
		pageDao pageDao = new pageDao(count, curPage);
		//현재 페이지의 시작, 끝 번호 계산
		int start = pageDao.getPageBegin();
		int end = pageDao.getPageEnd();
		
		///모든 게시물 가져오기
		List<QuestionBoardDto> questionBoardList = QBDao.selectAllQuestionBoards(start, end);
		
		///시간을 요일까지만 잘라서 넣어줌
		for (QuestionBoardDto questionBoardDto : questionBoardList) {
			String time1 = String.valueOf(questionBoardDto.getQuestdate());
			String yea = time1.substring(0,10);
			questionBoardDto.setShortdate(yea);
		}
		///게시판 리스트 전달 
		request.setAttribute("questionBoardList", questionBoardList);
		///페이지 객체 전달
		request.setAttribute("page", pageDao);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
