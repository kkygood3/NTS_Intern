/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.jdbc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.nts.jdbc.dto.TodoDto;

/**
 * @author 육성렬
 */
public class TodoDao {
	private static final int DB_VALID_TIMEOUT = 3000;
	private Connection dbConnection = null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

	private static TodoDao instance;

	private TodoDao() {}

	public static synchronized TodoDao getInstance() {
		if (instance == null) {
			instance = new TodoDao();
		}
		return instance;
	}

	public void connectDatabase() throws SQLException, ClassNotFoundException {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
		Properties dbProperties = new Properties();
		try {
			dbProperties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String dbUrl = dbProperties.getProperty("db.url");
		String dbUsername = dbProperties.getProperty("db.username");
		String dbPassword = dbProperties.getProperty("db.password");

		Class.forName("com.mysql.cj.jdbc.Driver");
		dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	}

	public void disconnectDatabase() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addTodo(TodoDto todoDto) throws SQLException, ClassNotFoundException {

		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?);";
		PreparedStatement preparedStatement = null;

		if (!dbConnection.isValid(DB_VALID_TIMEOUT)) {
			connectDatabase();
		}

		preparedStatement = dbConnection.prepareStatement(sql);
		preparedStatement.setString(1, todoDto.getTitle());
		preparedStatement.setString(2, todoDto.getName());
		preparedStatement.setInt(3, todoDto.getSequence());
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
		return result;
	}

	public List<TodoDto> getTodos() throws SQLException, ClassNotFoundException {

		List<TodoDto> result = new ArrayList<>();
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY regdate";
		PreparedStatement preparedStatement = null;

		if (!dbConnection.isValid(DB_VALID_TIMEOUT)) {
			connectDatabase();
		}

		preparedStatement = dbConnection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			String name = resultSet.getString("name");
			int sequence = resultSet.getInt("sequence");
			String type = resultSet.getString("type");
			Date regDate = resultSet.getDate("regdate");
			TodoDto todoDto = new TodoDto.Builder()
				.id(id)
				.title(title)
				.name(name)
				.sequence(sequence)
				.type(type)
				.regDate(dateFormat.format(regDate))
				.build();
			result.add(todoDto);
		}
		preparedStatement.close();
		return result;
	}

	public int updateTodo(TodoDto todoDto) throws SQLException, ClassNotFoundException {

		String sql = "UPDATE todo SET type = ? WHERE id = ?;";
		PreparedStatement preparedStatement = null;

		if (!dbConnection.isValid(DB_VALID_TIMEOUT)) {
			connectDatabase();
		}
		preparedStatement = dbConnection.prepareStatement(sql);
		preparedStatement.setString(1, todoDto.getType());
		preparedStatement.setInt(2, todoDto.getId());
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();
		return result;
	}
}
