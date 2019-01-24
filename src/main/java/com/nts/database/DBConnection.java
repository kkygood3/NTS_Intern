package com.nts.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	final static private String DB_URL = "jdbc:mysql://10.113.116.52:13306/user6?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
	final static private String DB_USER = "user6";
	final static private String DB_PASSWORD = "user6";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return dbConnection;
	}
}
