package com.showdown.dao;

import com.showdown.dto.BoardDto;

public interface BoardDaoInterface {

	public int InsertBoards(BoardDto bDTO);
	public int DeleteBoards(int boardnum);
	public int ModifyBoards(BoardDto bDTO);
}
