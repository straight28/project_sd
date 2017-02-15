package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardCommentDto;
import com.showdown.dto.MemberDto;

public class WriteDeepReply implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "DO?command=board_view&num=";
		
		int commentnum = Integer.parseInt(request.getParameter("comment"));
		System.out.println("코멘트번호 내용"+request.getParameter("comment"));
		
		/* 게시판글에 대한 대댓글 달기 */
		HttpSession session = request.getSession();
		MemberDto loginUser =(MemberDto) session.getAttribute("loginUser");

		BoardCommentDto bcDTO = new BoardCommentDto();
		BoardDao bDAO = BoardDao.getInstance();
		/* 원 댓글에 대한 정보 가져오기 */
		bcDTO = bDAO.selectOneBoardReplyCommentByCommentNum(commentnum);
		
		bcDTO.setCommentnum(commentnum);
		bcDTO.setBoardnum(Integer.parseInt(request.getParameter("num")));
		bcDTO.setUsernum(loginUser.getUsernum());
		bcDTO.setContent(request.getParameter("boardcomment"));
		bcDTO.setRef(Integer.parseInt(request.getParameter("ref")));
		bcDTO.setRe_step(Integer.parseInt(request.getParameter("step")));
		bcDTO.setRe_level(Integer.parseInt(request.getParameter("level")));
		/* 댓글 추가  */
		bDAO.InsertTwoBoardComment(bcDTO);
		url += request.getParameter("num");
		
		/*new ViewBoardAction().execute(request, response);*/
		
		response.sendRedirect(url);
		
		/* 
		
		 글을 등록 하거나 수정한 후에는 forward 로 보내면 안됨. redirect 로 보내는 것을 권장.
		forward 로 보내면 F5를 누르면 기존에 등록된 데이터가 반복되어서 등록되거나 수정됨 
		이 부분과 관련하여 해결법 찾아보기  ==> response.sendRedirect(url);
		
		*/
	}
}
