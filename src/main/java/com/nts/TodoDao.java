package com.nts;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * TodoDao implementation
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/pjt2?autoReconnect=true&useSSL=false";
	private static String dbUser = "root";
	private static String dbpasswd = "root";

	/**
	 * @getTodos()
	 * returns all rows in todo table through sql query
	 */
	public List<TodoDto> getTodos() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		List<TodoDto> result = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "select id, title, name, sequence, type, regdate from todo order by regdate asc, id asc";
			ps = conn.prepareStatement(sql);

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					result.add(
						new TodoDto(rs.getLong("id"),
							rs.getString("title"),
							rs.getString("name"),
							rs.getInt("sequence"),
							rs.getString("regdate").split(" ")[0],
							rs.getString("type")));
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @addTodo()	
	 * add single todo item into db
	 */
	public int addTodo(HttpServletRequest request) throws ClassNotFoundException, SQLException {

		//variable status to get the execution state;
		int status = 0;

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "insert into todo(title, name, sequence) values(?,?,?)";
			ps = conn.prepareStatement(sql);

			ps.setString(1, request.getParameter("title"));
			ps.setString(2, request.getParameter("name"));
			ps.setInt(3, Integer.valueOf(request.getParameter("sequence")));

			status = ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	/**
	 * @updateTodo()	
	 * update type of todo item in db
	 */
	public int updateTodo(TodoDto todo) throws ClassNotFoundException, SQLException {
		int status = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "update todo set type = ? where id = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, todo.getType());
			ps.setLong(2, todo.getId());
			status = ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
}
