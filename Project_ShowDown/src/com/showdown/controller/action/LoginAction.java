package com.showdown.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.showdown.controller.actionInterface.ActionInterface;
import com.showdown.dao.MemberDao;
import com.showdown.dto.MemberDto;

public class LoginAction implements ActionInterface {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;

		String url = "member/memberLogin.jsp";
		String message = ""; //// 알림을 위한 메세지
		String userid = request.getParameter("userid");
		System.out.println("아이디는"+userid);
		String userpass = request.getParameter("userpass");
		System.out.println("비밀번호는 "+userpass);
		
		MemberDao mDAO = MemberDao.getInstance();
		
		result = mDAO.checkID(userid,userpass);
		
		/// 진입페이지 변환 관련/// q가 1이면 질문게시판 0이면 자유게시판으로 이동
		String q=request.getParameter("q");
		if( q != null){
			request.setAttribute("q" , q);
		}else{
			q="0"; /// 자유게시판으로 화면 전환을 위한 q값
			request.setAttribute("q" , q);
		}
		int	q1 = Integer.parseInt(q);
		
		if(result == 2){ /// 로그인 성공
			
			MemberDto mDTO = mDAO.selectMemberByUserid(userid);
			
			if(mDTO != null){
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mDTO);
				url = "DO?command=userboard";      ///url 완성되면 페이지 전환 필요한 부분
				message= "로그인 성공";
				System.out.println("q의 값은"+q);
				if(q1 == 1){
				url = "DO?command=questionboard";  
				}
			}else{
				message= "정보를 가져올 수 없습니다.";
			}
			
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
