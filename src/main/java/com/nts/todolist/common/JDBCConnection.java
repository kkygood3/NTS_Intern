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

/**
 * JDBC의 Connection을 간소화시키는 class 
 * @author yongjoon.Park
 *
 */
public class JDBCConnection {

	/**
	 * Properties를 load하여 적용시키는 method
	 * @author yongjoon.Park
	 */
	public static Properties getProperties() {
		String propertiesPath = JDBCConnection.class.getResource("").getPath();

		try (FileReader fileReader = new FileReader(propertiesPath + "driver.properties");) {
			Properties properties = new Properties();
			properties.load(fileReader);

			return properties;
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : file이 존재하지 않음");
		} catch (IOException e) {
			System.out.println("IOException : file을 load할 수 없음");
		}

		return null;
	}

	/**
	 * 불러온 Properties를 driver에 저장하고
	 * connection 연결 후, 연결된 connection을 return
	 * @author yongjoon.Park
	 */
	public static Connection getConnection() {

		Properties properties = getProperties();

		Connection connection = null;
		try {
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));

		} catch (SQLException e) {
			System.out.println("SQLException : DB 접속 연결확인");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException : driver 경로 확인 필요");
		}

		return connection;
	}
}
