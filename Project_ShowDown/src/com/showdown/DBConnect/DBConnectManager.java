package com.showdown.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/* 데이터 베이스 접속 관리 */
public class DBConnectManager {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			 // 아래처럼 간소하게 줄일 수 있음 
			  Context initContext = new InitialContext();
			  Context envContext = (Context)initContext.lookup("java:/comp/env"); 
			  ///jdb/myoracle 이란   이름의 객체를 찾아서 dataSource가 받음 
			  DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle"); 
			  conn = ds.getConnection();
			  /// getConnection은 DataSource 안쪽의 인터페이스

			/*Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// public 메소드는 어디서나 이 메소드가 호출 될 수 있다는 의미
	// static 메소드는 지정된 메모리에 상주한다는 의미
	public static void disConnect(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}

	public static void disConnect(Connection conn, Statement stmt) {

		if (stmt != null) {
			try {stmt.close();
			} catch (SQLException e){}
		}
		if (conn != null) {
		
			try { 
				conn.close();
			}catch (SQLException e){}
		}
	}
	
	
/*	// test를 위한 Connection 가져오기..
	public static Connection getTestConnection(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "show", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}*/
}
