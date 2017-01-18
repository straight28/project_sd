package com.showdwon.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.showdwon.DAO.MemberDAO;
import com.showdwon.DTO.MemberDTO;
import com.showdwon.controller.actionInter.ActionInterface;

public class member_Join implements ActionInterface {
	@Override
	public void perForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result=0;
		String url = "/JoinPage.jsp";
		/// DTO 객체 생성
		MemberDTO mDTO = new MemberDTO();

		//// 회원 정보 확인
		mDTO.setUserid(request.getParameter("userid"));
		System.out.println(request.getParameter("userid"));
		mDTO.setUserpass(request.getParameter("userpass"));
		mDTO.setNickname(request.getParameter("nickname"));
		mDTO.setEmail(request.getParameter("email"));
		mDTO.setAdmin(0); //// 일반 회원은 0값 입력

		MemberDAO mDAO = MemberDAO.getInstance();
		result = mDAO.InsertMember(mDTO); //// dao에 있는 메소드에 회원정보값 입력
		
		System.out.println("여기까지 온건지 확인");
		
		
		if (result == 0) {
			url = "/JoinPage.jsp";
		}else{
			url = "member/member_Login.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
