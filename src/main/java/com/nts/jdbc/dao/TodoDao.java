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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.nts.TodoTag;
import com.nts.jdbc.dto.Todo;

public class TodoDao {
	private Connection dbConnection = null;

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

	public void registerTodo(String title, String name, int sequence) throws SQLException {

		String sql = "insert into todo(title, name, sequence) values(?, ?, ?);";
		PreparedStatement preparedStatement = null;

		if (dbConnection == null) {
			connectDatabase();
		}

		preparedStatement = dbConnection.prepareStatement(sql);
		preparedStatement.setString(1, title);
		preparedStatement.setString(2, name);
		preparedStatement.setInt(3, sequence);
		preparedStatement.executeUpdate();
	}

	public List<Todo> getAllTodos() throws SQLException {

		List<Todo> result = new ArrayList<>();
		String sql = "select id, title, name, sequence, type, regdate from todo";
		PreparedStatement preparedStatement = null;

		if (dbConnection == null) {
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
			result.add(new Todo(id, title, name, sequence, type, regdate));
		}
		return result;
	}

	public void updateTodoType(int id, String type) throws SQLException {

		String nextType = "";

		if (type.equals(TodoTag.TODO.getTodoTag())) {
			nextType = TodoTag.DOING.getTodoTag();
		} else if (type.equals(TodoTag.DOING.getTodoTag())) {
			nextType = TodoTag.DONE.getTodoTag();
		} else {
			return;
		}

		String sql = "update todo set type = ? where id = ? and type = ?;";
		PreparedStatement preparedStatement = null;

		if (dbConnection == null) {
			connectDatabase();
		}

		preparedStatement = dbConnection.prepareStatement(sql);
		preparedStatement.setString(1, nextType);
		preparedStatement.setInt(2, id);
		preparedStatement.setString(3, type);

		preparedStatement.executeUpdate();
	}
}
