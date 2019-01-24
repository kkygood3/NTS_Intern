/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dao.TodoDao;
import com.nts.enums.TodoType;
import com.nts.exception.ServerErrorException;
import com.nts.model.TodoDto;
import com.nts.model.TodosDto;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * TodoDao에서 얻어온 데이터를 처리
 */
public class TodoService {
	private TodoDao todoDao;

	public TodosDto getTodos() throws ServerErrorException {
		List<TodoDto> todoList = todoDao.getTodos();
		TodosDto todos = new TodosDto();
		for (TodoDto todo : todoList) {
			TodoType.valueOf(todo.getType()).addTodo(todos, todo);
		}
		return todos;
	}

	public void addTodo(TodoDto todo) throws ServerErrorException {
		todoDao.addTodo(todo);
	}

	public void changeTypeToNext(TodoDto todo) throws ServerErrorException {
		todo.changeTypeToNext();
		todoDao.updateTodo(todo);
	}

	private TodoService() {
		todoDao = TodoDao.getInstance();
	}

	private static class TodoServiceHolder {
		private static final TodoService TODOSERVICE_INSTANCE = new TodoService();
	}

	public static TodoService getInstance() {
		return TodoServiceHolder.TODOSERVICE_INSTANCE;
	}
}
