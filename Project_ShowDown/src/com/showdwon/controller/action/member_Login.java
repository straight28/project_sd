package com.showdwon.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdwon.DAO.MemberDAO;
import com.showdwon.DTO.MemberDTO;
import com.showdwon.controller.actionInter.ActionInterface;

public class member_Login implements ActionInterface {
	@Override
	public void perForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String url = "member/Login_fail.jsp";
		String url = "/ended.jsp";
		
		HttpSession session = request.getSession();
		
		String id = request.getParameter("userid");
		String pwd = request.getParameter("userpass");		
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		MemberDTO mDTO = memberDAO.selectMemberByUserid(id);
		
		if(mDTO != null){
			if(mDTO.getUserpass().equals(pwd)){
				session.removeAttribute("id");
				session.setAttribute("loginUser", mDTO);
				url ="DO?command=main_Page";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
