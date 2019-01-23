package com.nts.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection 정보를 가진 static class
 * @author 박우성
 */
public class MysqlConnector {
	private static Connection connection = null;

	private MysqlConnector() {}

	/**
	 * @return Mysql Connection
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(MysqlAccount.DBURL, MysqlAccount.DBUSER, MysqlAccount.DBPASSWD);
		}
		return connection;
	}

	@Override
	protected void finalize() throws Throwable {
		connection.close();
	}
}
