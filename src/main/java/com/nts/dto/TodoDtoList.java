/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 전연빈
 */
public class TodoDtoList {
	private List<TodoDto> todoList;
	private List<TodoDto> doingList;
	private List<TodoDto> doneList;

	public List<TodoDto> getTodoList() {
		if(todoList == null) {
			todoList = new ArrayList<>();
		}
		return todoList;
	}

	public List<TodoDto> getDoingList() {
		if(doingList == null) {
			doingList = new ArrayList<>();
		}
		return doingList;
	}

	public List<TodoDto> getDoneList() {
		if(doneList == null) {
			doneList = new ArrayList<>();
		}
		return doneList;
	}
}
