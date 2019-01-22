package com.nts.JDBC;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TodoDao {
	private static DataSource dataSource = null;

	/**
	 * @getDBConnection()
	 * returns connection to db, if there is no DataSource, init data source first
	 */
	private Connection getDBConnection() throws SQLException, NamingException,
		ClassNotFoundException {
		if (dataSource == null) {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/DevelopmentDB");
		}
		return dataSource.getConnection();
	}

	/**
	 * @getTodos()
	 * returns all rows in todo table through sql query
	 */
	public List<TodoDto> getTodos() throws ClassNotFoundException, SQLException, NamingException {

		List<TodoDto> result = new ArrayList<>();

		Connection conn = getDBConnection();
		String sql = "select id, title, name, sequence, type, DATE_FORMAT(regdate , '%Y-%d-%m %e') as regdate from todo order by regdate asc, id asc";
		PreparedStatement ps = conn.prepareStatement(sql);

		try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				result.add(
					new TodoDto(rs.getLong("id"),
						rs.getString("title"),
						rs.getString("name"),
						rs.getInt("sequence"),
						rs.getString("regdate"),
						rs.getString("type")));
			}
			rs.close();
			ps.close();
			conn.close();
		}

		return result;
	}

	/**
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @addTodo()	
	 * add single todo item into db
	 */
	public int addTodo(TodoDto dto) throws ClassNotFoundException, SQLException, NamingException {

		//variable status to get the execution state;
		int status = 0;
		Connection conn = getDBConnection();
		String sql = "insert into todo(title, name, sequence) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, dto.getTitle());
		ps.setString(2, dto.getName());
		ps.setInt(3, dto.getSequence());

		status = ps.executeUpdate();

		ps.close();
		conn.close();

		return status;
	}

	/**
	 * @throws NamingException 
	 * @updateTodo()	
	 * update type of todo item in db
	 */
	public int updateTodo(TodoDto todo) throws ClassNotFoundException, SQLException, NamingException {
		int status = 0;

		String nextType = "DOING";
		if (todo.getType().equals("DOING")) {
			nextType = "DONE";
		}
		Connection conn = getDBConnection();
		String sql = "update todo set type = ? where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nextType);
		ps.setLong(2, todo.getId());

		status = ps.executeUpdate();

		ps.close();
		conn.close();

		return status;
	}
}
