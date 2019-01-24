/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.common;

public enum TodoStatus {

	TODO("TODO"),
	DOING("DOING"),
	DONE("DONE");

	private String todoStatus;

	private TodoStatus(String todoStatus) {
		this.todoStatus = todoStatus;
	}

	public String getValue() {
		return todoStatus;
	}

	public String changNextStatus() {
		return TODO.equals(this) ? DOING.getValue() : DONE.getValue();
	}
	
}
