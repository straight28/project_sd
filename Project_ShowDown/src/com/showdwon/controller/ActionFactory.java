package com.showdwon.controller;

import com.showdwon.controller.action.IndexPageAction;
import com.showdwon.controller.action.JoinAction;
import com.showdwon.controller.action.JoinFormAction;
import com.showdwon.controller.action.LoginAction;
import com.showdwon.controller.action.LoginFailAction;
import com.showdwon.controller.action.LoginFormAction;
import com.showdwon.controller.actionInterface.ActionInterface;

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
			action = new JoinAction();
		}else if(command.equals("member_Join_Form")){
			action = new JoinFormAction();
		}else if(command.equals("member_Login_Form")){
			action = new LoginFormAction();
		}else if(command.equals("member_Login")){
			action = new LoginAction();
		}else if(command.equals("main_Page")){
			action = new IndexPageAction();
		}else if(command.equals("login_Fail")){
			action = new LoginFailAction();
		}
		return action;

	}
}
