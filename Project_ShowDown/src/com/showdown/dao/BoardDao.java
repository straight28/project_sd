package com.showdown.dao;

import java.sql.Connection;
import java.sql.Date;
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
	
	/********** 태그 방지 (원래 insert에서 하는게 가장 좋음) **********/
	public String checkArticle(String article){
		if(article != null){
		/* whiteList 방식을 쓴다면 사용하지만 지금은 사용하지 않음
		 * if(article.toLowerCase().indexOf("xmp") != -1 || article.indexOf("script")!= -1){
		}
		*/
		article = article.replace("<", "&lt");
		article = article.replace(">", "&gt");
		/* 공백 처리 */
		article = article.replace("  ", "&nbsp;&nbsp;");
		/* 줄바꿈 처리  */
		article = article.replace("\n", "<br>");
		}
		return article;
	}
		
	/********** 전체 게시판 글 갯수 **********/
	public int countBoard(){
		int result = 0;
		
		String sql = "select count(*) from board";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnectManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnectManager.disConnect(conn, stmt, rs);
		}
		return result;
	}
	
	
	
	/********** 검색한 게시판 글 갯수 **********/
	public int searchCountBoard(String search_option, String keyword){
		int result = 0;
		
		/*  nickname을 불러오는 sql이 달라서 안정성을 위해 sql을 분리시킴  */
		String nicksql =" select count(*) from BOARD, member where board.usernum = member.usernum and nickname like '%'||?||'%'";
		String titlesql =" select count(*) from board where boardtitle like '%'||?||'%'";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			if (search_option.equals("nickname")) {
				pstmt = conn.prepareStatement(nicksql);
				
			}else if(search_option.equals("boardtitle")){
				pstmt = conn.prepareStatement(titlesql);
			}	
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return result;
	}
	
	
	/********** 모든 게시판 글을 가져옴  **********/
	public List<BoardDto> selectAllBoards(int pageStart, int pageEnd){
		String sql = "select * "
				 		+ "   from (  "
						+ "   select rownum as rn, A.* "
						+ "   from (  "
						      + "   select board.boardnum, board.boardtitle, board.usernum, board.adminnum,  " 
							  + "   board.boardcontent, board.boarddate, board.hit,  "
							  + "   board.ref, board.re_step, board.re_level, "
							  + "   member.nickname,(select count(*) from board_comment  "
							  + "   where boardnum=board.boardnum) totalcomment  "
							  + "   from board, member where board.usernum = member.usernum  "
						      + "   order by ref desc, re_step asc "
						      + "   ) A "
						 + "  )    where rn between ? and ? ";
		List<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;   
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageStart);
			pstmt.setInt(2, pageEnd);
			rs = pstmt.executeQuery();
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
				Bdto.setBoarddate(rs.getTimestamp("boarddate"));
				Bdto.setTotalcomment(rs.getInt("totalcomment"));
				Bdto.setHit(rs.getInt("hit"));
				Bdto.setRef(rs.getInt("ref"));
				Bdto.setRe_step(rs.getInt("re_step"));
				Bdto.setRe_level(rs.getInt("re_level"));
				Bdto.setNickname(rs.getString("nickname"));
				list.add(Bdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllBoards 에러");
		}finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return list;
	}
	
	
	/********** 게시글 등록  **********/
	@Override
	public int InsertBoards(BoardDto bDTO) {
		int result = 0; 
		
		String sql ="insert into board(boardnum,boardtitle, usernum, boardcontent, ref, re_step, re_level) "
				+"values((select nvl(max(boardnum)+1,1) from board),?,?,?,(select nvl(max(boardnum)+1,1) from board),1,0)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDTO.getBoardtitle());
			pstmt.setInt(2, bDTO.getUsernum());
			pstmt.setString(3, bDTO.getBoardcontent());
			result = pstmt.executeUpdate(); // 등록되면 0이 아님.

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertBoard 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
	/********** 조회수 올리는 sql **********/
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
		System.out.println("업데이트 시간 "+update_time);
		System.out.println("현재 시간 "+current_time);
		try {
			///현재시간 - 업데이트 시간이 5초이상일 시 조회수 업데이트 
			if (current_time - update_time > 5*1000) {
				conn = DBConnectManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardnum);
				pstmt.executeUpdate();
				///업데이트한 시간을 세션에 저장 setArrtibute(변수명(최근열람한게시글), 값) 
			count_session.setAttribute("update_time_"+boardnum, current_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateHit 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
	}
	
	
	/********** 글 내용보기 **********/
	public BoardDto selectOneBoardByBoardNum(int boardnum){
		String sql = "select board.BOARDNUM, board.boardtitle, board.usernum, board.adminnum," 
				     + "board.boardcontent,	board.boarddate, board.HIT, board.ref, "
				     + "board.re_step, board.re_level, member.nickname "
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
				bdto.setBoarddate(rs.getTimestamp("boarddate"));
				bdto.setHit(rs.getInt("hit"));
				bdto.setRef(rs.getInt("ref"));
				bdto.setRe_step(rs.getInt("re_step"));
				bdto.setRe_level(rs.getInt("re_level"));
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
	
	
	/********** 글 삭제하기 **********/
	@Override
	public int DeleteBoards(int boardnum) {
		System.out.println("삭제되는 게시판 숫자는?"+boardnum);
		int result=0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			////코멘트 모두 삭제
			String commentsql = "delete from board_comment where boardnum=?";
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(commentsql);
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
			pstmt.close();
			
			////코멘트 모두 삭제 후 게시판 삭제
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
	
	/********** 글 수정하기 **********/
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
	
	
	
	/*********** 댓글 list 보여주기 **********/
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
				bcDto.setRegdate(rs.getTimestamp("regdate"));
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
	
	
	/********** 게시글 안에 댓글 등록 **********/
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
	
	
	/********** 게시판 아래에 답글달기 **********/
	public int InsertBoardReply(BoardDto bDTO) {
		int result = 0; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			
			int re_lev = bDTO.getRe_level()+1; //답글의 레벨을 올려줌
			int re_step = bDTO.getRe_step()+1; //답글의 단계를 올려줌
			
			/* 답글 들어가기 전에 기존 단계들을 1 증가시켜줌 */
			String updateSql = "update board set re_step = re_step+1"
								+ " where ref=? and re_step >= ?";
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, bDTO.getRef());
			pstmt.setInt(2, re_step);
			pstmt.executeUpdate();
			pstmt.close();
			
			/* 기존 단계 증가후 새로운 답글 단계 입력 */
			String sql ="insert into board(boardnum,boardtitle, usernum, boardcontent, ref, re_step, re_level) "
					+"values((select nvl(max(boardnum)+1,1) from board),?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDTO.getBoardtitle());
			pstmt.setInt(2, bDTO.getUsernum());
			pstmt.setString(3, bDTO.getBoardcontent());
			pstmt.setInt(4, bDTO.getRef());
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
	
	/********** 댓글 삭제하기 **********/
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
	
	
	/********** 검색을 위한 dao **********/
	public List<BoardDto> searchList(String search_option, String keyword, int pageStart, int pageEnd){
		List<BoardDto> searchlist = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		/* 검색 후 검색 결과에 대해서도 페이징 처리를 하기 위해 sql 수정 */
		
		/*String nicksql = "select board.BOARDNUM, board.boardtitle, board.usernum, board.adminnum, "
							+" board.boardcontent,	board.boarddate, board.HIT, board.ref, "
							+" board.re_step, board.re_level, member.nickname "
							+" from BOARD, member where board.usernum = member.usernum and nickname like '%'||?||'%'"
							+" order by ref desc, re_step asc";*/
		
		String nicksql = "select * "
		 		+ "   from (  "
				+ "   select rownum as rn, A.* "
				+ "   from (  "
				      + "   select board.BOARDNUM, board.boardtitle, board.usernum, board.adminnum,  " 
					  + "   board.boardcontent,	board.boarddate, board.HIT, board.ref, "
					  + "   board.re_step, board.re_level, member.nickname "
					  + " 	from BOARD, member where board.usernum = member.usernum and nickname like '%'||?||'%' "
				      + "   order by ref desc, re_step asc "
				      + "   ) A "
				 + "  )    where rn between ? and ? ";
		
		/*
		String titlesql = "select board.BOARDNUM, board.boardtitle, board.usernum, board.adminnum, "
				+" board.boardcontent,	board.boarddate, board.HIT, board.ref, "
				+" board.re_step, board.re_level, member.nickname "
				+" from BOARD, member where board.usernum = member.usernum and boardtitle like '%'||?||'%'"
				+" order by ref desc, re_step asc";*/
		

		String titlesql = "select * "
		 		+ "   from (  "
				+ "   select rownum as rn, A.* "
				+ "   from (  "
				      + "   select board.BOARDNUM, board.boardtitle, board.usernum, board.adminnum, " 
					  + "  board.boardcontent,	board.boarddate, board.HIT, board.ref, "
					  + "   board.re_step, board.re_level, member.nickname"
					  + " 	from BOARD, member where board.usernum = member.usernum and boardtitle like '%'||?||'%'"
				      + "   order by ref desc, re_step asc "
				      + "   ) A "
				 + "  )    where rn between ? and ? ";
		
		/********** 중복을 줄이기 위해 코드로 ? ? ? ? 4개주고 preparedStatement했으나 검색 되지 않음
		String sql = "select * "
		 		+ "   from (  "
				+ "   select rownum as rn, A.* "
				+ "   from (  "
				      + "   select board.BOARDNUM, board.boardtitle, board.usernum, board.adminnum, " 
					  + "  board.boardcontent,	board.boarddate, board.HIT, board.ref, "
					  + "   board.re_step, board.re_level, member.nickname"
					  + " 	from BOARD, member where board.usernum = member.usernum and ? like '%'||?||'%'"
				      + "   order by ref desc, re_step asc "
				      + "   ) A "
				 + "  )    where rn between ? and ? "; */
		
		try {
			conn = DBConnectManager.getConnection();
			if (search_option.equals("nickname")) {
				pstmt = conn.prepareStatement(nicksql);
			}else if(search_option.equals("boardtitle")){
				pstmt = conn.prepareStatement(titlesql);
			}
			pstmt.setString(1, keyword);
			pstmt.setInt(2, pageStart);
			pstmt.setInt(3, pageEnd);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardDto bdto = new BoardDto();
				
				/*  태그 방지, 공백추가 , 줄바꿈 */
				String boardtitle = rs.getString("boardtitle");
				boardtitle = checkArticle(boardtitle);
				////검색 한내용이 곧 keyword 이므로 keyword 값을 변경해주면 검색시 값을 변경해 줄 수 있음
				boardtitle = boardtitle.replace(keyword, "<span style='color:#ff3d4a'>"+keyword+"<span>"); 
				String boardcontent = rs.getString("boardcontent");
				boardcontent = checkArticle(boardcontent);
				String nickname = rs.getString("nickname");
				nickname = nickname.replace(keyword, "<span style='color:#ff3d4a'>"+keyword+"<span>");
				
				bdto.setBoardnum(rs.getInt("boardnum"));
				bdto.setBoardtitle(boardtitle);
				bdto.setUsernum(rs.getInt("usernum"));
				bdto.setAdminnum(rs.getInt("adminnum"));
				bdto.setBoardcontent(boardcontent);
				bdto.setBoarddate(rs.getTimestamp("boarddate"));
				bdto.setHit(rs.getInt("hit"));
				bdto.setRef(rs.getInt("ref"));
				bdto.setRe_step(rs.getInt("re_step"));
				bdto.setRe_level(rs.getInt("re_level"));
				bdto.setNickname(nickname);
				searchlist.add(bdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return searchlist;
		
	}
	
	
	
	
	
}