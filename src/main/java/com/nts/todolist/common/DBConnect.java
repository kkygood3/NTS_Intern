/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DBConnect {

	/**
	 * 데이터베이스 연결에 사용되는 url, user, password, driver 정보를 저장한 properties 파일에서 정보를 가져옵니다.
	 */
	public static Properties getProperties() {
		String propertiesPath = DBConnect.class.getResource("").getPath();
		Properties properties = null;

		try (FileReader fileReader = new FileReader(propertiesPath + "DB.properties");) {
			properties = new Properties();
			properties.load(fileReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * properties 파일에서 읽어온 정보로 데이터베이스를 연결합니다.
	 */
	public static Connection getConnection() {
		Properties properties = getProperties();
		Connection connection = null;

		try {
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

}
