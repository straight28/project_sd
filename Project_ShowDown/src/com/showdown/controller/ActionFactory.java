package com.showdown.controller;

import com.showdown.controller.action.IndexPageAction;
import com.showdown.controller.action.JoinAction;
import com.showdown.controller.action.JoinFormAction;
import com.showdown.controller.action.LoginAction;
import com.showdown.controller.action.LoginFailAction;
import com.showdown.controller.action.LoginFormAction;
import com.showdown.controller.action.LogoutAction;
import com.showdown.controller.action.MyMenuFormAction;
import com.showdown.controller.actionInterface.ActionInterface;

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
		}else if(command.equals("login_Fail")){
			action = new LoginFailAction();
		}else if(command.equals("index")){
			action = new IndexPageAction();
		}else if(command.equals("logout")){
			action = new LogoutAction();
		}else if(command.equals("mymenu_form")){
			action = new MyMenuFormAction();
		}
		
		
		return action;

	}
}
