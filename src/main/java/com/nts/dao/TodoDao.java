package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nts.Const;
import com.nts.database.DBQuery;
import com.nts.dto.TodoDto;

public class TodoDao {
	private TodoDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static class TodoDaoLazyHolder {
		public static final TodoDao INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return TodoDaoLazyHolder.INSTANCE;
	}

	public int addTodo(TodoDto todo) {
		int result = 0;
		try (Connection dbConnection = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASSWORD);
			PreparedStatement ps = dbConnection.prepareStatement(DBQuery.INSERT_SQL)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> todos = new ArrayList<TodoDto>();
		try (Connection dbConnection = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASSWORD);
			PreparedStatement ps = dbConnection.prepareStatement(DBQuery.SELECT_SQL);
			ResultSet rs = ps.executeQuery()) {
			rs.beforeFirst();
			while (rs.next()) {
				todos.add(new TodoDto(rs.getLong(Const.ID),
					rs.getString(Const.NAME),
					rs.getString(Const.REGDATE),
					rs.getInt(Const.SQQUENCE),
					rs.getString(Const.TITLE),
					rs.getString(Const.TYPE)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todos;
	}

	public int updateTodo(TodoDto todo) {
		int result = 0;
		try (Connection dbConnection = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.DB_USER, DBInfo.DB_PASSWORD);
			PreparedStatement ps = dbConnection.prepareStatement(DBQuery.UPDATE_DQL);) {
			ps.setString(1, getNextType(todo.getType()));
			ps.setLong(2, todo.getId());
			ps.setString(3, todo.getType());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static class DBInfo {
		static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user6?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
		static final String DB_USER = "user6";
		static final String DB_PASSWORD = "user6";
	}
	
	private String getNextType(String type) {
		type = type.toUpperCase();
		switch (type) {
			case Const.TODO:
				return Const.DOING;
			case Const.DOING:
				return Const.DONE;
		}
		return Const.UNKNOWN;
	}
}
