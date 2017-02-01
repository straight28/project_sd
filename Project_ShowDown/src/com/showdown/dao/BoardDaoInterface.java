package com.showdown.dao;

import com.showdown.dto.BoardDto;

public interface BoardDaoInterface {

	public int InsertBoards(BoardDto bDTO);
	public void DeleteBoards(String userid,String userpass);
	public int ModifyBoards(BoardDto bDTO);
}
