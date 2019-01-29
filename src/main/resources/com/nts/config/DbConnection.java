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

import com.nts.exception.ServerErrorException;

/**
 * Properties 메소드와 tryConnection 메소드를 위한 DbConnection 클래스입니다.
 * @author kkygood3
 */
public class DbConnection {

	public static final Properties PROPERTIESE = getProperties();

	private static Properties getProperties() {
		Properties properties = new Properties();
		String propertiesPath = DbConnection.class.getResource("").getPath();

		try (FileReader propertiesFileReader = new FileReader(propertiesPath + "db.properties")) {
			properties.load(propertiesFileReader);

		} catch (FileNotFoundException e) {
			System.out.println("해당 경로에 db.properties 파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("db.properties을 읽을 수 없습니다.");
			e.printStackTrace();
		}

		return properties;
	}

	public static Connection tryConnection() throws ServerErrorException {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(PROPERTIESE.getProperty("url"),
				PROPERTIESE.getProperty("username"), PROPERTIESE.getProperty("password"));

		} catch (SQLException e) {
			System.out.printf("DB 연결 실패 입력 정보 \n url : %s, username : %s, password : %s\n",
				PROPERTIESE.getProperty("url"), PROPERTIESE.getProperty("username"),
				PROPERTIESE.getProperty("password"));
			throw new ServerErrorException(e);
		}

		return connection;
	}

}
