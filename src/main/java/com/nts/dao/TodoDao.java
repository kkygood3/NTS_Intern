/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;
import com.nts.mysql.MysqlConnector;

/**
 * DB에 쿼리를 전송하는 DAO 클래스
 * @author 박우성
 */
public class TodoDao {
	private static TodoDao instance = null;

	private TodoDao() {}

	public static TodoDao getInstance() {
		if (instance == null)
			instance = new TodoDao();

		return instance;
	}

	private List executeQuery(PreparedStatement ps) {
		ArrayList<TodoDto> todo = new ArrayList();
		ResultSet rs = null;
		try {
			String psStr = ps.toString();
			char queryFirstChar = psStr.charAt(psStr.indexOf(' ') + 1);
			if (queryFirstChar == 's' || queryFirstChar == 'S') {
				rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					int sequence = rs.getInt(4);
					TodoType type = TodoType.valueOf(rs.getString(5));
					String regdate = rs.getString(6);

					todo.add(new TodoDto(id, title, name, sequence, type, regdate));
				}
			} else {
				if (ps.executeUpdate() != 0)
					;
				todo = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todo;
	}
	
	/**
	 * @return Insert query success : true 
	 */
	public boolean addTodo(TodoDto todo) {
		List<TodoDto> result = null;

		try (Connection conn = MysqlConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(TodoDaoQuery.INSERT_QUERY)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			result = executeQuery(ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return (result == null);
	}

	/**
	 * @return Update query success : true 
	 */
	public boolean updateTodo(TodoDto todo) {
		List<TodoDto> result = null;
		
		String nextType = "DOING";
		if (todo.getType().equals(TodoType.DOING))
			nextType = "DONE";

		try (Connection conn = MysqlConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(TodoDaoQuery.UPDATE_QUERY);) {
			ps.setString(1, nextType);
			ps.setInt(2, todo.getId());
			ps.setString(3, todo.getType().toString());

			result = executeQuery(ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return (result == null);
	}

	/**
	 * @return Select result(List) 
	 */
	public List getTodos() {
		List<TodoDto> result = null;

		try (Connection conn = MysqlConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(TodoDaoQuery.SELECT_QUERY);) {
			result = executeQuery(ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @return contains the TODO : true 
	 */
	public boolean verifyTodo(TodoDto todo) {
		boolean isContain = false;
		List<TodoDto> result = null;

		try (Connection conn = MysqlConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(TodoDaoQuery.VERIFY_QUERY);) {
			ps.setInt(1, todo.getId());
			ps.setString(2, todo.getType().toString());
			
			result = executeQuery(ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(result.size() == 1)  
			isContain = true;
		
		return isContain;
	}
}
