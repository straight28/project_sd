package com.showdown.controller;

import com.showdown.controller.action.DeleteBoardAction;
import com.showdown.controller.action.DeleteQuestionBoardAction;
import com.showdown.controller.action.DeleteReplyInBoard;
import com.showdown.controller.action.IndexPageAction;
import com.showdown.controller.action.JoinAction;
import com.showdown.controller.action.JoinFormAction;
import com.showdown.controller.action.LoginAction;
import com.showdown.controller.action.LoginFormAction;
import com.showdown.controller.action.LogoutAction;
import com.showdown.controller.action.ModifyBoardAction;
import com.showdown.controller.action.ModifyBoardFormAction;
import com.showdown.controller.action.ModifyQuestionBoardAction;
import com.showdown.controller.action.ModifyQuestionBoardFormAction;
import com.showdown.controller.action.MyMenuFormAction;
import com.showdown.controller.action.QuestionBoardAction;
import com.showdown.controller.action.QuestionBoardWriteReplyAction;
import com.showdown.controller.action.SearchKeyword;
import com.showdown.controller.action.UserBoardAction;
import com.showdown.controller.action.ViewBoardAction;
import com.showdown.controller.action.ViewQuestBoardAction;
import com.showdown.controller.action.WriteBoardAction;
import com.showdown.controller.action.WriteBoardFormAction;
import com.showdown.controller.action.WriteBoardReplyAction;
import com.showdown.controller.action.WriteBoardReplyFormAction;
import com.showdown.controller.action.WriteDeepReply;
import com.showdown.controller.action.WriteQuestionBoardAction;
import com.showdown.controller.action.WriteQuestionBoardFormAction;
import com.showdown.controller.action.WriteReplyAction;
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
		}else if(command.equals("index")){
			action = new IndexPageAction();
		}else if(command.equals("logout")){
			action = new LogoutAction();
		}else if(command.equals("mymenu_form")){
			action = new MyMenuFormAction();
		}else if(command.equals("board_Write_Form")){
			action = new WriteBoardFormAction();
		}else if(command.equals("userboard")){
			action = new UserBoardAction();
		}else if(command.equals("board_write")){
			action = new WriteBoardAction();
		}else if(command.equals("board_view")){
			action = new ViewBoardAction();
		}else if(command.equals("board_delete")){
			action = new DeleteBoardAction();
		}else if(command.equals("board_modify_form")){
			action = new ModifyBoardFormAction();
		}else if(command.equals("board_update")){
			action = new ModifyBoardAction();
		}else if(command.equals("repleWrite")){
			action = new WriteReplyAction();
		}else if(command.equals("writeboardreply")){
			action = new WriteBoardReplyFormAction();
		}else if(command.equals("boardReply_write")){
			action = new WriteBoardReplyAction();
		}else if(command.equals("deletereplyinboard")){
			action = new DeleteReplyInBoard();
		}else if(command.equals("searchkeyword")){
			action = new SearchKeyword();  				//// 리스트로 보냄
		}else if(command.equals("deepRepleWrite")){
			action = new WriteDeepReply();
		}else if(command.equals("questionboard")){ 		/// questionBoard 
			action = new QuestionBoardAction();
		}else if(command.equals("questboard_Write_Form")){
			action = new WriteQuestionBoardFormAction();
		}else if(command.equals("questionboard_write")){
			action = new WriteQuestionBoardAction();
		}else if(command.equals("questboard_view")){
			action = new ViewQuestBoardAction();
		}else if(command.equals("questBoard_delete")){
			action = new DeleteQuestionBoardAction();
		}else if(command.equals("questboard_modify_form")){
			action = new ModifyQuestionBoardFormAction();
		}else if(command.equals("questboard_update")){
			action = new ModifyQuestionBoardAction();
		}else if(command.equals("questBoardRepleWrite")){
			action = new QuestionBoardWriteReplyAction();
		}
		
		
		
		return action;

	}
}
