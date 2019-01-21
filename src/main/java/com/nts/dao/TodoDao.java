/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;

/**
 * DB Todo 테이블에 접근하여 조회 및 조작하는 클래스
 * @author jinwoo.bae
 */

public class TodoDao {
	private String url = "jdbc:mysql://localhost:3306/todo";
	private String id = "root";
	private String pw = "1234";

	/**
	 * DB 커넥션을 가져와주는 메소드
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}

	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		try {
			Connection conn = getConn();
			String sql = "insert into todo(title, name, sequence) values(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, todoDto.getTitle());
			pstmt.setString(2, todoDto.getName());
			pstmt.setInt(3, todoDto.getSequence());
			insertCount = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertCount;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<TodoDto>();
		try {
			Connection conn = getConn();
			String sql = "select * from todo";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TodoDto todo = new TodoDto();
				todo.setId(rs.getLong("id"));
				todo.setName(rs.getString("name"));
				todo.setRegdate(rs.getString("regdate"));
				todo.setSequence(rs.getInt("sequence"));
				todo.setTitle(rs.getString("title"));
				todo.setType(rs.getString("type"));
				todoList.add(todo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todoList;
	}

	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		try {
			Connection conn = getConn();
			String sql = "update todo set type = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, todoDto.getType());
			pstmt.setLong(2, todoDto.getId());
			updateCount = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount;
	}
}