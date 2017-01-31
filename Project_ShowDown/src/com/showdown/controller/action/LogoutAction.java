package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;

public class LogoutAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "DO?command=index";
		
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
