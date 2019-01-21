/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author 전연빈
 * @desc DBCP를 이용하여 connection 관리
 */
public class DBUtil {

	/**
	 * @desc db connection 얻어오기
	 * @return conn
	 * @throws SQLException
	 * @throws NamingException
	 */
	public static Connection getConnection() throws SQLException {

		DataSource dataSource = null;
		
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/mysql/todo_db");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataSource.getConnection();
	}
}
