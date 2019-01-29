/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nts.todolist.common.DbConnect;
import com.nts.todolist.dto.TodoDto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class TodoDao {

	private TodoDao() {}

	private static class TodoDaoHolder {
		private static final TodoDao INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return TodoDaoHolder.INSTANCE;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> todos = new ArrayList<>();

		try (
			Connection connection = DbConnect.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY sequence, regdate");
			ResultSet resultSet = preparedStatement.executeQuery();) {

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

		} catch (SQLException e) {
			//
			System.out.println("TodoDao.java 예외처리 1");
			//
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public int addTodo(TodoDto todoDto) throws Exception {

		try (
			Connection connection = DbConnect.getConnection();
			PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO todo (title, name, sequence) VALUES ( ?, ?, ? )");) {

			preparedStatement.setString(1, todoDto.getTitle());
			preparedStatement.setString(2, todoDto.getName());
			preparedStatement.setInt(3, todoDto.getSequence());

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			//
			System.out.println("TodoDao.java 예외처리 2");
			//
			e.printStackTrace();
		}
		return -1;
	}

	public int updateTodo(TodoDto todoDto) throws Exception {

		try (
			Connection connection = DbConnect.getConnection();
			PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE todo SET TYPE = ? WHERE id = ?");) {

			preparedStatement.setString(1, todoDto.getType());
			preparedStatement.setLong(2, todoDto.getId());

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			//
			System.out.println("TodoDao.java 예외처리 3");
			//
			e.printStackTrace();
		}
		return -1;
	}

}
