/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.enums;

import com.nts.model.TodoDto;
import com.nts.model.TodosDto;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * Todo의 Type
 */
public enum TodoType {

	TODO("DOING") {
		@Override
		public void addTodo(TodosDto todos, TodoDto todo) {
			todos.addTodo(todo);
		}
	},
	DOING("DONE") {
		@Override
		public void addTodo(TodosDto todos, TodoDto todo) {
			todos.addDoing(todo);
		}
	},
	DONE("DONE") {
		@Override
		public void addTodo(TodosDto todos, TodoDto todo) {
			todos.addDone(todo);
		}
	};

	private String nextType;

	private TodoType(String nextType) {
		this.nextType = nextType;
	}

	public String getNext() {
		return nextType;
	}

	/**
	 * list 분류용
	 */
	public abstract void addTodo(TodosDto todos, TodoDto todo);
}
