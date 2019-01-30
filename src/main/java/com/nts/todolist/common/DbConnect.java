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
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DbConnect {

	/**
	 * 데이터베이스 연결에 사용되는 url, user, password, driver 정보를 저장한 properties 파일에서 정보를 가져옴
	 */
	public static Properties getProperties() {
		String propertiesPath = DbConnect.class.getResource("").getPath();
		Properties properties = null;

		try (FileReader fileReader = new FileReader(propertiesPath + "DB.properties");) {
			properties = new Properties();
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println("DbConnect.java 예외처리 1-1");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("DbConnect.java 예외처리 1-2");
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * properties 파일에서 읽어온 정보로 데이터베이스를 연결
	 */
	public static Connection getConnection() {
		Properties properties = getProperties();
		Connection connection = null;

		try {
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));

		} catch (SQLException e) {
			System.out.println("DbConnect.java 예외처리 2-1");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DbConnect.java 예외처리 2-2");
			e.printStackTrace();
		}
		return connection;
	}

}
