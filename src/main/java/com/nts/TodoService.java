/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.util.List;


/**
*
* @description : Todo List Service
* @filename : TodoDao.java
* @package : com.nts
* @author : Seokhyeon Choi
* @method : TodoDao getInstance()
* @method : List<TodoDto> getTodos()
* @method : int addTodo(TodoDto todoDto)
* @method : int updateTodo(TodoDto todoDto)
*/
public class TodoService {
	private static final TodoService INSTANCE = new TodoService();
	
	private TodoService() {}
	
	public static TodoService getInstance() {
		return INSTANCE;
	}
	
	public List<TodoDto> getTodos(){
		return TodoDao.getInstance().getTodos();
	}
	
	public int addTodo(TodoDto todoDto) {
		return TodoDao.getInstance().addTodo(todoDto);
	}
	
	public int updateTodo(TodoDto todoDto) {

		if ("TODO".equals(todoDto.getType())) {
			todoDto.setType("DOING");
		} else {
			todoDto.setType("DONE");
		}
		
		return TodoDao.getInstance().updateTodo(todoDto);
	}
}
