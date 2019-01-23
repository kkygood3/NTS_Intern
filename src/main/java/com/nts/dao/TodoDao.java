package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.Const;
import com.nts.database.DBConnection;
import com.nts.dto.TodoDto;

public class TodoDao {
	Connection conn = null;

	public TodoDao() {
		try {
			conn = DBConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addTodo(TodoDto todo) {

		final String sql = "insert into todo(title, name, sequence) values(?, ?, ?)";
		int result = 0;
		try (
			PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql)) {
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

		final String sql = "select id, title, name, sequence, type, DATE_FORMAT(regdate, '%Y.%m.%d') as regdate from todo order by regdate desc";
		try (
			PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {
			rs.beforeFirst();
			while (rs.next()) {
				todos.add(new TodoDto(rs.getLong("id"),
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
		final String sql = "update todo set type = ? where id = ? and type = ?";
		try (
			PreparedStatement ps = conn.prepareStatement(sql);) {

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
}
