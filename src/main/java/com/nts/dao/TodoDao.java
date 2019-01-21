/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;
import com.nts.util.DBUtil;

/**
 * @desc TodoDao
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
	public int updateTodo(long id, String type) throws SQLException {

		String sql = "UPDATE todo SET TYPE = ? WHERE id = ?;";

		try (Connection conn = DBUtil.getConnection();
			PreparedStatement preparedStatment = conn.prepareStatement(sql)) {

			if (type.equals("TODO"))
				type = "DOING";
			else if (type.equals("DOING"))
				type = "DONE";
			
			preparedStatment.setString(1, type);
			preparedStatment.setLong(2, id);

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
