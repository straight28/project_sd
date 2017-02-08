package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardDto;

public class WriteBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		BoardDto bDTO = new BoardDto();

		bDTO.setBoardtitle(request.getParameter("boardtitle"));
		bDTO.setBoardcontent(request.getParameter("boardcontent"));
		bDTO.setUsernum(Integer.parseInt(request.getParameter("usernum")));
		BoardDao bDAO = BoardDao.getInstance();
		
		System.out.println(request.getParameter("boardcontent"));
		
		bDAO.InsertBoards(bDTO);
		
		new UserBoardAction().execute(request, response);
	}
}
