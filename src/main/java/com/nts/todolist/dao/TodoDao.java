/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class TodoDao {
	private static final String URL = "jdbc:mysql://localhost:3306/user2?serverTimezone=Asia/Seoul&useSSL=false";
	private static final String USER = "user2";
	private static final String PASSWORD = "1234";
	private static final String DIVICE_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static final String SQL_SELECT = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY sequence, regdate";
	private static final String SQL_INSERT = "INSERT INTO todo (title, name, sequence) VALUES ( ?, ?, ? )";
	private static final String SQL_UPDATE = "UPDATE todo SET TYPE = ? WHERE id = ?";

	public List<TodoDto> getTodos() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<TodoDto> todos = new ArrayList<>();
		try {
			Class.forName(DIVICE_DRIVER);

			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				TodoDto todo = new TodoDto();
				
				todo.setId(resultSet.getLong("id"));
				todo.setTitle(resultSet.getString("title"));
				todo.setName(resultSet.getString("name"));
				todo.setSequence(resultSet.getInt("sequence"));
				todo.setType(resultSet.getString("type"));
				/*
				 * LocalDate localDate =
				 * LocalDate.parse(resultSet.getString("regdate").substring(0, 10));
				 * todo.setRegdate(localDate.toString());
				 */
				todo.setRegdate(resultSet.getString("regdate"));
				
				todos.add(todo);
			}
			return todos;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public int addTodo(TodoDto todoDto) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DIVICE_DRIVER);

			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			preparedStatement = connection.prepareStatement(SQL_INSERT);

			preparedStatement.setString(1, todoDto.getTitle());
			preparedStatement.setString(2, todoDto.getName());
			preparedStatement.setInt(3, todoDto.getSequence());

			return preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updateTodo(TodoDto todoDto) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			preparedStatement = connection.prepareStatement(SQL_UPDATE);

			preparedStatement.setString(1, todoDto.getType());
			preparedStatement.setLong(2, todoDto.getId());

			return preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}

