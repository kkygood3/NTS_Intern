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

	/**
	 * 데이터베이스에 저장된 TodoDto 객체를 요소로 갖는 List를 생성하여 반환
	 * @return 올바르게 동작한 경우 TodoDto 타입의 List를, 그렇지 않은 경우 비어있는 List를 반환
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<>();

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

				todoList.add(todo);
			}

			return todoList;

		} catch (SQLException e) {
			// 로그 파일로 출력
			e.printStackTrace();
			System.out.println("SQLException 발생");
		}
		return Collections.emptyList();
	}

	/**
	 * 데이터베이스에 TodoDto 객체를 삽입
	 * @param todoDto TodoDto
	 * @return PreparedStatement 객체의 executeUpdate() 메소드의 반환값
	 */
	public int addTodo(TodoDto todoDto) throws RuntimeException {
		try (
			Connection connection = DbConnect.getConnection();
			PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO todo (title, name, sequence) VALUES ( ?, ?, ? )");) {

			preparedStatement.setString(1, todoDto.getTitle());
			preparedStatement.setString(2, todoDto.getName());
			preparedStatement.setInt(3, todoDto.getSequence());

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// 로그 파일로 출력
			e.printStackTrace();
			System.out.println("SQLException 발생");

			throw new RuntimeException(e);
		}
	}

	/**
	 * 데이터베이스에 저장된 TodoDto 객체의 타입을 갱신
	 * @param todoDto
	 * @return PreparedStatement 객체의 executeUpdate() 메소드의 반환값
	 */
	public int updateTodo(TodoDto todoDto) throws RuntimeException {
		try (
			Connection connection = DbConnect.getConnection();
			PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE todo SET TYPE = ? WHERE id = ?");) {

			preparedStatement.setString(1, todoDto.getType());
			preparedStatement.setLong(2, todoDto.getId());

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// 로그 파일로 출력
			e.printStackTrace();
			System.out.println("SQLException 발생");

			throw new RuntimeException(e);
		}
	}

}
