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
import java.util.List;

import com.nts.todolist.common.JdbcConnection;
import com.nts.todolist.common.TodoStatus;
import com.nts.todolist.dto.TodoDto;
import com.nts.todolist.exception.DatabaseAccessException;

/**
 * Todo Data Access Object (Singleton)
 * @author yongjoon.Park
*/
public class TodoDao {

	private TodoDao() {}

	private static class TodoDaoHolder {
		private static final TodoDao INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return TodoDaoHolder.INSTANCE;
	}

	/**
	 * 새로운 todo를 DataBase에 추가
	 * @author yongjoon.Park
	 * @throws DatabaseAccessException
	 */
	public int addTodo(TodoDto todoDto) throws DatabaseAccessException {
		final String INSERT_TODO = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (Connection connection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO);) {

			preparedStatement.setString(1, todoDto.getTitle());
			preparedStatement.setString(2, todoDto.getName());
			preparedStatement.setInt(3, todoDto.getSequence());

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseAccessException("DataBase Access Error", e);
		}
	}

	/**
	 * DB에 입력된 모든 todo를 list에 담음
	 * @author yongjoon.Park
	 * @return 조회된 모든 todo
	 * @throws DatabaseAccessException
	 */
	public List<TodoDto> getTodos() throws DatabaseAccessException {
		List<TodoDto> todoList = new ArrayList<>();

		final String GET_ALL_TODOS = "SELECT id, title, name, sequence, type, DATE_FORMAT(regdate, '%Y. %m. %d. ') " +
			"FROM todo " +
			"ORDER BY regdate";

		try (Connection connection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TODOS);
			ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {
				long id = resultSet.getLong(1);
				String title = resultSet.getString(2);
				String name = resultSet.getString(3);
				int sequence = resultSet.getInt(4);
				String type = resultSet.getString(5);
				String regdate = resultSet.getString(6);

				TodoDto todo = new TodoDto(id, title, name, sequence, type, regdate);
				todoList.add(todo);
			}
			return todoList;

		} catch (SQLException e) {
			throw new DatabaseAccessException("DataBase Access Error", e);
		}
	}

	/**
	 * Todo의 id, type값을 받아 다음 상태의 type으로 변경
	 * ex) todo -> doing, doing -> done
	 * @author yongjoon.Park
	 * @param id update할 todo의 id
	 * @param type update할 todo의 현재 type값
	 * @throws DatabaseAccessException
	 */
	public int updateTodo(long id, TodoStatus type) throws DatabaseAccessException {

		final String UPDATE_TODO = "UPDATE todo SET TYPE = ? WHERE id = ?";

		try (Connection connection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO);) {

			preparedStatement.setString(1, type.getTodoStatus());
			preparedStatement.setLong(2, id);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseAccessException("DataBase Access Error", e);
		}
	}
}
