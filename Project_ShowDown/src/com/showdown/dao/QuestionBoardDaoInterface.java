package com.showdown.dao;

import com.showdown.dto.QuestionBoardDto;

public interface QuestionBoardDaoInterface {
	public int InsertQuestionBoards(QuestionBoardDto QBDTO);
	public int DeleteQuestionBoards(int questboardnum);
	public void ModifyQuestionBoards(QuestionBoardDto QBDTO);
}
