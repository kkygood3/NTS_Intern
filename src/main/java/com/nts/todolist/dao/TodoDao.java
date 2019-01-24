/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class TodoDao {
	private static final String URL = "jdbc:mysql://10.113.116.52:13306/user2?serverTimezone=Asia/Seoul&useSSL=false";
	private static final String USER = "user2";
	private static final String PASSWORD = "1234";

	public List<TodoDto> getTodos() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<TodoDto> todos = new ArrayList<>();
		String query = "SELECT id, title, name, sequence, type, regdate FROM todo";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TodoDto todo = new TodoDto();

				todo.setId(resultSet.getLong("id"));
				todo.setTitle(resultSet.getString("title"));
				todo.setName(resultSet.getString("name"));
				todo.setSequence(resultSet.getInt("sequence"));
				todo.setType(resultSet.getString("type"));
				todo.setRegdate(resultSet.getString("regdate"));

				todos.add(todo);
			}

			return todos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}
