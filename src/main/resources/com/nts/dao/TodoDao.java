/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;

public class TodoDao {

	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy.MM.dd");

	private static String dbUrl = "jdbc:mysql://10.113.116.52:13306/user1";
	private static String dbUser = "user1";
	private static String dbPasswd = "user1";

	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String mySqlQuery = "SELECT id, title, name, sequence, type, regdate FROM todo";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(mySqlQuery)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					Long id = resultSet.getLong("id");
					String title = resultSet.getString("title");
					String name = resultSet.getString("name");
					int sequence = resultSet.getInt("sequence");
					String type = resultSet.getString("type");
					Date regDate = resultSet.getDate("regdate");
					String toStringRegDate = DATE_TIME_FORMAT.format(regDate);
					TodoDto todo = new TodoDto(id, title, name, sequence, type, toStringRegDate);
					todoList.add(todo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return todoList;
	}

	public int addTodo(TodoDto todo) {
		int insertCount = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);

			String mySqlQuery = "INSERT INTO todo (title, name, sequence) VALUES ( ?, ?, ? )";

			preparedStatement = connection.prepareStatement(mySqlQuery);

			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getName());
			preparedStatement.setInt(3, todo.getSequence());

			insertCount = preparedStatement.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {}
			}
		}
		return insertCount;
	}

	public int updateTodo(Long id, String type) {
		int updateCount = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);

			String mySqlQuery = "update todo set type = ? where id = ?";

			preparedStatement = connection.prepareStatement(mySqlQuery);

			preparedStatement.setString(1, type);
			preparedStatement.setLong(2, id);

			updateCount = preparedStatement.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception ex) {}
			} // if

			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ex) {}
			} // if
		} // finally

		return updateCount;
	}

}
