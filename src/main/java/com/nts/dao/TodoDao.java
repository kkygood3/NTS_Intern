/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.factory.MysqlConnectionFactory;
import com.nts.model.TodoDto;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * DB에서 Todo Data를 삽입, 수정, 조회 하는 기능
 */
public class TodoDao {

	public List<TodoDto> getTodos() {
		List<TodoDto> todoList = null;
		String query = "select id, title, name, sequence, type, regdate from todo order by regdate desc";
		try (
			Connection connection = MysqlConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			todoList = new ArrayList<>();
			while (resultSet.next()) {
				TodoDto todo = new TodoDto();
				todo.setId(resultSet.getLong("id"));
				todo.setTitle(resultSet.getString("title"));
				todo.setName(resultSet.getString("name"));
				todo.setSequence(resultSet.getInt("sequence"));
				todo.setType(resultSet.getString("type"));
				todo.setRegdate(resultSet.getString("regdate"));

				todoList.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return todoList;
	}

	public void addTodo(TodoDto todo) {
		String query = "insert into todo(title, name, sequence) values(?, ?, ?)";
		try (
			Connection connection = MysqlConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, todo.getTitle());
			statement.setString(2, todo.getName());
			statement.setInt(3, todo.getSequence());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTodo(TodoDto todo) {
		String query = "update todo set type=? where id=?";
		try (
			Connection connection = MysqlConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, todo.getType());
			statement.setLong(2, todo.getId());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
