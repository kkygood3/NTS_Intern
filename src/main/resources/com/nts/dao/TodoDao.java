/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.nts.config.DbConnection;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;
import com.nts.exception.ServerErrorException;

public class TodoDao {

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	public static final TodoDao TODODAO = new TodoDao();

	/**
	 * 처음 TodoDao 객체가 생성될때 드라이버를 찾습니다.
	 */
	private TodoDao() {
		try {
			Class.forName(DbConnection.PROPERTIESE.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("com.mysql.jdbc.Driver를 찾을 수 없습니다.");
		}
	}

	/**
	 * todo 테이블에 있는 정보를 가져오는 메소드입니다.
	 */
	public List<TodoDto> getTodos() throws ServerErrorException {

		String selectSqlQuery = "SELECT id, title, name, sequence, type, regdate FROM todo";
		try (Connection connection = DbConnection.tryConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSqlQuery);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			List<TodoDto> todoList = new ArrayList<>();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String title = resultSet.getString("title");
				String name = resultSet.getString("name");
				int sequence = resultSet.getInt("sequence");
				TodoType type = TodoType.valueOf(resultSet.getString("type"));
				LocalDate regDate = resultSet.getDate("regdate").toLocalDate();
				String toStringRegDate = DATE_TIME_FORMAT.format(regDate);
				TodoDto todo = new TodoDto(id, title, name, sequence, type, toStringRegDate);
				todoList.add(todo);
			}
			return todoList;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
			throw new ServerErrorException(e);
		}

	}

	/**
	 * todo 테이블에 정보를 추가하는 메소드입니다.
	 * @throws ServerErrorException
	 */
	public int addTodo(TodoDto todo) throws ServerErrorException {

		String insertSqlQuery = "INSERT INTO todo (title, name, sequence) VALUES ( ?, ?, ? )";
		try (Connection connection = DbConnection.tryConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSqlQuery)) {

			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getName());
			preparedStatement.setInt(3, todo.getSequence());

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
			throw new ServerErrorException(e);
		}
	}

	/**
	 * todo 테이블에 있는 정보를 수정하는 메소드입니다.
	 * @throws ServerErrorException
	 */
	public int updateTodo(Long id, TodoType type) throws ServerErrorException {

		String updateSqlQuery = "update todo set type = ? where id = ?";
		try (Connection connection = DbConnection.tryConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateSqlQuery)) {

			preparedStatement.setString(1, type.getNextType());
			preparedStatement.setLong(2, id);

			return preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
			throw new ServerErrorException(e);
		}
	}

}
