/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnectionFactory {

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/todo";
		String user = "root";
		String password = "root!@#123"; // TODO db 사용자 생성해서 하기
		Connection connection = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
