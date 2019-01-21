/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.nts.dto.TodoDto;

/**
 * DB Todo 테이블에 접근하여 조회 및 조작하는 클래스
 * @author jinwoo.bae
 */

public class TodoDao {
	private String url = "jdbc:mysql://localhost:3306/todo";
	private String id = "root";
	private String pw = "1234";

	/**
	 * DB 커넥션을 가져와주는 메소드
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}

	// TODO: Todo 테이블에 값 Insert 구현
	public int addTodo(TodoDto todoDto) {
		return 0;
	}

	// TODO: Todo 테이블 값들 가져오기 구현
	public List<TodoDto> getTodos() {
		return null;
	}

	// TODO: Todo 테이블 값 Update 구현
	public int updateTodo(TodoDto todoDto) {
		return 0;
	}
}