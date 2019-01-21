/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nts.todolist.dto.Todo;

/**
 * Todo Data Access Object
 * @author yongjoon.Park
*/
public class TodoDao {
	private static String DB_URL = "jdbc:mysql://10.113.116.52:13306/todolist?serverTimezone=Asia/Seoul&useSSL=false";
	private static String DB_USER = "user3";
	private static String DB_PASSWORD = "user3";
	private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final SimpleDateFormat SIMEPLE_DATA_FORMAT = new SimpleDateFormat("yyyy. MM. dd. ");

	/**
	 * get all todo list
	 * @author yongjoon.Park
	 * @return 조회된 모든 todo의 ArrayList<Todo>
	 * @throws ClassNotFoundException, SQLEception
	 */
	public ArrayList<Todo> getTodoList() {
		ArrayList<Todo> todoList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// query 
			String sql = "SELECT * FROM TODO WHERE TYPE = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "TODO");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String title = resultSet.getString(2);
				String name = resultSet.getString(3);
				int sequence = resultSet.getInt(4);
				String type = resultSet.getString(5);
				Date regdate = resultSet.getDate(6);
				// TODO java.util.Date vs. java.sql.Date
				// sql 쪽은 시, 분, 초가 없다고 함

				Todo todo = new Todo(id, title, name, sequence, type, SIMEPLE_DATA_FORMAT.format(regdate));
				
				todoList.add(todo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return todoList;
	}
}
