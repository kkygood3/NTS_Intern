/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.dto.TodoDtoList;
import com.nts.exception.ServerError500Exception;
import com.nts.type.TodoType;

/**
 * @author 전연빈
 */
public class TodoService {

	private TodoService() {

	}

	private static class TodoServiceLazyHolder {
		private static final TodoService INSTANCE = new TodoService();
	}

	public static TodoService getInstance() {
		return TodoServiceLazyHolder.INSTANCE;
	}

	/**
	 * @desc todo type 변경
	 * @param todoDto
	 * @throws SQLException
	 */
	public int updateTodo(TodoDto todoDto) throws ServerError500Exception {

		todoDto.setType(TodoType.valueOf(todoDto.getType()).getNextType());
		return TodoDao.getInstance().updateTodo(todoDto);
	}

	/**
	 * @desc todoList 가져오기
	 * @return todoDtoList
	 * @throws ServerError500Exception 
	 */
	public TodoDtoList getTodos() throws ServerError500Exception {

		TodoDtoList todoDtoList = new TodoDtoList();

		for (TodoDto todoDto : TodoDao.getInstance().getTodos()) {
			TodoType.valueOf(todoDto.getType()).addList(todoDtoList, todoDto);
		}

		return todoDtoList;
	}

	/**
	 * @desc todo 내용 삽입
	 * @param todoDto
	 * @throws ServerError500Exception
	 */
	public int addTodo(TodoDto todoDto) throws ServerError500Exception {
		return TodoDao.getInstance().addTodo(todoDto);
	}

}
