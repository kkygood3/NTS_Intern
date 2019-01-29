/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dao.TodoDao;
import com.nts.model.TodoDto;
import com.nts.model.TodosDto;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * TodoDao에서 얻어온 데이터를 처리
 */
public class TodoService {
	private TodoDao todoDao;

	public TodosDto getTodos() throws RuntimeException {
		List<TodoDto> todoList = todoDao.getTodos();
		TodosDto todos = new TodosDto();
		for (TodoDto todo : todoList) {
			todos.addTodo(todo);
		}
		return todos;
	}

	public void addTodo(TodoDto todo) throws RuntimeException {
		todoDao.addTodo(todo);
	}

	public void changeTypeToNext(TodoDto todo) throws RuntimeException {
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
