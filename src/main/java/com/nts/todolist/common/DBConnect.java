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
