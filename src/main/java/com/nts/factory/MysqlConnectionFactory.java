/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * connection을 만드는 정보를 가지고, 커넥션을 생성해줌
 */
public class MysqlConnectionFactory {
	private static final String URL = "jdbc:mysql://10.113.116.52:13306/user10";
	private static final String USER = "user10";
	private static final String PASSWORD = "user!@10";

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}

}
