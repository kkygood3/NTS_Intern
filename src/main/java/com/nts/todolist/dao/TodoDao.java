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

import com.nts.todolist.common.JDBCConnection;
import com.nts.todolist.common.TodoStatus;
import com.nts.todolist.dto.TodoDto;

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
	 * 새로운 todo를 DB에 추가
	 * @author yongjoon.Park
	 */
	public int addTodo(TodoDto todoDto) {

		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (Connection connection = JDBCConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

			int index = 1;
			preparedStatement.setString(index++, todoDto.getTitle());
			preparedStatement.setString(index++, todoDto.getName());
			preparedStatement.setInt(index++, todoDto.getSequence());

			//TODO return이 많으면 안 좋은 코드인지? / int result를 만들어서 사용하는게 낫나?
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.printf("SQLException : 입력 정보가 양식과 다릅니다. \t입력된 정보 :(%s, %s, %d)\n", todoDto.getTitle(), todoDto.getName(), todoDto.getSequence());
		}

		return -1;
	}

	/**
	 * DB에 입력된 모든 todo를 list에 담에 return
	 * @author yongjoon.Park
	 * @return 조회된 모든 todo의 List<Todo>
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<>();

		String sql = "SELECT id, title, name, sequence, type, DATE_FORMAT(regdate, '%Y. %m. %d. ') " +
			"FROM todo " +
			"ORDER BY regdate";

		try (Connection connection = JDBCConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {
				int index = 1;
				long id = resultSet.getLong(index++);
				String title = resultSet.getString(index++);
				String name = resultSet.getString(index++);
				int sequence = resultSet.getInt(index++);
				String type = resultSet.getString(index++);
				String regdate = resultSet.getString(index++);

				TodoDto todo = new TodoDto(id, title, name, sequence, type, regdate);
				todoList.add(todo);
			}
			return todoList;

		} catch (SQLException e) {
			System.out.println("SQLException : " + e.getMessage());
		}
		return Collections.emptyList();
	}

	/**
	 * Todo의 id, type값을 받아 다음 상태의 type으로 변경
	 * ex) todo -> doing, doing -> done
	 * @author yongjoon.Park
	 * @param update될 todo의 현재 id와 type값
	 */
	public int updateTodo(int id, TodoStatus type) {

		String sql = "UPDATE todo SET TYPE = ? WHERE id = ?";

		try (Connection connection = JDBCConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

			int index = 1;
			preparedStatement.setString(index++, type.changNextStatus());
			preparedStatement.setInt(index++, id);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.printf("입력된 정보가 양식과 다릅니다. \t입력된 정보 : (%d, %s)", id, type);
		}

		return -1;
	}
}
