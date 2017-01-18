package com.showdwon.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdwon.DAO.MemberDAO;
import com.showdwon.controller.actionInter.ActionInterface;

public class main_Page implements ActionInterface {
	@Override
	public void perForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "mainPage.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	
	}
}
