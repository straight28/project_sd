package com.showdown.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.showdown.DBConnect.DBConnectManager;
import com.showdown.dto.BoardCommentDto;
import com.showdown.dto.BoardDto;

public class BoardDao implements BoardDaoInterface{

	private BoardDao() {}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance(){
		return instance;
	}
	
	public String checkArticle(String article){
		/* 태그 방지 원래 insert에서 하는게 가장 좋음 */
		if(article != null){
		if(article.toLowerCase().indexOf("xmp") != -1 || article.indexOf("script")!= -1){
		}
		article = article.replace("<", "&lt");
		article = article.replace(">", "&gt");
		/* 공백 처리 */
		article = article.replace("  ", "&nbsp;&nbsp;");
		/* 줄바꿈 처리  */
		article = article.replace("\n", "<br>");
		}
		return article;
	}
	
	
	public List<BoardDto> selectAllBoards(){
//		String sql = "select * from board order by boardnum desc";   ///역 정렬
		String sql = "select board.boardnum, board.boardtitle, board.usernum, board.adminnum, " 
		        		+" board.boardcontent, board.boarddate, board.hit, "
		        		+" member.nickname,(select count(*) from board_comment "
		        		+" where boardnum=board.boardnum) totalcomment "
		        		+" from board, member where board.usernum = member.usernum "
		        		+" order by boardnum desc"  ;
		List<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null;
		Statement stmt = null;   ///이 메소드의 sql은 완성된 형태의 sql 문장임
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				BoardDto Bdto = new BoardDto();
				
				/*  태그 방지, 공백추가 , 줄바꿈 */
				String boardtitle = rs.getString("boardtitle");
				boardtitle = checkArticle(boardtitle);
				String boardcontent = rs.getString("boardcontent");
				boardcontent = checkArticle(boardcontent);
								
				Bdto.setBoardnum(rs.getInt("boardnum"));
				Bdto.setBoardtitle(boardtitle);
				Bdto.setUsernum(rs.getInt("usernum"));
				Bdto.setAdminnum(rs.getInt("adminnum"));
				Bdto.setBoardcontent(boardcontent);
				Bdto.setBoarddate(rs.getDate("boarddate"));
				Bdto.setTotalcomment(rs.getInt("totalcomment"));
				Bdto.setHit(rs.getInt("hit"));
				Bdto.setNickname(rs.getString("nickname"));
				list.add(Bdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllBoards 에러");
		}finally{
			DBConnectManager.disConnect(conn, stmt, rs);
		}
		return list;
	}
	
	
	/* 게시글 등록하기// boardnum에 seq 생성여부 확인 */
	@Override
	public int InsertBoards(BoardDto bDTO) {
		int result = 0; 
		
		String sql ="insert into board(boardnum,boardtitle, usernum, boardcontent, board_re_group, board_re_lev, board_re_seq) "
				+"values((select nvl(max(boardnum)+1,1) from board),?,?,?,(select nvl(max(boardnum)+1,1) from board),?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDTO.getBoardtitle());
			pstmt.setInt(2, bDTO.getUsernum());
			pstmt.setString(3, bDTO.getBoardcontent());
			pstmt.setInt(4, bDTO.getBoard_re_lev());
			pstmt.setInt(5, bDTO.getBoard_re_seq());
			result = pstmt.executeUpdate(); // 등록되면 0이 아님.

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertBoard 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
	/* 조회수 올리는 sql */
	public void updateHit(int boardnum, HttpSession count_session){
		String sql = "update board set hit=hit+1 where boardnum = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//현재 열람 시간 조회(세션 변수)
		long update_time = 0;
		if (count_session.getAttribute("update_time_"+boardnum)!=null) {
			update_time = (long)count_session.getAttribute("update_time_"+boardnum);
		}
		//현재 시간
		long current_time = System.currentTimeMillis();
		System.out.println(update_time);
		System.out.println(current_time);
		try {
			///현재시간 - 업데이트 시간이 5초이상일 시 조회수 업데이트 
			if (current_time - update_time > 5*1000) {
				conn = DBConnectManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardnum);
				pstmt.executeUpdate();
				///업데이트한 시간을 세션에 저장
			count_session.setAttribute("update_time_"+boardnum, current_time);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateHit 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
	}
	
	
	/* 글 내용보기 */
	public BoardDto selectOneBoardByBoardNum(int boardnum){
		String sql = "select board.BOARDNUM, board.boardtitle, board.usernum, board.adminnum," 
				     + "board.boardcontent,	board.boarddate, board.HIT, member.nickname "
				     + " from BOARD, member where board.usernum = member.usernum and BOARD.BOARDNUM=?";
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
				
				/*  태그 방지, 공백추가 , 줄바꿈 */
				String boardtitle = rs.getString("boardtitle");
				boardtitle = checkArticle(boardtitle);
				String boardcontent = rs.getString("boardcontent");
				boardcontent = checkArticle(boardcontent);
				
				bdto.setBoardnum(rs.getInt("boardnum"));
				bdto.setBoardtitle(boardtitle);
				bdto.setUsernum(rs.getInt("usernum"));
				bdto.setAdminnum(rs.getInt("adminnum"));
				bdto.setBoardcontent(boardcontent);
				bdto.setBoarddate(rs.getDate("boarddate"));
//				bdto.setTotalcomment(rs.getInt("totalcomment"));
				bdto.setHit(rs.getInt("hit"));
				bdto.setNickname(rs.getString("nickname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectOneBoardByBoardNum 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return bdto;
	}
	
	
	/* 글 삭제하기 */
	@Override
	public int DeleteBoards(int boardnum) {
		System.out.println("삭제되는 게시판 숫자는?"+boardnum);
		int result=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			////코멘트 모두 삭제후
			String commentsql = "delete from board_comment where boardnum=?";
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(commentsql);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
			pstmt.close();
			
			////코멘트 모두 삭제후 게시판 삭제
			String sql = "delete from board where boardnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			result = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteBoard 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	/* 글 수정하기 */
	@Override
	public void ModifyBoards(BoardDto bDTO) {
		
		String sql = "update board set boardcontent=?,usernum=? where boardnum=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDTO.getBoardcontent());
			pstmt.setInt(2, bDTO.getUsernum());
			pstmt.setInt(3, bDTO.getBoardnum());
			pstmt.executeUpdate();
			System.out.println("업데이트 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateBoard 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
	}
	
	
	
	/* 댓글 목록 보여주기 */
	public List<BoardCommentDto> commentList(int boardnum){
		List<BoardCommentDto> commentlist = new ArrayList<BoardCommentDto>();
		String sql = "select board_comment.commentnum, board_comment.content, board_comment.usernum, "
            		+" board_comment.regdate, board_comment.boardnum,  member.nickname "
            		+" from board_comment, member where board_comment.usernum = member.usernum "
            		+" and boardnum = ? "
					+" order by board_comment.commentnum asc ";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardCommentDto bcDto = new BoardCommentDto();
				
				/*  태그 방지, 공백추가 , 줄바꿈 */
				String content = rs.getString("content");
				content = checkArticle(content);
								
				bcDto.setCommentnum(rs.getInt("commentnum"));
				bcDto.setBoardnum(rs.getInt("boardnum"));
				bcDto.setUsernum(rs.getInt("usernum"));
				bcDto.setContent(content);
				bcDto.setRegdate(rs.getDate("regdate"));
				bcDto.setNickname(rs.getString("nickname"));
				commentlist.add(bcDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return commentlist;
	}
	
	
	/* 게시글 안에 댓글 등록 */
	public int InsertBoardComment(BoardCommentDto bcdto) {
		int result = 0; 
		
		String sql ="insert into board_comment (commentnum,boardnum,usernum,content) "
				+" values((select nvl(max(commentnum)+1,1) from board_comment) ,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bcdto.getBoardnum() );
			pstmt.setInt(2, bcdto.getUsernum());
			pstmt.setString(3, bcdto.getContent());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InsertBoardComment 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	} 
	
	
	/* 게시판아래에 답글달기 */
	public int InsertBoardReply(BoardDto bDTO) {
		int result = 0; 
		
		String sql ="insert into board(boardnum,boardtitle, usernum, boardcontent, board_re_group, board_re_lev, board_re_seq) "
				+"values((select nvl(max(boardnum)+1,1) from board),?,?,?,(select nvl(max(boardnum)+1,1) from board),?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			
			int ref = bDTO.getBoard_re_group();
			int re_step = bDTO.getBoard_re_seq()+1;
			int re_lev = bDTO.getBoard_re_lev()+1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDTO.getBoardtitle());
			pstmt.setInt(2, bDTO.getUsernum());
			pstmt.setString(3, bDTO.getBoardcontent());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_lev);
			result = pstmt.executeUpdate(); // 등록되면 0이 아님.
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InsertBoardReply 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	/* 댓글 삭제하기 */
	public int DeleteBoardReply(int commentnum) {
		
		int result=0;
		
		String sql = "delete from BOARD_COMMENT where commentnum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentnum);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DeleteBoardReply 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
}