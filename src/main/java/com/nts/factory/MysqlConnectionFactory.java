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
		String url = "jdbc:mysql://10.113.116.52:13306/user10";
		String user = "user10";
		String password = "user!@10";
		Connection connection = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class Load error"); // log 파일에 남기도록 변경해야함.
		}
		return connection;
	}
}
