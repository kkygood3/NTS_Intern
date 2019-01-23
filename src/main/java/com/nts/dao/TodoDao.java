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
import com.nts.type.TodoType;
import com.nts.util.DBUtil;

/**
 * @author 전연빈
 */
public class TodoDao {

	/**
	 * @desc todoList 가져오기
	 * @return todoMap key -> ( todoList, doingList, doneList)
	 * @throws SQLException 
	 */
	@SuppressWarnings("unlikely-arg-type")
	public List<TodoDto> getTodos() throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id, title, name, sequence, type, regdate ");
		sql.append("FROM todo ");
		sql.append("ORDER BY sequence,regdate DESC");

		try (Connection conn = DBUtil.getConnection();
			PreparedStatement preparedStatment = conn.prepareStatement(sql.toString())) {
			try (ResultSet resultSet = preparedStatment.executeQuery()) {

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
			}
		}

	}

	/**
	 * @desc todo type 변경
	 * @param id
	 * @param type
	 * @return result
	 * @throws SQLException 
	 */
	public int updateTodo(TodoDto todoDto) throws SQLException {

		String sql = "UPDATE todo SET TYPE = ? WHERE id = ?;";

		try (Connection conn = DBUtil.getConnection();
			PreparedStatement preparedStatment = conn.prepareStatement(sql)) {

			if (TodoType.TODO.toString().equals(todoDto.getType())) {
				todoDto.setType(TodoType.DOING.toString());
			} else if (TodoType.DOING.toString().equals(todoDto.getType())) {
				todoDto.setType(TodoType.DONE.toString());
			}

			preparedStatment.setString(1, todoDto.getType());
			preparedStatment.setLong(2, todoDto.getId());

			return preparedStatment.executeUpdate();
		}

	}

	/**
	 * @desc todo 내용 삽입
	 * @param todoDto
	 * @return result
	 * @throws SQLException
	 */
	public int addTodo(TodoDto todoDto) throws SQLException {

		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";

		try (Connection conn = DBUtil.getConnection();
			PreparedStatement preparedStatment = conn.prepareStatement(sql)) {
			preparedStatment.setString(1, todoDto.getTitle());
			preparedStatment.setString(2, todoDto.getName());
			preparedStatment.setInt(3, todoDto.getSequence());

			return preparedStatment.executeUpdate();

		}
	}
}
