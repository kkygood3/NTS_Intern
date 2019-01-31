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
import com.nts.todolist.dto.TodoDtoBuilder;
import com.nts.todolist.exception.DatabaseAccessException;

/**
 * Todo Data Access Object (Singleton)
 * @author yongjoon.Park
*/
public class TodoDao extends JdbcConnection {

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
		final String insertTodo = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (Connection connection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertTodo);) {

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

		final String getAllTodos = "SELECT id, title, name, sequence, type, DATE_FORMAT(regdate, '%Y. %m. %d. ') " +
			"FROM todo " +
			"ORDER BY regdate";

		try (Connection connection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getAllTodos);
			ResultSet resultSet = preparedStatement.executeQuery();) {

			List<TodoDto> todoList = new ArrayList<>();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				String title = resultSet.getString("title");
				String name = resultSet.getString("name");
				int sequence = resultSet.getInt("sequence");
				String type = resultSet.getString("type");
				String regdate = resultSet.getString(6);

				TodoDto todo = new TodoDtoBuilder()
					.setId(id)
					.setTitle(title)
					.setName(name)
					.setSequence(sequence)
					.setType(type)
					.setRegdate(regdate)
					.build();
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

		final String updateTodo = "UPDATE todo SET TYPE = ? WHERE id = ?";

		try (Connection connection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateTodo);) {

			preparedStatement.setString(1, type.getTodoStatus());
			preparedStatement.setLong(2, id);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseAccessException("DataBase Access Error", e);
		}
	}
}
