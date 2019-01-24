/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * TodoList를 타입별로 분류하여 저장.
 */
public class TodosDto {
	private List<TodoDto> todoList;
	private List<TodoDto> doingList;
	private List<TodoDto> doneList;
	private Map<String, List<TodoDto>> todoMap;

	public TodosDto() {
		todoList = new ArrayList<>();
		doingList = new ArrayList<>();
		doneList = new ArrayList<>();
		todoMap = new HashMap<>();
		todoMap.put("TODO", todoList);
		todoMap.put("DOING", doingList);
		todoMap.put("DONE", doneList);
	}

	public void addTodo(TodoDto todo) {
		todoMap.get(todo.getType()).add(todo);
	}

	public List<TodoDto> getTodoList() {
		return todoList;
	}

	public List<TodoDto> getDoingList() {
		return doingList;
	}

	public List<TodoDto> getDoneList() {
		return doneList;
	}

}
