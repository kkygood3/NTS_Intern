/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.nts.config.DbConnection;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoType;
import com.nts.exception.ServerErrorException;

public class TodoDao {

	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	public static final TodoDao TODODAO = new TodoDao();

	/**
	 * 처음 TodoDao 객체가 생성될때 드라이버를 찾습니다.
	 * @throws ServerErrorException
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
		try {
			return TransmitQuery.selectTransmit(selectSqlQuery);

		} catch (ServerErrorException e) {
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
		try {
			return TransmitQuery.insertTransmit(insertSqlQuery, todo);

		} catch (ServerErrorException e) {
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
		try {
			return TransmitQuery.updateTransmit(updateSqlQuery, id, type);

		} catch (ServerErrorException e) {
			e.printStackTrace();
			System.out.println("SQL query 전송에 실패했습니다.");
			throw new ServerErrorException(e);
		}
	}

}
