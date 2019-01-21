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
import java.util.HashMap;
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
	 * @return todoMap key -> ( todoList, doingList, doneList)
	 * @throws SQLException 
	 */
	@SuppressWarnings("unlikely-arg-type")
	public Map<String, List<TodoDto>> selectTodoListAll() throws SQLException {

		Connection conn = DBUtil.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id, title, personName, sequence, type, regdate ");
		sql.append("FROM todo ");
		sql.append("ORDER BY sequence,regdate DESC");
		
		try (PreparedStatement preparedStatment = conn.prepareStatement(sql.toString())) {
			try(ResultSet resultSet = preparedStatment.executeQuery()){
				
				List<TodoDto> todoList = new ArrayList<TodoDto>();
				List<TodoDto> doingList = new ArrayList<TodoDto>();
				List<TodoDto> doneList = new ArrayList<TodoDto>();
				
				while(resultSet.next()) {
					
					TodoDto todoDto = new TodoDto();
					
					todoDto.setId(resultSet.getLong("id"));
					todoDto.setPersonName(resultSet.getString("personName"));
					todoDto.setRegDate(resultSet.getDate("regdate"));
					todoDto.setSequence(resultSet.getInt("sequence"));
					todoDto.setTitle(resultSet.getString("title"));
					todoDto.setType( resultSet.getString("type"));
					
					if(todoDto.getType().equals("TODO")) {
						todoList.add(todoDto);
					}else if(todoDto.getType().equals("DOING")) {
						doingList.add(todoDto);
					}else if(todoDto.getType().equals("DONE")) {
						doneList.add(todoDto);
					}
				}
				
				Map<String,List<TodoDto>> todoMap = new HashMap<>();
				
				todoMap.put("todoList",todoList);
				todoMap.put("doingList",doingList);
				todoMap.put("doneList",doneList);
				
				return todoMap;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}

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
	 */
	public int insertTodo(TodoDto todoDto) throws SQLException {

		Connection conn = DBUtil.getConnection();
		
		String sql = "INSERT INTO todo(title, personName, sequence) VALUES(?, ?, ?)";
		
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
