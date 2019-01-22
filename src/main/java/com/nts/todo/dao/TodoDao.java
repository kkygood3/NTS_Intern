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
	private String connectionUrl = "jdbc:mysql://10.113.116.52:13306/user8?serverTimezone=Asia/Seoul";
	private String connectionUser = "user8";
	private String connectionPassword = "user8";

	public int addTodo(TodoDto todo) {
		int insertCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "insert into todo(title, name, sequence) values(?,?,?)";
		try (Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getName());
			preparedStatement.setInt(3, todo.getSequence());

			insertCount = preparedStatement.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

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
				Long id = resultsSet.getLong("id");
				String title = resultsSet.getString("title");
				String name = resultsSet.getString("name");
				int sequence = resultsSet.getInt("sequence");
				String type = resultsSet.getString("type");
				String regdate = resultsSet.getString("regdate");
				TodoDto todo = new TodoDto(id, title, name, sequence, type, regdate);
				todos.add(todo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return todos;
	}

	public int updateTodo(TodoDto todo) {
		int updateCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "update todo set type = ? where id = ?";
		try (Connection conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

			preparedStatement.setString(1, todo.getType());
			preparedStatement.setLong(2, todo.getId());

			updateCount = preparedStatement.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return updateCount;
	}
}