package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dto.BoardDto;

public class ModifyBoardAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardDto bDTO = new BoardDto();
		
		bDTO.setBoardnum(Integer.parseInt(request.getParameter("boardnum")));
		bDTO.setBoardcontent(request.getParameter("boardcontent"));
		bDTO.setUsernum(Integer.parseInt(request.getParameter("usernum")));
		
		System.out.println("boardnum"+request.getParameter("boardnum"));
		System.out.println("boardcontent"+request.getParameter("boardcontent"));
		System.out.println("usernum"+request.getParameter("usernum"));
		
		
		
		BoardDao bDao = BoardDao.getInstance();
		bDao.ModifyBoards(bDTO);
		
		new UserBoardAction().execute(request, response);
	}
}
