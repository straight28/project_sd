package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.MemberDao;

public class DeleteMemberAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url ="DO?command=logout";
		String message = "";
		String userid = request.getParameter("userid");
		int usernum = Integer.parseInt(request.getParameter("usernum"));
		String userpass = request.getParameter("userpass");
		
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.checkID(userid, userpass);
		
		if(result == 2){
			//id 비밀번호 일치하면 회원삭제
			mdao.DeleteMember(usernum);
		}else{
			url = "DO?command=deleteMemberForm";
			message = "비밀번호가 일치하지 않습니다. ";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
