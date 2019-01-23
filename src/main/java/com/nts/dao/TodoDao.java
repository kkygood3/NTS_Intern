package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.Const;
import com.nts.database.DBQuery;
import com.nts.dto.TodoDto;

public class TodoDao {
	private static Connection dbConnection;
	
	public int addTodo(TodoDto todo) {
		int result = 0;
		try (PreparedStatement ps = getConnection().prepareStatement(DBQuery.INSERT_SQL)) {
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
		try (PreparedStatement ps = getConnection().prepareStatement(DBQuery.SELECT_SQL);
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
		try (PreparedStatement ps = getConnection().prepareStatement(DBQuery.UPDATE_DQL);) {
			ps.setString(1, Const.DOING);
			ps.setLong(2, todo.getId());
			ps.setString(3, Const.TODO);
			if ((result = ps.executeUpdate()) == 0) {
				ps.setString(1, Const.DONE);
				ps.setString(3, Const.DOING);
				result = ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (dbConnection == null) {
			final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user6?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
			final String DB_USER = "user6";
			final String DB_PASSWORD = "user6";

			Class.forName("com.mysql.jdbc.Driver");
			dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}
		return dbConnection;
	}
	
	private static class Holder {
		public static final TodoDao INSTANCE = new TodoDao();
	}

	public static TodoDao getInstance() {
		return Holder.INSTANCE;
	}
}
