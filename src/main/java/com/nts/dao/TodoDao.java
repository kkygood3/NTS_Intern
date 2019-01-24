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

	private List executeSelectQuery(PreparedStatement ps) {
		ArrayList<TodoDto> todo = new ArrayList();

		try (ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int sequence = rs.getInt(4);
				TodoType type = TodoType.valueOf(rs.getString(5));
				String regdate = rs.getString(6);

				todo.add(new TodoDto(id, title, name, sequence, type, regdate));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return todo;
	}

	private boolean executeUpdateQuery(PreparedStatement ps) {
		boolean executeSuccess = false;

		try {
			if (ps.executeUpdate() != 0) {
				executeSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return executeSuccess;
	}

	/**
	 * @return Insert query success : true 
	 */
	public boolean addTodo(TodoDto todo) {
		boolean executeSuccess = false;

		try (Connection conn = MysqlConnector.getConnection();
			PreparedStatement ps = conn.prepareStatement(TodoDaoQuery.INSERT_QUERY)) {

			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			executeSuccess = executeUpdateQuery(ps);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return executeSuccess;
	}

	/**
	 * @return Update query success : true 
	 */
	public boolean updateTodo(TodoDto todo) {
		boolean executeSuccess = false;

		String nextType = "";

		if (!(todo.getType().equals(TodoType.DONE))) {
			try (Connection conn = MysqlConnector.getConnection();
				PreparedStatement ps = conn.prepareStatement(TodoDaoQuery.UPDATE_QUERY);) {

				if (todo.getType().equals(TodoType.TODO)) {
					nextType = "DOING";
				} else {
					nextType = "DONE";
				}

				ps.setString(1, nextType);
				ps.setInt(2, todo.getId());
				ps.setString(3, todo.getType().toString());

				executeSuccess = executeUpdateQuery(ps);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return executeSuccess;
	}

	/**
	 * @return Select result(List) 
	 */
	public List getTodos() {
		List<TodoDto> result = null;

		try (Connection conn = MysqlConnector.getConnection();
			PreparedStatement ps = conn.prepareStatement(TodoDaoQuery.SELECT_QUERY);) {

			result = executeSelectQuery(ps);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
