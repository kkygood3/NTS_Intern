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
import java.util.Map;

import javax.naming.NamingException;

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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Map<String, List<TodoDto>> selectTodoListAll() throws ClassNotFoundException, SQLException {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,title,name,sequence,type,TO_CHAR(regdate)");
		sql.append("FROM todo");
		
		try (PreparedStatement preparedStatment = conn.prepareStatement(sql.toString())) {

			try(ResultSet resultSet = preparedStatment.executeQuery()){
				
				List<TodoDto> todoList = new ArrayList<TodoDto>();
				List<TodoDto> doingList = new ArrayList<TodoDto>();
				List<TodoDto> doneList = new ArrayList<TodoDto>();
				
				while(resultSet.next()) {
					
					TodoDto todoDto = new TodoDto();
					todoDto.setId(resultSet.getLong("id"));
					todoDto.setPersonName(resultSet.getString("personName"));
					todoDto.setRegDate(resultSet.getString("regdate"));
					todoDto.setSequence(resultSet.getInt("sequence"));
					todoDto.setTitle(resultSet.getString("title"));
//					todoDto.setType( resultSet.getString("typetype"));
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
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

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
