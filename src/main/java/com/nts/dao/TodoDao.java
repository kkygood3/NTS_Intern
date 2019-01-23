/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.db.MysqlConnector;
import com.nts.db.SQL;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoDto.Type;

/**
 * DB Todo 테이블에 접근하여 조회 및 조작하는 클래스
 * @author jinwoo.bae
 */

public class TodoDao {
	private TodoDao() {};

	private static class LazyHolder {
		static final TodoDao INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		try (Connection conn = MysqlConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL.ADD_TODO);) {
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
		try (Connection conn = MysqlConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL.GET_TODOS);
			ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				TodoDto todo = new TodoDto();
				todo.setId(rs.getLong("id"));
				todo.setName(rs.getString("name"));
				todo.setRegdate(rs.getString("regdate"));
				todo.setSequence(rs.getInt("sequence"));
				todo.setTitle(rs.getString("title"));
				todo.setType(Type.valueOf(rs.getString("type")));
				todoList.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todoList;
	}

	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		try (Connection conn = MysqlConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL.UPDATE_TODO);) {
			pstmt.setString(1, todoDto.getType().name());
			pstmt.setLong(2, todoDto.getId());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateCount;
	}
}