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
	private String url = "jdbc:mysql://10.113.116.52:13306/user5";
	private String id = "user5";
	private String pw = "wlsdn123";

	/**
	 * mysql.jdbc.Driver를 DriverManager에 한번만 등록 
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}

	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		String sql = "insert into todo(title, name, sequence) values(?,?,?)";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, todoDto.getTitle());
			pstmt.setString(2, todoDto.getName());
			pstmt.setInt(3, todoDto.getSequence());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertCount;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = new ArrayList<TodoDto>();
		String sql = "select id, name, regdate, sequence, title, type from todo";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todoList;
	}

	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		String sql = "update todo set type = ? where id = ?";
		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, todoDto.getType());
			pstmt.setLong(2, todoDto.getId());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount;
	}
}