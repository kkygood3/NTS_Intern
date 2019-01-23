/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.dto.TodoDto;
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
					String type = rs.getString(5);
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

	public boolean addTodo(TodoDto todo) {
		List<TodoDto> result = null;

		try (PreparedStatement ps = MysqlConnector.getConnection().prepareStatement(TodoDaoQuery.INSERT_QUERY)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			result = executeQuery(ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return (result == null);
	}

	public boolean updateTodo(TodoDto todo) {
		List<TodoDto> result = null;

		String nextType = "DOING";
		if (todo.getType().equals("DOING"))
			nextType = "DONE";

		try (PreparedStatement ps = MysqlConnector.getConnection().prepareStatement(TodoDaoQuery.UPDATE_QUERY);) {
			ps.setString(1, nextType);
			ps.setInt(2, todo.getId());

			result = executeQuery(ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return (result == null);
	}

	public List getTodos() {
		List<TodoDto> result = null;

		try (PreparedStatement ps = MysqlConnector.getConnection().prepareStatement(TodoDaoQuery.SELECT_QUERY);) {
			result = executeQuery(ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
