/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;
import com.nts.exception.ServerError500Exception;
import com.nts.util.DBUtil;

/**
 * @author 전연빈
 */
public class TodoDao {

	private static final String GET_TODOS = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY regdate DESC";
	private static final String UPDATE_TODO = "UPDATE todo SET type = ? WHERE id = ?";
	private static final String ADD_TODO = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

	private TodoDao() {

	}

	private static class TodoDaoLazyHolder {
		private static final TodoDao INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return TodoDaoLazyHolder.INSTANCE;
	}

	/**
	 * @desc todoList 가져오기
	 * @return todoList
	 * @throws ServerError500Exception 
	 */
	public List<TodoDto> getTodos() throws ServerError500Exception {

		try (Connection conn = DBUtil.getConnection();
			PreparedStatement preparedStatment = conn.prepareStatement(GET_TODOS);
			ResultSet resultSet = preparedStatment.executeQuery()) {

			List<TodoDto> todoList = new ArrayList<>();

			while (resultSet.next()) {

				TodoDto todoDto = new TodoDto();

				todoDto.setId(resultSet.getLong("id"));
				todoDto.setName(resultSet.getString("name"));
				todoDto.setRegdate(resultSet.getString("regdate"));
				todoDto.setSequence(resultSet.getInt("sequence"));
				todoDto.setTitle(resultSet.getString("title"));
				todoDto.setType(resultSet.getString("type"));

				todoList.add(todoDto);
			}

			return todoList;
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new ServerError500Exception("Server Error");
		}
	}

	/**
	 * @desc todo type 변경
	 * @param todoDto
	 * @throws ServerError500Exception 
	 * @return result
	 */
	public int updateTodo(TodoDto todoDto) throws ServerError500Exception {

		try (Connection conn = DBUtil.getConnection();
			PreparedStatement preparedStatment = conn.prepareStatement(UPDATE_TODO)) {

			preparedStatment.setString(1, todoDto.getType());
			preparedStatment.setLong(2, todoDto.getId());

			return preparedStatment.executeUpdate();
		} catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new ServerError500Exception("Server Error");
		}

	}

	/**
	 * @desc todo 내용 삽입
	 * @param todoDto
	 * @return result
	 * @throws ServerError500Exception 
	 */
	public int addTodo(TodoDto todoDto) throws ServerError500Exception {

		try (Connection conn = DBUtil.getConnection();
			PreparedStatement preparedStatment = conn.prepareStatement(ADD_TODO)) {

			preparedStatment.setString(1, todoDto.getTitle());
			preparedStatment.setString(2, todoDto.getName());
			preparedStatment.setInt(3, todoDto.getSequence());

			return preparedStatment.executeUpdate();

		} catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new ServerError500Exception("Server Error");
		}
	}
}
