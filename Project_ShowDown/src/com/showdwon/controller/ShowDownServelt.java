package com.showdwon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdwon.controller.actionInter.ActionInterface;

@WebServlet("/DO")
public class ShowDownServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// command 값을 받을 String command 생성
		String command = request.getParameter("command"); 
		System.out.println("Servlet 에서 command 확인 "+command);
		
		///actionFactory 객체 생성
		ActionFactory af = ActionFactory.getInstance();
		
		ActionInterface action = af.getAction(command);
		
		if(action != null){
			action.perForm(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///한글 처리
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
