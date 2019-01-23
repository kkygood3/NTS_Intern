/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todo.dto;

import java.util.List;

/**
* FileName : Type.java
* @author  : 이승수
* @date    : 2019. 1. 23.
*/
public enum Type {
	TODO, DOING, DONE;

	public String next() {
		Type nextType;
		if (this == TODO) {
			nextType = DOING;
		} else {
			nextType = DONE;
		}
		return nextType.toString();
	}

	public void classify(TodoDto todo, List<TodoDto> todos, List<TodoDto> doings, List<TodoDto> dones) {
		if (this == TODO) {
			todos.add(todo);
		} else if (this == DOING) {
			doings.add(todo);
		} else {
			dones.add(todo);
		}
	}
}
