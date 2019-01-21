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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nts.model.Todo;

public class TodoDao {

	Connection connection;

	public TodoDao() {
		String url = "jdbc:mysql://localhost:3306/todo";
		String user = "root";
		String password = "root!@#123"; // TODO db 사용자 생성해서 하기

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Map<String, List<Todo>> getTodos() {
		Map<String, List<Todo>> todos = null;
		String query = "select * from todo order by regdate desc";
		try (
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			todos = resultSetToMap(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todos;
	}

	public void addTodo(Todo todo) {
		String query = "insert into todo(title, name, sequence) values(?, ?, ?)";
		try (
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getName());
			statement.setInt(3, todo.getSequence());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTodo(Todo todo) {
		String query = "update todo set type=? where id=?";
		try (
			PreparedStatement statement = connection.prepareStatement(query);) {

			if (todo.getType().equals("TODO")) {
				todo.setType("DOING");
			} else if (todo.getType().equals("DOING")) {
				todo.setType("DONE");
			}

			statement.setString(1, todo.getType());
			statement.setLong(2, todo.getId());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Map<String, List<Todo>> resultSetToMap(ResultSet resultSet)
		throws SQLException {
		Map<String, List<Todo>> todos = new HashMap<>();

		while (resultSet.next()) {
			Todo todo = new Todo();
			todo.setId(resultSet.getLong("id"));
			todo.setTitle(resultSet.getString("title"));
			todo.setName(resultSet.getString("name"));
			todo.setSequence(resultSet.getInt("sequence"));
			todo.setType(resultSet.getString("type"));
			todo.setRegdate(resultSet.getString("regdate"));

			if (!todos.containsKey(todo.getType())) {
				todos.put(todo.getType(), new ArrayList<>());
			}
			todos.get(todo.getType()).add(todo);
		}
		return todos;
	}
}
