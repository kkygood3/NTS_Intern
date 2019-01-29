/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.common;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public enum TodoType {
	TODO("TODO") {
		@Override
		public TodoType getNextType() {
			return DOING;
		}
	},
	DOING("DOING") {
		@Override
		public TodoType getNextType() {
			return DONE;
		}
	},
	DONE("DONE") {
		@Override
		public TodoType getNextType() {
			return DONE;
		}
	};

	private String todoType;

	private TodoType(String todoType) {
		this.todoType = todoType;
	}

	public String getTodoType() {
		return todoType;
	}

	public abstract TodoType getNextType();

}
