package com.showdown.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.showdown.DBConnect.DBConnectManager;
import com.showdown.dto.BoardDto;

public class BoardDao {

	private BoardDao() {}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance(){
		return instance;
	}
	
	public List<BoardDto> selectAllBoards(){
		String sql = "select * from board order by desc";   ///역 정렬
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null;
		Statement stmt = null;   ///이 메소드의 sql은 완성된 형태의 sql 문장임
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				BoardDto mdto = new BoardDto();
				mdto.setBoardnum(rs.getInt("boardnum"));
				mdto.setBoardtitle(rs.getString("boardtitle"));
				mdto.setUsernum(rs.getInt("usernum"));
				mdto.setAdminnum(rs.getInt("adminnum"));
				mdto.setBoardcontent(rs.getString("boardcontent"));
				mdto.setBoarddate(rs.getTimestamp("boarddate"));
				mdto.setReplequantity(rs.getInt("Replequantity"));
				mdto.setHit(rs.getInt("hit"));
				list.add(mdto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBConnectManager.disConnect(conn, stmt, rs);
		}
		return list;
	}
	
	
	/* 게시글 만들기 boardnum에 seq 생성여부 확인*/
	public void insertBoard(BoardDto bdto){ 
		String sql ="insert into board(boardtitle, usernum, adminnum, boardcontent) "
				+"values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bdto.getBoardtitle());
			pstmt.setInt(2, bdto.getUsernum());
			pstmt.setInt(3, bdto.getAdminnum());
			pstmt.setString(4, bdto.getBoardcontent());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
	}
	
	
	/* 조회수 올리는 sql (미 테스트)*/
	public void updateHit(int boardnum){
		String sql = "update board set hit=hit+1 where boardnum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
	}
	
	
	/* 글 내용보기 */
	public BoardDto selectOneBoardByBoardNum(int boardnum){
		String sql = "select * from board where boardnum = ?";
		BoardDto bdto = null; /// 실패시 null값
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bdto = new BoardDto();
				bdto.setBoardnum(rs.getInt("boardnum"));
				bdto.setBoardtitle(rs.getString("boardtitle"));
				bdto.setUsernum(rs.getInt("usernum"));
				bdto.setAdminnum(rs.getInt("adminnum"));
				bdto.setBoardcontent(rs.getString("boardcontent"));
				bdto.setBoarddate(rs.getTimestamp("boarddate"));
				bdto.setReplequantity(rs.getInt("replequantity"));
				bdto.setHit(rs.getInt("hit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return bdto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
