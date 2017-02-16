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

public class WriteDeepReplyInQuestionboard implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "DO?command=questBoard_view&num="; 
		
		int questcommentnum = Integer.parseInt(request.getParameter("comment"));
		System.out.println("질문 게시판에 댓글 번호 "+request.getParameter("comment"));
		
		/*  게시판에 대한 대댓글 달기   */
		HttpSession session = request.getSession();
		MemberDto loginUser =(MemberDto) session.getAttribute("loginUser");
		
		QuestionBoardCommentDto qbcDTO = new QuestionBoardCommentDto();
		QuestionBoardDao qbDAO = QuestionBoardDao.getInstance();
		
		/*  상위 댓글에 대한 정보 가져오기    */
		qbcDTO = qbDAO.selectOneReplyInfoByQuestboardcommentnum(questcommentnum);
		
		qbcDTO.setQuestcommentnum(questcommentnum);
		qbcDTO.setQuestboardnum(Integer.parseInt(request.getParameter("num")));
		qbcDTO.setUsernum(loginUser.getUsernum());
		qbcDTO.setContent(request.getParameter("questboardcomment"));
		qbcDTO.setRef(Integer.parseInt(request.getParameter("ref")));
		qbcDTO.setRe_step(Integer.parseInt(request.getParameter("step")));
		qbcDTO.setRe_level(Integer.parseInt(request.getParameter("level")));
		
		/*	댓글 추가 */
		qbDAO.InsertTwoQuestionBoardComment(qbcDTO);
		url += request.getParameter("num");
		
		response.sendRedirect(url);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
