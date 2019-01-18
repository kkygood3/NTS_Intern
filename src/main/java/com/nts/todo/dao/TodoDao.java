package com.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nts.todo.dto.TodoDto;

/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
public class TodoDao {
	private String connectionUrl = "jdbc:mysql://localhost:3306/todo?serverTimezone=Asia/Seoul";
	private String connectionUser = "root";
	private String connectionPassword = "!@#123";

	public int addTodo(TodoDto todo) {
		return 0;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> todos = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT id, title, name, sequence FROM todo order by id desc";
		try (Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			try (ResultSet resultsSet = preparedStatement.executeQuery()) {
				while (resultsSet.next()) {
					Long id = resultsSet.getLong("id");
					String title = resultsSet.getString("title");
					String name = resultsSet.getString("name");
					int sequence = resultsSet.getInt("sequence");
					TodoDto todo = new TodoDto(id, title, name, sequence);
					todos.add(todo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return todos;
	}

	public int updateTodo(TodoDao todo) {
		return 0;
	}
}