/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * TodoList를 타입별로 분류하여 저장.
 */
public class TodosDto {
	private List<TodoDto> todoList;
	private List<TodoDto> doingList;
	private List<TodoDto> doneList;

	public TodosDto() {
		todoList = new ArrayList<>();
		doingList = new ArrayList<>();
		doneList = new ArrayList<>();
	}

	public void addTodo(TodoDto todo) {
		todoList.add(todo);
	}

	public void addDoing(TodoDto todo) {
		doingList.add(todo);
	}

	public void addDone(TodoDto todo) {
		doneList.add(todo);
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
