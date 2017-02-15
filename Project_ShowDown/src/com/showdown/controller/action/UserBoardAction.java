package com.showdown.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dao.pageDao;
import com.showdown.dto.BoardDto;
import com.showdown.dto.MemberDto;

public class UserBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url ="board/boardList.jsp";
		
		HttpSession session = request.getSession();
		MemberDto loginUser =(MemberDto) session.getAttribute("loginUser");
		BoardDao bDAO = BoardDao.getInstance();
		
		if(loginUser == null){
			url = "DO?command=member_Login_Form";
		}
			
		//db에서 모든 게시글 수 계산
		int count = bDAO.countBoard();
		System.out.println("유저 게시판의 총 게시물 수는 ?"+count);
		//페이지 번호 설정
		int curPage = 1;
		if(request.getParameter("curPage") != null){
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		pageDao pageDAO = new pageDao(count, curPage);
		//현재 페이지의 시작, 끝 번호 계산
		int start = pageDAO.getPageBegin();
		int end = pageDAO.getPageEnd();
		
		/// 모든 게시물 목록 가져오기 
		List<BoardDto> boardList = bDAO.selectAllBoards(start,end);
		/// shortdate에 잘라낸 시간를 넣어줌
		for (BoardDto boardDto : boardList) {
			String time1 = String.valueOf(boardDto.getBoarddate());
			String yea = time1.substring(0,10);
			boardDto.setShortdate(yea);
		}
		
		request.setAttribute("boardList", boardList);
		/// 페이지 객체 전달
		request.setAttribute("page", pageDAO);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
