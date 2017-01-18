package com.showdwon.controller;

import com.showdwon.controller.action.Login_Fail;
import com.showdwon.controller.action.main_Page;
import com.showdwon.controller.action.member_Join;
import com.showdwon.controller.action.member_Join_Form;
import com.showdwon.controller.action.member_Login;
import com.showdwon.controller.action.member_Login_Form;
import com.showdwon.controller.actionInter.ActionInterface;

public class ActionFactory {

	private static ActionFactory instance = new ActionFactory();

	/// 싱글톤을 위한 메소드 -No1
	public static ActionFactory getInstance() {
		return instance;
	}

	public ActionInterface getAction(String command) {
		ActionInterface action = null;

		System.out.println("actionFactory에서 command " + command);

		if (command.equals("member_Join")) {
			action = new member_Join();
		}else if(command.equals("member_Join_Form")){
			action = new member_Join_Form();
		}else if(command.equals("member_Login_Form")){
			action = new member_Login_Form();
		}else if(command.equals("member_Login")){
			action = new member_Login();
		}else if(command.equals("main_Page")){
			action = new main_Page();
		}else if(command.equals("login_Fail")){
			action = new Login_Fail();
		}
		return action;

	}
}
