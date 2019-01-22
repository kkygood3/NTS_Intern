/**
 * Copyright 2019 Naver Corp. All rights Reserved.
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
 * DB에서 데이터를 가져오는 Dao클래스
 * @author 박우성
 */
public class TodoDao {
	private static String dburl = "jdbc:mysql://10.113.116.52:13306/user4?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
	private static String dbUser = "user4";
	private static String dbpasswd = "user4";

	private List executeQuery(String query) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<TodoDto> roles = new ArrayList();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			ps = conn.prepareStatement(query);

			if (query.substring(0, 6).equals("SELECT")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					int sequence = rs.getInt(4);
					String type = rs.getString(5);
					String regdate = rs.getString(6);

					regdate = regdate.split(" ")[0].replace('-', '.');

					roles.add(new TodoDto(id, title, name, sequence, type, regdate));
				}
			} else {
				ps.execute();
				roles = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return roles;
	}

	public boolean addTodo(TodoDto todo) {
		String sql = "insert into todo(title, name, sequence) values('" + todo.getTitle() + "', '" + todo.getName() + "', " + todo.getSequence() + ")";
		List<TodoDto> result = executeQuery(sql);
		return (result==null);
	}

	public boolean updateTodo(TodoDto todo) {
		String nextType = "DOING";
		if (todo.getType().equals("DOING"))
			nextType = "DONE";

		String sql = "update todo set type = '" + nextType + "' where id = " + todo.getId();
		List<TodoDto> result = executeQuery(sql);
		return (result==null);
	}

	public List getTodos() {
		String sql = "SELECT * FROM todo";
		ResultSet rs = null;
		return executeQuery(sql);
	}
}
