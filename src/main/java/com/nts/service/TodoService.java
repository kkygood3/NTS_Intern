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
		public static final TodoService INSTANCE = new TodoService();
	}

	public static TodoService getInstance() {
		return TodoServiceLazyHolder.INSTANCE;
	}

	/**
	 * @desc todo type 변경
	 * @param id
	 * @param type
	 * @throws SQLException
	 */
	public int updateTodo(TodoDto todoDto) throws ServerError500Exception {

		if (TodoType.TODO.toString().equals(todoDto.getType())) {
			todoDto.setType(TodoType.DOING.toString());
		} else if (TodoType.DOING.toString().equals(todoDto.getType())) {
			todoDto.setType(TodoType.DONE.toString());
		}

		return TodoDao.getInstance().updateTodo(todoDto);
	}

	/**
	 * @desc type에 맞게끔 map에 키 밸류 형태로 넣어줌 
	 * @return todoList
	 * @throws ServerError500Exception 
	 */
	public TodoDtoList getTodos() throws ServerError500Exception {

		TodoDao todoDao = TodoDao.getInstance();
		List<TodoDto> list = todoDao.getTodos();

		TodoDtoList todoDtoList = new TodoDtoList();

		List<TodoDto> todoList = new ArrayList<>();
		List<TodoDto> doingList = new ArrayList<>();
		List<TodoDto> doneList = new ArrayList<>();

		todoDtoList.setTodoList(todoList);
		todoDtoList.setDoingList(doingList);
		todoDtoList.setDoneList(doneList);

		for (TodoDto todoDto : list) {
			String type = todoDto.getType();

			TodoType.valueOf(type).addList(todoDtoList, todoDto);
		}

		return todoDtoList;
	}

	/**
	 * @desc todo 내용 삽입
	 * @param todoDto
	 * @return result
	 * @throws SQLException
	 */
	public int addTodo(TodoDto todoDto) throws ServerError500Exception {
		return TodoDao.getInstance().addTodo(todoDto);
	}

}
