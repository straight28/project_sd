package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.MemberDao;

public class ModifyPasswordAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url ="DO?command=logout";
		String message = ""; //// 알림을 위한 메세지
		String userid = request.getParameter("userid");
		int usernum = Integer.parseInt(request.getParameter("usernum"));
		
		//원래 비번
		String exuserpass = request.getParameter("exuserpass");
		
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.checkID(userid, exuserpass);
		
		if(result == 2){
			//변경 비번
			String userpass = request.getParameter("userpass");
			mdao.UpdateMemberPass(usernum,userpass);
			System.out.println("비번 변경 성공");
		}else{
			url = "DO?command=updatepassForm";
			message = "기존 비밀번호나 변경비밀번호의 확인이 일치하지 않습니다.";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
