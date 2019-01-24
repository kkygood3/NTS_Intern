package com.nts.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * DB정보를 저장하고 Connection 객체를 생성하여 DB에 연결시킨다
 * @author 시윤
 */
public class DBConnection {

	final static private String DB_URL = "jdbc:mysql://10.113.116.52:13306/user6?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
	final static private String DB_USER = "user6";
	final static private String DB_PASSWORD = "user6";

	/*
	 * 클래스 생성시 한번만 호출
	 * DB드라이버 load
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * DB연결하는 메서드
	 * @return 생성된 Connection객체
	 */
	public static Connection getConnection() throws SQLException {
		Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return dbConnection;
	}
}
