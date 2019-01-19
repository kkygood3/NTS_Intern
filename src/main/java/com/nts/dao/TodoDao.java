/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.nts.dto.TodoDto;
import com.nts.util.DBUtil;

/**
 * @desc TodoDao
 * @author 전연빈
 */
public class TodoDao {

	/**
	 * @desc todoList 가져오기
	 * @return list ( todoList, doingList, doneList)
	 */
	public Map<String, List<TodoDto>> selectTodoList() {

		// psmt 및 resultset 셋팅

		// todolist resultset에 반환

		// list 세개 초기화

		// 리스트를 불러오면서 각 title에 맞게 삽입

		// 리턴 map
		return null;

	}

	/**
	 * @desc todo type 변경
	 * @param id
	 * @param type
	 * @return result
	 */
	public int updateTypeTodo(long id, String type) {

		// psmt 셋팅

		// id 있는지 먼저체크

		// type이 todo doing done이 맞는지 체크

		// 해당 update

		// 결과 값 리턴
		return 0;
	}

	/**
	 * @desc todo 내용 삽입
	 * @param todoDto
	 * @return result
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insertTodo(TodoDto todoDto) throws ClassNotFoundException, SQLException {

		Connection conn = DBUtil.getConnection();

		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
		try (PreparedStatement preparedStatment = conn.prepareStatement(sql)) {

			preparedStatment.setString(1, todoDto.getTitle());
			preparedStatment.setString(2, todoDto.getPersonName());
			preparedStatment.setInt(3, todoDto.getSequence());

			return preparedStatment.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}

	}
}
