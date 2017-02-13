package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dto.MemberDto;

public class IndexPageAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "/index.jsp";
		
		HttpSession session = request.getSession();
		MemberDto loginUser =(MemberDto) session.getAttribute("loginUser");
		
		if (loginUser != null) {
			url = "/mainPage.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	
	}
}
