package com.showdwon.controller.actionInter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionInterface {

	///액션을 실행하게 하는 인터페이스
	public void perForm(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
	
}
