package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;

public class LoginFormAction implements ActionInterface {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/memberLogin.jsp";
		String q=request.getParameter("q");
		
		System.out.println("q의 값은?"+q);
		if( q != null){
			request.setAttribute("q" , q);
		}else{
			q="0"; /// 자유게시판으로 화면 전환을 위한 q값
			request.setAttribute("q" , q);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
