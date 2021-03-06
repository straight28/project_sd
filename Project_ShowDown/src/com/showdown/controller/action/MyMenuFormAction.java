package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.BoardDao;
import com.showdown.dao.MemberDao;
import com.showdown.dto.MemberDto;

public class MyMenuFormAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "member/mymenu.jsp";
		
		MemberDao mdao = new MemberDao();
		HttpSession session = request.getSession();
		MemberDto loginUser =(MemberDto) session.getAttribute("loginUser");
		if(loginUser == null){
			url = "DO?command=member_Login_Form&q=0";
		}
		
		String userid = loginUser.getUserid();
		MemberDto userinfo = mdao.selectMemberByUserid(userid);
		
		request.setAttribute("userinfo", userinfo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
