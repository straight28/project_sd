package com.showdwon.DAO;

import com.showdwon.DTO.MemberDTO;

public interface MemberDAO_Inter {

	
	public int InsertMember(MemberDTO memberDTO);
	public void DeleteMember(String userid);
	public int UpdateMember();
	
}
