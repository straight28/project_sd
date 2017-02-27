package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.MemberDao;

public class NickNameCheckFormAcion implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/nickcheck.jsp";
		String nickname = request.getParameter("nickname");
		
		MemberDao mDAO = MemberDao.getInstance();
		int message = mDAO.nickCheck(nickname);
		
		request.setAttribute("message", message);
		request.setAttribute("nickname", nickname);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
