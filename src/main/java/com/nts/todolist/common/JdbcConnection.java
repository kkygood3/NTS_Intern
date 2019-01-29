/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.nts.todolist.exception.DatabaseAccessException;

/**
 * JDBC의 Connection을 간소화시키는 class 
 * @author yongjoon.Park
 *
 */
public class JdbcConnection {
	private static Properties PROPERTIES = null;

	private static String PROPERTIES_NAME = "jdbc.properties";
	private static String DRIVER = "driver";
	private static String URL = "url";
	private static String USER = "user";
	private static String PASSWORD = "password";

	/**
	 * DB 구동을 위한 기본적인 setting을 구성
	 * @author yongjoon.Park
	 * @throws DatabaseAccessException
	 */
	public static Properties getDatabaseSetting() throws DatabaseAccessException {

		String propertiesPath = JdbcConnection.class.getResource("").getPath();

		try (FileReader fileReader = new FileReader(propertiesPath + PROPERTIES_NAME);) {
			PROPERTIES = new Properties();
			PROPERTIES.load(fileReader);

			Class.forName(PROPERTIES.getProperty(DRIVER));
		} catch (FileNotFoundException e) {
			throw new DatabaseAccessException("Not Found Properties. Please contact administrator.", e);
		} catch (IOException e) {
			throw new DatabaseAccessException("Cannot Read Properties. Please contact administrator.", e);
		} catch (ClassNotFoundException e) {
			throw new DatabaseAccessException("Not Found Driver Class. Please contact administrator.", e);
		}

		return PROPERTIES;
	}

	/**
	 * 불러온 Properties를 가져와 connection 연결 후,
	 * 연결된 connection을 return
	 * @author yongjoon.Park
	 * @throws DatabaseAccessException
	 */
	public static Connection getConnection() throws DatabaseAccessException {

		if (PROPERTIES == null) {
			PROPERTIES = getDatabaseSetting();
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(PROPERTIES.getProperty(URL),
				PROPERTIES.getProperty(USER), PROPERTIES.getProperty(PASSWORD));

		} catch (SQLException e) {
			throw new DatabaseAccessException("Database Access Error", e);
		}

		return connection;
	}
}
