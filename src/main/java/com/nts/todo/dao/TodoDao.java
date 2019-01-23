/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nts.todo.dto.TodoDto;

/**
* FileName : TodoDao.java
* @author  : 이승수
* @date    : 2019. 1. 22.
*/
public class TodoDao {
	private static final String connectionUrl = "jdbc:mysql://10.113.116.52:13306/user8?serverTimezone=Asia/Seoul";
	private static final String connectionUser = "user8";
	private static final String connectionPassword = "user8";

	public int addTodo(TodoDto todo) {
		String sql = "insert into todo(title, name, sequence) values(?,?,?)";
		Object[] params = {todo.getTitle(), todo.getName(), todo.getSequence()};

		int insertCount = queryForObject(sql, params);

		return insertCount;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> todos = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "select id, title, name, sequence, type, date_format(regdate, \"%Y.%m.%d\") as regdate from todo order by regdate";
		try (Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultsSet = preparedStatement.executeQuery()) {
			while (resultsSet.next()) {
				TodoDto todo = new TodoDto();
				todo.setId(resultsSet.getLong("id"));
				todo.setTitle(resultsSet.getString("title"));
				todo.setName(resultsSet.getString("name"));
				todo.setSequence(resultsSet.getInt("sequence"));
				todo.setType(resultsSet.getString("type"));
				todo.setRegdate(resultsSet.getString("regdate"));

				todos.add(todo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return todos;
	}

	public int updateTodo(TodoDto todo) {
		String sql = "update todo set type = ? where id = ?";
		Object[] params = {todo.getType(), todo.getId()};

		int updateCount = queryForObject(sql, params);

		return updateCount;
	}

	/*
	 * INSERT, UPDATE, DELETE문에 한해 사용할 수 있습니다.
	 */
	private int queryForObject(String sql, Object[] params) {
		int resultCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}

			resultCount = preparedStatement.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resultCount;
	}
}