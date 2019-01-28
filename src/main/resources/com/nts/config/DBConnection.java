/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Properties getProperties() {
		String propertiesPath = DBConnection.class.getResource("").getPath();
		Properties properties = new Properties();

		try (FileReader propertiesFileReader = new FileReader(propertiesPath + "db.properties")) {
			properties.load(propertiesFileReader);

		} catch (FileNotFoundException e) {
			System.out.println("해당 파일이 존재하지 않습니다.");
		} catch (IOException e) {
			System.out.println("해당파일을 읽을 수 없습니다.");
		}

		return properties;
	}

	public static Connection tryConnection() {

		Properties properties = getProperties();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("username"), properties.getProperty("password"));

		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		}

		return connection;
	}

}
