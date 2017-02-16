package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.QuestionBoardDao;
import com.showdown.dto.MemberDto;
import com.showdown.dto.QuestionBoardCommentDto;

public class QuestionBoardWriteReplyAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "DO?command=questBoard_view&num=";
		
		QuestionBoardCommentDto qbcDTO = new QuestionBoardCommentDto();
		HttpSession session = request.getSession();
		MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
		
		qbcDTO.setQuestboardnum(Integer.parseInt(request.getParameter("num")));
		url += request.getParameter("num");
		qbcDTO.setContent(request.getParameter("questboardcomment"));
		qbcDTO.setUsernum(loginUser.getUsernum());
		
		QuestionBoardDao qbdao = QuestionBoardDao.getInstance();
		qbdao.InsertQuestionBoardComment(qbcDTO);
		
		response.sendRedirect(url);
		
	}
}
