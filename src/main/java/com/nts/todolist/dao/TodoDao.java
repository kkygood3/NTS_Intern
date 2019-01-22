/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nts.todolist.dto.TodoDto;

/**
 * Todo Data Access Object
 * @author yongjoon.Park
*/
public class TodoDao {
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user3?serverTimezone=Asia/Seoul&useSSL=false";
	private static final String DB_USER = "user3";
	private static final String DB_PASSWORD = "user3";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final SimpleDateFormat SIMEPLE_DATA_FORMAT = new SimpleDateFormat("yyyy. MM. dd. ");
	
	// TODO todo, doing, done -> ENUM?

	/**
	 * 새로운 todo 추가
	 * @author yongjoon.Park
	 */
	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DB_DRIVER);
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, todoDto.getTitle());
			preparedStatement.setString(2, todoDto.getName());
			preparedStatement.setInt(3, todoDto.getSequence());
			
			insertCount = preparedStatement.executeUpdate();
			
			if(insertCount > 0) {
				// TODO insert 성공 시 필요한 동작이 있을까?
			}else {
				// TODO insert 실패 시 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertCount;
	}

	/**
	 * get all todo list
	 * @author yongjoon.Park
	 * @return 조회된 모든 todo의 List<Todo>
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// query 
			String sql = "SELECT id, title, name, sequence, type, regdate " +
				"FROM todo " +
				"ORDER BY sequence, regdate DESC";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long id = resultSet.getLong(1);
				String title = resultSet.getString(2);
				String name = resultSet.getString(3);
				int sequence = resultSet.getInt(4);
				String type = resultSet.getString(5);
				Date regdate = resultSet.getDate(6);

				TodoDto todo = new TodoDto(id, title, name, sequence, type, SIMEPLE_DATA_FORMAT.format(regdate));
				todoList.add(todo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return todoList;
	}

	/**
	 * Todo의 type을 변경
	 * ex) todo -> doing, doing -> done
	 * @author yongjoon.Park
	 */
	public int updateTodo(int id) {

		return 0;
	}
}
