package com.showdwon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.showdwon.DBConnect.DBConnectManager;
import com.showdwon.DTO.MemberDTO;

/* 회원 정보 dao */
public class MemberDAO implements MemberDAO_Inter{

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
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
	
	/*  회원가입을 위한 메소드 "C"  */
	@Override
	public int InsertMember(MemberDTO mDTO) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member(userid, userpass, nickname, email, admin) values(?,?,?,?,?)";
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mDTO.getUserid());
			pstmt.setString(2, mDTO.getUserpass());
			pstmt.setString(3, mDTO.getNickname());
			pstmt.setString(4, mDTO.getEmail());
			pstmt.setInt(5, mDTO.getAdmin());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 가입  실패");
		} finally{
			DBConnectManager.disConnect(conn, pstmt);
		}
		
		return result;
	}
	
	
	/* 회원탈퇴   */
	@Override
	public void DeleteMember(String userid) {
		String sql = "delete from member where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectManager.disConnect(conn, pstmt);
		}
		
	}
	
	/* 1명의 회원정보 가져오기  */
	public MemberDTO selectMemberByUserid(String userid){
		MemberDTO mDTO = null;
		
		String sql = "select * from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mDTO = new MemberDTO();
				mDTO.setUserid(rs.getString("userid"));
				mDTO.setUserpass(rs.getString("userpass"));
				mDTO.setNickname(rs.getString("nickname"));
				mDTO.setEmail(rs.getString("email"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return mDTO;
	}
	
	/* 로그인시 회원 검사 */
	public int checkID(String userid, String userpass){
		int result = -1; //-1이면 아이디가 db에 없는 상태
		
		String sql = "select userpass from member where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnectManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 0; //  0 이면 아이디가 존재
				String DBpassword = rs.getString(1);
				if (DBpassword.equals(userpass)) {
					result = 2;  //2이면 아이디와 비번 둘다 일치
				}else{
					result = 1;   //1이면 아이디는 맞으나 비번 불일치
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBConnectManager.disConnect(conn, pstmt, rs);
		}
		return result;
	}
	
	 
	/* 회원 정보 업데이트 */
	@Override
	public int UpdateMember() {
		return 0;
	}
	
	
	
	
	
	
}
