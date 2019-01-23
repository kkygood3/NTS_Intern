package com.nts.database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection implements java.lang.AutoCloseable {

	private static Connection dbConnection;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (dbConnection == null) {
			//dbConn이 null이면, 연결된 것이 없으니 연결해야한다.

			final String DB_URL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
			final String DB_USER = "root";
			final String DB_PASSWORD = "admin";
			Class.forName("com.mysql.jdbc.Driver");
			dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}
		return dbConnection;
	}


}
