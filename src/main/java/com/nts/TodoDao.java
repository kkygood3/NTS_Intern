/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


/**
*
* @description : Todo List DAO
* @filename : TodoDao.java
* @package : com.nts
* @author : Seokhyeon Choi
* @method : TodoDao getInstance()
* @method : List<TodoDto> getTodos()
* @method : int addTodo(TodoDto todoDto)
* @method : int updateTodo(TodoDto todoDto)
*/
public class TodoDao {
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user12?characterEncoding=UTF-8";
	private static final String DB_USER = "user12";
	private static final String DB_PASSWORD = "0000";
	private static final String SQL_SELECT = "SELECT id, name, regdate, sequence, title, type FROM todo order by regdate asc";
	private static final String SQL_INSERT = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE todo SET type = ? WHERE id = ?";

	private static final TodoDao INSTANCE = new TodoDao();

	private TodoDao() {
	}

	public static TodoDao getInstance() {
		return INSTANCE;
	}

	/**
	 * @description : todo table의 모든 정보 조회 및 todo List 생성
	 * @return : List<TodoDto>
	 */
	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = null;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(SQL_SELECT);
				ResultSet rs = ps.executeQuery()) {

			todoList = new ArrayList<>();
			
			while (rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String regDate = rs.getString("regDate");
				int sequence = rs.getInt("sequence");
				String title = rs.getString("title");
				String type = rs.getString("type");

				TodoDto todoDto = new TodoDto(id, name, regDate, sequence, title, type);
				todoList.add(todoDto);
			}
			
			return todoList;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * @description : 전달받은 TodoDto 객체를 DB에 Insert
	 * @param : todoDto
	 * @return : int
	 */
	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());
			insertCount = ps.executeUpdate();

			return insertCount;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return 0;
	}

	/**
	 * @description : 전달받은 TodoDto 객체의 정보를 업데이트
	 * @param : todoDto
	 * @return : int
	 */
	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

			ps.setString(1, todoDto.getType());
			ps.setLong(2, todoDto.getId());
			updateCount = ps.executeUpdate();

			return updateCount;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
