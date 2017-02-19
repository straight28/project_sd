package com.showdown.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.showdown.DBConnect.DBConnectManager;
import com.showdown.dto.MemberDto;
import com.showdown.util.BCrypt;

/*********** 회원 정보 dao **********/
public class MemberDao implements MemberDaoInterface{

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

/* 접속확인용 코드 
	public static Connection ggggetConnection() throws Exception{
		Connection conn = null;
		try {
//			 Context initContext = new InitialContext();
//			 Context envContext = (Context)initContext.lookup("java:/comp/env");  ///jdb/myoracle 이란 이름의 객체를 찾아서 dataSource가 받음
//			 DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
//			 conn = ds.getConnection(); 
			 /// getConnection은 DataSource 안쪽의 	 인터페이스
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
*/
	
	
	/**********  회원가입을 위한 메소드 "C"  **********/
	@Override
	public int InsertMember(MemberDto mDTO) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member(userid, userpass, nickname, email,managecode) values(?,?,?,?,?)";
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			/********** Bcrypt 알고리즘으로 암호화 **********/
			///hashpw(평문, 암호화키 생성(기본값은 10))
			String password = BCrypt.hashpw(mDTO.getUserpass(), BCrypt.gensalt());
			
			pstmt.setString(1, mDTO.getUserid());
			pstmt.setString(2, password);
			pstmt.setString(3, mDTO.getNickname());
			pstmt.setString(4, mDTO.getEmail());
			pstmt.setInt(5, mDTO.getManagecode());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println("InsertMember 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
	/********** 회원탈퇴   **********/
	@Override
	public void DeleteMember(int usernum) {
		String sql = "delete from member where usernum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, usernum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DeleteMember 에러");
		} finally {
			DBConnectManager.disConnect(conn, pstmt);
		}
		
	}
	
	/********** 1명의 회원정보 가져오기 **********/
	public MemberDto selectMemberByUserid(String userid){
		MemberDto mDTO = null;
		
		String sql = "select * from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
//			conn = DBConnectManager.getTestConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mDTO = new MemberDto();
				mDTO.setUsernum(rs.getInt("usernum"));
				mDTO.setUserid(rs.getString("userid"));
				mDTO.setUserpass(rs.getString("userpass"));
				mDTO.setNickname(rs.getString("nickname"));
				mDTO.setEmail(rs.getString("email"));
				mDTO.setManagecode(rs.getInt("managecode"));
				mDTO.setIndate(rs.getTimestamp("indate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectMemberByUserid 에러");
		}finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return mDTO;
	}
	


	
	/********** 로그인시 회원 검사 **********/
	public int checkID(String userid, String userpass){
		int result = -1; //-1이면 아이디가 db에 없는 상태
		String sql = "SELECT USERPASS FROM MEMBER WHERE USERID=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("유저의 아이디는"+userid);
		
		try {
			conn = DBConnectManager.getConnection();
//			conn = DBConnectManager.getTestConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()){
			String DBpassword = rs.getString(1); ///암호문
			result = 0; //  0 이면 아이디가 존재
			
			/********** checkpw (평문, 암호문) **********/
			if(BCrypt.checkpw(userpass, DBpassword)){
				result = 2;  //2이면 아이디와 비번 둘다 일치
			}else{
				result = 1;   //1이면 아이디는 맞으나 비번 불일치
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("checkID 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return result;
	}
	
	 
	/********** 회원 정보 업데이트 **********/
	@Override
	public int UpdateMember(MemberDto mDTO) {
		int result = -1;
		String sql = "update member set nickname=?, email=? where usernum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDTO.getNickname());
			pstmt.setString(2, mDTO.getEmail());
			pstmt.setInt(3,mDTO.getUsernum());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UpdateMember 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	/********** 비밀번호 업데이트 **********/
	public int UpdateMemberPass(int usernum, String userpass1) {
		int result = -1;
		String sql = "update member set userpass=? where usernum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			/********** Bcrypt 알고리즘으로 암호화 **********/
			///hashpw(평문, 암호화키 생성(기본값은 10))
			String userpass = BCrypt.hashpw(userpass1, BCrypt.gensalt());
			
			pstmt.setString(1, userpass);
			pstmt.setInt(2, usernum);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UpdateMember 에러");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		return result;
	}
	
	
}
