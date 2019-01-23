/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.JsonParser;
import com.nts.dto.TodoDto;

/**
 * DB에서 데이터를 가져오는 Dao클래스
 * @author 박우성
 */
public class TodoDao {
	private static String dburl = null;
	private static String dbUser = null;
	private static String dbpasswd = null;
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static TodoDao instance = null;
	
	private TodoDao() {}
	
	private static void init() {
		instance = new TodoDao();

		try {
			dburl = DbAccount.dburl;
			dbUser = DbAccount.dbUser;
			dbpasswd = DbAccount.dbpasswd;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			//TODO: Exception 세분화 할 필요 있음
			e.printStackTrace();
		}
	}
	
	public static TodoDao getInstance() {
		if(instance == null)
			init();
		
		return instance;
	}
	
	@Override
	protected void finalize() throws Throwable {
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

	private List executeQuery(String query) {
		ArrayList<TodoDto> roles = new ArrayList();
		try {
			ps = conn.prepareStatement(query);

			if (query.charAt(0)=='s') {
				rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					int sequence = rs.getInt(4);
					String type = rs.getString(5);
					String regdate = rs.getString(6);

					roles.add(new TodoDto(id, title, name, sequence, type, regdate));
				}
			} else {
				if(ps.executeUpdate() != 0);
					roles = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		String sql = "select id, title, name, sequence, type, DATE_FORMAT(regdate,'%Y.%m.%d') as regdate from todo";
		ResultSet rs = null;
		return executeQuery(sql);
	}
}
