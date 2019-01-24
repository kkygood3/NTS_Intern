package com.nts.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection 정보를 가진 static class
 * @author 박우성
 */
public class MysqlConnector {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(MysqlAccount.DBURL, MysqlAccount.DBUSER, MysqlAccount.DBPASSWD);
		return connection;
	}
}
