package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardDto;

public class WriteBoardReplyAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="DO?command=userboard";
		/* 게시판글에 대한 답글달기 */
		BoardDto bDTO = new BoardDto();
		bDTO.setBoardnum(Integer.parseInt(request.getParameter("boardnum")));
		bDTO.setBoardtitle(request.getParameter("boardtitle"));
		bDTO.setBoardcontent(request.getParameter("boardcontent"));
		bDTO.setUsernum(Integer.parseInt(request.getParameter("usernum")));
		bDTO.setRef(Integer.parseInt(request.getParameter("ref")));
		bDTO.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		bDTO.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		BoardDao bDAO = BoardDao.getInstance();
				
		bDAO.InsertBoardReply(bDTO);
		response.sendRedirect(url);
		
	}
}
