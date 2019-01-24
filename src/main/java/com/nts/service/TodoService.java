/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dao.TodoDao;
import com.nts.dto.TodoDto;
import com.nts.exception.DataAccessException;

/**
 *
 * @description : DAO를 통해 DB에 Access
 * @filename : TodoDao.java
 * @package : com.nts.service
 * @author : Seokhyeon Choi
 * @method : TodoDao getInstance()
 * @method : List<TodoDto> getTodos()
 * @method : int addTodo(TodoDto todoDto)
 * @method : int updateTodo(TodoDto todoDto)
 */
public class TodoService {
	private static final TodoService INSTANCE = new TodoService();

	private TodoService() {
	}

	public static TodoService getInstance() {
		return INSTANCE;
	}

	public List<TodoDto> getTodos() throws DataAccessException {
		return TodoDao.getInstance().getTodos();
	}

	public int addTodo(TodoDto todoDto) throws DataAccessException {
		return TodoDao.getInstance().addTodo(todoDto);
	}

	public int updateTodo(TodoDto todoDto) throws DataAccessException {

		if ("TODO".equals(todoDto.getType())) {
			todoDto.setType("DOING");
		} else {
			todoDto.setType("DONE");
		}

		return TodoDao.getInstance().updateTodo(todoDto);
	}
}
