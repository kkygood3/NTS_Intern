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

import com.nts.exception.ServerError500Exception;

/**
 * @author 전연빈
 * @desc DBCP를 이용하여 connection 관리
 */
public class DBUtil {

	private static final String DATA_SOURCE_CONTEXT = "jdbc/mysql/todo_db";
	private static final String ENV_CONTEXT = "java:comp/env";

	/**
	 * @return conn
	 * @throws SQLException 
	 * @throws ServerError500Exception 
	 */
	public static Connection getConnection() throws SQLException, ServerError500Exception {

		DataSource dataSource = null;

		try {
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup(ENV_CONTEXT);
			dataSource = (DataSource)envContext.lookup(DATA_SOURCE_CONTEXT);
		} catch (NamingException e) {
			System.err.println("webapp/META-INF/context.xml 파일  webapp/WEB-INF/web.xml 확인 해주시거나 상수를 한번 다시확인해주세요");
			e.printStackTrace();
			throw new ServerError500Exception("서버상에 문제가 있습니다. 잠시만 기다려주세요");
		}

		return dataSource.getConnection();
	}
}
