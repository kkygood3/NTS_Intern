/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;

public class TodoDao {

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	private static String dbUrl = "jdbc:mysql://10.113.116.52:13306/user1";
	private static String dbUser = "user1";
	private static String dbPasswd = "user1";

	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<>();

		String mySqlQuery = "SELECT id, title, name, sequence, type, regdate FROM todo";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(mySqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery()) {


				while (resultSet.next()) {
					Long id = resultSet.getLong("id");
					String title = resultSet.getString("title");
					String name = resultSet.getString("name");
					int sequence = resultSet.getInt("sequence");
					TodoType type = TodoType.valueOf(resultSet.getString("type"));
					LocalDate regDate = resultSet.getDate("regdate").toLocalDate();
					String toStringRegDate = DATE_TIME_FORMAT.format(regDate);
					TodoDto todo = new TodoDto(id, title, name, sequence, type, toStringRegDate);
					todoList.add(todo);
				}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
		}

		return todoList;
	}

	public int addTodo(TodoDto todo) {

		String mySqlQuery = "INSERT INTO todo (title, name, sequence) VALUES ( ?, ?, ? )";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(mySqlQuery)) {

			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getName());
			preparedStatement.setInt(3, todo.getSequence());

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
		}

		return -1;
	}

	public int updateTodo(Long id, String type) {

		String mySqlQuery = "update todo set type = ? where id = ?";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(mySqlQuery)) {

			preparedStatement.setString(1, type);
			preparedStatement.setLong(2, id);

			return preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
		}

		return -1;
	}

}
