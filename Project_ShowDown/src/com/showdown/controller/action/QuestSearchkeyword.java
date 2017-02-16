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
import com.showdown.dto.BoardDto;
import com.showdown.dto.MemberDto;
import com.showdown.dto.QuestionBoardDto;

public class QuestSearchkeyword implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="questionBoard/questionBoardList.jsp";
		
		HttpSession session = request.getSession();
		MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
		QuestionBoardDao QBDAO = QuestionBoardDao.getInstance();
		
		if(loginUser == null){
			url = "DO?command=member_Login_Form";
		}
		///검색 옵션
		String search_option = request.getParameter("search_option");
		System.out.println("질문 게시판 search_option 값은 "+search_option);
		
		///검색 키워드
		String keyword = request.getParameter("keyword");
		System.out.println("keyword 값은 "+keyword);
		
		//db에서 검색한 모든 게시글 수 계산
		int count = QBDAO.searchCountBoard(search_option, keyword);
		System.out.println("검색한 게시글 수는 ? "+count);
		
		//페이지 번호 설정
		int curPage = 1;
		if(request.getParameter("curPage") != null){
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		pageDao pageDao = new pageDao(count, curPage);
		// 페이지 시작, 끝번호 계산
		int start = pageDao.getPageBegin();
		int end = pageDao.getPageEnd();
		
		///검색 내용에 대한 페이지 출력
		List<QuestionBoardDto> questionBoardList = QBDAO.searchList(search_option, keyword,start,end);
		
		request.setAttribute("questionBoardList", questionBoardList);
		request.setAttribute("search_option", search_option);
		request.setAttribute("keyword", keyword);
		request.setAttribute("page", pageDao);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
