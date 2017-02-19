package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.MemberDao;
import com.showdown.dto.MemberDto;

public class ModifyMyInfo implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		String url = "DO?command=mymenu_form";
		MemberDto mdto = new MemberDto();
		
		mdto.setUsernum(Integer.parseInt(request.getParameter("usernum")));
		mdto.setNickname(request.getParameter("nickname"));
		mdto.setEmail(request.getParameter("email"));

		MemberDao mdao = MemberDao.getInstance();
		result = mdao.UpdateMember(mdto);
		System.out.println("modyfiy가 실패시 result값은 -1 현재 result값 "+result); //result 가 -1이면 실패
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}
}
