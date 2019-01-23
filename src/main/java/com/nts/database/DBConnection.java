package com.nts.database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection implements java.lang.AutoCloseable {

	private static Connection dbConnection;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (dbConnection == null) {
			final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user6?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
			final String DB_USER = "user6";
			final String DB_PASSWORD = "user6";
			Class.forName("com.mysql.jdbc.Driver");
			dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}
		return dbConnection;
	}

}
