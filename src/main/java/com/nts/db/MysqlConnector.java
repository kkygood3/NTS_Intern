/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySql 커넥션을 맺어주는 클래스
 * @author jinwoo.bae
 */
public class MysqlConnector {
	private static String url = "jdbc:mysql://10.113.116.52:13306/user5";
	private static String id = "user5";
	private static String pw = "wlsdn123";

	/**
	 * 클래스가 로드될때 mysql.jdbc.Driver를 DriverManager에 한번만 등록 
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}

}
