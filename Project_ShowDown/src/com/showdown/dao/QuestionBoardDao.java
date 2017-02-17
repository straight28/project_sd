package com.showdown.dao;
import com.showdown.dao.BoardDao;
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
import com.showdown.dto.QuestionBoardCommentDto;
import com.showdown.dto.QuestionBoardDto;


public class QuestionBoardDao implements QuestionBoardDaoInterface {
	
	private QuestionBoardDao() {}
	private static QuestionBoardDao instance = new QuestionBoardDao();
	
	public static QuestionBoardDao getInstance(){
		return instance;
	}
	/**********  조회수 올림   **********/
	public void questionBoardUpdateHit(int questboardnum, HttpSession countSession){
		String sql = "update questionboard set hit=hit+1 where questboardnum = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		///현재 열람 시간 조회(세션변수)
		long updateTime = 0;
		if(countSession.getAttribute("updatetime"+questboardnum) != null){
			updateTime = (long)countSession.getAttribute("updatetime"+questboardnum);
		}
		///현재시간
		long currentTime = System.currentTimeMillis();
		System.out.println("질문게시판 업데이트 시간"+updateTime);
		System.out.println("오늘 현재 시간"+currentTime);
		
		try {
			///현재시 - 업데이트 시간 5초 이상일시 조회수 업뎃
			if (currentTime - updateTime > 5*1000) {
				conn = DBConnectManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, questboardnum);
				pstmt.executeUpdate();
			///업데이트한 시간을 세션에 저장.setAttribute(변수명(최근열람한 게시글), 값)	
				countSession.setAttribute("updatetime"+questboardnum, currentTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("questionBoardUpdateHit 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
	}
	
	/**********  게시판 등록   **********/
	@Override
	public int InsertQuestionBoards(QuestionBoardDto QBDTO) {
		int result = 0;
		
		String sql ="insert into questionboard ( questboardnum , questboardtitle , usernum ,  questboardcontent , "
				+ " ref , re_step , re_level) values((select nvl(max(questboardnum)+1,1) from questionboard) ,?,?,?,(select nvl(max(questboardnum)+1,1) "
				+ " from questionboard),1,0)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, QBDTO.getQuestboardtitle());
			pstmt.setInt(2, QBDTO.getUsernum());
			pstmt.setString(3, QBDTO.getQuestboardcontent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InsertQuestionBoards 에러");
		}finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	/********** 전체 게시판 글 갯수   **********/
	public int countQuestionBoard(){
		int result = 0;
		
		String sql = "select count(*) from questionboard";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("countQuestionBoard 에러 ");
		}finally{
			DBConnectManager.disConnect(conn, stmt, rs);
		}
		return result;
	}
	
	/********** 검색한 게시판 글 갯수 **********/
	public int searchCountBoard(String search_option, String keyword){
		int result = 0;
		
		/*  nickname을 불러오는 sql이 달라서 안정성을 위해 sql을 분리시킴  */
		String nicksql =" select count(*) from questionboard, member where questionboard.usernum = member.usernum and nickname like '%'||?||'%'";
		String titlesql =" select count(*) from questionboard where questboardtitle like '%'||?||'%'";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			if (search_option.equals("nickname")) {
				pstmt = conn.prepareStatement(nicksql);
				
			}else if(search_option.equals("questboardtitle")){
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
	
	/*******  모든 게시판 글 가져오기 (페이징 처리 하지 않은 것)  ******
	
	public List<QuestionBoardDto> selectAllQuestionBoards(){
		
		String sql = " select questionboard.questboardnum, questionboard.questboardtitle, questionboard.usernum, "
					+" questionboard.questboardcontent, questionboard.questdate, questionboard.hit , questionboard.ref, questionboard.re_step, questionboard.re_level, " 
					+" member.nickname "
					+" from questionboard, member "
					+" where questionboard.usernum = member.usernum "
					+" order by questboardnum desc ";
		
		List<QuestionBoardDto> list = new ArrayList<QuestionBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
			
				QuestionBoardDto qbdto = new QuestionBoardDto();
				String questboardtitle = rs.getString("questboardtitle");
				questboardtitle = BoardDao.getInstance().checkArticle(questboardtitle);
				String questboardcontent = rs.getString("questboardcontent");
				questboardcontent = BoardDao.getInstance().checkArticle(questboardcontent);
				
				qbdto.setQuestboardnum(rs.getInt("questboardnum"));
				qbdto.setQuestboardtitle(questboardtitle);
				qbdto.setUsernum(rs.getInt("usernum"));
				qbdto.setQuestboardcontent(questboardcontent);
				qbdto.setQuestdate(rs.getTimestamp("questdate"));
//				qbdto.setTotalcomment(rs.getInt("totalcomment"));
				qbdto.setHit(rs.getInt("hit"));
				qbdto.setRef(rs.getInt("ref"));
				qbdto.setRe_step(rs.getInt("re_step"));
				qbdto.setRe_level(rs.getInt("re_level"));
				qbdto.setNickname(rs.getString("nickname"));  
				list.add(qbdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("questionBoard selectAllQuestionBoards 에러");
		}finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return list;
	}
	*/
	
	
	/**********  모든 게시판 글 가져오기   **********/
	
	public List<QuestionBoardDto> selectAllQuestionBoards(int pageStart, int pageEnd){
		
		String sql = "select * from  ( select rownum as rn, A.* from ( select QUESTIONBOARD.questboardnum, QUESTIONBOARD.questboardtitle, QUESTIONBOARD.usernum, "
					+" QUESTIONBOARD.questboardcontent, QUESTIONBOARD.questdate, QUESTIONBOARD.hit, QUESTIONBOARD.ref, QUESTIONBOARD.re_step, QUESTIONBOARD.re_level, "
					+" member.nickname, ( select count(*) from QUESTIONBOARD_COMMENT where questboardnum = QUESTIONBOARD.questboardnum ) totalcomment "
					+" from QUESTIONBOARD, member where QUESTIONBOARD.usernum = member.usernum "
					+" order by ref desc, re_step asc ) A ) WHERE rn between ? and ? ";
		
		List<QuestionBoardDto> list = new ArrayList<QuestionBoardDto>();
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
				
				/*  태그 방지, 공백추가, 줄바꿈   */
				QuestionBoardDto qbdto = new QuestionBoardDto();
				String questboardtitle = rs.getString("questboardtitle");
				questboardtitle = BoardDao.getInstance().checkArticle(questboardtitle);
				String questboardcontent = rs.getString("questboardcontent");
				questboardcontent = BoardDao.getInstance().checkArticle(questboardcontent);
				
				qbdto.setQuestboardnum(rs.getInt("questboardnum"));
				qbdto.setQuestboardtitle(questboardtitle);
				qbdto.setUsernum(rs.getInt("usernum"));
				qbdto.setQuestboardcontent(questboardcontent);
				qbdto.setQuestdate(rs.getTimestamp("questdate"));
				qbdto.setTotalcomment(rs.getInt("totalcomment"));
				qbdto.setHit(rs.getInt("hit"));
				qbdto.setRef(rs.getInt("ref"));
				qbdto.setRe_step(rs.getInt("re_step"));
				qbdto.setRe_level(rs.getInt("re_level"));
				qbdto.setNickname(rs.getString("nickname"));
				list.add(qbdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("questionBoard selectAllQuestionBoards 에러");
		}finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return list;
	}
	
	/********** 글 내용보기   **********/
	public QuestionBoardDto selectOneQuestionBoardByQuestionBoardNum(int questboardnum){
		String sql =" select questionboard.questboardnum, questionboard.questboardtitle, questionboard.usernum,  "
				   +" questionboard.questboardcontent,	questionboard.questdate, questionboard.HIT, questionboard.ref, "
				   +" questionboard.re_step, questionboard.re_level, member.nickname "
				   +" from questionboard, member where questionboard.usernum = member.usernum and questionboard.questboardnum=? ";  
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QuestionBoardDto qbdto = new QuestionBoardDto();
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questboardnum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				String questboardtitle = rs.getString("questboardtitle");
				questboardtitle = BoardDao.getInstance().checkArticle(questboardtitle);
				String questboardcontent = rs.getString("questboardcontent");
				questboardcontent = BoardDao.getInstance().checkArticle(questboardcontent);
				
				qbdto.setQuestboardnum(rs.getInt("questboardnum"));
				qbdto.setQuestboardtitle(questboardtitle);
				qbdto.setUsernum(rs.getInt("usernum"));
				qbdto.setQuestboardcontent(questboardcontent);
				qbdto.setQuestdate(rs.getTimestamp("questdate"));
				qbdto.setHit(rs.getInt("hit"));
				qbdto.setRef(rs.getInt("ref"));
				qbdto.setRe_step(rs.getInt("re_step"));
				qbdto.setRe_level(rs.getInt("re_level"));
				qbdto.setNickname(rs.getString("nickname"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectOneQuestionBoardByQuestionBoardNum 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return qbdto;
	}
	
	
	
	/**********  게시판 수정   **********/
	@Override
	public void ModifyQuestionBoards(QuestionBoardDto QBDTO) {
		
		String sql = "update questionboard set questboardcontent=?,usernum=? where questboardnum=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, QBDTO.getQuestboardcontent());
			pstmt.setInt(2, QBDTO.getUsernum());
			pstmt.setInt(3, QBDTO.getQuestboardnum());
			pstmt.executeUpdate();
			System.out.println("질문 게시판 수정 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ModifyQuestionBoards 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
	}

	
	
	/**********  게시판 삭제   **********/
	@Override
	public int DeleteQuestionBoards(int questboardnum) {
		System.out.println("삭제되는 질문게시판 번호는 "+questboardnum);
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			///코멘트 모두 삭제 
			String commentsql = "delete from questionboard_comment where questboardnum=?";
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(commentsql);
			pstmt.setInt(1, questboardnum);
			pstmt.executeUpdate();
			pstmt.close();
			
			
			///코멘트 모두 삭제 후 게시판 삭제
			String sql = "delete from questionboard where questboardnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questboardnum);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DeleteQuestionBoards 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
	/********** 게시물 안에 댓글 등록   **********/
	public int InsertQuestionBoardComment(QuestionBoardCommentDto qbcDTO){
		int result = 0;
		String sql = " insert into questionboard_comment (questcommentnum,questboardnum,usernum,content, ref, re_step, re_level) " 
				    +" values((select nvl(max(questcommentnum)+1,1) from questionboard_comment),?,?,?, "
				    +" (select nvl(max(questcommentnum)+1,1) from questionboard_comment),1,0)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qbcDTO.getQuestboardnum());
			pstmt.setInt(2, qbcDTO.getUsernum());
			pstmt.setString(3, qbcDTO.getContent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InsertQuestionBoardComment 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	/**********	게시글 안애 대댓글 등록    **********/
	
	public int InsertTwoQuestionBoardComment(QuestionBoardCommentDto qbcDTO){
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			
			int re_lev = qbcDTO.getRe_level()+1;
			int re_step = qbcDTO.getRe_step()+1;
			
			/*	댓글 들어가기 전에 기존 단계들을 1 증가 시켜줌     */
			
			String updateSql ="update questionboard_comment set re_step = re_step+1"
					+ " where ref=? and re_step >= ?";
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, qbcDTO.getRef());
			pstmt.setInt(2, re_step);
			pstmt.executeUpdate();
			pstmt.close();
			
			String sql = " insert into questionboard_comment (questcommentnum,questboardnum,usernum,content, ref, re_step, re_level) "
					 +" values((select nvl(max(questcommentnum)+1,1) from questionboard_comment) ,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qbcDTO.getQuestboardnum());
			pstmt.setInt(2, qbcDTO.getUsernum());
			pstmt.setString(3, qbcDTO.getContent());
			pstmt.setInt(4, qbcDTO.getRef());
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_lev);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InsertTwoQuestionBoardComment 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
	
	
	
	/********** 댓글 번호에 따른 댓글 정보를 가져옴   **********/
	public QuestionBoardCommentDto selectOneReplyInfoByQuestboardcommentnum(int questcommentnum){
		String sql = " select questionboard_comment.questcommentnum, questionboard_comment.questboardnum, questionboard_comment.usernum, "
				  	+" questionboard_comment.content, questionboard_comment.regdate, questionboard_comment.ref, "
				  	+" questionboard_comment.re_step, questionboard_comment.re_level "
				  	+" from questionboard_comment where questionboard_comment.questcommentnum=?";
		QuestionBoardCommentDto qbcdto = new QuestionBoardCommentDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questcommentnum);
			rs = pstmt.executeQuery();
			if(rs.next()){
							
				qbcdto.setQuestcommentnum(rs.getInt("questcommentnum"));
				qbcdto.setQuestboardnum(rs.getInt("questboardnum"));
				qbcdto.setUsernum(rs.getInt("usernum"));
				qbcdto.setContent(rs.getString("content"));
				qbcdto.setRegdate(rs.getTimestamp("regdate"));
				qbcdto.setRef(rs.getInt("ref"));
				qbcdto.setRe_step(rs.getInt("re_step"));
				qbcdto.setRe_level(rs.getInt("re_level"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectOneReplyInfoByQuestboardcommentnum 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return qbcdto;
	}
	
    /**********  댓글 List 보여주기   **********/
	public List<QuestionBoardCommentDto> commentList(int questboardnum){
		List<QuestionBoardCommentDto> commentList = new ArrayList<QuestionBoardCommentDto>();
		
		String sql =" select questionboard_comment.QUESTCOMMENTNUM, questionboard_comment.content, questionboard_comment.usernum, "
			       +" questionboard_comment.regdate, questionboard_comment.QUESTBOARDNUM, questionboard_comment.ref, "
			       +" questionboard_comment.re_step, questionboard_comment.re_level, "
			       +" member.nickname from questionboard_comment, member where questionboard_comment.usernum = member.usernum  "
			       +" and questboardnum = ? "
			       +" order by questionboard_comment.ref asc, questionboard_comment.re_step asc"; 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questboardnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				QuestionBoardCommentDto QBCDTO	= new QuestionBoardCommentDto();
				
				/* 태그 방지, 공백 추가, 줄바꿈  */
				String content = rs.getString("content");
				content = BoardDao.getInstance().checkArticle(content);
				
				QBCDTO.setQuestcommentnum(rs.getInt("QUESTCOMMENTNUM"));
				QBCDTO.setQuestboardnum(rs.getInt("questboardnum"));
				QBCDTO.setUsernum(rs.getInt("usernum"));
				QBCDTO.setContent(content);
				QBCDTO.setNickname(rs.getString("nickname"));
				QBCDTO.setRegdate(rs.getTimestamp("regdate"));
				QBCDTO.setRef(rs.getInt("ref"));
				QBCDTO.setRe_step(rs.getInt("re_step"));
				QBCDTO.setRe_level(rs.getInt("re_level"));
				commentList.add(QBCDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("");
		} finally{
			DBConnectManager.disConnect(conn, pstmt,rs);
		}
		return commentList;
	}
	
	/********** 댓글 삭제하기 **********/
	public int DeleteQuestionBoardReply(int questcommentnum) {
		int result=0;
		
		String sql = "delete from questionboard_comment where questcommentnum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questcommentnum);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DeleteQuestionBoardReply 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
	
	
	
	/********** 검색을 위한 dao **********/
	public List<QuestionBoardDto> searchList(String search_option, String keyword, int pageStart, int pageEnd){
		List<QuestionBoardDto> searchlist = new ArrayList<QuestionBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String nicksql = "select * "
		 			   + "   from (  "
		 			   + "   select rownum as rn, A.* "
				       + "   from (  "
				       + "   select questionboard.questboardnum, questionboard.questboardtitle, questionboard.usernum, " 
					   + "   questionboard.questboardcontent,	questionboard.questdate, questionboard.HIT, questionboard.ref, "
					   + "   questionboard.re_step, questionboard.re_level, member.nickname "
					   + " 	from questionboard, member where questionboard.usernum = member.usernum and nickname like '%'||?||'%' "
				       + "   order by ref desc, re_step asc "
				       + "   ) A "
				       + "  )    where rn between ? and ? ";
			

		String titlesql = "select * "
		 				+ "   from (  "
		 				+ "   select rownum as rn, A.* "
		 				+ "   from (  "
		 				+ "   select questionboard.questboardnum, questionboard.questboardtitle, questionboard.usernum,  " 
		 				+ "  questionboard.questboardcontent,	questionboard.questdate, questionboard.HIT, questionboard.ref, "
		 				+ "   questionboard.re_step, questionboard.re_level, member.nickname"
		 				+ " 	from questionboard, member where questionboard.usernum = member.usernum and questboardtitle like '%'||?||'%'"
		 				+ "   order by ref desc, re_step asc "
		 				+ "   ) A "
		 				+ "  )    where rn between ? and ? ";
	
		try {
			conn = DBConnectManager.getConnection();
			if (search_option.equals("nickname")) {
				pstmt = conn.prepareStatement(nicksql);
			}else if(search_option.equals("questboardtitle")){
				pstmt = conn.prepareStatement(titlesql);
			}
			pstmt.setString(1, keyword);
			pstmt.setInt(2, pageStart);
			pstmt.setInt(3, pageEnd);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				QuestionBoardDto qbdto = new QuestionBoardDto();
				
				/*  태그 방지, 공백추가 , 줄바꿈 */
				String questboardtitle = rs.getString("questboardtitle");
				questboardtitle = BoardDao.getInstance().checkArticle(questboardtitle);
				
				////검색 한내용이 곧 keyword 이므로 keyword 값을 변경해주면 검색시 값을 변경해 줄 수 있음
				questboardtitle = questboardtitle.replace(keyword, "<span style='color:#ff3d4a'>"+keyword+"<span>"); 
				String questboardcontent = rs.getString("questboardcontent");
				questboardcontent = BoardDao.getInstance().checkArticle(questboardcontent);
				String nickname = rs.getString("nickname");
				
				nickname = nickname.replace(keyword, "<span style='color:#ff3d4a'>"+keyword+"<span>");
				
				qbdto.setQuestboardnum(rs.getInt("questboardnum"));
				qbdto.setQuestboardtitle(questboardtitle);
				qbdto.setUsernum(rs.getInt("usernum"));
				qbdto.setQuestboardcontent(questboardcontent);
				qbdto.setQuestdate(rs.getTimestamp("questdate"));
				qbdto.setHit(rs.getInt("hit"));
				qbdto.setRef(rs.getInt("ref"));
				qbdto.setRe_step(rs.getInt("re_step"));
				qbdto.setRe_level(rs.getInt("re_level"));
				qbdto.setNickname(nickname);
				searchlist.add(qbdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return searchlist;
		
	}
	
	
	
	
	
	
	
	
	
}
