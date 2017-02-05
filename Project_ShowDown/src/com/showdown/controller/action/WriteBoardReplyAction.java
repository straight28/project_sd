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
		/* 게시판글에 대한 답글달기 */
		BoardDto bDTO = new BoardDto();
		bDTO.setBoardnum(Integer.parseInt(request.getParameter("boardnum")));
		bDTO.setBoardtitle(request.getParameter("boardtitle"));
		bDTO.setBoardcontent(request.getParameter("boardcontent"));
		bDTO.setUsernum(Integer.parseInt(request.getParameter("usernum")));
		BoardDao bDAO = BoardDao.getInstance();
				
				
		bDAO.InsertBoardReply(bDTO);
		
		new UserBoardAction().execute(request, response);
	}
}
