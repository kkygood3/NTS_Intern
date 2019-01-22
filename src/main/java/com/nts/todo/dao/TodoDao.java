package com.nts.todo.dao;

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

import com.nts.todo.dto.TodoDto;

public class TodoDao {
	private static DataSource dataSource = null;

	/**
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @getDBConnection()
	 * returns connection to db, if there is no DataSource, init data source first
	 * infos in context.xml in META-INF
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
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @getTodos()
	 * returns all rows in todo table through sql query
	 */
	public List<TodoDto> getTodos() throws ClassNotFoundException, NamingException, SQLException {

		List<TodoDto> result = new ArrayList<>();
		String sql = "SELECT id, title, name, sequence, type, DATE_FORMAT(regdate , '%Y-%d-%m %e') AS regdate FROM todo ORDER BY regdate ASC, id ASC";
		try (Connection conn = getDBConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				TodoDto dto = new TodoDto.Builder()
					.id(rs.getLong("id"))
					.title(rs.getString("title"))
					.name(rs.getString("name"))
					.sequence(rs.getInt("sequence"))
					.regdate(rs.getString("regdate"))
					.type(rs.getString("type"))
					.build();
				result.add(dto);
			}
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
	public int addTodo(TodoDto dto) throws ClassNotFoundException, NamingException, SQLException {

		//variable status to get the execution state;
		int status = 0;
		String sql = "insert into todo(title, name, sequence) values(?,?,?)";

		try (Connection conn = getDBConnection();
			PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());
			status = ps.executeUpdate();
		}

		return status;
	}

	/**
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @updateTodo()	
	 * update type of todo item in db
	 */
	public int updateTodo(TodoDto todo) throws ClassNotFoundException, NamingException, SQLException {
		int status = 0;
		String sql = "update todo set type = ? where id = ?";

		try (Connection conn = getDBConnection();
			PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, todo.getType());
			ps.setLong(2, todo.getId());
			status = ps.executeUpdate();
		}

		return status;
	}
}
