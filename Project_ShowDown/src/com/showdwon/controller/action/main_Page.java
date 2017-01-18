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

		String url = "DO?command=member_Join_Form";
		String message = ""; //// 알림을 위한 메세지
		String userid = request.getParameter("userid").trim();
		String userpass = request.getParameter("userpass").trim();
		
		MemberDAO mDAO = MemberDAO.getInstance();
		
		
	//	int result = mDAO.여기에 아이디 비번 확인 할 메소드 필요
		int result =0;
		
		
		
		if(result == 1){ /// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			url = "DO?command=main_Page";
		}else if(result == 0){ ////비밀번호 틀림
			message= "비밀번호를 확인하세요";
		}else if(result == -1){ ///아이디 틀림
			message= "아이디를 확인하세요";
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
