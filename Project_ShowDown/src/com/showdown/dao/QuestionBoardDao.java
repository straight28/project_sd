package com.showdown.dao;
import com.showdown.dao.BoardDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.showdown.DBConnect.DBConnectManager;
import com.showdown.dto.QuestionBoardDto;


public class QuestionBoardDao implements QuestionBoardDaoInterface {
	
	private QuestionBoardDao() {}
	private static QuestionBoardDao instance = new QuestionBoardDao();
	
	public static QuestionBoardDao getInstance(){
		return instance;
	}
	
	
	/*******  게시판 등록   *******/
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
			pstmt.setString(3, QBDTO.getQuestboardtitle());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InsertQuestionBoards 에러");
		}finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	/********** 전체 게시판 글 갯수 **********/
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
	
	
	/*******  모든 게시판 글 가져오기   *******/
	
	public List<QuestionBoardDto> selectAllQuestionBoards(int pageStart, int pageEnd){
		
		String sql = "select * from  ( select rownum as rn, A.* from ( select questionboard.questboardnum, questionboard.questboardtitle, QUESTIONBOARD.usernum, "
					+" QUESTIONBOARD.questboardcontent, QUESTIONBOARD.questdate, QUESTIONBOARD.hit, QUESTIONBOARD.ref, QUESTIONBOARD.re_step, QUESTIONBOARD.re_level, "
					+" member.nickname, ( select count(*) from quetionboard_comment where questboardnum = QUESTIONBOARD.questboardnum ) totalcomment "
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
	
	
	
	
	
	
	/*******  게시판 수정   *******/
	@Override
	public void ModifyQuestionBoards(QuestionBoardDto QBDTO) {
		// TODO Auto-generated method stub

	}
	
	/*******  게시판 삭제   *******/
	@Override
	public int DeleteQuestionBoards(int boardnum) {
		// TODO Auto-generated method stub
		return 0;
	}
}
