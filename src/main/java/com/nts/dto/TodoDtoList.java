/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dto;

import java.util.List;

/**
 * @author 전연빈
 */
public class TodoDtoList {
	private List<TodoDto> todoList;
	private List<TodoDto> doingList;
	private List<TodoDto> doneList;

	public List<TodoDto> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoDto> todoList) {
		this.todoList = todoList;
	}

	public List<TodoDto> getDoingList() {
		return doingList;
	}

	public void setDoingList(List<TodoDto> doingList) {
		this.doingList = doingList;
	}

	public List<TodoDto> getDoneList() {
		return doneList;
	}

	public void setDoneList(List<TodoDto> doneList) {
		this.doneList = doneList;
	}

}
