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
public abstract class JdbcConnection {
	private static Properties properties = null;

	private static final String PROPERTIES_NAME = "jdbc.properties";
	private static final String DRIVER = "driver";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String PASSWORD = "password";

	/**
	 * DB 구동을 위한 기본적인 setting을 구성
	 * @author yongjoon.Park
	 * @throws DatabaseAccessException
	 */
	public static Properties getDatabaseSetting() throws DatabaseAccessException {

		String propertiesPath = JdbcConnection.class.getResource("/" + PROPERTIES_NAME).getFile();

		try (FileReader fileReader = new FileReader(propertiesPath);) {
			properties = new Properties();
			properties.load(fileReader);

			Class.forName(properties.getProperty(DRIVER));
		} catch (FileNotFoundException e) {
			throw new DatabaseAccessException("Not Found Properties. Please contact administrator.", e);
		} catch (IOException e) {
			throw new DatabaseAccessException("Cannot Read Properties. Please contact administrator.", e);
		} catch (ClassNotFoundException e) {
			throw new DatabaseAccessException("Not Found Driver Class. Please contact administrator.", e);
		}

		return properties;
	}

	/**
	 * 불러온 Properties를 가져와 connection 연결 후,
	 * 연결된 connection을 return
	 * @author yongjoon.Park
	 * @throws DatabaseAccessException
	 */
	public static Connection getConnection() throws DatabaseAccessException {
		if (properties == null) {
			properties = getDatabaseSetting();
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty(URL),
				properties.getProperty(USER), properties.getProperty(PASSWORD));

		} catch (SQLException e) {
			throw new DatabaseAccessException("Database Access Error", e);
		}

		return connection;
	}
}
