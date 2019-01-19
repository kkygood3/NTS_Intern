/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 전연빈
 * TODO mysql driver tomcat server 폴더 lib에 넣어야됨
 */
public class DBUtil {

	private static Connection conn;
	private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final static String URI = "jdbc:mysql://localhost:3306/todo_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	private final static String USER_NAME = "root";
	private final static String PASSWORD = "1234";

	/**
	 * @desc db connection 얻어오기
	 * @return conn
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		try {
			if (conn.isClosed()) connect();
		
		} catch (NullPointerException e) {
			connect();
		}
		return conn;

	}

	/**
	 * @desc connection 연결
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		conn = DriverManager.getConnection(URI, USER_NAME, PASSWORD);
	}

}
