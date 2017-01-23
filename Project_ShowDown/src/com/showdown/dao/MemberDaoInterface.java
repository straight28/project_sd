package com.showdown.dao;

import com.showdown.dto.MemberDto;

public interface MemberDaoInterface {
	public int InsertMember(MemberDto memberDTO);
	public void DeleteMember(String userid);
	public int UpdateMember(MemberDto memberDTO);
}
