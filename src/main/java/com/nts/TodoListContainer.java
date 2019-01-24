/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts;

import java.util.ArrayList;
import java.util.List;


/**
*
* @description : TodoList Model
* @filename : TodoListContainer.java
* @package : com.nts
* @author : Seokhyeon Choi
*/
public class TodoListContainer {
	private List<TodoDto> todoDtoList;
	private List<TodoDto> doingDtoList;
	private List<TodoDto> doneDtoList;

	public TodoListContainer(List<TodoDto> todoList) {
		todoDtoList = new ArrayList<TodoDto>();
		doingDtoList = new ArrayList<TodoDto>();
		doneDtoList = new ArrayList<TodoDto>();

		for (TodoDto todoDto : todoList) {
			TodoType.valueOf(todoDto.getType()).addTodoList(this, todoDto);
		}
	}

	public List<TodoDto> getTodoDtoList() {
		return todoDtoList;
	}

	public List<TodoDto> getDoingDtoList() {
		return doingDtoList;
	}

	public List<TodoDto> getDoneDtoList() {
		return doneDtoList;
	}

	public void setTodoDtoList(List<TodoDto> todoDtoList) {
		this.todoDtoList = todoDtoList;
	}

	public void setDoingDtoList(List<TodoDto> doingDtoList) {
		this.doingDtoList = doingDtoList;
	}

	public void setDoneDtoList(List<TodoDto> doneDtoList) {
		this.doneDtoList = doneDtoList;
	}
}
