package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.MemberDao;

public class LoginAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		String url = "DO?command=login_Fail";
		String message = ""; //// 알림을 위한 메세지
		String userid = request.getParameter("userid").trim();
		String userpass = request.getParameter("userpass").trim();
		
		MemberDao mDAO = MemberDao.getInstance();
		
		result = mDAO.checkID(userid,userpass);
		
		if(result == 2){ /// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			url = "DO?command=main_Page";
			message= "로그인 성공";
		}else if(result == 1){ ////비밀번호 틀림
			message= "비밀번호를 확인하세요.";
		}else if(result == -1){ ///아이디 없음
			message= "가입되지 않은 아이디 입니다.";
		}
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
