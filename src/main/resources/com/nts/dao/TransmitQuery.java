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
import java.util.ArrayList;
import java.util.List;

import com.nts.config.DbConnection;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;
import com.nts.exception.ServerErrorException;

/**
 * update, insert, select 쿼리를 읽어드려 전송해주는 클래스
 * Overloading 이용.
 * @author kkygood3
 */
public class TransmitQuery {

	public static int updateTransmit(String query, Long id, TodoType type) throws ServerErrorException {
		try (Connection connection = DbConnection.tryConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, type.getNextType());
			preparedStatement.setLong(2, id);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
			throw new ServerErrorException(e);
		}
	}

	public static int insertTransmit(String query, TodoDto todo) throws ServerErrorException {
		try (Connection connection = DbConnection.tryConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

	public static List<TodoDto> selectTransmit(String query) throws ServerErrorException {
		try (Connection connection = DbConnection.tryConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			List<TodoDto> todoList = new ArrayList<>();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String title = resultSet.getString("title");
				String name = resultSet.getString("name");
				int sequence = resultSet.getInt("sequence");
				TodoType type = TodoType.valueOf(resultSet.getString("type"));
				LocalDate regDate = resultSet.getDate("regdate").toLocalDate();
				String toStringRegDate = TodoDao.DATE_TIME_FORMAT.format(regDate);
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
}
