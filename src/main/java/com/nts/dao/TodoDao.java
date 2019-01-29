/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.factory.MysqlConnectionFactory;
import com.nts.model.TodoDto;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * DB에서 Todo Data를 삽입, 수정, 조회 하는 기능
 */
public class TodoDao {

	private static final String SELECT_TODOS = "select id, title, name, sequence, type, regdate from todo order by regdate asc";
	private static final String INSERT_TODO = "insert into todo(title, name, sequence) values(?, ?, ?)";
	private static final String CHANGE_TYPE_TODO = "update todo set type=? where id=?";

	private TodoDao() {}

	private static class TodoDaoHolder {
		private static final TodoDao TODODAO_INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return TodoDaoHolder.TODODAO_INSTANCE;
	}

	public List<TodoDto> getTodos() throws RuntimeException {
		List<TodoDto> todoList = new ArrayList<>();
		try (
			Connection connection = MysqlConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_TODOS);
			ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				TodoDto todo = new TodoDto();
				todo.setId(resultSet.getLong("id"));
				todo.setTitle(resultSet.getString("title"));
				todo.setName(resultSet.getString("name"));
				todo.setSequence(resultSet.getInt("sequence"));
				todo.setType(resultSet.getString("type"));
				todo.setRegdate(resultSet.getString("regdate"));

				todoList.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // 콘솔 출력 -> log file 출력으로 ..
			throw new RuntimeException(e);
		}
		return todoList;
	}

	public int addTodo(TodoDto todo) throws RuntimeException {
		try (
			Connection connection = MysqlConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_TODO);) {

			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getName());
			statement.setInt(3, todo.getSequence());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // 콘솔 출력 -> log file 출력으로 ..
			throw new RuntimeException(e);
		}
	}

	public int updateTodo(TodoDto todo) throws RuntimeException {
		try (
			Connection connection = MysqlConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(CHANGE_TYPE_TODO);) {

			statement.setString(1, todo.getType());
			statement.setLong(2, todo.getId());

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // 콘솔 출력 -> log file 출력으로 ..
			throw new RuntimeException(e);
		}
	}

}
