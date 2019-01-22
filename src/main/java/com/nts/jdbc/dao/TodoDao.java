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

public class TodoDao {
	private Connection dbConnection = null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

	public TodoDao() {

		init();
	}

	private void init() {

		connectDatabase();
	}

	private void connectDatabase() {

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

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addTodo(TodoDto todoDto) throws SQLException {

		String sql = "insert into todo(title, name, sequence) values(?, ?, ?);";
		PreparedStatement preparedStatement = null;

		if (dbConnection == null || !dbConnection.isValid(3000)) {
			connectDatabase();
		}

		preparedStatement = dbConnection.prepareStatement(sql);
		preparedStatement.setString(1, todoDto.getTitle());
		preparedStatement.setString(2, todoDto.getName());
		preparedStatement.setInt(3, todoDto.getSequence());
		int result = preparedStatement.executeUpdate();
		return result;
	}

	public List<TodoDto> getTodos() throws SQLException {

		List<TodoDto> result = new ArrayList<>();
		String sql = "select id, title, name, sequence, type, regdate from todo order by regdate";
		PreparedStatement preparedStatement = null;

		if (dbConnection == null || !dbConnection.isValid(3000)) {
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
			Date regdate = resultSet.getDate("regdate");
			result.add(new TodoDto(id, title, name, sequence, type, dateFormat.format(regdate)));
		}
		return result;
	}

	public void updateTodo(TodoDto todoDto) throws SQLException {

		String sql = "update todo set type = ? where id = ? and type = ?;";
		PreparedStatement preparedStatement = null;

		if (dbConnection == null || !dbConnection.isValid(3000)) {
			connectDatabase();
		}

		preparedStatement = dbConnection.prepareStatement(sql);
		preparedStatement.setString(1, todoDto.getType());
		preparedStatement.setInt(2, todoDto.getId());
		preparedStatement.setString(3, todoDto.getType());

		preparedStatement.executeUpdate();
	}
}
